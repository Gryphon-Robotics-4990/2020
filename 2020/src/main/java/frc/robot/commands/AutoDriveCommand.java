package frc.robot.commands;

import java.io.IOException;
import java.nio.file.Path;

import static frc.robot.Constants.*;
import frc.robot.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RamseteCommand;


public class AutoDriveCommand extends CommandBase{
     
    DrivetrainSubsystem m_drive;

    public AutoDriveCommand(){
        Command command = doAuto();
        command.schedule();

    }

    public Command doAuto(){
        // Create a voltage constraint to ensure we don't accelerate too fast
        DifferentialDriveVoltageConstraint autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(KS_VOLTS, KV_VOLT_SECONDS_PER_METER, KA_VOLT_SECONDS_SQUARED_PER_METER),
            K_DRIVE_KINEMATICS,
            10);
        // Create config for trajectory
        TrajectoryConfig config =
        new TrajectoryConfig(K_MAX_SPEED_METERS_PER_SECOND, K_MAX_ACCELERATION_METERS_PER_SECOND_SQUARED)
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(K_DRIVE_KINEMATICS)
            // Apply the voltage constraint
            .addConstraint(autoVoltageConstraint);
        
        String trajectoryJSON = "paths/YourPath.wpilib.json";
        Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
        Trajectory trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
        RamseteCommand ramseteCommand = new RamseteCommand(
            trajectory,
            () -> m_drive.getPose(),
            new RamseteController(K_RAMSETE_B, K_RAMSETE_Z),
            new SimpleMotorFeedforward(KS_VOLTS,
            KV_VOLT_SECONDS_PER_METER,
            KA_VOLT_SECONDS_SQUARED_PER_METER),
            K_DRIVE_KINEMATICS,
            /*get wheel speeds(Supplier)*/,
            new PIDController(LIMELIGHT_DRIVETRAIN_KP, 0, 0),
            new PIDController(LIMELIGHT_DRIVETRAIN_KP, 0, 0),
            // RamseteCommand passes volts to the callback
            /*output volts (BiConsumer)*/,
            m_drive
        );

        // Run path following command, then stop at the end.
        return ramseteCommand;
    }

    @Override
    public void execute(){
    }
}