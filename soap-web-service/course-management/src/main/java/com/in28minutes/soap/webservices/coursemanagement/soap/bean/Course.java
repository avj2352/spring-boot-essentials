package com.in28minutes.soap.webservices.coursemanagement.soap.bean;

public class Course {
    private int id;
    private String name;
    private String description;

    public Course(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Course setId (int id) {
        this.id = id;
        return this;
    }

    public int getId () {
        return this.id;
    }

    public Course setName (String name) {
        this.name = name;
        return this;
    }

    public String getName () {
        return this.name;
    }

    public Course setDescription (String desc) {
        this.description = desc;
        return this;
    }

    public String getDescription () {
        return this.description;
    }

    @Override
    public String toString () {
        return String.format("{id: %s, name: %s, description: %s}", id, name, description);
    }

    @Override
    public boolean equals (Object obj) {
        Course instance = (Course) obj;
        return instance.toString().equals(this.toString());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
