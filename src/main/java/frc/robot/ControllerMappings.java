package frc.robot;

import java.util.Map;

public class ControllerMappings {
    public Map<String, Controller> ControllerMappings;

    public static class Controller {
        public Map<String, Integer> Buttons;
        public Map<String, Integer> Axes;
    }
}