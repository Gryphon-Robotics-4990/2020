package frc.robot;

import frc.robot.JoystickF310.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class RobotContainer {

    private final JoystickF310 joystickDrive = new JoystickF310(Constants.PORT_JOYSTICK_DRIVE);
    private final JoystickF310 joystickOperator = new JoystickF310(Constants.PORT_JOYSTICK_OPERATOR);

    private final DrivetrainSubsystem m_drivetrain = new DrivetrainSubsystem();

    //TODO Add drivetrain commands
    private final TeleopTankDriveCommand m_teleopTankDriveCommand = new TeleopTankDriveCommand(m_drivetrain);
    


    public RobotContainer() {
        configureButtonBindings();
        configureDefaultCommands();
    }

    private void configureButtonBindings() {

        //TODO add drivetrain command bindings (?)
        m_teleopTankDriveCommand.setSuppliers(() -> joystickDrive.getRawAxis(AxisF310.JoystickLeftY), () -> joystickDrive.getRawAxis(AxisF310.JoystickRightY));
    }

    private void configureDefaultCommands() {
        CommandScheduler.getInstance().setDefaultCommand(m_drivetrain, m_teleopTankDriveCommand);
    }

    public Command getAutonomousCommand() {
        return null;
    }
}
