package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
    private final CANSparkMax arm_motor = new CANSparkMax(5, MotorType.kBrushless);
    private SparkMaxPIDController arm_pidController = arm_motor.getPIDController();
    private RelativeEncoder arm_encoder = arm_motor.getEncoder();

    public double kP = 0.32, kI = 0, kD = 0, kIz = 0, kFF = 0, kMaxOutput = 0.5, kMinOutput = -0.5;
    public int smartMotionSlot = 0;

    public ArmSubsystem() {
        arm_motor.restoreFactoryDefaults();
        arm_motor.setInverted(false);

        arm_pidController.setP(kP, smartMotionSlot);
        arm_pidController.setI(kI, smartMotionSlot);
        arm_pidController.setD(kD, smartMotionSlot);
        arm_pidController.setIZone(kIz, smartMotionSlot);
        arm_pidController.setFF(kFF, smartMotionSlot);
        arm_pidController.setOutputRange(kMinOutput, kMaxOutput, smartMotionSlot);
    }

    public void periodic(){
        SmartDashboard.putNumber("Encoder Value", arm_encoder.getPosition());
    }
    
    public Command resetEncoder() {
        return runOnce( () -> arm_encoder.setPosition(0) );
    }

    public Command runArmCommand(double percent) {
        return run( () -> arm_motor.set(percent) );
    }

    public Command pidArmCommand(double point) {
        return run( () -> arm_pidController.setReference(point, CANSparkMax.ControlType.kPosition) );
    }
}