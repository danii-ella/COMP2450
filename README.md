---
title: My Student Planner Project!
author: Adewusi Daniella Oluwadamilola (adewusdo@myumanitoba.ca)
date: Fall 2026
---

# Domain model

## Resources


## Diagram

Here is the diagram for my domain model

```mermaid
classDiagram
    class Student{
        - text studentName
        - positiveNumber startYear 
        - positiveNumber endYear
        - positiveNumber studentNumber
        - List<Faculty> studentFaculty
        - List<Map> campusMaps
        - Schedule studentSchedule
        - text Announcements
        + addFaculty(Faculty wantFaculty)
        + removeFaculty(Faculty notWantFacult)
        + showSchedule()
        + showMap(Map campusMap)
        + addMap(Map campusMap)
        + removeMap(Map campusMap)
        + showFaculty(Faculty currFaculty)
    }

    class Course{
        - text courseName
        - Faculty courseFaculty
        - CourseDate courseDate
        - time courseTime
        - CourseTerm courseTerm
        - CourseType courseType
        - positiveNumber courseNumber
        - Instructor courseInstructor
        

    }
    class CourseType<<Enumeration>>{
        LECTURE,
        LAB
    }
    class CourseTerm<<Enumeration>>{
        FALL,
        WINTER,
        SUMMER
    }
    class CourseDate<<Enumeration>>{
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY
    }

    class Faculty{
        - FacultyName facultyName
        - Building facultyBuilding
        - List<Course> facultyCourses
        - List<Instructor> facultyInstructors
        + showAllInstructors()
        + addInstructor(Instructor wantInstructor)
        + removeInstructor(Instructor exInstructor)
        + removeAllInstructor()
    }
    class FacultyName<<Enumeration>>{
        SCIENCE,
        ART,
        BUSINESS,
        MUSIC,
        ARCHITECTURE
    }  

    
    class Instructor{
        - Faculty instructorFaculty
        - boolean isProfessor
        - text instructorName
        - Building intructorOffice
        + InstructorInfo()
    }

    class Building{
        - text buildingName
        - List<Door> buildingDoors
        - Faculty buildingFaculty
        + addDoor()
        + removeDoor()
    }
    
    class Door{
        - DoorLocation doorPosition
    }
    class DoorLocation<<Enumeration>>{
        FRONT,
        LSIDE,
        RSIDE,
        BACK
    }  

    class Map{
        - text mapName
        - List<text> buildingsOnMap
        - list<Building> mapped
        + addBuilding(Building wantBuilding)
        + removeBuilding(Building notWantBuilding)
        + removeAllBuilding()
    }

    class Schedule{
        - List<Course> offeringCourses
        + showCourseInfo(Course selectCourse)
        + addCourse(Course courseWant)
        + removeCourse(Course courseRemove)
        + removeAllCourse()
       
    }


    %%Relationships
    Student --o Course
    Student --o Faculty
    Student --o Map
    Student --* Schedule
    Faculty --* Course
    Course --o Building
    Faculty --* Instructor
    Faculty --* Building
    Instructor --* Course
    Instructor --o Building
    Building --* Door
    Building --o Map
    Schedule --o Course
    
    Course --* CourseType
    Course --* CourseTerm
    Course --* CourseDate
    Door --* DoorLocation
    Facutly --* FacultyName
    
   
```