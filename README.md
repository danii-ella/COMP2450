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
        - positiveNumber startYear / endYear
        - positiveNumber studentNumber
        - List<Faculty>  listOfFaculties
        - Schedule currSchedule
        - Resource Announcements
        + showFaculty()
        + showSchedule (Schedule currSchedule)
        + showMap()
        + showStudResources()
        + addInstructor(Professor instructor)
    }

    class Course{
        - text courseName
        - Faculty courseFaculty
        - positiveNumber courseDate
        - positiveNumber courseTime
        - positiveNumber courseNumber
        - Professor teachingProfessor
        + showCourseInfo (Course currCourse)

    }

    class Faculty{
        - text facultyName
        - Building facultyBuilding
        - List<Faculty> facultyCourses
        - List Professor facultyProfessors
        + showAllProf (Professor<ArrayList> allProfessor)
    }

    class Professor{
        - Faculty professorFaculty
        - text professorName
        - List<Course> professorCourses
        - Building professorOffice
        + viewAllProfCourse()
    }

    class Building{
        - text buildingName
        - Faculty buildingFaculty
        + addDoor (Building currBuilding)
        + removeDoor (Building currBuilding)
    }

    class Map{
        - text mapName
        - List<Buildings> mapBuildings
        + addBuilding (Building wantBuilding)
        + removeBuilding (Building notWantBuilding)
        + removeAllBuilding()
    }

    class Schedule{
        - List<Course> offeringCourses
        + showCourseInfo(Course iCourse)
        + addCourse(Course courseWant)
        + removeCourse (Course courseRemove)
        + removeAllCourse (List<Course> courseList)
    }

    class StudResource{
        - link academicCalendar
        - link helpCenter
        + viewAcademicCalendar()
        + viewHelpCenter()


    }

    class DevResource{
        - list<Faculty> facultyList
        - list<Professor> professorList
        - Map campusMap
        + addFaculty (Faculty wantFaculty)
        + removeFaculty (Faculty notFaculty)
        + removeMap(Map oldMap)
        + addMap(Map newMap)
        + removeInstructor(Professor instructor)
        + removeAllInstructor()
    }


    class Groal{
        - List<Course> currTakinCourse
        - List<> courseGrades
        - List<number/letter> goals
        + addGoal (Groal desireGoal)
        + removeGoal (Groal removeGoal)
        + viewGoals
        + viewGPA

    }

    class Direction{
        - Map directionOnMap
        + showDirection (Course currCourse)
        + showDirection (Professor currProf)
        + showDirection (Building currBuilding)

    }

    %%Relationships
    Student --o Course
    Student --o StudResource
    Student --* Groal
    Student --o Faculty
    Student --o Schedule
    Course --o Building
    Faculty --o Building
    Groal -- Course
    Professor --o Building
    Student --o DevResource
    Schedule --o Course
    Faculty --* Course
    Map --* Building
    Map --* Direction
    Professor --* Course
    Faculty --* Professor
    DevResource --o Map
    DevResource --o Faculty
    DevResource --o Professor
```