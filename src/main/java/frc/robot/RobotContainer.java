package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  private final ArmSubsystem m_armSubsystem = new ArmSubsystem();
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();

  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    m_driverController.b().whileTrue(Commands.sequence(m_armSubsystem.pidArmCommand(10)
      , Commands.parallel(m_intakeSubsystem.runIntakeCommand(0.3)
      , m_armSubsystem.pidArmCommand(37) ) ) )
    
    .whileFalse(Commands.parallel(m_armSubsystem.runArmCommand(0)
      , m_intakeSubsystem.runIntakeCommand(0)));

    m_driverController.y().onTrue(m_armSubsystem.resetEncoder());
  }
  
}
