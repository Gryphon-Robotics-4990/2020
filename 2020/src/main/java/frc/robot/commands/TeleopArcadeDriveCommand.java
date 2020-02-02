package frc.robot.commands;

import static frc.robot.Constants.*;
import frc.robot.subsystems.DrivetrainSubsystem;
import io.github.oblarg.oblog.annotations.Log;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TeleopArcadeDriveCommand extends CommandBase {

    private final DrivetrainSubsystem m_drive;
    private DoubleSupplier m_speedSupplier, m_rotationSupplier;
    @Log
    private PIDController m_pidL, m_pidR;

    public TeleopArcadeDriveCommand(DrivetrainSubsystem drive) {
        addRequirements(drive);
        m_drive = drive;
    }

    public void setSuppliers(DoubleSupplier speed, DoubleSupplier rot) {
        m_speedSupplier = speed;
        m_rotationSupplier = rot;
    }

    @Override
    public void execute() {
        System.out.println("l: "+ m_speedSupplier.getAsDouble() +"r: "+ m_rotationSupplier.getAsDouble());
        m_drive.tankDrive(convertToTank(m_speedSupplier.getAsDouble(), m_rotationSupplier.getAsDouble()*0.65));
    }

    private static double[] convertToTank(double speed, double rot) {
        double left, right, maxSpeed;

        if (Math.abs(speed) > Math.abs(rot)) maxSpeed = speed;
        else maxSpeed = speed > 0 ? Math.abs(rot) : Math.abs(rot) * -1;

        if (speed > 0) {
            if (rot > 0) {
                left = maxSpeed;
                right = speed - rot;
            }
            else {
                left = speed + rot;
                right = maxSpeed;
            }
        }
        else {
            if (rot > 0) {
                left = speed + rot;
                right = maxSpeed;
            }
            else {
                left = maxSpeed;
                right = speed - rot;
            }
        }
        return new double[] {left, right};
    }

}
