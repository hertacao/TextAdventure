package util;

public enum Logic implements Token{
    YES {public String toString(){
        return "yes";
    }},
    NO {public String toString(){
        return "no";
    }}
}
