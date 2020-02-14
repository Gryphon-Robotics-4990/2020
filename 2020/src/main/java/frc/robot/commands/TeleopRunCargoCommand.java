package frc.robot.commands;

import frc.robot.subsystems.CargoSubsystem;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TeleopRunCargoCommand extends CommandBase {

    private final CargoSubsystem m_cargo;
    private DoubleSupplier m_supplier;

    public TeleopRunCargoCommand(CargoSubsystem cargo) {
        addRequirements(cargo);
        m_cargo = cargo;
    }

    public void setSupplier(DoubleSupplier ti) {
        m_supplier = ti;
    }

    @Override
    public void execute() {
        if (m_supplier.getAsDouble() > 0.05 || m_supplier.getAsDouble() < 0.05) m_cargo.run(m_supplier.getAsDouble());
        /*else if (m_in.getAsDouble() > 0.05) m_cargo.run(m_in.getAsDouble());
        else if (m_out.getAsDouble() > 0.05) m_cargo.run(m_out.getAsDouble());
        else m_cargo.run(0);*/
    }

}
