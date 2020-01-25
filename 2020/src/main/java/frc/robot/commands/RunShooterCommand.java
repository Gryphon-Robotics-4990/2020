package frc.robot.commands;

import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import io.github.oblarg.oblog.annotations.Config;

import static frc.robot.Constants.*;

public class RunShooterCommand extends CommandBase {

    private final ShooterSubsystem m_shooter;
    private final PIDController m_pid;

    private double m_target;

    public RunShooterCommand(ShooterSubsystem shooter) {
        addRequirements(shooter);
        m_shooter = shooter;
        m_pid = new PIDController(SHOOTER_FIRE_KP, SHOOTER_FIRE_KI, SHOOTER_FIRE_KD);
        m_target = 0;
    }

    @Config
    public void setTarget(double t) {
        m_target = t;
    }

    @Override
    public void execute() {
        m_shooter.fire(m_pid.calculate(m_shooter.getRateFire(), m_target));
    }

}
