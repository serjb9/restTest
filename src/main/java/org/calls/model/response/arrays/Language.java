package org.calls.model.response.arrays;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by serjb on 29.11.2017.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "iso639_1",
        "iso639_2",
        "name",
        "nativeName"
})
public class Language {

    @JsonProperty("iso639_1")
    public String iso6391;
    @JsonProperty("iso639_2")
    public String iso6392;
    @JsonProperty("name")
    public String name;
    @JsonProperty("nativeName")
    public String nativeName;

    public String getIso6391() {
        return iso6391;
    }

    public String getIso6392() {
        return iso6392;
    }

    public String getName() {
        return name;
    }

    public String getNativeName() {
        return nativeName;
    }
}
