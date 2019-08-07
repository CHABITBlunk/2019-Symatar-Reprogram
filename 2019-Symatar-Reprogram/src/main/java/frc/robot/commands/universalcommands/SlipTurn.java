package frc.robot.commands.universalcommands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;
import frc.robot.commands.universalcommands.StopMotors;
import frc.utility.PID;

public class SlipTurn extends Command {

  private double speed = 0;
  private double time;
  private double target;
  private double kp = 0.00035;
  private double ki = 0.00024;
  private double kd = 0;
  private PID orientation;
  private double startAngle;
  private int zeroed;
  private float turnPower;
  private double fwdSpeed;
  private boolean across = false;
  private int run;

  public SlipTurn(double angle, float power, double fwdSpeed) {
    target = angle;
    this.fwdSpeed = fwdSpeed;
    orientation = new PID(kp, ki, kd);
    orientation.setContinuous(true);
    orientation.setMaxOutput(power);
    orientation.setMinOutput(-power);
  }

  @Override
  protected void initialize() {
    startAngle = RobotMap.navx.getAngle();
    run = 0;
    orientation.setSetPoint(startAngle + target);
  }

  @Override
  protected void execute() {
    if (RobotMap.analog.getValue() > 240) {
      StopMotors.stopIntakeMotors();
    } else {
      RobotMap.leftIntake.set(ControlMode.PercentOutput, -0.2);
      RobotMap.rightIntake.set(ControlMode.PercentOutput, -0.2);
    }
    orientation.updatePID(RobotMap.navx.getAngle());
    RobotMap.leftDriveLead.set(ControlMode.PercentOutput, -fwdSpeed - orientation.getResult());
    RobotMap.rightDriveLead.set(ControlMode.PercentOutput, -fwdSpeed + orientation.getResult());
  }

  @Override
  protected boolean isFinished() {
    if (Math.abs(RobotMap.navx.getAngle() - (startAngle + target)) <= 2) {
      return true;
    }
    return false;
  }

  @Override
  protected void end() {
    RobotMap.leftDriveLead.set(ControlMode.PercentOutput, 0);
    RobotMap.rightDriveLead.set(ControlMode.PercentOutput, 0);
    orientation.setSetPoint(RobotMap.navx.getAngle());
  }

  @Override
  protected void interrupted() {
    end();
  }
}
