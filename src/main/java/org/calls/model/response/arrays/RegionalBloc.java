package org.calls.model.response.arrays;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/**
 * Created by serjb on 29.11.2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "acronym",
        "name",
        "otherAcronyms",
        "otherNames"
})
public class RegionalBloc {

    @JsonProperty("acronym")
    public String acronym;
    @JsonProperty("name")
    public String name;
    @JsonProperty("otherAcronyms")
    public List<String> otherAcronyms = null;
    @JsonProperty("otherNames")
    public List<String> otherNames = null;
}

