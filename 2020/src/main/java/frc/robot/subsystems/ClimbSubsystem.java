package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class ClimbSubsystem extends SubsystemBase {

    private final WPI_TalonSRX m_climb, m_balance;
    private final WPI_VictorSPX m_slave;
    private final Solenoid m_solenoid;
    private DigitalInput m_topLimit1, m_topLimit2, m_bottomLimit1, m_bottomLimit2;

    public ClimbSubsystem() {
        m_climb = new WPI_TalonSRX(CAN_CLIMB_MAIN_TALONSRX);
        m_balance = new WPI_TalonSRX(CAN_CLIMB_BALANCE_TALONSRX);
        m_slave = new WPI_VictorSPX(CAN_CLIMB_MAIN_VICTORSPX);

        m_topLimit1 = new DigitalInput(DIO_LIMIT_CLIMB_TOP_1);
        m_topLimit2 = new DigitalInput(DIO_LIMIT_CLIMB_TOP_2);
        m_bottomLimit1 = new DigitalInput(DIO_LIMIT_CLIMB_BOTTOM_1);
        m_bottomLimit2 = new DigitalInput(DIO_LIMIT_CLIMB_BOTTOM_2);

        m_solenoid = new Solenoid(CAN_PCM, PCM_CLIMB);
		m_solenoid.set(false);

        configureMotors();
    }

    @Override
    public void periodic() {
    }

    public void runClimb(double speed) {
        System.out.println("Climbing at " + speed);
        if (getBottomSensors() && speed < 0) speed = 0;
        if (getTopSensors() && speed > 0) speed = 0;
        m_climb.set(speed);
    }
    

    public void setLock(boolean lock){
        m_solenoid.set(! lock);
    }

    public boolean getTopSensors() {
        return ! m_topLimit1.get() || ! m_topLimit2.get();
    }
    
    public boolean getBottomSensors() {
        return ! m_bottomLimit1.get() || ! m_bottomLimit2.get();
    }

    public boolean isLocked() {
        return ! m_solenoid.get();
    }

    private void configureMotors() {
        
        //First setup talons with default settings
        m_climb.configFactoryDefault();
        m_balance.configFactoryDefault();
        m_slave.configFactoryDefault();

        m_slave.follow(m_climb, FollowerType.PercentOutput);

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