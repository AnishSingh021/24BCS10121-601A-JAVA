package com.course.model;

import java.util.ArrayList;

public class Course {

    private int courseId;
    private String courseName;
    private int maxSeats;

    public ArrayList<Student> students = new ArrayList<>();

    public Course(int courseId, String courseName, int maxSeats) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.maxSeats = maxSeats;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public void display() {
        System.out.println("Course ID: " + courseId);
        System.out.println("Course Name: " + courseName);
        System.out.println("Max Seats: " + maxSeats);
        System.out.println("Students Enrolled: " + students.size());
    }
}