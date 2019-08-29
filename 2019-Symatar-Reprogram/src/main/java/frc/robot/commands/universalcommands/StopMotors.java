
package frc.robot.commands.universalcommands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.RobotMap;

public class StopMotors  {
  public StopMotors() {

  }
  public void stopDriveTrainMotors() {
    for (TalonSRX talon : RobotMap.driveMotors) {
      talon.set(ControlMode.PercentOutput, 0);
    }
  }

  public void stopIntakeMotors() {
    for (TalonSRX talon : RobotMap.intakeMotors) {
      talon.set(ControlMode.PercentOutput, 0);
    }
  }

  public void stopAllMotors() {
    for (TalonSRX talon : RobotMap.allMotors) {
      talon.set(ControlMode.PercentOutput, 0);
    }
  }
}
