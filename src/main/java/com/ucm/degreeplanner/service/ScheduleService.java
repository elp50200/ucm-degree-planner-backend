package com.ucm.degreeplanner.service;

import com.ucm.degreeplanner.controller.UserController;
import com.ucm.degreeplanner.domain.Course;
import com.ucm.degreeplanner.domain.Schedule;
import com.ucm.degreeplanner.domain.User;
import com.ucm.degreeplanner.domain.WebCourse;
import com.ucm.degreeplanner.repository.CourseDAO;
import com.ucm.degreeplanner.repository.ScheduleDAO;
import com.ucm.degreeplanner.repository.ScheduleRepository;
import com.ucm.degreeplanner.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
            schedule.setCourseCode(course);
            schedule.setUserID(user);
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
            schedule.setCourseCode(course);
            schedule.setUserID(user);
            schedule.setEnrolledSemester(data.getAcademicSemester());
            scheduleDAO.removeFromSchedule(schedule);
        }
        else{
            throw new Exception("The user or course are null");
        }
    }
}
