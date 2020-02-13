package frc.robot.commands;

import frc.robot.subsystems.StorageSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.*;

public class RunStorageCommand extends CommandBase {

    private final StorageSubsystem m_storage;
    private boolean lastLow, lastHigh;

    public RunStorageCommand(StorageSubsystem storage) {
        addRequirements(storage);
        m_storage = storage;
        lastLow = m_storage.hasBallLow();
        lastHigh = m_storage.hasBallHigh();
    }
    
    @Override
    public void execute() {
        //Storage low
        if (m_storage.getBallsLow() == 0) m_storage.runLow(false);
        else if (m_storage.getLastEntered() >= STORAGE_MINIMUM_BALL_SPACING_ENCODER_UNITS || m_storage.hasBallLow()) m_storage.runLow(true);
        else m_storage.runLow(false);

        if (lastLow && ! m_storage.hasBallLow()) m_storage.moveBall();

        //Storage high
        if (m_storage.getBallsHigh() == 0 || m_storage.hasBallHigh()) m_storage.runHigh(false);
        else if (m_storage.hasBallLow() && m_storage.getLastEntered() >= STORAGE_MINIMUM_BALL_SPACING_ENCODER_UNITS) m_storage.runHigh(true);
        else if (m_storage.getLastEntered() >= STORAGE_MINIMUM_BALL_SPACING_ENCODER_UNITS && m_storage.getBallsLow() > 0) m_storage.runHigh(false);
        else m_storage.runHigh(true);

        if (lastHigh && ! m_storage.hasBallHigh()) m_storage.removeBall();
    }

}
