package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.PID;
import frc.robot.RobotConfig;
import frc.robot.RobotMap;

public class MPArm extends Command {
  private double kp = 0.004;
  private double ki = 0.000075;
  private double kd = 0.011;
  private PID armPid = new PID(kp, ki, kd);
  private double endpoint;
  private double currentPosition;
  private double startTime;

  public MPArm(double angle) {
    endpoint = angle;
    requires(RobotMap.arm);
    armPid.setMaxOutput(0.3);
    armPid.setMinOutput(-0.3);
    setInterruptible(true);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    startTime = Timer.getFPGATimestamp();
    armPid.setSetPoint(endpoint);

    if (RobotMap.armMaster.getSensorCollection().isRevLimitSwitchClosed()) {
      RobotMap.armMaster.getSensorCollection().setQuadraturePosition(RobotConfig.armMaxEncoderTicks, 0);
    }
    if (RobotMap.armMaster.getSensorCollection().isFwdLimitSwitchClosed()) {
      RobotMap.armMaster.getSensorCollection().setQuadraturePosition(0, 0);
    }
    currentPosition = -(RobotMap.armMaster.getSensorCollection().getQuadraturePosition() / 2048.0) * 180;
    armPid.updatePID(currentPosition);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    RobotMap.armMaster.set(ControlMode.PercentOutput, -armPid.getResult());
    currentPosition = -(RobotMap.armMaster.getSensorCollection().getQuadraturePosition() / 2048.0) * 180;
    armPid.updatePID(currentPosition);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(currentPosition - endpoint) < 5 || (Timer.getFPGATimestamp() - startTime) > 5;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    RobotMap.armMaster.set(ControlMode.PercentOutput, 0);
    RobotMap.armBrake.set(RobotMap.engageBrake);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
