package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.*;
import frc.robot.utils.PID;

public class DriveTrain extends Subsystem {

  private PID alignment;
  private double p = 0.005;
  private double i = 0;
  private double d = 0;

  public DriveTrain() {
    initPID();
  }

  @Override
  public void initDefaultCommand() {
  }

  private void initPID() {
    alignment = new PID(p, i, d);
    alignment.setMinOutput(-0.4);
    alignment.setMaxOutput(0.4);
    alignment.setSetPoint(0);
  }

  public void setLowGear() {
    RobotMap.shifters.set(RobotMap.lowGear);
  }

  public void setHighGear() {
    RobotMap.shifters.set(RobotMap.highGear);
  }

  public double getPIDOutput() {
    Robot.visionCamera.updateVision();
    if (Timer.getFPGATimestamp() - Robot.visionCamera.lastParseTime > 0.25) {
      SmartDashboard.putBoolean("Has Camera Values", false);
      return 0;
    } else {
      SmartDashboard.putBoolean("Has Camera Values", true);
      if (Robot.visionCamera.getAngle() > 25) {
        return 0;
      }
      alignment.updatePID(Robot.visionCamera.getAngle());
      return alignment.getResult();
    }
  }

  public void setPowerPercent(double power) {
    RobotMap.leftDriveLead.set(ControlMode.PercentOutput, power);
    RobotMap.rightDriveLead.set(ControlMode.PercentOutput, power);
  }

  public void stopMotors() {
    RobotMap.leftDriveLead.set(ControlMode.PercentOutput, 0);
    RobotMap.rightDriveLead.set(ControlMode.PercentOutput, 0);
  }
}
