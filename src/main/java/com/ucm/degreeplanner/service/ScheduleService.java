package com.ucm.degreeplanner.service;

import com.ucm.degreeplanner.controller.UserController;
import com.ucm.degreeplanner.domain.*;
import com.ucm.degreeplanner.repository.CourseDAO;
import com.ucm.degreeplanner.repository.ScheduleDAO;
import com.ucm.degreeplanner.repository.ScheduleRepository;
import com.ucm.degreeplanner.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleService {
    private final CourseService courseService;
    private final UserService userService;
    private final ScheduleDAO scheduleDAO;
    private final ScheduleRepository scheduleRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public void addToSchedule(WebCourse enrollmentData) throws Exception {
        Course course = courseService.getSingleCourse(enrollmentData.getCourseCode());
        User user = userService.getUserByStudentNumber(enrollmentData.getStudentNumber());
        if (user != null && course != null) {
            Schedule schedule = new Schedule();
            schedule.setCourse(course);
            schedule.setUser(user);
            schedule.setEnrolledSemester(enrollmentData.getAcademicSemester());
            scheduleRepository.save(schedule);
        }
        else{
            throw new Exception("The user or course are null");
        }
    }

    public void removeFromSchedule(WebCourse data) throws Exception {
        Course course = courseService.getSingleCourse(data.getCourseCode());
        User user = userService.getUserByStudentNumber(data.getStudentNumber());
        if (user != null && course != null) {
            Schedule schedule = new Schedule();
            schedule.setCourse(course);
            schedule.setUser(user);
            schedule.setEnrolledSemester(data.getAcademicSemester());
            scheduleDAO.removeFromSchedule(schedule);
        }
        else{
            throw new Exception("The user or course are null");
        }
    }
    public List<WebCourse> getSchedule(String studentNumber) throws Exception {
        try {

            List list = scheduleDAO.getSchedule(studentNumber);

            if (!list.isEmpty()) {
                return list;
            } else {
                throw new Exception("No schedule was found for user: " + studentNumber);
            }
        }
        catch(Exception e)
        {
            throw e;
        }
    }
    public void bulkSchedule(BulkSchedule enrollmentData) throws Exception {
        User user = userService.getUserByStudentNumber(enrollmentData.getStudentNumber());
        if (user != null || !user.equals("") || enrollmentData.getCourses().length != 0){
            Schedule schedule = new Schedule();
            schedule.setUser(user);
            schedule.setEnrolledSemester(enrollmentData.getAcademicSemester());
            for(int i=0;i<enrollmentData.getCourses().length;i++){
                Course course = courseService.getSingleCourse(enrollmentData.getCourses()[i]);
                schedule.setCourse(course);

                scheduleDAO.add(schedule);
            }
        }
        else{
            throw new Exception("The user or course are null");
        }
    }
}
