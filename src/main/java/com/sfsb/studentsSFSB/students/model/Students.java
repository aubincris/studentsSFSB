package com.sfsb.studentsSFSB.students.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "students")
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;
    @Column(name = "Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @Column(name = "LoginTime")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime loginTime;
    @Column(name = "BreakPeriod")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime breakPeriod;
    @Column(name = "LogoutTime")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime logoutTime;
    @Column(name = "HoursWorkedPerDay")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime workHoursPerDay;
    @Column(name = "Task")
    private String task;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime workHoursPerWeek;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime totalHoursForCurrentContract;
    @DateTimeFormat(pattern = "HH:mm")
    //@DateTimeFormat(iso = DateTimeFormat.ISO.TIME) if above does not work
    private LocalTime hoursLeftOnCurrentContract;


    //Method to calculate work hours per day based on login,break and logout values
    public LocalTime calculateWorkHoursPerDay(){
        //DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("H:mm");
        Duration breakDuration = Duration.between(LocalTime.MIDNIGHT, breakPeriod);
        Duration workDuration = Duration.between(loginTime,logoutTime).minus(breakDuration);
        String workHoursInString = LocalTime.MIDNIGHT.plus(workDuration).toString();
        workHoursPerDay = LocalTime.parse(workHoursInString);
        return workHoursPerDay;

    }

}
