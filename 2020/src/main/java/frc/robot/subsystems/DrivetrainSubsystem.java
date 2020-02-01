package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class DrivetrainSubsystem extends SubsystemBase {

    private final WPI_TalonSRX m_leftTalon, m_rightTalon;
    private final WPI_VictorSPX m_leftVictor, m_rightVictor;

    private final AHRS m_gyro;

    //private final DifferentialDrive m_drive;
    
    private final DifferentialDriveKinematics m_kinematics;
    private final DifferentialDriveOdometry m_odometry;

    public DrivetrainSubsystem() {
        m_leftTalon = new WPI_TalonSRX(CAN_DRIVETRAIN_LEFT_TALONSRX);
        m_leftVictor = new WPI_VictorSPX(CAN_DRIVETRAIN_LEFT_VICTORSPX);
        m_rightTalon = new WPI_TalonSRX(CAN_DRIVETRAIN_RIGHT_TALONSRX);
        m_rightVictor = new WPI_VictorSPX(CAN_DRIVETRAIN_RIGHT_VICTORSPX);

        m_leftTalon.configFactoryDefault();
        m_leftVictor.configFactoryDefault();
        m_rightTalon.configFactoryDefault();
        m_rightVictor.configFactoryDefault();

        m_leftTalon.setSensorPhase(true);
        m_rightTalon.setSensorPhase(true);
        //m_leftVictor.setInverted(true);

        m_leftVictor.follow(m_leftTalon,FollowerType.PercentOutput);
        m_rightVictor.follow(m_rightTalon,FollowerType.PercentOutput);

        //m_drive = new DifferentialDrive(m_leftTalon, m_rightTalon);

        m_gyro = new AHRS(SPI.Port.kMXP);
        m_gyro.reset();

        m_kinematics = new DifferentialDriveKinematics(DRIVETRAIN_TRACKWIDTH_METERS);
        m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(m_gyro.getAngle()));
        configureTalonClosedLoops();
    }

    @Override
    public void periodic() {
        //Update odometry
        m_odometry.update(Rotation2d.fromDegrees(m_gyro.getAngle()), getDistanceLeft(), getDistanceRight());
    }

    private void configureTalonClosedLoops() {
        m_leftTalon.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.CTRE_MagEncoder_Relative, 0, 5);
        m_leftTalon.config_kP(0, 0.5);
        m_leftTalon.config_kI(0, 0.0);
        m_leftTalon.config_kD(0, 15);
        m_leftTalon.config_kF(0, 0.3);

        m_rightTalon.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.CTRE_MagEncoder_Relative, 0, 5);
        m_rightTalon.config_kP(0, 0.5);
        m_rightTalon.config_kI(0, 0.0);
        m_rightTalon.config_kD(0, 15);
        m_rightTalon.config_kF(0, 0.3);
    }

    public void tankDrive(double left, double right) {
        //m_drive.tankDrive(left, right, false);
        m_leftTalon.set(ControlMode.Velocity, left*3000);
        m_rightTalon.set(ControlMode.Velocity, left*3000);
        //m_rightTalon.set(ControlMode.Velocity, right*3000);
    }

    public void arcadeDrive(double speed, double rot) {
        //m_drive.arcadeDrive(speed, rot);
    }

    public double getGyroRate() {
        return m_gyro.getRate();
    }

    public double getDistanceLeft() {
        return m_leftTalon.getSelectedSensorPosition() * DRIVETRAIN_ENCODER_DISTANCE_TO_METERS;
    }

    public double getDistanceRight() {
        return m_rightTalon.getSelectedSensorPosition() * DRIVETRAIN_ENCODER_DISTANCE_TO_METERS;
    }

    public double getRateLeft() {
        return m_leftTalon.getSelectedSensorVelocity() * DRIVETRAIN_ENCODER_VELOCITY_TO_METERS_PER_SECOND;
    }

    public double getRateRight() {
        return m_rightTalon.getSelectedSensorVelocity() * DRIVETRAIN_ENCODER_VELOCITY_TO_METERS_PER_SECOND;
    }
}
