package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.*;

public class DriveTrain extends Subsystem {
  @Override
  public void initDefaultCommand() {
  }

  public void setPowerPercent(double power) {
    RobotMap.leftDriveLead.set(ControlMode.PercentOutput, power);
    RobotMap.rightDriveLead.set(ControlMode.PercentOutput, power);
  }
}
