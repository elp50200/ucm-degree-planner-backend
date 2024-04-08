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

    /*
    This is the service layer for the addCourse.
    It makes sure the courseCode is set to all upper case before being stored in the database to ensure uniformity
     */
    public void addCourse(Course course){
        course.setCourseCode(course.getCourseCode().toUpperCase());
        courseRepository.save(course);
        logger.info("Course has been added to the database.");
    }
    /*
    This is the service layer for removeCourse
    This method removes a single course from the table
     */
    public void removeCourse(String courseCode) throws Exception{
        courseCode = courseCode.toUpperCase();
        courseRepository.delete(courseRepository.findCourseByCourseCode(courseCode));
        logger.info("Successfully removed course "+courseCode);
    }
    /*
    This is the service layer for getSingleCourse
    This looks for one course based off the courseCode and returns it if found
     */
    public Course getSingleCourse(String courseCode) throws Exception {
        try{
            courseCode = courseCode.toUpperCase();
            String sanitizedCode = preventSQLInjection(courseCode);

            if(!(courseCode.equals(sanitizedCode))) {
                logger.error("An SQLi attempt was stopped in getCourse with course code: " + courseCode);
                throw new Exception("An SQLi attempt was stopped in getCourse with course code: " + courseCode);
            }
            else {
                Course course = courseDAO.getSingleCourse(courseCode);
                return course;
            }
        }
        catch(Exception e)
        {
            logger.error("A problem has occurred trying to retrieve a course.");
            throw new Exception(e);
        }
    }
    /*
    This is the service layer for getAllCourses
    This method returns all courses from the database
     */
    public ArrayList<Course> getAllCourses() throws Exception{
        ArrayList<Course> courses = courseDAO.getAllCourses();
        if(courses.isEmpty())
        {
            logger.error("There were no courses retrieved in getAllCourses");
            throw new Exception("There were no courses retrieved in getAllCourses");
        }
        else
        {
            return courses;
        }
    }
    /*
    This is the service layer for getCourseLevel
    This method is not used in frontend however it returns all courses based on their requirement_level (eg 1xxx,2xxx,3xxx,4xxx)
     */
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

    /*
    This is the service layer for preventSQLInjection
    This is an old protection used to sanitize inputs from the frontend. It is still used in some places.
     */
    private String preventSQLInjection(String input) {
        // Define a regular expression pattern to match potentially harmful characters
        String regex = "[;\\\\/'\"()|&%#@<>]";

        // Remove characters matching the pattern
        String sanitizedInput = input.replaceAll(regex, "XXX");

        return sanitizedInput;
    }
}
