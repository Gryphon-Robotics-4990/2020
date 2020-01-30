package frc.robot;

import io.github.oblarg.oblog.annotations.*;

public final class Constants {
    public static int PORT_JOYSTICK_DRIVE = 0;
    public static int PORT_JOYSTICK_OPERATOR = 1;

    public static int CAN_DRIVETRAIN_LEFT_REAR_TALONSRX = 1;
    public static int CAN_DRIVETRAIN_LEFT_FRONT_TALONSRX = 2;
    public static int CAN_DRIVETRAIN_RIGHT_REAR_TALONSRX = 3;
    public static int CAN_DRIVETRAIN_RIGHT_FRONT_TALONSRX = 4;

    //Just a note, this thing is not a constant because you can change it on smartdashboard so we might have to move it to a command if it makes it to final code
    @Config
    public static double SLOW_MULTIPLIER = 0.3;

}
