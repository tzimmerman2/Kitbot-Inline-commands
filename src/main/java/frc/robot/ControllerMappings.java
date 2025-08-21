package frc.robot;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ControllerMappings {
    public Map<String, Controller> ControllerMappings;

    public static class Controller {
        @JsonProperty("ReadableName") // Map the "ReadableName" property in JSON to this field
        public String Name;
        
        public Map<String, Integer> Buttons;
        public Map<String, Integer> Axes;
    }
}