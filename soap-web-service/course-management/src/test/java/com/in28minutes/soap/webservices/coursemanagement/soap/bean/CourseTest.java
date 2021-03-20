package com.in28minutes.soap.webservices.coursemanagement.soap.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CourseTest {
    private Course undertest;
    private static final Logger logger = LoggerFactory.getLogger(CourseTest.class);

    @BeforeEach
    public void init() {
        this.undertest = new Course(1, "Pramod", "JS Developer");
        System.out.println(logger + " value is: " + this.undertest);
    }

    @Test
    public void testObjectEquals() {
        Course expected = new Course(1, "Pramod", "JS Developer");
        assertEquals(expected, this.undertest);
    }

    // utilities

    // private void assertViolationMessage(String s) {
    //     violations = validator.validate(productDTO);

    //     ConstraintViolation<CarProductDTO> violation = violations.iterator().next();
    //     Assert.assertEquals(s, violation.getMessage());
    // }

    // private void assertViolatedField(String name) {
    //     violations = validator.validate(productDTO);
    //     ConstraintViolation<CarProductDTO> violation = violations.iterator().next();

    //     String fieldName = findFieldViolated(violation.getPropertyPath());
    //     assertEquals(fieldName, name);
    // }


    // private String findFieldViolated(Path p) {
    //     Iterator<Path.Node> it = p.iterator();
    //     Path.Node n = null;
    //     while (it.hasNext()) {
    //         n = it.next();
    //     }

    //     if(Objects.nonNull(n)) {
    //         return n.getName();
    //     } else {
    //         return null;
    //     }
    // }
}
