import java.util.ArrayList;

public class Area
{
    String areaName;
    String description;
    ArrayList<String> newArea;
   
    public Area(String areaName, String description)
    {
        this.newArea = new ArrayList<String>();
        this.areaName = areaName;
        this.description = description;
    }
 
    public void setNames(String areaName){
        this.areaName = areaName;
    }

    public String getAreaNames(){
        return this.areaName;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

   
    public void addNewArea(String newAreas)
    {
        newArea.add(newAreas);
    }

    public String listNewAreas(){
        String listNewAreas = "";
        for (int i = 0; i < newArea.size(); i++){
            listNewAreas+= String.format(newArea.get(i) + " \n");
        }
        return listNewAreas;
    }

    public String toString()
    {
        String stringND;

        stringND = getAreaNames() + ": " + getDescription();
        stringND += "\n\nNew:\n" + listNewAreas();
        
        return stringND;

    }
}
