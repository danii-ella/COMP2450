package org.example.domain;
import java.util.ArrayList;
import java.util.Scanner;

public class SPMain {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the student planner!");
        System.out.println("Please enter your name:");
        String studName = input.nextLine();
        System.out.println("Please enter your student number:");
        long studNumber = input.nextLong();
        System.out.println("Please enter your start year:");
        int startYear = input.nextInt();
        System.out.println("Please enter your end year:");
        int endYear = input.nextInt();

        Student profile = new Student(studName, startYear, endYear, studNumber);

        String devMenu = "DEV MENU\n" +
                "1. Add Building: To a map\n" +
                "2. Remove Building: From a map\n" +
                "3. Remove all Building: From a map\n" +
                "4. Add Door: To a building\n" +
                "5. Remove Door: From a building\n" +
                "6. Add Instructor: To a course\n" +
                "7. Remove Instructor: From a course\n" +
                "8. Remove all Instructor: From a faculty\n" +
                "9. Exit: Quit program\n";

        String studMenu = "STUDENT MENU\n" +
                "1. Add Course: To student schedule\n" +
                "2. Remove Course: From student schedule\n" +
                "3. Remove all Courses: From student schedule\n" +
                "4. Show Schedule: Student schedule\n" +
                "5. Show Map: Official university map\n" +
                "6. Add Faculty: Join a faculty\n" +
                "7. Show Faculty: Information of a faculty\n" +
                "8. Remove Faculty: From registered faculties\n" +
                "9. Exit: Quit program\n";

        input.nextLine();
        System.out.println("Enter your role: S for Student or D for Developer or E to Exit");
        String role = input.nextLine();

        while(!role.equalsIgnoreCase("E")) {
            int choice;

            if(role.equalsIgnoreCase("D")) {
                String devInput;
                System.out.print("Enter your choice: ");
                System.out.println(devMenu);
                choice = input.nextInt();
                input.nextLine();

                while(choice != 9){
                    if(choice == 1) {
                        //Adding building to a map
                        boolean added = false;
                        Building newBuilding;
                        String buildingName;
                        String facultyName;
                        Faculty buildingFaculty;

                        System.out.println("Enter the name of the building you want to add to the University Map");
                        buildingName = input.nextLine();

                        System.out.println("Enter the name of the faculty this building is in");
                        facultyName = input.nextLine();
                        buildingFaculty = profile.getFaculty(facultyName);

                        if(buildingFaculty != null) {
                            newBuilding = new Building(buildingName, buildingFaculty);
                            added = Student.universityMap.addBuilding(newBuilding);

                        }else{
                            System.out.println("Faculty does not exist");
                        }

                        if(added == true){
                            System.out.println("New building was added successfully");
                        }else{
                            System.out.println("New building was not added to the University Map");
                        }

                    }else if(choice == 2) {
                        //Remove building from a map
                        boolean removed;
                        Building chosenBuilding;
                        String buildingName;

                        System.out.println("Enter the name of the building you want to remove from the University Map");
                        buildingName = input.nextLine();

                        chosenBuilding = Student.universityMap.getBuilding(buildingName);

                        if(chosenBuilding != null){
                            removed = Student.universityMap.removeBuilding(chosenBuilding);

                            if(removed){
                                System.out.println("Building has been removed from the University Map");
                            }else{
                                System.out.println("Building has NOT been removed from the University Map");
                            }

                        }else{
                            System.out.println("Building not found.");
                        }

                    }else if(choice == 3) {
                        //Remove all building from a map
                        boolean cleared = false;
                        String confirm;

                        System.out.println("Enter Y or N to confirm clearing the University Map");
                        confirm = input.nextLine();

                        if(confirm.equalsIgnoreCase("Y")) {
                            cleared = Student.universityMap.removeAllBuildings();
                        }else if(confirm.equalsIgnoreCase("N")) {
                            System.out.println("University map was not cleared.");
                            break;
                        }else{
                            System.out.println("Invalid choice");
                        }

                        if(cleared){
                            System.out.println("University map has been cleared.");
                        }else{
                            System.out.println("University map was not cleared.");
                        }

                    }else if(choice == 4) {
                        //Add door to a building
                        boolean added = false;
                        Building doorBuilding;
                        String buildingName;
                        Door newDoor;

                        System.out.println("Enter the name of the building you want to add a door to: ");
                        buildingName = input.nextLine();
                        doorBuilding = Student.universityMap.getBuilding(buildingName);

                        if(doorBuilding != null){
                            System.out.println("Enter L to add a door to the Left, R to add a door to the Right, B to add a door to the Back: ");
                            devInput = input.nextLine();
                            String dLocation;

                            if(devInput.equalsIgnoreCase("L")) {
                                dLocation = "LEFT";
                                newDoor = new Door(DoorLocation.valueOf(dLocation));
                                added = doorBuilding.addDoor(newDoor);

                            }else if(devInput.equalsIgnoreCase("R")) {
                                dLocation = "RIGHT";
                                newDoor = new Door(DoorLocation.valueOf(dLocation));
                                added = doorBuilding.addDoor(newDoor);

                            }else if(devInput.equalsIgnoreCase("B")) {
                                dLocation = "BACK";
                                newDoor = new Door(DoorLocation.valueOf(dLocation));
                                added = doorBuilding.addDoor(newDoor);

                            }else{
                                System.out.println("Invalid door location character");

                            }

                            if(added == true){
                                System.out.println("Door added successfully");
                            }else{
                                System.out.println("Door was not added");
                            }

                        }else{
                            System.out.println("Building does not exist");
                        }


                    }else if(choice == 5) {
                        //Remove door from a building
                        boolean removed = false;
                        Building chosenBuilding;
                        String buildingName;
                        String doorLocation;
                        DoorLocation dLocation;

                        System.out.println("Enter the name of the building you want to remove: ");
                        buildingName = input.nextLine();
                        chosenBuilding = Student.universityMap.getBuilding(buildingName);

                        if(chosenBuilding != null){
                            System.out.println("Enter the door location you would like to remove: L for Left, R for Right or B for Back");
                            doorLocation = input.nextLine();

                            if(doorLocation.equalsIgnoreCase("L")) {
                                dLocation = DoorLocation.LEFT;
                                removed = chosenBuilding.removeDoor(dLocation);

                            }else if(doorLocation.equalsIgnoreCase("R")) {
                                dLocation = DoorLocation.RIGHT;
                                removed = chosenBuilding.removeDoor(dLocation);

                            }else if(doorLocation.equalsIgnoreCase("B")) {
                                dLocation = DoorLocation.BACK;
                                removed = chosenBuilding.removeDoor(dLocation);

                            }else{
                                System.out.println("Invalid door location");
                                break;
                            }

                            if(removed == true){
                                System.out.println("Door removed successfully");
                            }else{
                                System.out.println("Door was not removed");
                            }


                        }else{
                            System.out.println("Building does not exist");

                        }


                    }else if(choice == 6) {
                        //Add instructor to a course
                        boolean added;
                        Instructor courseInstructor;
                        String instructorName;
                        String courseName;
                        int courseNumber;
                        Course chosenCourse;
                        Faculty courseFaculty;

                        System.out.println("Enter the course faculty:");
                        devInput = input.nextLine();
                        courseFaculty = profile.getFaculty(devInput);

                        if(courseFaculty != null){
                            System.out.println("Enter the course name:");
                            courseName = input.nextLine();

                            System.out.println("Enter the course number:");
                            courseNumber = input.nextInt();
                            input.nextLine();

                            chosenCourse = courseFaculty.getCourse(courseName.toUpperCase(), courseNumber);

                            if(chosenCourse != null) {
                                System.out.println("Enter the instructor's name:");
                                instructorName = input.nextLine();
                                courseInstructor = courseFaculty.getInstructor(instructorName);

                                if(courseInstructor != null) {
                                    added = chosenCourse.addInstructor(courseInstructor);

                                    if(added) {
                                        System.out.println("Instructor has been added");
                                    }else{
                                        System.out.println("Instructor has NOT been added");
                                    }

                                }else{
                                    System.out.println("Instructor not found");
                                }

                            }else{
                                System.out.println("Course does not exist");
                            }

                        }else{
                            System.out.println("Course faculty does not exist");
                        }


                    }else if(choice == 7) {
                        //Remove instructor from a course
                        boolean removed;
                        Instructor courseInstructor;
                        String instructorName;
                        String courseName;
                        int courseNumber;
                        Course chosenCourse;
                        Faculty courseFaculty;

                        System.out.println("Enter the course faculty:");
                        devInput = input.nextLine();
                        courseFaculty = profile.getFaculty(devInput);

                        if(courseFaculty != null){
                            System.out.println("Enter the course name:");
                            courseName = input.nextLine();

                            System.out.println("Enter the course number:");
                            courseNumber = input.nextInt();
                            input.nextLine();

                            chosenCourse = courseFaculty.getCourse(courseName.toUpperCase(), courseNumber);

                            if(chosenCourse != null) {
                                System.out.println("Enter the instructor's name:");
                                instructorName = input.nextLine();
                                courseInstructor = courseFaculty.getInstructor(instructorName);

                                if(courseInstructor != null) {
                                    removed = chosenCourse.removeInstrucotor(courseInstructor);

                                    if(removed) {
                                        System.out.println("Instructor has been removed");
                                    }else{
                                        System.out.println("Instructor has NOT been removed");
                                    }

                                }else{
                                    System.out.println("Instructor not found");
                                }

                            }else{
                                System.out.println("Course does not exist");
                            }
                        }else{
                            System.out.println("Course faculty does not exist");
                        }


                    }else if(choice == 8) {
                        //Remove all instructor from a faculty
                        boolean cleared = false;
                        Faculty chosenFaculty;
                        System.out.println("Enter the faculty you would like to clear the instructors:");
                        devInput = input.nextLine();
                        chosenFaculty = profile.getFaculty(devInput);

                        if(chosenFaculty == null) {
                            System.out.println("Invalid faculty name");
                        }else{
                            cleared = chosenFaculty.removeAllInstructors();
                        }

                        if(cleared == true){
                            System.out.println("Faculty has been cleared");
                        }else{
                            System.out.println("Faculty has NOT been cleared");
                        }


                    }
                    System.out.print("Enter your choice: ");
                    System.out.println(devMenu);
                    choice = input.nextInt();
                    input.nextLine();
                }

            }else if(role.equalsIgnoreCase("S")){
                System.out.print("Enter your choice: ");
                System.out.println(studMenu);
                choice = input.nextInt();
                input.nextLine();

                while(choice != 9) {
                    if(choice == 1) {
                        //Add course to student schedule
                        String courseName;
                        Faculty courseFaculty;
                        String facultyName;
                        Course chosenCourse;
                        int courseNumber;
                        boolean added;

                        System.out.println("Enter the course faculty:");
                        facultyName = input.nextLine();
                        courseFaculty = profile.getFaculty(facultyName);

                        if(courseFaculty != null) {
                            System.out.println("Enter the course name:");
                            courseName = input.nextLine();

                            System.out.println("Enter the course number:");
                            courseNumber = input.nextInt();
                            input.nextLine();

                            chosenCourse = courseFaculty.getCourse(courseName.toUpperCase(), courseNumber);

                            if(chosenCourse != null) {
                                added = profile.studentSchedule.addCourse(chosenCourse);

                                if(added) {
                                    System.out.println("Course has been added");
                                }else{
                                    System.out.println("Course has NOT been added");
                                }

                            }else{
                                System.out.println("Course does not exist");
                            }

                        }else{
                            System.out.println("Invalid faculty choice");
                        }


                    }else if(choice == 2) {
                        //Remove course from student schedule
                        String courseName;
                        Faculty courseFaculty;
                        String facultyName;
                        Course chosenCourse;
                        int courseNumber;
                        boolean removed;

                        System.out.println("Enter the course faculty:");
                        facultyName = input.nextLine();
                        courseFaculty = profile.getFaculty(facultyName);

                        if(courseFaculty != null) {
                            System.out.println("Enter the course name:");
                            courseName = input.nextLine();

                            System.out.println("Enter the course number:");
                            courseNumber = input.nextInt();
                            input.nextLine();

                            chosenCourse = courseFaculty.getCourse(courseName.toUpperCase(), courseNumber);

                            if(chosenCourse != null) {
                                removed = profile.studentSchedule.removeCourse(chosenCourse);

                                if(removed) {
                                    System.out.println("Course has been removed");
                                }else{
                                    System.out.println("Course has NOT been removed");
                                }

                            }else{
                                System.out.println("Course does not exist");
                            }

                        }else{
                            System.out.println("Invalid faculty choice");
                        }

                    }else if(choice == 3) {
                        //Remove all courses from student schedule
                        boolean removed;
                        String confirm;

                        System.out.println("Enter your Y or N to comfirm clearing your student schedule");
                        confirm = input.nextLine();

                        if(confirm.equalsIgnoreCase("Y")){
                            removed = profile.studentSchedule.removeAllCourses();

                            if(removed) {
                                System.out.println("Schedule has been cleared");
                            }else{
                                System.out.println("Schedule has NOT been cleared");
                            }

                        }else{
                            System.out.println("Invalid choice");
                            System.out.println("Student schedule was not cleared");
                        }


                    }else if(choice == 4) {
                        //Show student schedule
                        Schedule studSchedule = profile.studentSchedule;
                        ArrayList<Course> scheduleCourses = studSchedule.offeringCourses;
                        System.out.println("This is your current schedule:");
                        for (int i = 0; i < scheduleCourses.size(); i++) {
                            System.out.println(scheduleCourses.get(i));
                        }

                    }else if(choice == 5) {
                        //Show university map
                        System.out.println("This is the official university map:");
                        System.out.println(profile.universityMap.showMap());

                    }else if(choice == 6) {
                        //Join a faculty
                        String facultyName;
                        Faculty interestedFaculty;
                        boolean added;

                        System.out.println("Enter the faculty name you are interested in:");
                        facultyName = input.nextLine();
                        interestedFaculty = profile.getFaculty(facultyName);

                        if(interestedFaculty != null) {
                            added = profile.addFaculty(interestedFaculty);

                            if(added) {
                                System.out.println("Faculty has been added");
                            }else{
                                System.out.println("Faculty has NOT been added");
                            }

                        }else{
                            System.out.println("Faculty does not exist");
                        }

                    }else if(choice == 7) {
                        //Show information of a faculty
                        String facultyName;
                        Faculty chosenFaculty;
                        System.out.println("Enter the faculty name you are interested in:");
                        facultyName = input.nextLine();

                        chosenFaculty = profile.getFaculty(facultyName);

                        if(chosenFaculty != null) {
                            profile.facultyInfo(chosenFaculty);

                        }else{
                            System.out.println("Faculty does not exist");
                        }

                    }else if(choice == 8) {
                        //Remove a faculty
                        String facultyName;
                        Faculty chosenFaculty;
                        boolean removed;

                        System.out.println("Enter the faculty name you want to remove:");
                        facultyName = input.nextLine();
                        chosenFaculty = profile.getFaculty(facultyName);

                        if(chosenFaculty != null) {
                            removed = profile.removeFaculty(chosenFaculty);

                            if(removed) {
                                System.out.println("Faculty has been removed");
                            }else{
                                System.out.println("Faculty has NOT been removed");
                            }

                        }else{
                            System.out.println("Faculty does not exist");
                        }

                    }
                    System.out.print("Enter your choice: ");
                    System.out.println(studMenu);
                    choice = input.nextInt();
                    input.nextLine();

                }

            }

            System.out.println("Enter your role: S for Student or D for Developer or E to Exit");
            role = input.nextLine();
        }



    }
}
