// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.RollerConstants;
import frc.robot.commands.Autos;
import frc.robot.subsystems.CANDriveSubsystem;
import frc.robot.subsystems.CANRollerSubsystem;

import java.util.EventObject;

import edu.wpi.first.units.measure.Time;
//import frc.robot.SwappableController;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.event.EventLoop;




/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  private boolean orientation = true;
  double stickDeadband(double x, double deadband) {
    return Math.abs(x) < deadband ? 0 : x;
  }
  // The robot's subsystems
  public final CANDriveSubsystem driveSubsystem = new CANDriveSubsystem();
  private final CANRollerSubsystem rollerSubsystem = new CANRollerSubsystem();

  // The driver's controller
  
  public final SwappableController driverController;
  public final SwappableController operatorController;
  //public final CommandXboxController driverController = new CommandXboxController(0);

  // The operator's controller
  



  // The gyro for the drive subsystem
  //private final Pigeon2 m_gyro = new Pigeon2(OperatorConstants.GYRO_ID);

  // The autonomous chooser
  private final SendableChooser<Command> autoChooser = new SendableChooser<>();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    
    System.out.println("alliance: " +DriverStation.getAlliance());
    driverController = new SwappableController(OperatorConstants.DRIVER_CONTROLLER_PORT, this::configureBindings);
    operatorController = new SwappableController(OperatorConstants.OPERATOR_CONTROLLER_PORT, this::configureBindings);
    

    
    SmartDashboard.putData("Remap both controllers", Commands.sequence(
      driverController.automaticallyConfigureController(),
      operatorController.automaticallyConfigureController()
    ));

    SmartDashboard.putData("Remap driverController",
      driverController.automaticallyConfigureController(5)
    );

    SmartDashboard.putData("Remap operatorController",
      operatorController.automaticallyConfigureController(5)
    );

    // Set the options to show up in the Dashboard for selecting auto modes. If you
    // add additional auto modes you can add additional lines here with
    // autoChooser.addOption
    autoChooser.setDefaultOption("Autonomous", Autos.exampleAuto(driveSubsystem));
    /*
    SmartDashboard.putData("Remap Controllers", new InstantCommand(() -> {
      System.out.println("Button clicked! Running event...");
      driverController.handleControllerChange();
    }));
    */
    
     
    SmartDashboard.putData(autoChooser);


    
      
    

      configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   * created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
   * an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link
   * CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or
   * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
   
  private void configureBindings() {
    

    System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    /* 
    SmartDashboard.putData("Remap controllers", new InstantCommand(() -> {
      driverController.handleControllerChange();
    }).ignoringDisable(true));
    */
    
    
    
    driverController.y()
      .onTrue(new InstantCommand(() -> orientation= !orientation));
    
    
    

    // Set the A button to run the "runRoller" command from the factory with a fixed
    // value ejecting the gamepiece while the button is held
    driverController.a()
        .whileTrue(rollerSubsystem.runRoller(rollerSubsystem, () -> RollerConstants.ROLLER_EJECT_VALUE, () -> 0));
    
    // Set the B button to run the "runRoller" command from the factory with a fixed
    // value retracting the gamepiece while the button is held
    driverController.b()
        .whileTrue(rollerSubsystem.runRoller(rollerSubsystem, () -> RollerConstants.ROLLER_RETRACT_VALUE, () -> 0));

    driveSubsystem.setDefaultCommand(
      driveSubsystem.driveArcade(
      driveSubsystem,
      () -> -driverController.getLeftY() *
            ((driverController.rightBumper().getAsBoolean() ? 1 : OperatorConstants.SPEED)) *
            (orientation ? 1 : -1),
      () -> -driverController.getRightX() *
            ((driverController.rightBumper().getAsBoolean() ? 1 : OperatorConstants.SPEED))
      )
    );

    
    // Set the default command for the roller subsystem to the command from the
    // factory with the values provided by the triggers on the operator controller
    rollerSubsystem.setDefaultCommand(
        rollerSubsystem.runRoller(
            rollerSubsystem,
            () -> driverController.getRightTriggerAxis(),
            () -> driverController.getLeftTriggerAxis())
    );

    
    if (Robot.isSimulation()) {
      configureSimBindings();
    }
  }
  
  

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return autoChooser.getSelected();
  }
  
  


  
 
  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   * created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
   * an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link
   * CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or
   * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureSimBindings() {
    
    //System.out.println("i needed to have something here");
     
    
    //SmartDashboard.putNumberArray("SimulatedControllerData", new double[] {-driverController.getY(), -driverController.getX(), -driverController.getZ(), driveSubsystem.currentPose.getRotation().getDegrees()});
    
    driverController.leftBumper().onTrue(
      driveSubsystem.resetPose(driveSubsystem)
    );
    driverController.leftBumper().onTrue(new InstantCommand(() -> {
      System.out.println("Left bumper pressed on driverController");
    }).ignoringDisable(true));
    driverController.rightBumper().onTrue(new InstantCommand(() -> {
      System.out.println("Right bumper pressed on driverController");
    }).ignoringDisable(true));
    driverController.a().onTrue(new InstantCommand(() -> {
      System.out.println("A pressed on driverController");
    }).ignoringDisable(true));
    driverController.b().onTrue(new InstantCommand(() -> {
      System.out.println("B pressed on driverController");
    }).ignoringDisable(true));
    driverController.x().onTrue(new InstantCommand(() -> {
      System.out.println("X pressed on driverController");
    }).ignoringDisable(true));
    driverController.y().onTrue(new InstantCommand(() -> {
      System.out.println("Y pressed on driverController");
      orientation = !orientation;
    }).ignoringDisable(true));
    driverController.povUp().onTrue(new InstantCommand(() -> {
      System.out.println("POV Up pressed on driverController");
    }).ignoringDisable(true));
    driverController.povDown().onTrue(new InstantCommand(() -> {
      System.out.println("POV Down pressed on driverController");
    }).ignoringDisable(true));
    driverController.povLeft().onTrue(new InstantCommand(() -> {
      System.out.println("POV Left pressed on driverController");
    }).ignoringDisable(true));
    driverController.povRight().onTrue(new InstantCommand(() -> {
      System.out.println("POV Right pressed on driverController");
    }).ignoringDisable(true));
    driverController.leftTrigger().onTrue(new InstantCommand(() -> {
      System.out.println("Left Trigger pressed on driverController");
    }).ignoringDisable(true));
    driverController.rightTrigger().onTrue(new InstantCommand(() -> {
      System.out.println("Right Trigger pressed on driverController");
    }).ignoringDisable(true));
    driverController.minus().onTrue(new InstantCommand(() -> {
      System.out.println("Minus pressed on driverController");
    }).ignoringDisable(true));
    driverController.plus().onTrue(new InstantCommand(() -> {
      System.out.println("Plus pressed on driverController");
    }).ignoringDisable(true));
    driverController.capture().onTrue(new InstantCommand(() -> {
      System.out.println("Capture pressed on driverController");
    }).ignoringDisable(true));
    driverController.home().onTrue(new InstantCommand(() -> {
      System.out.println("Home pressed on driverController");
    }).ignoringDisable(true));
    driverController.leftStick().onTrue(new InstantCommand(() -> {
      System.out.println("Left Stick pressed on driverController");
    }).ignoringDisable(true));
    driverController.rightStick().onTrue(new InstantCommand(() -> {
      System.out.println("Right Stick pressed on driverController");
    }).ignoringDisable(true));
    driverController.povUpLeft().onTrue(new InstantCommand(() -> {
      System.out.println("Pov UpLeft pressed on driverController");
    }).ignoringDisable(true));
    driverController.povUpRight().onTrue(new InstantCommand(() -> {
      System.out.println("Pov UpRight pressed on driverController");
    }).ignoringDisable(true));
    driverController.povDownLeft().onTrue(new InstantCommand(() -> {
      System.out.println("Pov DownLeft pressed on driverController");
    }).ignoringDisable(true));
    driverController.povDownRight().onTrue(new InstantCommand(() -> {
      System.out.println("Pov DownRight pressed on driverController");
    }).ignoringDisable(true));
    






    operatorController.leftBumper().onTrue(new InstantCommand(() -> {
      System.out.println("Left bumper pressed on operatorController");
    }).ignoringDisable(true));
    operatorController.rightBumper().onTrue(new InstantCommand(() -> {
      System.out.println("Right bumper pressed on operatorController");
    }).ignoringDisable(true));
    operatorController.a().onTrue(new InstantCommand(() -> {
      System.out.println("A pressed on operatorController");
    }).ignoringDisable(true));
    operatorController.b().onTrue(new InstantCommand(() -> {
      System.out.println("B pressed on operatorController");
    }).ignoringDisable(true));
    operatorController.x().onTrue(new InstantCommand(() -> {
      System.out.println("X pressed on operatorController");
    }).ignoringDisable(true));
    operatorController.y().onTrue(new InstantCommand(() -> {
      System.out.println("Y pressed on operatorController");
      orientation = !orientation;
    }).ignoringDisable(true));
    operatorController.povUp().onTrue(new InstantCommand(() -> {
      System.out.println("POV Up pressed on operatorController");
    }).ignoringDisable(true));
    operatorController.povDown().onTrue(new InstantCommand(() -> {
      System.out.println("POV Down pressed on operatorController");
    }).ignoringDisable(true));
    operatorController.povLeft().onTrue(new InstantCommand(() -> {
      System.out.println("POV Left pressed on operatorController");
    }).ignoringDisable(true));
    operatorController.povRight().onTrue(new InstantCommand(() -> {
      System.out.println("POV Right pressed on operatorController");
    }).ignoringDisable(true));
    operatorController.leftTrigger().onTrue(new InstantCommand(() -> {
      System.out.println("Left Trigger pressed on operatorController");
    }).ignoringDisable(true));
    operatorController.rightTrigger().onTrue(new InstantCommand(() -> {
      System.out.println("Right Trigger pressed on operatorController");
    }).ignoringDisable(true));
    operatorController.minus().onTrue(new InstantCommand(() -> {
      System.out.println("Minus pressed on operatorController");
    }).ignoringDisable(true));
    operatorController.plus().onTrue(new InstantCommand(() -> {
      System.out.println("Plus pressed on operatorController");
    }).ignoringDisable(true));
    operatorController.capture().onTrue(new InstantCommand(() -> {
      System.out.println("Capture pressed on operatorController");
    }).ignoringDisable(true));
    operatorController.home().onTrue(new InstantCommand(() -> {
      System.out.println("Home pressed on operatorController");
    }).ignoringDisable(true));
    operatorController.leftStick().onTrue(new InstantCommand(() -> {
      System.out.println("Left Stick pressed on operatorController");
    }).ignoringDisable(true));
    operatorController.rightStick().onTrue(new InstantCommand(() -> {
      System.out.println("Right Stick pressed on operatorController");
    }).ignoringDisable(true));
    operatorController.povUpLeft().onTrue(new InstantCommand(() -> {
      System.out.println("Pov UpLeft pressed on operatorController");
    }).ignoringDisable(true));
    operatorController.povUpRight().onTrue(new InstantCommand(() -> {
      System.out.println("Pov UpRight pressed on operatorController");
    }).ignoringDisable(true));
    operatorController.povDownLeft().onTrue(new InstantCommand(() -> {
      System.out.println("Pov DownLeft pressed on operatorController");
    }).ignoringDisable(true));
    operatorController.povDownRight().onTrue(new InstantCommand(() -> {
      System.out.println("Pov DownRight pressed on operatorController");
    }).ignoringDisable(true));

    /* 
    driverController.mapableButton1().onTrue(new InstantCommand(() -> {
      System.out.println("Mapable Button 1 pressed");
    }).ignoringDisable(true));
    driverController.mapableButton2().onTrue(new InstantCommand(() -> {
      System.out.println("Mapable Button 2 pressed");
    }).ignoringDisable(true));
    driverController.mapableButton3().onTrue(new InstantCommand(() -> {
      System.out.println("Mapable Button 3 pressed");
    }).ignoringDisable(true));
    driverController.mapableButton4().onTrue(new InstantCommand(() -> {
      System.out.println("Mapable Button 4 pressed");
    }).ignoringDisable(true));
    */
  
  }
  
}