package com.ucm.degreeplanner.service;

import com.ucm.degreeplanner.domain.Course;
import com.ucm.degreeplanner.repository.CourseDAO;
import com.ucm.degreeplanner.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CourseService {

    private final CourseDAO courseDAO;
    private final CourseRepository courseRepository;

    public void addCourse(Course course){
        courseRepository.save(course);
    }

    public Course getSingleCourse(String courseCode) throws Exception {
        try{

            String sanitizedCode = preventSQLInjection(courseCode);
            if(!(courseCode.equals(sanitizedCode)))
                throw new Exception("An SQLi attempt was stopped in getCourse with course code: "+courseCode);
                Course course = courseDAO.getSingleCourse(courseCode);
                return course;
        }
        catch(Exception e){
            throw new Exception(e);
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
