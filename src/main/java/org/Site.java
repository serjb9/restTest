package org;

public enum Site {
    ALL("/all"),
    NAME("/name/%s"),
    FULL_NAME("/name/%s?fullText=true"),
    CODE("/alpha/%s"),
    LIST_OF_CODES("/alpha?codes=%s");

    String s;

    Site(String s){
        this.s = s;
    }

    public String get(){
        return s;
    }

}
