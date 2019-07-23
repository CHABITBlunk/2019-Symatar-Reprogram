package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.*;

public class Clapper extends Subsystem {

  @Override
  public void initDefaultCommand() {
  }

  public void openClapper() {
      RobotMap.rightIntakePiston.set(RobotMap.openRightIntake);
      RobotMap.leftIntakePiston.set(RobotMap.openLeftIntake);
  }
  public void closeClapper(){
    RobotMap.rightIntakePiston.set(RobotMap.closeRightIntake);
    RobotMap.leftIntakePiston.set(RobotMap.closeLeftIntake);
  }
  
  public void outtake() {
      for (TalonSRX talon : RobotMap.intakeMotors) {
        talon.set(ControlMode.PercentOutput, 0.4);
    }
  }

  public void stopMotors() {
    for (TalonSRX talon : RobotMap.intakeMotors) {
      talon.set(ControlMode.PercentOutput, 0);
    }
  }

  public void intake() {
      for (TalonSRX talon : RobotMap.intakeMotors) {
        talon.set(ControlMode.PercentOutput, -0.4);
    }
  }
}
