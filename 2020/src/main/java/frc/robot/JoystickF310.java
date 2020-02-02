package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.POVButton;

public class JoystickF310 extends Joystick {
    
	public JoystickF310(int joystickNumber) { super(joystickNumber); }

	public Button getButton(ButtonF310 button) {
		return new JoystickButton(this, button.ordinal());
	}

	public Button getButton(POVF310 button) {
		return new POVButton(this, button.ordinal() * 45);
	}

	public double getRawAxis(AxisF310 axis) {
		return Math.abs(getRawAxis(axis.ordinal())) < 0.05 ? 0 : getRawAxis(axis.ordinal());
	}

	public static enum POVF310 {
		Top, TopRight, Right, BottomRight, Bottom, BottomLeft, Left, TopLeft
	}

	public static enum ButtonF310 {
		None, A, B, X, Y, BumperLeft, BumperRight, Back, Start, JoystickLeft, JoystickRight
	}

	public static enum AxisF310 {
		JoystickLeftX, JoystickLeftY, TriggerLeft, TriggerRight, JoystickRightX, JoystickRightY
    }
}