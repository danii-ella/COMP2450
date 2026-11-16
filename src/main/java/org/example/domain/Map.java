package org.example.domain;
import com.google.common.base.Preconditions;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Map {
    final public String mapName;
    public ArrayList<String> buildingsOnMap = new ArrayList<>();
    public Building [][] mapped = new Building[10][10];

    public Map(String mapName) {
        this.mapName = mapName;

        //postconditions
        checkMap();
    }

    public boolean addBuilding(Building building) {
        //preconditions
        Preconditions.checkNotNull(building, "Building cannot be null");
        checkMap();

        boolean result = false;
        //logic for adding a building goes here
        for (int i1 = 1; i1 < mapped.length; i1+=2) {
            for(int i2 = 1; i2 < mapped[i1].length; i2+=2){
                if(mapped[i1][i2] == null){
                    mapped[i1][i2] = building;
                    this.buildingsOnMap.add(building.buildingName);
                    result = true;
                    break;
                }
            }
            if(result){
                break;
            }
        }

        //postcondition
        checkMap();
        Preconditions.checkNotNull(building, "building cannot be null");

        return result;
    }
    public boolean removeBuilding(Building building) {
        //precondition
        Preconditions.checkNotNull(building, "building cannot be null");
        checkMap();

        boolean result = false;
        //logic for removing a building goes here
        for(int i1 = 1; i1 < mapped.length; i1+=2){
            for(int i2 = 1; i2 < mapped[i1].length; i2+=2){
                if(mapped[i1][i2] == building){
                    mapped[i1][i2] = null;
                    this.buildingsOnMap.remove(building.buildingName);
                    result = true;
                    break;
                }
            }
        }

        //postcondition
        checkMap();
        Preconditions.checkNotNull(building, "building cannot be null");

        return result;
    }
    public boolean removeAllBuildings() {
        //precondition
        checkMap();

        boolean result = false;
        this.buildingsOnMap.clear();
        //logic for clearing all buildings go here
        for(int i1 = 1; i1 < mapped.length; i1+=2){
            for(int i2 = 1; i2 < mapped[i1].length; i2+=2){
                if(mapped[i1][i2] != null){
                    mapped[i1][i2] = null;
                }
            }
        }

        result = true;

        //for loop to ensure the array is empty
        for(int i1 = 1; i1 < mapped.length; i1+=2){
            for(int i2 = 1; i2 < mapped[i1].length; i2+=2){
                if(mapped[i1][i2] != null){
                    result = false;
                }
            }
        }

        //postcondition
        checkMap();
        Preconditions.checkState(buildingsOnMap.isEmpty(), "Buildings on map should be empty");

        return result;
    }

    public Building getBuilding(String buildingName) {
        for(int i = 0; i < mapped.length; i++){
            for(int j = 0; j < mapped[i].length; j++){
                if((mapped[i][j] != null) && (buildingName.equals(mapped[i][j].buildingName))){
                    return mapped[i][j];
                }
            }
        }
        return null;
    }

    public String showMap(){
        String mapVisual = "";
        Building mappedBuilding;
        for(int i = 0; i < mapped.length; i++){
            for(int j = 0; j < mapped[i].length; j++){
                mappedBuilding = mapped[i][j];

                if(mappedBuilding != null){

                    if(mappedBuilding.buildingFaculty.facultyName == FacultyName.SCIENCE){
                        mapVisual += "SCI";

                    }else if(mappedBuilding.buildingFaculty.facultyName == FacultyName.ART){
                        mapVisual += "ART";

                    }else if(mappedBuilding.buildingFaculty.facultyName == FacultyName.BUSINESS){
                        mapVisual += "BUS";

                    }else if(mappedBuilding.buildingFaculty.facultyName == FacultyName.MUSIC){
                        mapVisual += "MUS";

                    }else if(mappedBuilding.buildingFaculty.facultyName == FacultyName.ARCHITECTURE){
                        mapVisual += "ARC";

                    }

                }else{
                    mapVisual += "*";
                }

            }
           mapVisual += "\n";
        }
        return mapVisual;
    }

    public void checkMap(){
        Preconditions.checkNotNull(this.mapName, "Map cannot be null");
        Preconditions.checkState(!this.mapName.isEmpty(), "Map name cannot be empty");
    }
}
