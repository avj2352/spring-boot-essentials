# Simple XML Structure

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

## XML Schema Definition

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

You can create a reusable complexType defintion by providing `complexType` a `name` attribute - 
