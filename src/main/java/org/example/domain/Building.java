package org.example.domain;
import com.google.common.base.Preconditions;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Building {
    final public String buildingName;
    public ArrayList<Door> buildingDoors = new ArrayList<>();
    public Faculty buildingFaculty;

    public Building(String buildingName, Faculty buildingFaculty) {
        //preconditions
        Preconditions.checkNotNull(buildingName, "Building name cannot be null");
        Preconditions.checkState(!buildingName.isEmpty(), "Building name cannot be empty");
        Preconditions.checkNotNull(buildingFaculty, "Faculty cannot be null");

        this.buildingName = buildingName;
        this.buildingFaculty = buildingFaculty;
        this.buildingDoors.add(new Door(DoorLocation.FRONT));

        //postcondition
        checkBuilding();

    }

    public Building(String buildingName) {
        //preconditions
        Preconditions.checkNotNull(buildingName, "Building name cannot be null");
        Preconditions.checkState(!buildingName.isEmpty(), "Building name cannot be empty");

        this.buildingName = buildingName;
        this.buildingDoors.add(new Door(DoorLocation.FRONT));

        //postcondition
        checkBuilding();
    }

    public void addFaculty(Faculty newFaculty) {
        this.buildingFaculty = newFaculty;
    }

    public boolean addDoor(Door door) {
        //precondition
        checkBuilding();
        Preconditions.checkNotNull(door, "Door cannot be null");

        boolean result = false;

        for(int i = 0; i < this.buildingDoors.size(); i++) {
            if(buildingDoors.get(i).doorLocation == door.doorLocation) {
                return result;
            }
        }

        buildingDoors.add(door);
        result = true;

        //postcondtion
        checkBuilding();
        Preconditions.checkNotNull(door, "Door cannot be null");

        return result;
    }

    public boolean removeDoor(DoorLocation doorLocation) {
        //precondition
        checkBuilding();
        Preconditions.checkNotNull(doorLocation, "Door location cannot be null");

        boolean result = false;

        if(doorLocation != DoorLocation.FRONT){
            for(int i = 0; i < buildingDoors.size(); i++){
                if(buildingDoors.get(i).doorLocation == doorLocation){
                    buildingDoors.remove(i);
                    result = true;
                    break;
                }
            }
        }

        //postcondition
        checkBuilding();
        Preconditions.checkNotNull(doorLocation, "Door location cannot be null");

        return result;
    }

    public void checkBuilding() {
        Preconditions.checkNotNull(buildingName, "Building name cannot be null");
        Preconditions.checkState(!buildingName.isEmpty(), "Building name cannot be empty");
        Preconditions.checkState(!buildingDoors.isEmpty(), "Building doors cannot be 0");

    }
}
