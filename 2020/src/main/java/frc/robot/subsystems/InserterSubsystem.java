package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Direction;

import static frc.robot.Constants.*;

public class InserterSubsystem extends SubsystemBase {

    private final WPI_TalonSRX m_inserter;

    public InserterSubsystem() {
        m_inserter = new WPI_TalonSRX(CAN_SHOOTER_INSERTER_TALONSRX);
    }

    public void run(Direction direction) {
        int multiplier = direction.getMultiplier();
        m_inserter.set(multiplier * INSERTER_MOTOR_SPEED);
    }
}