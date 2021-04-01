# Spring Restful Web-services

## Important Links
- [Spring Web Services - Github](https://github.com/in28minutes/spring-web-services)
- [Spring RESTful Web Services - Github](https://github.com/in28minutes/spring-web-services/tree/master/restful-web-services)
- [Spring Initializer](https://start.spring.io)
- [Spring @PathVariable versus @RequestParam](https://www.baeldung.com/spring-requestparam-vs-pathvariable)
- [Spring RESTful Webservice Code backup](https://github.com/in28minutes/spring-web-services/blob/master/restful-web-services/2.3.1.RELEASE-upgrade.md)




## Initializing the Project

> NOTE: _If we do not create a getter for a bean, Autowiring fails and we get the following error on running Spring framework -"No converter found for return type of class: com.in28minutes.rest.webservices.restfulwebservices.HelloWorldBean"_



## Debugging the Spring Framework

In Order to debug the workings within the Spring framework.
- Open up the application.properties file
- Add the following line

```bash
logging.level.org.springframework = debug
```
- You can now see the workings of Spring Framework under the "Auto-configuration" section
- All the Dispatcher servlet, Error mapping and Jackson's JSON2ObjectMapper is being taken care by the  - SpringBootAutoConfiguration
- the Dispatcher servlet, acts like a - FrontController JEE Design Pattern here
- Spring framework's Jackson, by default -  serializes Date objects as _date-time-stamp_. In order to override that setting

```bash
spring.jackson.serialization.write-dates-as-timestamps = false
```



## PathVariable versus RequestParam

In otherwords, Query Parameters versus URI Path

- **Syntax Usage**: The usage for QueryParam is different from PathVariable usage. Following is the code snippet to using PathVariable

> PathVariable

```java
@GetMapping("/foos/{id}")
@ResponseBody
public String getFooById(@PathVariable String id) {
    return "ID: " + id;
}
```
- The above code translates to the following URL call

```bash
# Calling a method using PathVariable
http://localhost:8080/foos/abc
# ID: abc
```
> RequestParam

```java
@GetMapping("/foos")
@ResponseBody
public String getFooByIdUsingQueryParam(@RequestParam String id) {
    return "ID: " + id;
}
```

- which would give us

```bash
http://localhost:8080/foos?id=abc
```

- **Encoded vs Exact Value** : Because @PathVariable is extracting values from the URI path, it’s not encoded. On the other hand, @RequestParam is.

- Using the previous example, ab+c will return as-is:

```bash
http://localhost:8080/foos/ab+c
# ID: ab+c
```

- But for a @RequestParam request, the parameter is URL decoded:

```bash
http://localhost:8080/foos?id=ab+c
# ID: ab c the + gets decoded to a space when it comes to RequestParams
```

- **Optional Values**: Both @RequestParam and @PathVariable can be optional.

- We can make @PathVariable optional by using the required attribute starting with Spring 4.3.3:

```java
@GetMapping({"/myfoos/optional", "/myfoos/optional/{id}"})
@ResponseBody
public String getFooByOptionalId(@PathVariable(required = false) String id){
    return "ID: " + id;
}
```

- Which, then, we can do either:

```bash
http://localhost:8080/myfoos/optional/abc
# ID: abc
```

- or:

```bash
http://localhost:8080/myfoos/optional
#ID: null
```

- For @RequestParam, we can also use the `required` attribute.
- **Note** that we should be careful when making @PathVariable optional, to avoid conflicts in paths.

## Spring REST Api Exception Handling

In Spring you can define a custom exception by creating a custom Exception class which extends the **RuntimeException** superclass

```java
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
   public UserNotFoundException (String msg) {
     super(msg);
   }
}
```

## Handling Generic Exception Handling for all Responses

In order to define a standard / uniform structure for Error responses in our API project, Spring provides a bunch of different ways to setup Common Exception Handlers
- Extending Abstract class called - **ResponseEntityExceptionHandler**. All we need to do is extend our custom class from this abstract class and override some of it's abstract methods
- Annotating @RestController - Used for annotating a class as a Rest Controller
- Annotating @ControllerAdvice - Used for annotating a  common controller with shareable methods

```java
import java.util.Date;

public class ExceptionResponse {
  // get timestamp
  private Date timestamp;
  // message
  private String message;
  // detail
  private String details;

  public ExceptionResponse (Date timestamp, String message, String details) {
     super();
     this.timestamp = timestamp;
     this.message = message;
     this.details = details;
  }
 
 // Generate Getters & Setters
 public Date getTimestamp() { return this.timestamp; }

 public ExceptionResponse setTimestamp(Date date) { this.timestamp = data; return this; }

 public String getMessage() { return this.message; }

 public ExceptionResponse setTimestamp(String msg) { this.message = msg; return this; }

}

// Creating a Common Shareable Controller for Exception Handler

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed",
				ex.getBindingResult().toString());
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}	
}

```

## Spring Annotations List

- **@RestController**: Annotate a Class as RESTFUL Web-service endpoint
- **@RequestMapping**: Very Similar to @PayloadRoute when it comes to SOAP web-service. Takes 2 arguments - RequestMethod and Path
- **@GetMapping**: Wrapper annotation around @RequetMapping. Takes only one argument - Path
- **@ResponseBody** - One of the important annotations present within the @RestController annotation



## Important Points & Videos

- ***Lesson 81*** - Implementing Static Filtering when deserializing Responses
  - **@JsonIgnore** (3:28)
  - **@JsonIgnoreProperties** (5:56)
  - Filtering of some fields in Response Entities

---

- ***Lesson 82*** - Implementing Dynamic Filtering when deserializing Responses
  - Sending specific fields for specific requests
  - **MappingJacksonValue** (2:05) - Create a dynamic filter
  - **SimpleBeanPropertyFilter.filterOutAllExcept("field1, field2")** - (4:16)
  - **@JsonFilter** (6:05)

```java
// field1,field2
	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean() {
		SomeBean someBean = new SomeBean("value1", "value2", "value3");

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");

		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
    
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);

		return mapping;
	}
```

```java
package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("SomeBeanFilter")
public class SomeBean {
	
	private String field1;
	
	private String field2;
	
	private String field3;

	public SomeBean(String field1, String field2, String field3) {
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

}
```

---

- ***Lesson 83*** - Versioning (4:00)

  - **Media type** versioning - Github
  - **Custom headers** versioning - Microsoft
  - **URI** versioning - Twitter
  - **Parameter** versioning - Amazon

  - Pros & Cons of each approach  (6:00)

---



## Richardson Maturity Model

A model (developed by **Leonard Richardson**) that breaks down the principal elements of a REST approach into three steps. These introduce resources, http verbs, and hypermedia controls.

- **Level 0** - Expose SOAP Web-services in REST style
- **Level 1** - Expose Resource with Proper URI
- **Level 2** - Level 1 + Proper use of HTTP Request Methods
- **Level 3** - Level 2 + Hyper Media As The Engine Of Application State (HATEOAS)
  - Data + Next Possible Actions

---

