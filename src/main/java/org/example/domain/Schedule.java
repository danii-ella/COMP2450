package org.example.domain;
import com.google.common.base.Preconditions;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Schedule {
    public ArrayList<Course> offeringCourses = new ArrayList<>();

    public Schedule(Course course) {
        //preconditions
        Preconditions.checkNotNull(course, "Course cannot be null");

        offeringCourses.add(course);

        //postconditions
        Preconditions.checkNotNull(course, "Course cannot be null");
        Preconditions.checkNotNull(offeringCourses, "Offered courses cannot be null");
    }
    public Schedule(){

    }

    public boolean addCourse(Course course){
        //precondition
        Preconditions.checkNotNull(course, "Course cannot be null");

        boolean result = false;
        if(offeringCourses.add(course)){
            result = true;
        }

        //postcondition
        Preconditions.checkNotNull(course, "Course cannot be null");
        Preconditions.checkNotNull(offeringCourses, "Offering courses cannot be null");

        return result;
    }

    public boolean removeCourse(Course course){
        //precondition
        Preconditions.checkNotNull(course, "Course cannot be null");


        boolean result = false;
        if(offeringCourses.remove(course)){
            result = true;
        }

        //postcondition
        Preconditions.checkNotNull(course, "Course cannot be null");
        Preconditions.checkNotNull(offeringCourses, "Offering courses cannot be null");

        return result;
    }

    public boolean removeAllCourses(){
        //preconditions
        Preconditions.checkNotNull(offeringCourses, "Offering courses cannot be null");

        boolean result = false;
        offeringCourses.clear();
        if(offeringCourses.isEmpty()){
            result = true;
        }

        //postcondition
        Preconditions.checkState(offeringCourses.size() == 0, "Offering courses should be empty");

        return result;
    }

    public String showCourseInfo(Course course){
        //precondition
        Preconditions.checkNotNull(course, "Course cannot be null");
        Preconditions.checkNotNull(offeringCourses, "Offering courses cannot be null");

        String coureInfo = "";
        for (int i = 0; i < offeringCourses.size(); i++){
            coureInfo = offeringCourses.get(i).toString();
        }

        //postcondition
        Preconditions.checkNotNull(course, "Course cannot be null");
        Preconditions.checkNotNull(offeringCourses, "Offering courses cannot be null");

        return coureInfo;
    }

}
