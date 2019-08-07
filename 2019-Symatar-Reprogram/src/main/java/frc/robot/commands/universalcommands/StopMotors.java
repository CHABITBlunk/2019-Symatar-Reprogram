
package frc.robot.commands.universalcommands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.RobotMap;

public class StopMotors  {
  public StopMotors() {

  }
  public static void stopDriveTrainMotors() {
    for (TalonSRX talon : RobotMap.driveMotors) {
      talon.set(ControlMode.PercentOutput, 0);
    }
  }

  public static void stopIntakeMotors() {
    for (TalonSRX talon : RobotMap.intakeMotors) {
      talon.set(ControlMode.PercentOutput, 0);
    }
  }

  public static void stopAllMotors() {
    for (TalonSRX talon : RobotMap.allMotors) {
      talon.set(ControlMode.PercentOutput, 0);
    }
  }
}
