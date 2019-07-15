package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.*;

public class Clapper extends Subsystem {

  @Override
  public void initDefaultCommand() {
  }

  public void closeClapper() {
    RobotMap.rightIntakePiston.set(RobotMap.openRightIntake);
    RobotMap.leftIntakePiston.set(RobotMap.openLeftIntake);
  }

  public void openClapper() {
    RobotMap.rightIntakePiston.set(RobotMap.closeRightIntake);
    RobotMap.leftIntakePiston.set(RobotMap.closeLeftIntake);
  }
  
  public void outtake() {
    if (OI.pilotController.getRawAxis(3) >= 0.5) {
      for (TalonSRX talon : RobotMap.intakeMotors) {
        talon.set(ControlMode.PercentOutput, 0.4);
      }
    }
  }

  public void intake() {
    if (OI.pilotController.getRawAxis(2) >= 0.5) {
      for (TalonSRX talon : RobotMap.intakeMotors) {
        talon.set(ControlMode.PercentOutput, -0.4);
      }
    }
  }
}
