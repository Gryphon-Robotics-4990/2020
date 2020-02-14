package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

//See https://docs.wpilib.org/en/latest/docs/software/commandbased/pid-subsystems-commands.html
//And perhaps https://github.com/wpilibsuite/allwpilib/blob/master/wpilibjExamples/src/main/java/edu/wpi/first/wpilibj/examples/gyrodrivecommands/commands/TurnToAngle.java

//But most importantly https://docs.wpilib.org/en/latest/docs/software/commandbased/pid-subsystems-commands.html
//Maybe change this back to "normal" subsystem and use a PIDCommand because the drivetrain can both turn and go straight?

//https://www.chiefdelphi.com/t/frc-driving-straight/364640

public class DrivetrainSubsystem extends SubsystemBase {
    private WPI_TalonSRX m_leftFront;
    //private WPI_TalonSRX m_leftRear;
    private WPI_TalonSRX m_rightFront;
    //private WPI_TalonSRX m_rightRear;

    private final AHRS m_gyro;

    private final SpeedControllerGroup m_motorGroupLeft;
    private final SpeedControllerGroup m_motorGroupRight;

<<<<<<< Updated upstream
    private final DifferentialDrive m_drive;
    
    private final DifferentialDriveKinematics m_kinematics;
    private final DifferentialDriveOdometry m_odometry;
=======
    //private final DifferentialDrive m_drive;
>>>>>>> Stashed changes

    private DriveMode driveMode = DEFAULT_DRIVE_MODE;

    public DrivetrainSubsystem() {
        m_leftFront = new WPI_TalonSRX(CAN_DRIVETRAIN_LEFT_FRONT_TALONSRX);
        //m_leftRear = new WPI_TalonSRX(CAN_DRIVETRAIN_LEFT_REAR_TALONSRX);
        m_rightFront = new WPI_TalonSRX(CAN_DRIVETRAIN_RIGHT_FRONT_TALONSRX);
<<<<<<< Updated upstream
        m_rightRear = new WPI_TalonSRX(CAN_DRIVETRAIN_RIGHT_REAR_TALONSRX);

        m_gyro = new AHRS(SPI.Port.kMXP);

        m_motorGroupLeft = new SpeedControllerGroup(m_leftFront, m_leftRear);
        m_motorGroupRight = new SpeedControllerGroup(m_rightFront, m_rightRear);
=======
        //m_rightRear = new WPI_TalonSRX(CAN_DRIVETRAIN_RIGHT_REAR_TALONSRX);
        
        m_motorGroupLeft = new SpeedControllerGroup(m_leftFront/*, m_leftRear*/);
        m_motorGroupRight = new SpeedControllerGroup(m_rightFront/*, m_rightRear*/);
>>>>>>> Stashed changes

        //m_drive = new DifferentialDrive(m_motorGroupLeft, m_motorGroupRight);

        //TODO Reset gyro

        m_kinematics = new DifferentialDriveKinematics(DRIVETRAIN_TRACKWIDTH_METERS);
        m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(m_gyro.getAngle()));
    }

    @Override
    public void periodic() {
        //Update odometry
        m_odometry.update(Rotation2d.fromDegrees(m_gyro.getAngle()), getDistanceLeft(), getDistanceRight());
    }

    public void drive(double left, double right) {
<<<<<<< Updated upstream
        if (driveMode == DriveMode.Tank) m_drive.tankDrive(left, right, true);
        else if (driveMode == DriveMode.Arcade) m_drive.arcadeDrive(left, right);
    }

    public double getGyroRate() {
        return m_gyro.getRate();
    }

    private double getDistanceLeft() {
        return m_leftFront.getSelectedSensorPosition() * DRIVETRAIN_ENCODER_DISTANCE_TO_METERS;
    }

    private double getDistanceRight() {
        return m_leftFront.getSelectedSensorPosition() * DRIVETRAIN_ENCODER_DISTANCE_TO_METERS;
    }

    //TODO get actual rates
    private double getRateLeft() {
        return m_leftFront.getSelectedSensorVelocity() * DRIVETRAIN_ENCODER_VELOCITY_TO_METERS_PER_SECOND;
    }

    private double getRateRight() {
        return m_rightFront.getSelectedSensorVelocity() * DRIVETRAIN_ENCODER_VELOCITY_TO_METERS_PER_SECOND;
    }


    public void setDriveMode(DriveMode mode) { driveMode = mode; }

    public DriveMode getDriveMode() { return driveMode; }

    public enum DriveMode {
        Tank,
        Arcade
=======
        //m_drive.tankDrive(left, -right);
>>>>>>> Stashed changes
    }
}
