package util;

public enum RelativeDirection implements Direction{
    RIGHT {public String toString(){
        return "right";
    }},
    LEFT {public String toString(){
        return "left";
    }},
    FRONT {public String toString(){
        return "front";
    }},
    BACK {public String toString(){
        return "back";
    }},
}
