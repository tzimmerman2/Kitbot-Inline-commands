
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
// Removed unused import
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.event.EventLoop;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;



public class SwappableController {
  /** Creates a new SwappableController. */
  private int port;

  public static final class ControllerMappingsConstants{
    public static final class XboxConstants{
      public enum Buttons {
        
        kA(XboxController.Button.kA.value),
        kB(XboxController.Button.kB.value),
        kX(XboxController.Button.kX.value),
        kY(XboxController.Button.kY.value),
        kLeftBumper(XboxController.Button.kLeftBumper.value),
        kRightBumper(XboxController.Button.kRightBumper.value),
        // Xbox does not have a button for left trigger
        // Xbox does not have a button for right trigger
        kMinus(XboxController.Button.kBack.value),
        kPlus(XboxController.Button.kStart.value),
        kLeftStick(XboxController.Button.kLeftStick.value),
        kRightStick(XboxController.Button.kRightStick.value),
        kHome(XboxController.Button.kBack.value), // Xbox does not have a home button
        kCapture(XboxController.Button.kStart.value); // Xbox does not have a capture button
    
        private final int value;
    
        Buttons(int value) {
          this.value = value;
        }
    
        public int getValue() {
          return value;
        }
      }
      public enum Axes {
        kLeftX(XboxController.Axis.kLeftX.value),
        kLeftY(XboxController.Axis.kLeftY.value),
        kRightX(XboxController.Axis.kRightX.value),
        kRightY(XboxController.Axis.kRightY.value),
        kLeftTrigger(XboxController.Axis.kLeftTrigger.value),
        kRightTrigger(XboxController.Axis.kRightTrigger.value);
    
        private final int value;
    
        Axes(int value) {
          this.value = value;
        }
    
        public int getValue() {
          return value;
        }
      }
      static Map<Buttons, Integer> ButtonsMap = new EnumMap<>(Buttons.class);
      static {
        for (Buttons button : Buttons.values()) {
          ButtonsMap.put(button, button.getValue());
        }
      }
      static Map<Axes, Integer> AxesMap = new EnumMap<>(Axes.class);
      static {
        for (Axes axis : Axes.values()) {
          AxesMap.put(axis, axis.getValue());
        }
      }
    }
    public static final class SwitchConstants{
      public enum Buttons {
        kA(1),
        kB(0),
        kX(3),
        kY(2),
        kLeftBumper(4),
        kRightBumper(5),
        kLeftTriggerButton(6),
        kRightTriggerButton(7),
        kMinus(8),
        kPlus(9),
        kLeftStick(10),
        kRightStick(11),
        kHome(12),
        kCapture(13);
    
        private final int value;
    
        Buttons(int value) {
          this.value = value;
        }
    
        public int getValue() {
          return value;
        }
      }
      public enum Axes {
        kLeftX(0),
        kLeftY(1),
        kRightX(2),
        kRightY(3);
    
        private final int value;
    
        Axes(int value) {
          this.value = value;
        }
    
        public int getValue() {
          return value;
        }
      }
      static Map<Buttons, Integer> ButtonsMap = new EnumMap<>(Buttons.class);
      static {
        for (Buttons button : Buttons.values()) {
          ButtonsMap.put(button, button.getValue());
        }
      }
      static Map<Axes, Integer> AxesMap = new EnumMap<>(Axes.class);
      static {
        for (Axes axis : Axes.values()) {
          AxesMap.put(axis, axis.getValue());
        }
      }
    }
    public static final class JoystickConstants{
      public enum Buttons {
        kA(11),
        kB(12),
        kX(3),
        kY(2),
        kLeftBumper(4),
        kRightBumper(1),
        kLeftTriggerButton(6),
        kRightTriggerButton(7),
        kMinus(8),
        kPlus(9),
        kLeftStick(10),
        kRightStick(11),
        kHome(12),
        kCapture(13);
    
        private final int value;
    
        Buttons(int value) {
          this.value = value;
        }
    
        public int getValue() {
          return value;
        }
      }
      public enum Axes {
        kLeftX(Joystick.AxisType.kX.value),
        kLeftY(Joystick.AxisType.kY.value),
        kRightX(Joystick.AxisType.kZ.value),
        kRightY(Joystick.AxisType.kTwist.value),
        kThrottle(Joystick.AxisType.kThrottle.value);
    
        private final int value;
    
        Axes(int value) {
          this.value = value;
        }
    
        public int getValue() {
          return value;
        }
      }
      static Map<Buttons, Integer> ButtonsMap = new EnumMap<>(Buttons.class);
      static {
        for (Buttons button : Buttons.values()) {
          ButtonsMap.put(button, button.getValue());
        }
      }
      static Map<Axes, Integer> AxesMap = new EnumMap<>(Axes.class);
      static {
      for (Axes axis : Axes.values()) {
        AxesMap.put(axis, axis.getValue());
      }
    }
    }
    public static final class Joycon2Constants{
      public enum Buttons {
        kA(5),
        kB(6),
        kX(7),
        kY(8),
        kLeftBumper(3),
        kRightBumper(4),
        kLeftTriggerButton(1),
        kRightTriggerButton(2),
        kMinus(14),
        kPlus(13),
        kLeftStick(19),
        kRightStick(20),
        kHome(21),
        kCapture(22),
        kPovRight(9),
        kPovDown(10),
        kPovLeft(12),
        kPovUp(11),
        kMapableButton1(17),
        kMapableButton2(18),
        kMapableButton3(15),
        kMapableButton4(16);
    
        private final int value;
    
        Buttons(int value) {
          this.value = value;
        }
    
        public int getValue() {
          return value;
        }
      }
      public enum Axes {
        kLeftX(0),
        kLeftY(1),
        kRightX(2),
        kRightY(3);
    
        private final int value;
    
        Axes(int value) {
          this.value = value;
        }
    
        public int getValue() {
          return value;
        }
      }
      static Map<Buttons, Integer> ButtonsMap = new EnumMap<>(Buttons.class);
      static {
        for (Buttons button : Buttons.values()) {
          ButtonsMap.put(button, button.getValue());
        }
      }
      static Map<Axes, Integer> AxesMap = new EnumMap<>(Axes.class);
      static {
        for (Axes axis : Axes.values()) {
            AxesMap.put(axis, axis.getValue());
        }
      }
    }

  }

  public enum m_buttons {
    kA, kB, kX, kY, kLeftBumper, kRightBumper, kLeftTriggerButton, kRightTriggerButton,
    kMinus, kPlus, kLeftStick, kRightStick, kHome, kCapture, kMapableButton1, kMapableButton2,
    kMapableButton3, kMapableButton4, kPovUp, kPovDown, kPovLeft, kPovRight
  }
  public enum m_axes {
    kLeftX, kLeftY, kRightX, kRightY, kLeftTrigger, kRightTrigger, kThrottle
  }
  Map<m_buttons, Integer> m_ButtonsMap = new HashMap<>();
  Map<m_axes, Integer> m_AxesMap = new HashMap<>();
  


  

  private CommandGenericHID activeController;

  public SwappableController(int port) {
    this.port = port;
    handleControllerChange();
    
  }

  public void handleControllerChange() {
    
    System.out.println("Driverstation connected: " +DriverStation.waitForDsConnection(10));
    for (int i = 0; i < DriverStation.kJoystickPorts; i++) {
      System.out.println("Joystick connected: "+ DriverStation.isJoystickConnected(i));
    }
    System.out.println("Joystick connected: "+ DriverStation.isJoystickConnected(port));
    System.out.println("Handling controller change for port: " + port);
    if (DriverStation.getJoystickIsXbox(port)){
      this.activeController = (CommandXboxController) new CommandXboxController(port);
      remapButtons(ControllerMappingsConstants.XboxConstants.ButtonsMap, ControllerMappingsConstants.XboxConstants.AxesMap);
    } else if (DriverStation.getJoystickName(port).toLowerCase().contains("extreme 3d pro")) {
      this.activeController = (CommandJoystick) new CommandJoystick(port);
      remapButtons(ControllerMappingsConstants.JoystickConstants.ButtonsMap, ControllerMappingsConstants.JoystickConstants.AxesMap);
    } else if (DriverStation.getJoystickName(port).toLowerCase().contains("wireless gamepad")) {
      this.activeController = (CommandGenericHID) new CommandGenericHID(port);
      remapButtons(ControllerMappingsConstants.SwitchConstants.ButtonsMap, ControllerMappingsConstants.SwitchConstants.AxesMap);
    } else if (DriverStation.getJoystickName(port).toLowerCase().contains("vjoy device")) {
      this.activeController = (CommandGenericHID) new CommandGenericHID(port);
      remapButtons(ControllerMappingsConstants.Joycon2Constants.ButtonsMap, ControllerMappingsConstants.Joycon2Constants.AxesMap);
    } else{
      System.out.println("Controller type not recognized or unsupported. Using generic HID controller.");
      System.out.println("Joystick Name: " + DriverStation.getJoystickName(port));
      System.out.println("Joystick Type: " + DriverStation.getJoystickType(port));
      System.out.println("Joystick Is Xbox: " + DriverStation.getJoystickIsXbox(port));
      this.activeController = (CommandGenericHID) new CommandGenericHID(port); // Handle other types or invalid cases
    }
    SmartDashboard.putString("controller", activeController.toString());
  }

  CommandGenericHID getCommandHid(){
    return activeController;
  }

  void remapButtons(Map<> buttonMapping, Map<? extends Enum<?>, Integer> axisMapping) {
    ControllerMappingsConstants.Joycon2Constants.
    System.out.println("buttons remaped");
    m_ButtonsMap.clear();
    m_AxesMap.clear();

    // Map buttons
    for (m_buttons button : buttonMapping.keySet()) {
        try {
            m_ButtonsMap.put((m_buttons) button, buttonMapping.get(button));
        } catch (IllegalArgumentException e) {
            System.out.println("Button " + button.name() + " does not exist in this controller mapping.");
        }
    }

    // Map axes
    for (Enum<?> axis : axisMapping.keySet()) {
        try {
            m_AxesMap.put((m_axes) axis, axisMapping.get(axis));
        } catch (IllegalArgumentException e) {
            System.out.println("Axis " + axis.name() + " does not exist in this controller mapping.");
        }
    }
}
  //only use the pov methods from this class, not the pov methods from the CommandGenericHID class as they won't work with all controllers
  public CommandGenericHID getActiveController() {
    return activeController;
  }

  public int getPort() {
    return port;
  }

  public boolean controlerIsValid(){
    if (activeController == null || !activeController.isConnected()) {
      return false;
    } else {
      return true;
    }
  }

  public boolean isConnected() {
    return activeController != null && activeController.isConnected();
  }

  

  public double getX() {
    return activeController.getHID().getRawAxis(m_AxesMap.getOrDefault(m_axes.kLeftX, 24));
  }

  public double getY() {
    return activeController.getHID().getRawAxis(m_AxesMap.getOrDefault(m_axes.kLeftY, 24));
  }

  public double getZ() {
    return activeController.getHID().getRawAxis(m_AxesMap.getOrDefault(m_axes.kRightX, 24));
  }

  public Trigger a(){
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kA, 24));
  }

  public Trigger a(EventLoop eventLoop) {
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kA, 24), eventLoop);
  }

  public Trigger b(){
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kB, 24));
  }

  public Trigger b(EventLoop eventLoop) {
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kB, 24), eventLoop);
  }

  public Trigger x(){
   return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kX, 24));
  }

  public Trigger x(EventLoop eventLoop) {
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kX, 24), eventLoop);
  }

  public Trigger y(){
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kY, 24));
  }

  public Trigger y(EventLoop eventLoop) {
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kY, 24), eventLoop);
  }

  public Trigger leftBumper() {
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kLeftBumper, 24));
  }

  public Trigger leftBumper(EventLoop eventLoop) {
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kLeftBumper, 24), eventLoop);
  }
  public Trigger rightBumper() {
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kRightBumper, 24));
  }
  public Trigger rightBumper(EventLoop eventLoop) {
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kRightBumper, 24), eventLoop);
  }
  public Trigger leftTrigger() {
    if (m_AxesMap.getOrDefault(m_axes.kLeftTrigger, null) == null) {
      return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kLeftTriggerButton, 24));
    } else {
      return new Trigger(() -> 0.5<activeController.getHID().getRawAxis(m_AxesMap.getOrDefault(m_axes.kLeftTrigger, 24)));
    }
  }

  public Trigger leftTrigger(double threshold) {
    if (m_AxesMap.getOrDefault(m_axes.kLeftTrigger, null) == null) {
      return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kLeftTriggerButton, 24));
    } else {
      return new Trigger(() -> threshold<activeController.getHID().getRawAxis(m_AxesMap.getOrDefault(m_axes.kLeftTrigger, 24)));
    }
  }

  public Trigger leftTrigger(double threshold, EventLoop eventLoop) {
    if (m_AxesMap.getOrDefault(m_axes.kLeftTrigger, null) == null) {
      return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kLeftTriggerButton, 24), eventLoop);
    } else {
      return new Trigger(eventLoop, () -> threshold<activeController.getHID().getRawAxis(m_AxesMap.getOrDefault(m_axes.kLeftTrigger, 24)));
    }
  }

  public Trigger rightTrigger() {
    if (m_AxesMap.getOrDefault(m_axes.kRightTrigger, null) == null) {
      return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kRightTriggerButton, 24));
    } else {
      return new Trigger(() -> 0.5<activeController.getHID().getRawAxis(m_AxesMap.getOrDefault(m_axes.kRightTrigger, 24)));
    }
  }

  public Trigger rightTrigger(double threshold) {
    if (m_AxesMap.getOrDefault(m_axes.kRightTrigger, null) == null) {
      return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kRightTriggerButton, 24));
    } else {
      return new Trigger(() -> threshold<activeController.getHID().getRawAxis(m_AxesMap.getOrDefault(m_axes.kRightTrigger, 24)));
    }
  }

  public Trigger rightTrigger(double threshold, EventLoop eventLoop) {
    if (m_AxesMap.getOrDefault(m_axes.kRightTrigger, null) == null) {
      return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kRightTriggerButton, 24), eventLoop);
    } else {
      return new Trigger(eventLoop, () -> threshold<activeController.getHID().getRawAxis(m_AxesMap.getOrDefault(m_axes.kRightTrigger, 24)));
    }
  }

  public Trigger minus(){
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kMinus, 24));
  }

  public Trigger minus(EventLoop eventLoop){
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kMinus, 24), eventLoop);
  }

  public Trigger plus(){
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kPlus, 24));
  }

  public Trigger plus(EventLoop eventLoop){
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kPlus, 24), eventLoop);
  }

  public Trigger leftStick(){
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kLeftStick, 24));
  }
  
  public Trigger leftStick(EventLoop eventLoop){
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kLeftStick, 24), eventLoop);
  }
  public Trigger rightStick(){
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kRightStick, 24));
  }
  public Trigger rightStick(EventLoop eventLoop){
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kRightStick, 24), eventLoop);
  }

  public double getRightX() {
    //Throttle on the joystick is the right X axis 
    if (m_AxesMap.getOrDefault(m_axes.kRightX, null) == null) {
      return activeController.getHID().getRawAxis(m_AxesMap.getOrDefault(m_axes.kThrottle, 24));
    } else {
      return activeController.getHID().getRawAxis(m_AxesMap.getOrDefault(m_axes.kRightX, 24));
    }
  }

  public double getRightTriggerAxis(){
    if (m_AxesMap.getOrDefault(m_axes.kRightTrigger, null) == null) {
      return (activeController.getHID().getRawButton(m_ButtonsMap.getOrDefault(m_buttons.kRightTriggerButton, 24)) ? 1.0 : 0.0);
    }
    return activeController.getHID().getRawAxis(m_AxesMap.getOrDefault(m_axes.kRightTrigger, 24));
  }

  public double getLeftTriggerAxis(){
    if (m_AxesMap.getOrDefault(m_axes.kLeftTrigger, null) == null) {
      return (activeController.getHID().getRawButton(m_ButtonsMap.getOrDefault(m_buttons.kLeftTriggerButton, 24)) ? 1.0 : 0.0);
    }
    return activeController.getHID().getRawAxis(m_AxesMap.getOrDefault(m_axes.kLeftTrigger, 24));
  }
  
  public Trigger home() {
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kHome, 24));
  }
  public Trigger home(EventLoop eventLoop) {
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kHome, 24), eventLoop);
  }
  public Trigger capture() {
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kCapture, 24));
  }
  public Trigger capture(EventLoop eventLoop) {
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kCapture, 24), eventLoop);
  }
  public Trigger mapableButton1() {
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kMapableButton1, 24));
  }
  public Trigger mapableButton1(EventLoop eventLoop) {
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kMapableButton1, 24), eventLoop);
  }
  public Trigger mapableButton2() {
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kMapableButton2, 24));
  }
  public Trigger mapableButton2(EventLoop eventLoop) {
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kMapableButton2, 24), eventLoop);
  }
  public Trigger mapableButton3() {
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kMapableButton3, 24));
  }
  public Trigger mapableButton3(EventLoop eventLoop) {
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kMapableButton3, 24), eventLoop);
  }
  public Trigger mapableButton4() {
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kMapableButton4, 24));
  }
  public Trigger mapableButton4(EventLoop eventLoop) {
    return activeController.button(m_ButtonsMap.getOrDefault(m_buttons.kMapableButton4, 24), eventLoop);
  }
  public double getLeftX() {
    return activeController.getHID().getRawAxis(m_AxesMap.getOrDefault(m_axes.kLeftX, 24));
  }
  public double getLeftY() {
    return activeController.getHID().getRawAxis(m_AxesMap.getOrDefault(m_axes.kLeftY, 24));
  }
  public double getRightY() {
    return activeController.getHID().getRawAxis(m_AxesMap.getOrDefault(m_axes.kRightY, 24));
  }

  public double getThrottle() {
    return activeController.getHID().getRawAxis(m_AxesMap.getOrDefault(m_axes.kThrottle, 24));
  }

  public Trigger povUp() {
    return pov(0);
  }

  public Trigger povUp(EventLoop eventLoop) {
    return pov(0, eventLoop);
  }

  public Trigger povLeft() {
    return pov(270);
  }

  public Trigger povLeft(EventLoop eventLoop) {
    return pov(270, eventLoop);
  }

  public Trigger povDown() {
    return pov(180);
  }

  public Trigger povDown(EventLoop eventLoop) {
    return pov(180, eventLoop);
  }

  public Trigger povRight() {
    return pov(90);
  }

  public Trigger povRight(EventLoop eventLoop) {
    return pov(90, eventLoop);
  }

  public Trigger povUpLeft(){
    return pov(315);
  }

  public Trigger povUpLeft(EventLoop eventLoop){
    return pov(315, eventLoop);
  }

  public Trigger povUpRight(){
    return pov(45);
  }

  public Trigger povUpRight(EventLoop eventLoop){
    return pov(45, eventLoop);
  }

  public Trigger povDownLeft(){
    return pov(225);
  }

  public Trigger povDownLeft(EventLoop eventLoop){
    return pov(225, eventLoop);
  }

  public Trigger povDownRight(){
    return pov(135);
  }

  public Trigger povDownRight(EventLoop eventLoop){
    return pov(135, eventLoop);
  }

  public Trigger povCenter() {
    return pov(-1);
  }

  public Trigger povCenter(EventLoop eventLoop) {
    return pov(-1, eventLoop);
  }
  
  public double pov(){
    //System.out.println("pov Count" + activeController.getHID().getPOVCount());
    if (activeController.getHID().getPOVCount() > 0 || !isConnected()){
      //System.out.println("has pov");
      return activeController.getHID().getPOV();
    }
    System.out.println("doesn't have pov");
    if (activeController.getHID().getRawButton(m_ButtonsMap.getOrDefault(m_buttons.kPovUp, null)) && 
        activeController.getHID().getRawButton(m_ButtonsMap.getOrDefault(m_buttons.kPovLeft, null))) {
      return 315;
    } else if (activeController.getHID().getRawButton(m_ButtonsMap.getOrDefault(m_buttons.kPovUp, null)) && 
               activeController.getHID().getRawButton(m_ButtonsMap.getOrDefault(m_buttons.kPovRight, null))) {
      return 45;
    } else if (activeController.getHID().getRawButton(m_ButtonsMap.getOrDefault(m_buttons.kPovDown, null)) && 
               activeController.getHID().getRawButton(m_ButtonsMap.getOrDefault(m_buttons.kPovLeft, null))) {
      return 225;
    } else if (activeController.getHID().getRawButton(m_ButtonsMap.getOrDefault(m_buttons.kPovDown, null)) && 
               activeController.getHID().getRawButton(m_ButtonsMap.getOrDefault(m_buttons.kPovRight, null))) {
      return 135;
    } else if (activeController.getHID().getRawButton(m_ButtonsMap.getOrDefault(m_buttons.kPovUp, null))) {
      return 0;
    } else if (activeController.getHID().getRawButton(m_ButtonsMap.getOrDefault(m_buttons.kPovDown, null))) {
      return 180;
    } else if (activeController.getHID().getRawButton(m_ButtonsMap.getOrDefault(m_buttons.kPovLeft, null))) {
      return 270;
    } else if (activeController.getHID().getRawButton(m_ButtonsMap.getOrDefault(m_buttons.kPovRight, null))) {
      return 90;
    }
    return -1;
  }

  public Trigger pov(int angle) {
    return new Trigger(() -> pov() == angle);
  }

  public Trigger pov(int angle, EventLoop eventLoop) {
    return new Trigger(eventLoop, () -> pov() == angle);
  }
}
