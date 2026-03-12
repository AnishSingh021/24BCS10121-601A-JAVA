package com.course.service;

import java.util.ArrayList;
import java.io.*;

import com.course.model.Course;
import com.course.model.Student;
import com.course.exception.*;

public class CourseService {

    ArrayList<Course> courseList = new ArrayList<>();

    public void addCourse(Course c) {
        courseList.add(c);
    }

    public void enrollStudent(int courseId, Student s)
            throws CourseFullException, CourseNotFoundException, DuplicateEnrollmentException {

        Course found = null;

        for (Course c : courseList) {
            if (c.getCourseId() == courseId) {
                found = c;
            }
        }

        if (found == null) {
            throw new CourseNotFoundException("Course not found");
        }

        if (found.students.size() >= found.getMaxSeats()) {
            throw new CourseFullException("Course is full");
        }

        for (Student st : found.students) {
            if (st.getStudentId() == s.getStudentId()) {
                throw new DuplicateEnrollmentException("Student already enrolled");
            }
        }

        found.students.add(s);

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("courses.txt", true));

            bw.write(courseId + " , " + s.getStudentId() + " , " + s.getStudentName());
            bw.newLine();

            bw.close();

        } catch (Exception e) {
            System.out.println("File error");
        }

        System.out.println("Student enrolled successfully");
    }

    public void viewCourses() {

        try {
            BufferedReader br = new BufferedReader(new FileReader("courses.txt"));

            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Cannot read file");
        }
    }
}