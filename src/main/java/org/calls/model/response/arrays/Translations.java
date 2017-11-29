package org.calls.model.response.arrays;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


/**
 * Created by serjb on 29.11.2017.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "de",
        "es",
        "fr",
        "ja",
        "it",
        "br",
        "pt",
        "nl",
        "hr",
        "fa"
})
public class Translations {

    @JsonProperty("de")
    public String de;
    @JsonProperty("es")
    public String es;
    @JsonProperty("fr")
    public String fr;
    @JsonProperty("ja")
    public String ja;
    @JsonProperty("it")
    public String it;
    @JsonProperty("br")
    public String br;
    @JsonProperty("pt")
    public String pt;
    @JsonProperty("nl")
    public String nl;
    @JsonProperty("hr")
    public String hr;
    @JsonProperty("fa")
    public String fa;


}