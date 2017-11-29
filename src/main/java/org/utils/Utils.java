package org.utils;

import java.io.IOException;
import java.util.Properties;

public class Utils {

    private Properties props;

    public Utils(){
        initProps();
    }

    private void initProps(){
        props = new Properties();
        try {
            props.load(this.getClass().getClassLoader().getResourceAsStream("prop.properties"));
        } catch (IOException e) {
//            e.printStackTrace();
            e.getCause().getLocalizedMessage();
        }
    }

    public String getEndpoint() {
        return props.getProperty("endpoint");
    }
}
