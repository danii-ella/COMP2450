package org.example.domain;
import com.google.common.base.Preconditions;
import java.util.ArrayList;

public class Faculty {
    final public FacultyName facultyName;
    final public Building facultyBuilding;
    final public ArrayList<Course> facultyCourses = new ArrayList<>();
    final public ArrayList<Instructor> facultyInstructors = new ArrayList<>();

    public Faculty(FacultyName facultyName, Building facultyBuilding) {
        //precondtions
        Preconditions.checkNotNull(facultyName, "FacultyName cannot be null");
        Preconditions.checkNotNull(facultyBuilding, "FacultyBuilding cannot be null");

        this.facultyName = facultyName;
        this.facultyBuilding = facultyBuilding;

        //postcondition
        checkFaculty();
    }

    public String showAllInstructors() {
        //precondition
        checkFaculty();

        String allInstructors = "";
        for (int i = 0; i < this.facultyInstructors.size(); i++) {
            allInstructors += this.facultyInstructors.get(i).instructorName;
        }

        //postcondition
        checkFaculty();

        return allInstructors;
    }

    public boolean addInstructor(Instructor instructor) {
        //precondition
        Preconditions.checkNotNull(instructor, "Instructor cannot be null");
        Preconditions.checkNotNull(facultyInstructors, "FacultyInstructors can not be null");
        checkFaculty();

        boolean result = false;
        this.facultyInstructors.add(instructor);
        if(facultyInstructors.contains(instructor)) {
            result = true;
        }

        //postcondition
        Preconditions.checkNotNull(instructor, "Instructor cannot be null");
        Preconditions.checkNotNull(facultyInstructors, "FacultyInstructors can not be null");
        checkFaculty();

        return result;
    }

    public boolean removeInstructor(Instructor instructor) {
        //precondition
        Preconditions.checkNotNull(instructor, "Instructor cannot be null");
        checkFaculty();

        boolean result = false;
        this.facultyInstructors.remove(instructor);
        if(!facultyInstructors.contains(instructor)) {
            result = true;
        }

        //postcondition
        checkFaculty();
        Preconditions.checkNotNull(instructor, "Instructor cannot be null");


        return result;
    }
    public boolean removeAllInstructors() {
        //precondition
        checkFaculty();

        boolean result = false;
        this.facultyInstructors.clear();
        if(facultyInstructors.isEmpty()) {
            result = true;
        }

        //postcondition
        checkFaculty();

        return result;
    }

    public Course getCourse(String courseName, int courseNumber) {
        for(int i = 0; i < this.facultyCourses.size(); i++) {
            if((facultyCourses.get(i).courseName.equals(courseName)) && (facultyCourses.get(i).courseNumber == courseNumber)) {
                return this.facultyCourses.get(i);
            }
        }
        return null;
    }

    public Instructor getInstructor(String instructorName) {
        for(int i = 0; i < this.facultyInstructors.size(); i++) {
            if(facultyInstructors.get(i).instructorName.equals(instructorName)) {
                return this.facultyInstructors.get(i);
            }
        }
        return null;
    }

    public boolean addCourse(Course course) {
        boolean added = false;

        added = facultyCourses.add(course);

        return added;
    }

    public String toString() {
        String  facultyInfo = "";
        facultyInfo += "Faculty Name: " + this.facultyName + "\n" + "Building: "  + this.facultyBuilding.buildingName;
        return facultyInfo;
    }

    private void checkFaculty(){
        Preconditions.checkNotNull(this.facultyName, "Faculty name cannot be null");
        Preconditions.checkNotNull(this.facultyBuilding, "Faculty building cannot be null");
    }
}

