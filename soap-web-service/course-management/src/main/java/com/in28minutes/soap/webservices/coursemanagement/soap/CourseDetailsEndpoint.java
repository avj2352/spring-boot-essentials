package com.in28minutes.soap.webservices.coursemanagement.soap;
import org.springframework.beans.factory.annotation.Autowired;
// spring imports
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.in28minutes.courses.CourseDetails;
// custom imports
import com.in28minutes.courses.GetCourseDetailsRequest;
import com.in28minutes.courses.GetCourseDetailsResponse;
import com.in28minutes.soap.webservices.coursemanagement.soap.service.CourseDetailsService;

@Endpoint
public class CourseDetailsEndpoint {

    @Autowired
    CourseDetailsService service;
    // method
    // input - GetCourseDetailsRequest
    // output - GetCourseDetailsResponse
    @PayloadRoot(namespace = "http://in28minutes.com/courses", localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse 
                processCourseDetailsRequest (
                    @RequestPayload GetCourseDetailsRequest request) {
        CourseDetails record = new CourseDetails();
        record.setId(1);
        record.setName("Microservices course");
        record.setDescription("Wonderful course");
        GetCourseDetailsResponse result = new GetCourseDetailsResponse();
        result.setCourseDetails(record);
        return result;
    }
}
