// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.Constants.DriveConstants;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class CANDriveSubsystem extends SubsystemBase {
  private final SparkMax leftLeader;
  //private final SparkMax leftFollower;
  private final SparkMax rightLeader;
  //private final SparkMax rightFollower;
  public Pose2d currentPose;
  private Field2d m_field;
  

  private final DifferentialDrive drive;

  public CANDriveSubsystem() {
    //pose for simulation
    currentPose = new Pose2d(6, 7, new Rotation2d(0));
    m_field = new Field2d();

    // create brushed motors for drive
    leftLeader = new SparkMax(DriveConstants.LEFT_LEADER_ID, MotorType.kBrushed);
    //leftFollower = new SparkMax(DriveConstants.LEFT_FOLLOWER_ID, MotorType.kBrushed);
    rightLeader = new SparkMax(DriveConstants.RIGHT_LEADER_ID, MotorType.kBrushed);
    //rightFollower = new SparkMax(DriveConstants.RIGHT_FOLLOWER_ID, MotorType.kBrushed);

    // set up differential drive class
    drive = new DifferentialDrive(leftLeader, rightLeader);

    // Set can timeout. Because this project only sets parameters once on
    // construction, the timeout can be long without blocking robot operation. Code
    // which sets or gets parameters during operation may need a shorter timeout.
    leftLeader.setCANTimeout(250);
    rightLeader.setCANTimeout(250);
    //leftFollower.setCANTimeout(250);
    //rightFollower.setCANTimeout(250);

    // Create the configuration to apply to motors. Voltage compensation
    // helps the robot perform more similarly on different
    // battery voltages (at the cost of a little bit of top speed on a fully charged
    // battery). The current limit helps prevent tripping
    // breakers.
    SparkMaxConfig config = new SparkMaxConfig();
    config.voltageCompensation(12);
    config.smartCurrentLimit(DriveConstants.DRIVE_MOTOR_CURRENT_LIMIT);

    // Set configuration to follow leader and then apply it to corresponding
    // follower. Resetting in case a new controller is swapped
    // in and persisting in case of a controller reset due to breaker trip
    config.follow(leftLeader);
    //leftFollower.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    config.follow(rightLeader);
    //rightFollower.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // Remove following, then apply config to right leader
    config.disableFollowerMode();
    rightLeader.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    // Set conifg to inverted and then apply to left leader. Set Left side inverted
    // so that postive values drive both sides forward
    config.inverted(true);
    leftLeader.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {
  }

  // Command to drive the robot with joystick inputs
  public Command resetPose(CANDriveSubsystem canDriveSubsystem){
    return Commands.run(
        () -> {
          // Reset the robot's pose to the origin (0, 0) with no rotation
          currentPose = Pose2d.kZero;
          m_field.setRobotPose(currentPose);
        },
        canDriveSubsystem)
        .finallyDo(() -> {
            drive.stopMotor();
            m_field.setRobotPose(Pose2d.kZero); 
        })
        .handleInterrupt(() -> {
            drive.stopMotor();
            m_field.setRobotPose(Pose2d.kZero); 
        });
  }

  public Command setPose(CANDriveSubsystem canDriveSubsystem, Pose2d pose) {
    return Commands.run(
        () -> {
          // Set the robot's pose to the specified pose
          currentPose = pose;
          m_field.setRobotPose(currentPose);
        },
        canDriveSubsystem)
        .finallyDo(() -> {
            drive.stopMotor();
            m_field.setRobotPose(Pose2d.kZero); 
        })
        .handleInterrupt(() -> {
            drive.stopMotor();
            m_field.setRobotPose(Pose2d.kZero); 
        });
  }

  
  public Command driveArcade(
      CANDriveSubsystem driveSubsystem, DoubleSupplier xSpeed, DoubleSupplier zRotation) {
        
    return Commands.run(
        () -> {
          
          drive.arcadeDrive(xSpeed.getAsDouble(), zRotation.getAsDouble());


          if (Robot.isSimulation()) {
          
            // Get the current pose
            Pose2d currentPose = m_field.getRobotPose();
            Rotation2d robotRotation = currentPose.getRotation(); // Robot's current orientation

            // Rotate the joystick input by the robot's current orientation
            double joystickX = xSpeed.getAsDouble();
            double joystickY = 0; // No strafe movement in arcade drive
            Translation2d joystickInput = new Translation2d(joystickX, joystickY)
                .rotateBy(robotRotation); // Align joystick input with robot's heading

            // Calculate the forward movement relative to the robot's orientation
            double forward = joystickInput.getX(); // Forward/backward movement
            double rotation = zRotation.getAsDouble(); // Rotation movement

            Translation2d translation = new Translation2d(forward, 0)
              .rotateBy(new Rotation2d(robotRotation.getRadians())); // Align translation with robot's heading


            // Scale the rotation appropriately (convert to radians if necessary)
            double scaledRotation = Math.toRadians(rotation * 180); // Scale rotation to match one full spin

            // Update the pose by applying the translation and scaled rotation
            currentPose = currentPose.transformBy(
                new Transform2d(translation, new Rotation2d(scaledRotation)) // Apply scaled rotation
            );

            // Set the updated pose on the field
            m_field.setRobotPose(currentPose);
          }
        },
        driveSubsystem)
        .finallyDo(() -> { 
            drive.stopMotor(); 
            m_field.setRobotPose(Pose2d.kZero); 
        })
        .handleInterrupt(() -> {
            drive.stopMotor();
            m_field.setRobotPose(Pose2d.kZero); 
        });
  }

  double normalizeRad180(double rad) {
    // Normalize the angle to be within -180 to 180 degrees
    while (rad > Math.PI) {
      rad -= 2*Math.PI;
    }
    while (rad < -Math.PI) {
      rad += 2*Math.PI;
    }
    return rad;
  }



  /*
  public Command driveArcadeWithGyro(
    CANDriveSubsystem driveSubsystem, DoubleSupplier xSpeed, DoubleSupplier ySpeed, DoubleSupplier zRotation, DoubleSupplier gyroRad) {
      
      return Commands.run(
          () -> {
              double theta = Math.atan2(ySpeed.getAsDouble(), xSpeed.getAsDouble()); // Calculate angle in RAd
              double gyro180=normalizeRad180(gyroRad.getAsDouble());
              double theta180=normalizeRad180(theta);
              double thetaBackward = normalizeRad180(theta180 + Math.PI);

                  // Determine the closest angle (forward or backward)
              double forwardDifference = Math.abs(normalizeRad180(gyro180 - theta180));
              double backwardDifference = Math.abs(normalizeRad180(gyro180 - thetaBackward));

              // Use the angle with the smallest difference
              boolean moveBackward = backwardDifference < forwardDifference;

              // Adjust the speed direction based on whether to move backward
              double speedMultiplier = 1;//moveBackward ? -1.0 : 1.0;

              // Calculate the closest angle to rotate towards
              double closestAngle = moveBackward ? thetaBackward : theta180;
              
              //double closestAngle = moveBackward ? thetaBackward : theta180;
              double difference = normalizeRad180(gyro180 - closestAngle);

              if (Math.abs(difference) < 0.05) { // Threshold to ignore small differences
                difference = 0;
              }
              // Adjust rotation based on the difference
              double rotateToJoystick = difference / Math.PI; // Scale to [-1, 1] for rotation control
              rotateToJoystick= rotateToJoystick >= 0.1 ? rotateToJoystick : 0;
              if (theta==0){
                rotateToJoystick=0;
              }
              SmartDashboard.putNumber("zRotation", zRotation.getAsDouble());
              SmartDashboard.putNumber("theta180", theta180);
              SmartDashboard.putNumber("gyro180", gyro180);
              SmartDashboard.putNumber("diffrence", difference);
              SmartDashboard.putNumber("rotateToJoystick", rotateToJoystick);
              currentPose = m_field.getRobotPose();
              if (Robot.isSimulation()){
                //currentPose = currentPose.rotateBy(new Rotation2d((((zRotation.getAsDouble()*2) - rotateToJoystick))/180));
                
                double joystickX = xSpeed.getAsDouble();
                double joystickY = ySpeed.getAsDouble();
                Rotation2d robotRotation = currentPose.getRotation(); // Robot's current orientation

                // Rotate the joystick input by the robot's current orientation
                Translation2d joystickInput = new Translation2d(joystickX, joystickY);//.rotateBy(robotRotation);

                
                // Calculate the translation based on joystick input
                double forward = speedMultiplier * joystickInput.getX(); // Forward/backward movement
                double strafe = speedMultiplier * joystickInput.getY();  // Side-to-side movement
                Translation2d translation = new Translation2d(forward, strafe)
                  .div(10); // Scale down for simulation purposes

                // Update the pose by applying the translation and rotation

                  currentPose = currentPose.transformBy(
                    new Transform2d(translation, new Rotation2d(Math.toRadians(((zRotation.getAsDouble()/180)*20) - rotateToJoystick/5))));


                m_field.setRobotPose(currentPose);
                SmartDashboard.putData("pose", m_field);
              }
              
              
              
              double magnitude=Math.hypot(xSpeed.getAsDouble(), ySpeed.getAsDouble())*speedMultiplier;
              System.out.println(rotateToJoystick);
              drive.arcadeDrive(magnitude >= 0.15 ? magnitude : 0, ((zRotation.getAsDouble()) - rotateToJoystick));
          }, 
          driveSubsystem)
          .finallyDo(interrupted -> drive.stopMotor())
          .handleInterrupt(() -> drive.stopMotor());
          
  }
  */
}
