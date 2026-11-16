package org.example.domain;
import com.google.common.base.Preconditions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



public class Student {
    final public String studentName;
    final public int startYear;
    final public int endYear;
    final public long studentNumber;
    final public ArrayList<Faculty> studentFaculty = new ArrayList<>();
    final static public ArrayList<Faculty> universityFaculties = new ArrayList<>();
    public Schedule studentSchedule;
    static public Map universityMap = new Map("University Map");

    public Student(String studentName, int startYear, int endYear, long studentNumber) {
        //preconditions
        Preconditions.checkNotNull(studentName, "Student name cannot be null");
        Preconditions.checkState(startYear >= 2026, "Student start year must start from 2026");
        Preconditions.checkState(endYear >= 2026, "Student end year must end from 2026");
        Preconditions.checkState((studentNumber >= 1000000000) && (studentNumber <= 9999999999L), "Student number must be 10 digits.");


        this.studentName = studentName;
        this.startYear = startYear;
        this.endYear = endYear;
        this.studentNumber = studentNumber;
        this.studentSchedule = new Schedule();

        if(universityFaculties.isEmpty()){
            initializeUniversityFaculty();
            initializeInstructors();
            initializeUniversityCourses();
        }

        //postconditions
        checkStudent();
    }

    public Faculty getFaculty(String facultyName) {
        for(int i = 0; i < universityFaculties.size(); i++) {
            if(universityFaculties.get(i).facultyName.name().equals(facultyName.toUpperCase())) {
                return universityFaculties.get(i);
            }
        }
        return null;
    }

    public String toString() {
        String studentInfo = "";
        studentInfo += "Name: " + this.studentName + "\n" + "Student Number: " + this.studentNumber + "\n" + "Start Year: " + this.startYear + "\n" + "End Year: " + this.endYear + "Faculty: " + this.studentFaculty;
        return studentInfo;
    }

    public void viewMap(){
        System.out.println(universityMap.showMap());
    }

    public boolean addFaculty(Faculty faculty) {
        return studentFaculty.add(faculty);
    }

    public boolean removeFaculty(Faculty faculty) {
        return studentFaculty.remove(faculty);
    }

    public void facultyInfo(Faculty chosenFaculty) {
        System.out.println("Faculty Info: " + chosenFaculty);
    }

    private void initializeUniversityFaculty() {
        Faculty newFaculty;

        Building facultyBuilding = new Building("scienceBuilding");
        universityFaculties.add(new Faculty(FacultyName.SCIENCE, facultyBuilding));
        newFaculty = getFaculty("SCIENCE");
        facultyBuilding.addFaculty(newFaculty);
        universityMap.addBuilding(facultyBuilding);

        facultyBuilding = new Building("artBuilding");
        universityFaculties.add(new Faculty(FacultyName.ART, facultyBuilding));
        newFaculty = getFaculty("ART");
        facultyBuilding.addFaculty(newFaculty);
        universityMap.addBuilding(facultyBuilding);

        facultyBuilding = new Building("businessBuilding");
        universityFaculties.add(new Faculty(FacultyName.BUSINESS, facultyBuilding));
        newFaculty = getFaculty("BUSINESS");
        facultyBuilding.addFaculty(newFaculty);
        universityMap.addBuilding(facultyBuilding);

        facultyBuilding = new Building("musicBuilding");
        universityFaculties.add(new Faculty(FacultyName.MUSIC, facultyBuilding));
        newFaculty = getFaculty("MUSIC");
        facultyBuilding.addFaculty(newFaculty);
        universityMap.addBuilding(facultyBuilding);

        facultyBuilding = new Building("archiBuilding");
        universityFaculties.add(new Faculty(FacultyName.ARCHITECTURE, facultyBuilding));
        newFaculty = getFaculty("ARCHITECTURE");
        facultyBuilding.addFaculty(newFaculty);
        universityMap.addBuilding(facultyBuilding);
    }

    private void initializeUniversityMap() {
        universityMap = new Map("Univerity Map");
        //read it in from a file
        try{
            File buildingsFile = new File("Buildings.txt");
            Scanner scnr = new Scanner(buildingsFile);

            System.out.println(buildingsFile.getAbsolutePath());
            System.out.println(buildingsFile.exists());

            while (scnr.hasNextLine()) {
                String fileLine = scnr.nextLine();
                String[] token = fileLine.split(",");
                String buildingName = token[0];
                String facultyName = token[1];
                Faculty buildingFaculty = getFaculty(facultyName);
                Building fileBuilding = new Building(buildingName, buildingFaculty);
                universityMap.addBuilding(fileBuilding);
            }

        }catch(FileNotFoundException e){
            System.out.println("Building file not found");
        }


    }

    private void initializeUniversityCourses(){
        try{
            File coursesFile = new File("Courses.txt");
            Scanner scnr = new Scanner(coursesFile);

            while(scnr.hasNextLine()){
                String fileLine = scnr.nextLine();
                String[] token = fileLine.split(",");

                Faculty courseFaculty = getFaculty(token[0]);
                String courseName = token[1];
                int courseNumber = Integer.parseInt(token[2]);
                CourseDate courseDate = CourseDate.valueOf(token[3]);
                CourseTerm courseTerm = CourseTerm.valueOf(token[4]);
                CourseType courseType = CourseType.valueOf(token[5]);
                double courseTime = Double.parseDouble(token[6]);
                Instructor courseInstructor;

                if(token[7].trim().equalsIgnoreCase("null")){
                    courseInstructor = null;
                }else{
                    courseInstructor = new Instructor(token[7],courseFaculty, courseFaculty.facultyBuilding);
                    courseFaculty.addInstructor(courseInstructor);
                }

                Course uniCourse = new Course(courseName, courseFaculty, courseDate, courseTerm, courseType, courseNumber, courseTime, courseInstructor);
                courseFaculty.addCourse(uniCourse);

            }

        }catch(FileNotFoundException e){
            System.out.println("Course file not found");
        }
    }

    private void initializeInstructors(){
        try{
            File instructorsFile = new File("Instructors.txt");
            Scanner scnr = new Scanner(instructorsFile);

            while(scnr.hasNextLine()){
                String fileLine = scnr.nextLine();
                String[] token = fileLine.split(",");

                Faculty instructorFaculty = getFaculty(token[0]);
                String instructorName = token[1];

                Instructor newInstructor = new Instructor(instructorName, instructorFaculty,  instructorFaculty.facultyBuilding);
                instructorFaculty.addInstructor(newInstructor);

            }

        }catch(FileNotFoundException e){
            System.out.println("Instructors file not found");
        }
    }

    private void checkStudent(){
        //class invariants
        Preconditions.checkNotNull(studentName, "Student name cannot be null");
        Preconditions.checkState(!studentName.isEmpty(), "Student name cannot be empty");
        Preconditions.checkState(startYear >= 2026, "Student start year must start from 2026");
        Preconditions.checkState(endYear >= 2026, "Student end year must end from 2026");
        Preconditions.checkState(studentNumber >= 1000000000, "Student number must be exactly 10 digits");
    }
}
