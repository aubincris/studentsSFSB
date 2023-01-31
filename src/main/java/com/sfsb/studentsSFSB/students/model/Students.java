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
    private LocalDate loginDate;
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

    //@DateTimeFormat(iso = DateTimeFormat.ISO.TIME) if above does not work
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime hoursLeftOnCurrentContract;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(LocalDate loginDate) {
        this.loginDate = loginDate;
    }

    public LocalTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalTime loginTime) {
        this.loginTime = loginTime;
    }

    public LocalTime getBreakPeriod() {
        return breakPeriod;
    }

    public void setBreakPeriod(LocalTime breakPeriod) {
        this.breakPeriod = breakPeriod;
    }

    public LocalTime getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(LocalTime logoutTime) {
        this.logoutTime = logoutTime;
    }

    public LocalTime getWorkHoursPerDay() {
        return workHoursPerDay;
    }

    public void setWorkHoursPerDay(LocalTime workHoursPerDay) {
        this.workHoursPerDay = workHoursPerDay;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public LocalTime getWorkHoursPerWeek() {
        return workHoursPerWeek;
    }

    public void setWorkHoursPerWeek(LocalTime workHoursPerWeek) {
        this.workHoursPerWeek = workHoursPerWeek;
    }

    public LocalTime getTotalHoursForCurrentContract() {
        return totalHoursForCurrentContract;
    }

    public void setTotalHoursForCurrentContract(LocalTime totalHoursForCurrentContract) {
        this.totalHoursForCurrentContract = totalHoursForCurrentContract;
    }

    public LocalTime getHoursLeftOnCurrentContract() {
        return hoursLeftOnCurrentContract;
    }

    public void setHoursLeftOnCurrentContract(LocalTime hoursLeftOnCurrentContract) {
        this.hoursLeftOnCurrentContract = hoursLeftOnCurrentContract;
    }

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
