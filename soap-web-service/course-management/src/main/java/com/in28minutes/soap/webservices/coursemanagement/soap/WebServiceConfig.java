package com.in28minutes.soap.webservices.coursemanagement.soap;


import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

// Enable spring webserivce
@EnableWs
// Spring configuration
@Configuration
public class WebServiceConfig {

    // Message dispatcher servlet
    // Servlet for simplifying dispatching of Web Services
    // ApplicationContext
    // URL -> /ws/*
    @Bean
    public ServletRegistrationBean messageDispatcherServlet (ApplicationContext context) {
        MessageDispatcherServlet msgDispatcherServlet = new MessageDispatcherServlet();
        // map instance of msgDispatcherServlet to URL
        msgDispatcherServlet.setApplicationContext(context);
        msgDispatcherServlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(msgDispatcherServlet, "/ws/*");
    }

    // /ws/courses.wsdl
    // port type
    // namespace
    @Bean(name="courses")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema coureSchema) {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("CoursePort");
        definition.setTargetNamespace("http://in28minutes.com/courses");
        definition.setSchema(coursesSchema());
        return definition;
    }
    // course-details.xsd
    @Bean
    public XsdSchema coursesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/course-details.xsd"));
    }

}
