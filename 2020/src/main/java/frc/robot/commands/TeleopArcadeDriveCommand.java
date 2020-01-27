package frc.robot.commands;

import static frc.robot.Constants.*;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem.DriveMode;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TeleopArcadeDriveCommand extends CommandBase {

    private final DrivetrainSubsystem m_drive;
    private DoubleSupplier m_speedSupplier, m_rotationSupplier;
    private PIDController m_pid;

    public TeleopArcadeDriveCommand(DrivetrainSubsystem drive) {
        addRequirements(drive);
        m_drive = drive;
        m_drive.setDriveMode(DriveMode.Arcade);
        m_pid = new PIDController(DRIVETRAIN_ARCADE_KP, DRIVETRAIN_ARCADE_KI, DRIVETRAIN_ARCADE_KD);
    }

    public void setSuppliers(DoubleSupplier left, DoubleSupplier right) {
        m_speedSupplier = left;
        m_rotationSupplier = right;
    }

    @Override
    public void execute() {
        m_drive.drive(m_speedSupplier.getAsDouble(), -1 * m_pid.calculate(m_drive.getGyroRate(), m_rotationSupplier.getAsDouble()));
    }

}
