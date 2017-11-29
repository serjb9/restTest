package org.calls.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.calls.model.ACall;
import org.calls.model.response.arrays.Currency;
import org.calls.model.response.arrays.Language;
import org.calls.model.response.arrays.Translations;

import java.util.List;

/**
 * Created by serjb on 21.11.2017.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "name",
        "topLevelDomain",
        "alpha2Code",
        "alpha3Code",
        "callingCodes",
        "capital",
        "altSpellings",
        "region",
        "subregion",
        "population",
        "latlng",
        "demonym",
        "area",
        "gini",
        "timezones",
        "borders",
        "nativeName",
        "numericCode",
        "currencies",
        "languages",
        "translations",
        "flag",
        "regionalBlocs",
        "cioc"
})
public class FullTextResponse extends ACall {

    @JsonProperty("name")
    public String name;
    @JsonProperty("topLevelDomain")
    public List<String> topLevelDomain = null;
    @JsonProperty("alpha2Code")
    public String alpha2Code;
    @JsonProperty("alpha3Code")
    public String alpha3Code;
    @JsonProperty("callingCodes")
    public List<String> callingCodes = null;
    @JsonProperty("capital")
    public String capital;
    @JsonProperty("altSpellings")
    public List<String> altSpellings = null;
    @JsonProperty("region")
    public String region;
    @JsonProperty("subregion")
    public String subregion;
    @JsonProperty("population")
    public Integer population;
    @JsonProperty("latlng")
    public List<Float> latlng = null;
    @JsonProperty("demonym")
    public String demonym;
    @JsonProperty("area")
    public Float area;
    @JsonProperty("gini")
    public Object gini;
    @JsonProperty("timezones")
    public List<String> timezones = null;
    @JsonProperty("borders")
    public List<Object> borders = null;
    @JsonProperty("nativeName")
    public String nativeName;
    @JsonProperty("numericCode")
    public String numericCode;
    @JsonProperty("currencies")
    public List<Currency> currencies = null;
    @JsonProperty("languages")
    public List<Language> languages = null;
    @JsonProperty("translations")
    public Translations translations;
    @JsonProperty("flag")
    public String flag;
    @JsonProperty("regionalBlocs")
    public List<Object> regionalBlocs = null;
    @JsonProperty("cioc")
    public String cioc;

    public String getCapital() {
        return capital;
    }

    public String getName() {
        return name;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public Translations getTranslations() {
        return translations;
    }
}
