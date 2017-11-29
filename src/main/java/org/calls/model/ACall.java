package org.calls.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by serjb on 21.11.2017.
 */
public abstract class ACall {

    @Override
    public String toString() {

        String jsonToString = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            jsonToString = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonToString ;
    }
}
