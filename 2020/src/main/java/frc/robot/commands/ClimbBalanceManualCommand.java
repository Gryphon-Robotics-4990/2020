package frc.robot.commands;

import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.*;

import java.util.function.DoubleSupplier;

public class ClimbBalanceManualCommand extends CommandBase {

    private final ClimbSubsystem m_climb;
    private DoubleSupplier m_speed;

    public ClimbBalanceManualCommand(ClimbSubsystem climb) {
        addRequirements(climb);
        m_climb = climb;
    }

    public void setSupplier(DoubleSupplier d) {
        m_speed = d;
    }

    @Override
    public void execute() {
        m_climb.runBalance(m_speed.getAsDouble());
    }

}