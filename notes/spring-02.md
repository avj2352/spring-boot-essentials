# SOAP Web Service

## Important Links

- [XML Schema Wikipedia](http://edutechwiki.unige.ch/en/XML_Schema_tutorial_-_Basics)


We will be creating the following Request response in XML.

*GetCourseDetailsRequest* :arrow_right: *GetCourseDetailsResponse*

## Request XML

- **Namespace** enables us to make the XML unique to our project
- The Directive tells the system seeing this document that it's an XML document

```xml
<!-- XML Directive -->
<?xml version="1.0" encoding="UTF-8"?>
<GetCourseDetailsRequest xmlns="http://in28minutes.com/courses">
  <id>123</id> <!--should be numbers only-->
</GetCourseDetailsRequest>
```

In order to define the datatype for each element. we use an - **XSD** *XML Schema Definition*

> In order to bind an xml to a schema, use the `xsi:schemaLocation` which takes in 2 parameters with a space delimiter

```bash
xsi:schemaLocation="http://in28minutes.com/courses course-details.xsd"
```


## Response XML

```xml
<?xml version="1.0" encoding="UTF-8"?>
<GetCourseDetailsResponse xmlns="http://in28minutes.com/courses">
  <CourseDetails>
    <id>123</id>
    <name>Spring in 28 Minutes</name>
    <description>You would learn the basics of spring framework</description>
  </CourseDetails>
</GetCourseDetailsResponse>
```

## Step1: XML Schema Definition

- An XML Schema Definition is accessed in an XML using the `xsi:schemaLocation`
- Since the `xsi` is again a namespace, its `xmlns:xsi` needs to be defined also


### Using in a Request.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<GetCourseDetailsRequest xmlns="http://in28minutes.com/courses"
xsi:schemaLocation="http://in28minutes.com/courses course-details.xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <id>123</id>
</GetCourseDetailsRequest>
```

### XSD Schema File
```xml
<?xml version="1.0" encoding="UTF-8"?>
<schema 
  xmlns="https:///www.w3.org/2011/XMLSchema" 
  targetNameSpace="http://in28minutes.com/courses" 
  xmlns:tns="http://in28minutes.com/courses" 
  elementFormDefault="qualified">
  <element name="GetCourseDetailsRequest">
    <complexType>
      <sequence>
        <element name="id" type="integer"></element>
      </sequence>
    </complexType>
  </element>

</schema>
```

## Reusable Complex Types in XSD

You can create a reusable "complexType" definition by providing `complexType` a `name` attribute - CourseDetails

```xml
<!-- Reusable Complex type -->
    <xs:complexType name="CourseDetails">
        <xs:sequence>
            <xs:element name="id" type="xs:int"/>
            <xs:element name="name" type="xs:string"/>
            <xs:element name="description" type="xs:string"/>
        </xs:sequence>
    </xs:complexType>
```

## Step2: Adding JAXB to our Java Project

The following plugin configuration finally worked. also note that you need to dedicate a folder with only `.xsd` file types, in your **resources** folder

```xml
<!-- JAXB2 maven plugin -->
			<plugin>
				<groupId>org.codehaus.mojo</groupId>
				<artifactId>jaxb2-maven-plugin</artifactId>
				<version>2.5.0</version>
				<executions>
					<execution>
						<id>xjc</id>
						<goals>
							<goal>xjc</goal>
						</goals>
					</execution>
				</executions>
				<configuration>
					<sources>
						<source>${project.basedir}/src/main/resources/xsd/</source>
					 </sources>
					<clearOutputDir>false</clearOutputDir>					
					<outputDirectory>${project.basedir}/src/main/java</outputDirectory>
				</configuration>
			</plugin>
```

## Step3: Check POM.xml

The following pom.xml finally worked for me :tired_face:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.4.3</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.in28minutes.soap.webservices</groupId>
	<artifactId>spring-course-management</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>spring-course-management</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>15</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web-services</artifactId>
		</dependency>

		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
		<plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.7.0</version>
            <configuration>
                <release>10</release>
            </configuration>
        </plugin>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
			<!-- JAXB2 maven plugin -->
			<plugin>
				<groupId>org.codehaus.mojo</groupId>
				<artifactId>jaxb2-maven-plugin</artifactId>
				<version>2.5.0</version>
				<executions>
					<execution>
						<id>xjc</id>
						<goals>
							<goal>xjc</goal>
						</goals>
					</execution>
				</executions>
				<configuration>
					<sources>
						<source>${project.basedir}/src/main/resources/xsd/</source>
					 </sources>
					<clearOutputDir>false</clearOutputDir>					
					<outputDirectory>${project.basedir}/src/main/java</outputDirectory>
				</configuration>
			</plugin>
		</plugins>
	</build>
</project>

```

---

## Creating a WebService Endpoint

### Importing the JAR for handling SOAP Web-service

In order to work with SOAP web-services, we need the following dependency in our maven project

```xml
<dependency>
  <groupId>wsdl4j</groupId>
  <artifactId>wsdl4j</artifactId>
</dependency>
```

### Step 1: Define your Webservice class and method

In order to make your web-service class, first create a normal class with the Request Response datatypes

```java
public class CourseDetailsEndpoint {
  ...
// our method    
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
```

### Step 2: Use Spring annotations - 

the following Spring annotations are used

```java
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
```

- @Endpoint - Enable a SOAP web service endpoint
- @PayloadRoot - Create a route for the Webservice endpoint
- @ResponsePayload - Enables spring to serialize the POJO into an XML response
- @RequestPayload - Enables spring to de-serialize the XML into Java POJO

```java
package com.in28minutes.soap.webservices.coursemanagement.soap;
// spring imports
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.in28minutes.courses.CourseDetails;
// custom imports
import com.in28minutes.courses.GetCourseDetailsRequest;
import com.in28minutes.courses.GetCourseDetailsResponse;

@Endpoint
public class CourseDetailsEndpoint {
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

```

### Step 4: Creating a WSDL contract using Java

A WSDL requires three important things

- A Port Type
- A Namespace (or service endpoint)
- A Schema Definition file (or XSD)

We use **Spring Web Service** to generate the WSDL file for us. The code for handling messages on Webservice using a Dispatcher servlet is as follows:

```java
package com.in28minutes.soap.webservices.coursemanagement.soap;
// WebServiceConfig.java
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

}

```

### Step 5: FINALLY, creating a Web-service endpoing to fetch all courses

Once all the above configurations have been made, we can easily create our SOAP web-service endpoint

```java
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

```

