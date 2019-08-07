package frc.robot.commands.universalcommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;
import frc.utility.PID;

public class PIDArm extends Command {

  private double endpoint;
  private double kp = 0.004;
  private double ki = 0.000075;
  private double kd = 0.011;
  private PID armPID = new PID(kp, ki, kd);
  private double currentAngle;

  public PIDArm(double angle) {
    endpoint = angle;
    requires(RobotMap.arm);
    armPID.setMaxOutput(0.3);
    armPID.setMinOutput(-0.3);
    setInterruptible(true);
  }

  @Override
  protected void initialize() {
    if (RobotMap.armMaster.getSensorCollection().isRevLimitSwitchClosed()) {
      RobotMap.armMaster.getSensorCollection().setQuadraturePosition(RobotConfig.armMaxEncoderTicks, RobotConfig.timeOut);
    }
    if (RobotMap.armMaster.getSensorCollection().isFwdLimitSwitchClosed()) {
      RobotMap.armMaster.getSensorCollection().setQuadraturePosition(0, RobotConfig.timeOut);
    }
    currentAngle = -(RobotMap.armMaster.getSensorCollection().getQuadraturePosition() / 2048.0) * 180;
    RobotMap.arm.disengageBrake();
    armPID.setSetPoint(endpoint);
  }

  @Override
  protected void execute() {
    currentAngle = -(RobotMap.armMaster.getSensorCollection().getQuadraturePosition() / 2048.0) * 180;
    armPID.updatePID(currentAngle);
    RobotMap.arm.setPower(-armPID.getResult());
  }

  @Override
  protected boolean isFinished() {
    return Math.abs(this.currentAngle - endpoint) < 5;
  }

  @Override
  protected void end() {
    RobotMap.arm.stopMotors();
    RobotMap.arm.engageBrake();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
