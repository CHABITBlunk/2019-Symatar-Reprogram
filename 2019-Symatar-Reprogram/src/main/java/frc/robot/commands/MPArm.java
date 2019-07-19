package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

public class MPArm extends Command {

  private double endpoint;
	public static double currentAngle;
	private double startingAngle;
	private int run;
	private int angleTolerance;
	private double crateMultiplier = 0.25;
	private double startTime;
	private double minPower = 0.140;
  private double cosMultiplier = 0.122;
  
	public MPArm(double angle, int tolerance) {
    	endpoint = angle;
    	angleTolerance = tolerance; 	
      crateMultiplier = 0;
  }

  @Override
  protected void initialize() {
    startTime = Timer.getFPGATimestamp();
    startingAngle = -(RobotMap.armMaster.getSensorCollection().getQuadraturePosition() / 2048.0) * 180;
    run = 0;
    setInterruptible(true);
    RobotMap.arm.disengageBrake();
  }

  @Override
  protected void execute() {
    if (RobotMap.armMaster.getSensorCollection().isRevLimitSwitchClosed()) {
      RobotMap.armMaster.getSensorCollection().setQuadraturePosition(RobotConfig.armMaxEncoderTicks, RobotConfig.timeOut);
    }
    if (RobotMap.armMaster.getSensorCollection().isFwdLimitSwitchClosed()) {
      RobotMap.armMaster.getSensorCollection().setQuadraturePosition(0, RobotConfig.timeOut);
    }
    currentAngle = -(RobotMap.armMaster.getSensorCollection().getQuadraturePosition() / 2048.0) * 180;

    if (startingAngle < endpoint) {
      if (currentAngle + angleTolerance < endpoint) {
        RobotMap.arm.setPower(-minPower + crateMultiplier * (-cosMultiplier * Math.cos((-currentAngle * Math.PI) / 180)));
      } else end();
    } else if (startingAngle > endpoint) {
      if (currentAngle - angleTolerance > endpoint) {
        RobotMap.arm.setPower(minPower + crateMultiplier * (-cosMultiplier * Math.cos((-currentAngle * Math.PI) / 180)));
      } else end();
    }
  }

  @Override
  protected boolean isFinished() {
    return (currentAngle + angleTolerance > endpoint && currentAngle - angleTolerance < endpoint) || (OI.aButton.get() || OI.bButton.get() || OI.xButton.get() || OI.yButton.get());
  }

  @Override
  protected void end() {
    RobotMap.arm.stopMotors();
    RobotMap.arm.engageBrake();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
