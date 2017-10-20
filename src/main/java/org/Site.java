package org;

public enum Site {
    ALL("/all"),
    NAME("/name/%s"),
    FULL_NAME("/name/%s?fullText=true");

    String s;

    Site(String s){
        this.s = s;
    }

    public String get(){
        return s;
    }

}
