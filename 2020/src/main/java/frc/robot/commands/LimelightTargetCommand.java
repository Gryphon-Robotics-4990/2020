package frc.robot.commands;

import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem.DriveMode;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.*;

public class LimelightTargetCommand extends CommandBase {

    private final DrivetrainSubsystem m_drive;
    private final ShooterSubsystem m_shooter;
    private PIDController m_pidS, m_pidD;

    public LimelightTargetCommand(DrivetrainSubsystem drive, ShooterSubsystem shooter) {
        addRequirements(drive);
        addRequirements(shooter);

        m_drive = drive;
        m_shooter = shooter;
        //Because PID needs to rotate base
        m_drive.setDriveMode(DriveMode.Arcade);

        m_pidS = new PIDController(SHOOTER_FIRE_KP, SHOOTER_FIRE_KI, SHOOTER_FIRE_KD);
        m_pidD = new PIDController(LIMELIGHT_DRIVETRAIN_KP, LIMELIGHT_DRIVETRAIN_KI, LIMELIGHT_DRIVETRAIN_KD);
    }

    public void setTargetAngle(double angle) {
        m_pidS.setSetpoint(angle);
    }
    public void setTargetHeading(double heading) {
        m_pidD.setSetpoint(heading);
    }

    @Override
    public void execute() {
        //idk yet
    }

}
