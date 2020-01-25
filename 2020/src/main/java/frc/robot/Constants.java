package frc.robot;

import frc.robot.subsystems.DrivetrainSubsystem.DriveMode;

public final class Constants {
    public static int PORT_JOYSTICK_DRIVE = 0;
    public static int PORT_JOYSTICK_OPERATOR = 1;

    public static int CAN_PCM = 12;
    public static int CAN_SHOOTER_FIRE_TALONSRX = 36;
    public static int CAN_SHOOTER_AIM_TALONSRX = 36;
    public static int CAN_DRIVETRAIN_LEFT_REAR_TALONSRX = 32;
    public static int CAN_DRIVETRAIN_LEFT_FRONT_TALONSRX = 31;
    public static int CAN_DRIVETRAIN_RIGHT_REAR_TALONSRX = 34;
    public static int CAN_DRIVETRAIN_RIGHT_FRONT_TALONSRX = 33;
    

    public static double DRIVETRAIN_TRACKWIDTH_METERS = 0.606425;
    public static double DRIVETRAIN_MINIMUM_SPEED_METERS_PER_SECOND = 0.1;
    public static double DRIVETRAIN_ENCODER_DISTANCE_TO_METERS = 0.10914037;//1 rotation is 4096 encoder units, 10.71:1 gear ratio, 6 inch wheels
    public static double DRIVETRAIN_ENCODER_VELOCITY_TO_METERS_PER_SECOND = DRIVETRAIN_ENCODER_DISTANCE_TO_METERS / 10;
    public static double DRIVETRAIN_MAXIMUM_SPEED_METERS_PER_SECOND = 8;
    public static DriveMode DEFAULT_DRIVE_MODE = DriveMode.Arcade;

    public static double SHOOTER_ENCODER_VELOCITY_TO_METERS_PER_SECOND = 0;

    public static double DRIVETRAIN_ARCADE_KP = 0.1;
    public static double DRIVETRAIN_ARCADE_KI = 0.1;
    public static double DRIVETRAIN_ARCADE_KD = 0.1;

    public static double SHOOTER_FIRE_KP = 0.1;
    public static double SHOOTER_FIRE_KI = 0.1;
    public static double SHOOTER_FIRE_KD = 0.1;
    

    public static double LIMELIGHT_DRIVETRAIN_KP = 0.1;
    public static double LIMELIGHT_DRIVETRAIN_KI = 0.1;
    public static double LIMELIGHT_DRIVETRAIN_KD = 0.1;

    public static double ENCODER_RESOLUTION = 4096;
    public static int TALON_TIMEOUT_MS = 5;
}
