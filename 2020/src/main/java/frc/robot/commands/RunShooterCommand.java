package frc.robot.commands;

import frc.robot.subsystems.ShooterSubsystem;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;

import static frc.robot.Constants.*;

public class RunShooterCommand extends CommandBase {

    private final ShooterSubsystem m_shooter;
    private final PIDController m_pid;
    private DoubleSupplier m_target;

    public RunShooterCommand(ShooterSubsystem shooter) {
        addRequirements(shooter);
        m_shooter = shooter;
        m_pid = new PIDController(SHOOTER_FIRE_KP, SHOOTER_FIRE_KI, SHOOTER_FIRE_KD);
    }

    public void setSupplier(DoubleSupplier t) {
        m_target = t;
    }

    @Override
    public void execute() {
        m_shooter.fire(m_pid.calculate(m_shooter.getRateFire(), m_target.getAsDouble()));
    }

}
