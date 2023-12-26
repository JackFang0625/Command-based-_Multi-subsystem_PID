package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
  private final CANSparkMax intake_motor = new CANSparkMax(6, MotorType.kBrushless);
  
  public IntakeSubsystem() {
    intake_motor.restoreFactoryDefaults();    
    intake_motor.setInverted(false);
  }

  public Command runIntakeCommand(double percent) {
    return run(() -> intake_motor.set(percent));
  }
}