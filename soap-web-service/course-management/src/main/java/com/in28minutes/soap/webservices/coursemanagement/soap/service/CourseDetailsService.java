package com.in28minutes.soap.webservices.coursemanagement.soap.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.in28minutes.soap.webservices.coursemanagement.soap.bean.Course;

import org.springframework.stereotype.Component;

@Component
public class CourseDetailsService {

    private static List<Course> courses = new ArrayList<>();

    static {
        Course course01 = new Course(1, "Pramod", "JS Developer");
        Course course02 = new Course(2, "Raktima", "JS Developer");
        Course course03= new Course(3, "Vignesh", "JS Developer");
        courses.add(course01);
        courses.add(course02);
        courses.add(course03);
    }

    // course - 1
    public Course findById( int id ) {
        for (Course item : courses) {
            if (item.getId() == id) return item;
        }
        return null;
    }
    // all courses 
    public List<Course> findAll () {
        return courses;
    }

    // delete a course
    public int deleteById (int id) {
        Iterator <Course> iterator = courses.iterator();
        while (iterator.hasNext()) {
            Course item = iterator.next();
            if (item.getId() == id) {
                iterator.remove();
                return 1;
            }
        }
        return 0;    
    }
}
