package com.ucm.degreeplanner.service;

import com.ucm.degreeplanner.controller.UserController;
import com.ucm.degreeplanner.domain.Course;
import com.ucm.degreeplanner.repository.CourseDAO;
import com.ucm.degreeplanner.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static com.mysql.cj.conf.PropertyKey.logger;

@RequiredArgsConstructor
@Service
public class CourseService {

    private final CourseDAO courseDAO;
    private final CourseRepository courseRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public void addCourse(Course course){
        String courseCode = course.getCourseCode();
        course.setCourseCode(courseCode.toUpperCase());
        courseRepository.save(course);
    }
    public void removeCourse(String courseCode) throws Exception{
        courseCode = courseCode.toUpperCase();
        logger.info("Attempting to remove course "+courseCode);
        courseRepository.delete(courseRepository.findCourseByCourseCode(courseCode));
        logger.info("Successfully removed course "+courseCode);
    }

    public Course getSingleCourse(String courseCode) throws Exception {
        try{
            courseCode = courseCode.toUpperCase();
            String sanitizedCode = preventSQLInjection(courseCode);

            if(!(courseCode.equals(sanitizedCode))) {
                System.out.println(courseCode + " " + sanitizedCode);
                throw new Exception("An SQLi attempt was stopped in getCourse with course code: " + courseCode);
            }
            else {
                Course course = courseDAO.getSingleCourse(courseCode);
                return course;
            }
        }
        catch(Exception e)
        {
            throw new Exception(e);
        }
    }

    public ArrayList<Course> getAllCourses() throws Exception{
        ArrayList<Course> courses = courseDAO.getAllCourses();
        if(courses.isEmpty())
        {
            throw new Exception("There were no courses retrieved in getAllCourses");
        }
        else
        {
            return courses;
        }
    }

    public ArrayList<Course> getCourseLevel(String level) throws Exception{

        System.out.println("level before slice "+ level);
        level = level.substring(0,1);
        System.out.println("level after slice "+ level);
        int levelNumber = Integer.parseInt(level);
        System.out.println("levelNumber "+levelNumber);

        ArrayList<Course> courses = courseDAO.getCourseLevel(levelNumber);
        if(courses.isEmpty())
        {
            throw new Exception("There were no courses retrieved in getCourseLevel");
        }
        else
        {
            return courses;
        }
    }


    private String preventSQLInjection(String input) {
        // Define a regular expression pattern to match potentially harmful characters
        String regex = "[;\\\\/'\"()|&%#@<>]";

        // Remove characters matching the pattern
        String sanitizedInput = input.replaceAll(regex, "XXX");

        return sanitizedInput;
    }
}

//                String sanitizedCatalogYear = preventSQLInjection(userUpdates.getCatalogYear());
//                if(userUpdates.getCatalogYear().equals(sanitizedCatalogYear)) {
//                    userCurrent.setCatalogYear(userUpdates.getCatalogYear());
//                } else {
//                    throw new Exception("A invalid/dangerous character has been detected on " + LocalDate.now() + " and the process updateUserInformation for catalog year has been stopped.");
//                }
