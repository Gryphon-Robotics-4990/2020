package frc.robot;

import frc.robot.JoystickF310.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import static frc.robot.Constants.*;
import io.github.oblarg.oblog.Logger;

public class RobotContainer {

    private final JoystickF310 joystickDrive = new JoystickF310(PORT_JOYSTICK_DRIVE);
    private final JoystickF310 joystickOperator = new JoystickF310(PORT_JOYSTICK_OPERATOR);

<<<<<<< Updated upstream
    private final DrivetrainSubsystem m_drivetrain = new DrivetrainSubsystem();
    private final StorageSubsystem m_storage  = new StorageSubsystem();

    //TODO Add drivetrain commands
    private final TeleopTankDriveCommand m_teleopTankDriveCommand = new TeleopTankDriveCommand(m_drivetrain);
    

=======
    //private final DrivetrainSubsystem m_drivetrain = new DrivetrainSubsystem();
    private final CargoSubsystem m_cargo = new CargoSubsystem();

    //TODO Add drivetrain commands
    //private final TeleopTankDriveCommand m_teleopTankDriveCommand = new TeleopTankDriveCommand(m_drivetrain);
    private final TeleopRunCargoCommand m_teleopRunCargoCommand = new TeleopRunCargoCommand(m_cargo);
    
    //private final Solenoid testSolenoid = new Solenoid(12,1);
>>>>>>> Stashed changes

    public RobotContainer() {
        configureButtonBindings();
        configureDefaultCommands();
<<<<<<< Updated upstream
        Logger.configureLoggingAndConfig(this, false);
    }

    private void configureButtonBindings() {

        m_teleopTankDriveCommand.setSuppliers(() -> joystickDrive.getRawAxis(AxisF310.JoystickLeftY), () -> joystickDrive.getRawAxis(AxisF310.JoystickRightY));
    }

    private void configureDefaultCommands() {
        CommandScheduler.getInstance().setDefaultCommand(m_drivetrain, m_teleopTankDriveCommand);
=======
        //testSolenoid.set(false);
    }

    private void configureButtonBindings() {
        //m_teleopTankDriveCommand.setSuppliers(() -> joystickDrive.getRawAxis(AxisF310.JoystickLeftY), () -> joystickDrive.getRawAxis(AxisF310.JoystickRightY));
        m_teleopRunCargoCommand.setSupplier(() -> joystickDrive.getRawAxis(AxisF310.JoystickLeftY));
    }

    private void configureDefaultCommands() {
        //CommandScheduler.getInstance().setDefaultCommand(m_drivetrain, m_teleopTankDriveCommand);
        CommandScheduler.getInstance().setDefaultCommand(m_cargo, m_teleopRunCargoCommand);
>>>>>>> Stashed changes
    }

    public Command getAutonomousCommand() {
        return null;
    }

    public void updateLoggerEntries() {
        Logger.updateEntries();
    }
}
