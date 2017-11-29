package org;

public enum Site {
    ALL("/all"),
    NAME("/name/%s"),
    FULL_TEXT("/name/%s?fullText=true"),
    CODE("/alpha/%s"),
    LIST_OF_CODES("/alpha?codes=%s"),
    CURRENCY("/currency/%s"),
    LANGUAGE("/lang/%s");

    String s;

    Site(String s){
        this.s = s;
    }

    public String get(){
        return s;
    }

}
