package org.example.domain;
import com.google.common.base.Preconditions;

public class Instructor {
    final public String instructorName;
    final public Faculty instructorFaculty;
    public boolean isProfessor;
    final public Building instructorOffice;

    public Instructor(String instructorName, Faculty instructorFaculty, Building instructorOffice) {
        //preconditions
        Preconditions.checkNotNull(instructorName, "InstructorName cannot be null");
        Preconditions.checkState(!instructorName.isEmpty(), "InstructorName cannot be empty");
        Preconditions.checkNotNull(instructorFaculty, "InstructorFaculty cannot be null");
        Preconditions.checkNotNull(instructorOffice, "InstructorOffice cannot be null");

        this.instructorName = instructorName;
        this.instructorFaculty = instructorFaculty;
        this.instructorOffice = instructorOffice;
        this.isProfessor = false;

        //postcondtion
        checkInstructor();
    }

    public String instructorInfo() {
        String info = "";
        info += "Instructor Name: " + this.instructorName + "Instructor Faculty: " + this.instructorFaculty.facultyName + "Instructor Office" + this.instructorOffice.buildingName;
        return info;
    }

    public void checkInstructor() {
        Preconditions.checkNotNull(this.instructorName);
        Preconditions.checkState(!instructorName.isEmpty(), "Instructor name should not be empty");
        Preconditions.checkNotNull(this.instructorFaculty);
        Preconditions.checkNotNull(this.instructorOffice);
    }
}
