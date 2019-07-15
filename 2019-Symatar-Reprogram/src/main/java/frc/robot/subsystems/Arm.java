package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.*;

public class Arm extends Subsystem {

  @Override
  public void initDefaultCommand() {
  }

  public void disengageBrake() {
    RobotMap.armBrake.set(RobotMap.disengageBrake);
  }

  public void engageBrake() {
    RobotMap.armBrake.set(RobotMap.engageBrake);
  }

  public void moveFwd() {
    disengageBrake();
    RobotMap.armMaster.set(ControlMode.PercentOutput, -0.08);
  }

  public boolean[] limitSwitchesStatus() {
    boolean fwd = true;
    boolean rev = true;
    if (RobotMap.armMaster.getSensorCollection().isRevLimitSwitchClosed()) {
      fwd = false;
    }
    else if (RobotMap.armMaster.getSensorCollection().isFwdLimitSwitchClosed()) {
      rev = false;
    }
    return new boolean[] {fwd, rev};
  }

  public void moveRev() {
    disengageBrake();
    RobotMap.armMaster.set(ControlMode.PercentOutput, 0.08);
  }

  public void stopMotors() {
    RobotMap.armMaster.set(ControlMode.PercentOutput, 0);
  }

  public void setPower(double power) {
    RobotMap.armMaster.set(ControlMode.PercentOutput, power);
  }
}
