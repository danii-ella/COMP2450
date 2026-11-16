package org.example.domain;
import com.google.common.base.Preconditions;

public class Door {
    final public DoorLocation doorLocation;

    public Door(DoorLocation doorLocation) {
        this.doorLocation = doorLocation;

        //postcondition
        checkDoor();
    }

    public void checkDoor(){
        //class invariants
        Preconditions.checkNotNull(doorLocation, "DoorLocation cannot be null");
    }
}
