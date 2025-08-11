// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.RollerConstants;
import frc.robot.commands.Autos;
import frc.robot.subsystems.CANDriveSubsystem;
import frc.robot.subsystems.CANRollerSubsystem;
//import frc.robot.SwappableController;
import edu.wpi.first.wpilibj.DriverStation;




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
    driverController = new SwappableController(OperatorConstants.DRIVER_CONTROLLER_PORT);
    System.out.println("Controller name" + DriverStation.getJoystickName(OperatorConstants.DRIVER_CONTROLLER_PORT));
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
    if (Robot.isSimulation()){
      configureSimBindings();
    } else {
      configureBindings();
    }
    configureSimBindings();
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
            () -> driverController.getLeftTriggerAxis()));
    
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
    System.out.println("i needed to have something here");
    /* 
    
    //SmartDashboard.putNumberArray("SimulatedControllerData", new double[] {-driverController.getY(), -driverController.getX(), -driverController.getZ(), driveSubsystem.currentPose.getRotation().getDegrees()});
    
    driverController.leftBumper().onTrue(
      driveSubsystem.resetPose(driveSubsystem)
    );
    driverController.leftBumper().onTrue(new InstantCommand(() -> {
      System.out.println("Left bumper pressed");
    }));
    driverController.rightBumper().onTrue(new InstantCommand(() -> {
      System.out.println("Right bumper pressed");
    }));
    driverController.a().onTrue(new InstantCommand(() -> {
      System.out.println("A pressed");
    }));
    driverController.b().onTrue(new InstantCommand(() -> {
      System.out.println("B pressed");
    }));
    driverController.x().onTrue(new InstantCommand(() -> {
      System.out.println("X pressed");
    }));
    driverController.y().onTrue(new InstantCommand(() -> {
      System.out.println("Y pressed");
      orientation = !orientation;
    }));
    driverController.povUp().onTrue(new InstantCommand(() -> {
      System.out.println("POV Up pressed");
    }));
    driverController.povDown().onTrue(new InstantCommand(() -> {
      System.out.println("POV Down pressed");
    }));
    driverController.povLeft().onTrue(new InstantCommand(() -> {
      System.out.println("POV Left pressed");
    }));
    driverController.povRight().onTrue(new InstantCommand(() -> {
      System.out.println("POV Right pressed");
    }));
    driverController.leftTrigger().onTrue(new InstantCommand(() -> {
      System.out.println("Left Trigger pressed");
    }));
    driverController.rightTrigger().onTrue(new InstantCommand(() -> {
      System.out.println("Right Trigger pressed");
    }));
    driverController.minus().onTrue(new InstantCommand(() -> {
      System.out.println("Minus pressed");
    }));
    driverController.plus().onTrue(new InstantCommand(() -> {
      System.out.println("Plus pressed");
    }));
    driverController.capture().onTrue(new InstantCommand(() -> {
      System.out.println("Capture pressed");
    }));
    driverController.home().onTrue(new InstantCommand(() -> {
      System.out.println("Home pressed");
    }));
    driverController.leftStick().onTrue(new InstantCommand(() -> {
      System.out.println("Left Stick pressed");
    }));
    driverController.rightStick().onTrue(new InstantCommand(() -> {
      System.out.println("Right Stick pressed");
    }));
    driverController.mapableButton1().onTrue(new InstantCommand(() -> {
      System.out.println("Mapable Button 1 pressed");
    }));
    driverController.mapableButton2().onTrue(new InstantCommand(() -> {
      System.out.println("Mapable Button 2 pressed");
    }));
    driverController.mapableButton3().onTrue(new InstantCommand(() -> {
      System.out.println("Mapable Button 3 pressed");
    }));
    driverController.mapableButton4().onTrue(new InstantCommand(() -> {
      System.out.println("Mapable Button 4 pressed");
    }));
  */
  }
  
}