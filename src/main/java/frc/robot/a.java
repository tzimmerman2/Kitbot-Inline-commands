package frc.robot;

//   public static final class ControllerMappingsConstants{
//     public static final class XboxConstants{
//       public enum Buttons {
        
//         kA(XboxController.Button.kA.value),
//         kB(XboxController.Button.kB.value),
//         kX(XboxController.Button.kX.value),
//         kY(XboxController.Button.kY.value),
//         kLeftBumper(XboxController.Button.kLeftBumper.value),
//         kRightBumper(XboxController.Button.kRightBumper.value),
//         // Xbox does not have a button for left trigger
//         // Xbox does not have a button for right trigger
//         kMinus(XboxController.Button.kBack.value),
//         kPlus(XboxController.Button.kStart.value),
//         kLeftStick(XboxController.Button.kLeftStick.value),
//         kRightStick(XboxController.Button.kRightStick.value),
//         kHome(XboxController.Button.kBack.value), // Xbox does not have a home button
//         kCapture(XboxController.Button.kStart.value); // Xbox does not have a capture button
    
//         private final int value;
    
//         Buttons(int value) {
//           this.value = value;
//         }
    
//         public int getValue() {
//           return value;
//         }
//       }
//       public enum Axes {
//         kLeftX(XboxController.Axis.kLeftX.value),
//         kLeftY(XboxController.Axis.kLeftY.value),
//         kRightX(XboxController.Axis.kRightX.value),
//         kRightY(XboxController.Axis.kRightY.value),
//         kLeftTrigger(XboxController.Axis.kLeftTrigger.value),
//         kRightTrigger(XboxController.Axis.kRightTrigger.value);
    
//         private final int value;
    
//         Axes(int value) {
//           this.value = value;
//         }
    
//         public int getValue() {
//           return value;
//         }
//       }
//       static Map<Buttons, Integer> ButtonsMap = new EnumMap<>(Buttons.class);
//       static {
//         for (Buttons button : Buttons.values()) {
//           ButtonsMap.put(button, button.getValue());
//         }
//       }
//       static Map<Axes, Integer> AxesMap = new EnumMap<>(Axes.class);
//       static {
//         for (Axes axis : Axes.values()) {
//           AxesMap.put(axis, axis.getValue());
//         }
//       }
//     }
//     public static final class SwitchConstants{
//       public enum Buttons {
//         kA(1),
//         kB(0),
//         kX(3),
//         kY(2),
//         kLeftBumper(4),
//         kRightBumper(5),
//         kLeftTriggerButton(6),
//         kRightTriggerButton(7),
//         kMinus(8),
//         kPlus(9),
//         kLeftStick(10),
//         kRightStick(11),
//         kHome(12),
//         kCapture(13);
    
//         private final int value;
    
//         Buttons(int value) {
//           this.value = value;
//         }
    
//         public int getValue() {
//           return value;
//         }
//       }
//       public enum Axes {
//         kLeftX(0),
//         kLeftY(1),
//         kRightX(2),
//         kRightY(3);
    
//         private final int value;
    
//         Axes(int value) {
//           this.value = value;
//         }
    
//         public int getValue() {
//           return value;
//         }
//       }
//       static Map<String, Integer> ButtonsMap = new HashMap<>();
//       static {
//         for (Buttons button : Buttons.values()) {
//           ButtonsMap.put(button.toString(), button.getValue());
//         }
//       }
//       static Map<String, Integer> AxesMap = new HashMap<>();
//       static {
//         for (Axes axis : Axes.values()) {
//           AxesMap.put(axis.toString(), axis.getValue());
//         }
//       }
//     }
//     public static final class JoystickConstants{
//       public enum Buttons {
//         kA(11),
//         kB(12),
//         kX(3),
//         kY(2),
//         kLeftBumper(4),
//         kRightBumper(1),
//         kLeftTriggerButton(6),
//         kRightTriggerButton(7),
//         kMinus(8),
//         kPlus(9),
//         kLeftStick(10),
//         kRightStick(11),
//         kHome(12),
//         kCapture(13);
    
//         private final int value;
    
//         Buttons(int value) {
//           this.value = value;
//         }
    
//         public int getValue() {
//           return value;
//         }
//       }
//       public enum Axes {
//         kLeftX(Joystick.AxisType.kX.value),
//         kLeftY(Joystick.AxisType.kY.value),
//         kRightX(Joystick.AxisType.kZ.value),
//         kRightY(Joystick.AxisType.kTwist.value),
//         kThrottle(Joystick.AxisType.kThrottle.value);
    
//         private final int value;
    
//         Axes(int value) {
//           this.value = value;
//         }
    
//         public int getValue() {
//           return value;
//         }
//       }
//       static Map<String, Integer> ButtonsMap = new HashMap<>();
//       static {
//         for (Buttons button : Buttons.values()) {
//           ButtonsMap.put(button.toString(), button.getValue());
//         }
//       }
//       static Map<String, Integer> AxesMap = new HashMap<>();
//       static {
//         for (Axes axis : Axes.values()) {
//           AxesMap.put(axis.toString(), axis.getValue());
//         }
//       }
//     }
//     public static final class Joycon2Constants{
//       public enum Buttons {
//         kA(5),
//         kB(6),
//         kX(7),
//         kY(8),
//         kLeftBumper(3),
//         kRightBumper(4),
//         kLeftTriggerButton(1),
//         kRightTriggerButton(2),
//         kMinus(14),
//         kPlus(13),
//         kLeftStick(19),
//         kRightStick(20),
//         kHome(21),
//         kCapture(22),
//         kPovRight(9),
//         kPovDown(10),
//         kPovLeft(12),
//         kPovUp(11),
//         kMapableButton1(17),
//         kMapableButton2(18),
//         kMapableButton3(15),
//         kMapableButton4(16);
    
//         private final int value;
    
//         Buttons(int value) {
//           this.value = value;
//         }
    
//         public int getValue() {
//           return value;
//         }
//       }
//       public enum Axes {
//         kLeftX(0),
//         kLeftY(1),
//         kRightX(2),
//         kRightY(3);
    
//         private final int value;
    
//         Axes(int value) {
//           this.value = value;
//         }
    
//         public int getValue() {
//           return value;
//         }
//       }
//       static Map<Buttons, Integer> ButtonsMap = new EnumMap<>(Buttons.class);
//       static {
//         for (Buttons button : Buttons.values()) {
//           ButtonsMap.put(button, button.getValue());
//         }
//       }
//       static Map<Axes, Integer> AxesMap = new EnumMap<>(Axes.class);
//       static {
//         for (Axes axis : Axes.values()) {
//             AxesMap.put(axis, axis.getValue());
//         }
//       }
//     }

//   }
