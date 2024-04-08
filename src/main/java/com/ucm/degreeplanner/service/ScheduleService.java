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

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleService {
    private final CourseService courseService;
    private final UserService userService;
    private final ScheduleDAO scheduleDAO;
    private final ScheduleRepository scheduleRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /*
    This is the service layer for addToSchedule
    This method is the old version of adding courses to schedule. It can add one course at a time.
     */
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
    /*
    This is the service layer for removeFromSchedule
    This method is used to remove one course from a students schedule
     */
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
    /*
    This is the service layer for getSchedule
    This method returns a list of all schedules tied to one student number
     */
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
    /*
    This is the service layer for bulkService
    This is the main method in this file and works to finalize data before adding courses to the backend.
     */
    public void bulkSchedule(BulkSchedule enrollmentData, String startSemester) throws Exception {

        if (enrollmentData.getStudentNumber() != null
                || !enrollmentData.getStudentNumber().equals("")
                || enrollmentData.getCourses().length != 0)
        {
            User user = userService.getUserByStudentNumber(enrollmentData.getStudentNumber());
            Schedule schedule = new Schedule();
            schedule.setUser(user);
            int semesterID =Integer.parseInt(enrollmentData.getSemesterID());
            schedule.setEnrolledSemester(getAcademicSemester(semesterID, startSemester));

            schedule.setLastUpdated(Date.valueOf(LocalDate.now()));

            for(int i=0;i<enrollmentData.getCourses().length;i++)
            {
                Course course = courseService.getSingleCourse(enrollmentData.getCourses()[i]);
                schedule.setCourse(course);

                if(scheduleDAO.courseExist(schedule))
                {
                    scheduleDAO.removeFromSchedule(schedule);
                }

                scheduleDAO.add(schedule);
            }
        }
        else{
            throw new Exception("The user or course are null");
        }
    }
    /*
    This is the service layer for getAcademicSemester
    This method uses the startSemester and semesterID to return the semester the student wishes to enroll their classes in based on the frontend data
     */
    public String getAcademicSemester(int semesterID, String startSemester){

        String[] semesters = getSemesters(startSemester);

        return semesters[semesterID];
    }
    /*
    This is the service layer for getSemesters
    This method is sent an academic semester code and returns an 8 spot array with the next 8 semester academic codes starting with the given one
     */
    public String[] getSemesters(String startSemester){
        String[] semesters = new String[8];

        String yearPt1 = startSemester.substring(0, 4);
        String yearPt2 = startSemester.substring(4, 6);
        semesters[0] = startSemester;
        for(int i =1;i< semesters.length;i++){

            if(yearPt2.equals("10"))
            {
                yearPt2 = "20";
            }
            else if (yearPt2.equals("20"))
            {
                yearPt2 = "10";
                int year = Integer.parseInt(yearPt1);
                year++;
                yearPt1 = String.valueOf(year);
            }

            String semester = yearPt1.concat(yearPt2);
            semesters[i] = semester;
        }
        return semesters;
    }
}
