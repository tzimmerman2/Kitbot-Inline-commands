package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import com.ctre.phoenix6.hardware.Pigeon2;
import frc.robot.subsystems.CANDriveSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class Gyro {
    public static final Command resetYaw(Pigeon2 m_gyro) {
        return new InstantCommand(() -> m_gyro.setYaw(0));
        
    }

    public static final Command SwapDriveSystem(CANDriveSubsystem driveSubsystem, SendableChooser<Command> gyroControlsChooser) {
        System.out.println("swap");
        return new InstantCommand(() -> driveSubsystem.setDefaultCommand(gyroControlsChooser.getSelected()));
    }
    
  
}


