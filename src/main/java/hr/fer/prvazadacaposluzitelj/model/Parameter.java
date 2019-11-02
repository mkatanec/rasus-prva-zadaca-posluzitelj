package hr.fer.prvazadacaposluzitelj.model;

import java.util.HashMap;
import java.util.Map;

public enum Parameter {
    TEMPERATURE("Temperature"),
    PRESSURE("Pressure"),
    HUMIDITY("Humidity"),
    CO("CO"),
    NO2("NO2"),
    SO2("SO2");

    private static final Map<String, Parameter> LOOKUP = new HashMap<>();

    static {
        for(final Parameter parameter : Parameter.values()){
            LOOKUP.put(parameter.getCode(), parameter);
        }
    }

    private String code;

    Parameter(final String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
