package org.example.domain;

import com.google.common.base.Preconditions;

public class Course {
    final public String courseName;
    final public Faculty courseFaculty;
    final public CourseDate courseDate;
    final public CourseTerm courseTerm;
    final public CourseType courseType;
    final public double courseTime;
    final public int courseNumber;
    public Instructor courseInstructor;

    public Course(String courseName, Faculty courseFaculty,  CourseDate courseDate, CourseTerm courseTerm, CourseType courseType, int courseNumber, double courseTime, Instructor courseInstructor) {
        //preconditions
        Preconditions.checkNotNull(courseName, "Course name cannot be null");
        Preconditions.checkNotNull(courseFaculty, "Faculty cannot be null");
        Preconditions.checkNotNull(courseDate, "Date cannot be null");
        Preconditions.checkNotNull(courseTerm, "CourseTerm cannot be null");
        Preconditions.checkNotNull(courseType, "CourseType cannot be null");
        Preconditions.checkState((courseNumber > 999) && (courseNumber <= 9999), "CourseNumber must be 4 digits");
        Preconditions.checkState((courseTime >= 0.0) && (courseTime <= 23.59), "Course time format is not valid");

        this.courseName = courseName;
        this.courseFaculty = courseFaculty;
        this.courseDate = courseDate;
        this.courseTerm = courseTerm;
        this.courseType = courseType;
        this.courseNumber = courseNumber;
        this.courseInstructor = courseInstructor;
        this.courseTime = courseTime;

        //postconditions
        checkCourse();
    }


    public boolean removeInstrucotor(Instructor instructor) {
        boolean removed;

        if(this.courseInstructor == instructor) {
            this.courseInstructor = null;
            removed = true;
        }else{
            removed = false;
        }

        return removed;
    }

    public boolean addInstructor(Instructor instructor) {
        boolean added;
        if(this.courseInstructor == null) {
            this.courseInstructor = instructor;
            added = true;
        }else{
            added = false;
        }
        return added;
    }

    public String toString() {
        String courseInfo = "";
        courseInfo += "Course: " + this.courseName + "\n" + "Faculty: " + this.courseFaculty + "\n" + "Code: " + this.courseNumber + "\n" + "Instructor: ";

        if(this.courseInstructor == null) {
            courseInfo += "Not assigned\n";
        }else{
            courseInfo += this.courseInstructor.instructorName + "\n";
        }

        return courseInfo;
    }

    private void checkCourse(){
        //class invariants
        Preconditions.checkNotNull(courseName, "Course name cannot be null");
        Preconditions.checkState(!courseName.isEmpty(), "Course name cannot be empty");
        Preconditions.checkNotNull(courseFaculty, "Faculty cannot be null");
        Preconditions.checkNotNull(courseDate, "CourseDate cannot be null");
        Preconditions.checkNotNull(courseTerm, "CourseTerm cannot be null");
        Preconditions.checkNotNull(courseType, "CourseType cannot be null");
        Preconditions.checkNotNull(courseTime, "CourseTime cannot be null");
        Preconditions.checkState((courseNumber > 999) && (courseNumber <= 9999), "Course number must be exactly 4 didgits.");
    }
}
