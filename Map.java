import java.util.HashMap;

public class Map
{
    HashMap<String, Area> map;

    public Map()
    {
        map = new HashMap<>();
    }

    public void addArea(Area area)
    {
        map.put(area.getAreaNames().toLowerCase(), area);
    }

    
    public Area getArea(String areaName)
    {
        return map.get(areaName.toLowerCase());
    }
}
