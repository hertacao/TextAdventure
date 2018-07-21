package util;

public enum CardinalDirection implements Direction{
    NORTH {public String toString(){
        return "north";
    }},
    NORTHEAST {public String toString(){
        return "northeast";
    }},
    EAST {public String toString(){
        return "east";
    }},
    SOUTHEAST {public String toString(){
        return "southeast";
    }},
    SOUTH {public String toString(){
        return "south";
    }},
    SOUTHWEST {public String toString(){
        return "southwest";
    }},
    WEST {public String toString(){
        return "west";
    }},
    NORTHWEST {public String toString(){
        return "northwest";
    }},
}
