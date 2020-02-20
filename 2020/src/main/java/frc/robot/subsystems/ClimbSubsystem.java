package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class ClimbSubsystem extends SubsystemBase {

    private final WPI_TalonSRX m_climb, m_balance;
    private final Solenoid m_solenoid;

    public ClimbSubsystem() {
        m_climb = new WPI_TalonSRX(CAN_CLIMB_MAIN_TALONSRX);
        m_balance = new WPI_TalonSRX(CAN_CLIMB_BALANCE_TALONSRX);

        m_solenoid = new Solenoid(CAN_PCM, PCM_CLIMB);
		m_solenoid.set(false);

        configureMotors();
    }

    @Override
    public void periodic() {
    }

    public void climb(double position) {
        m_climb.set(ControlMode.Position, position);
    }
    
    public int getEncoderTicks(){
        return m_climb.getSelectedSensorPosition();
    }

    public void setLock(boolean lock){
        m_solenoid.set(lock);
    }

    private void configureMotors() {
        
        //First setup talons with default settings
        m_climb.configFactoryDefault();
        m_balance.configFactoryDefault();

        //Setup talon built-in PID
        m_climb.configSelectedFeedbackSensor(TALON_DEFAULT_FEEDBACK_DEVICE, TALON_DEFAULT_PID_ID, TALON_TIMEOUT_MS);

        //Create config objects
        TalonSRXConfiguration config = new TalonSRXConfiguration();

        //Setup config objects with desired values
        config.slot0 = CLIMB_FPID;

        //Configure talons
        m_climb.configAllSettings(config);
    }
    public void runBalance(double speed){
        m_balance.set(speed);
    }
}