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
	private double minPower = 0.280;
	private double cosMultiplier = 0.122;
	public MPArm(double angle, int tolerance) {
    	endpoint = angle;
    	angleTolerance = tolerance; 	
    	crateMultiplier = 0;
    }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    startTime = Timer.getFPGATimestamp();
    startingAngle = -(RobotMap.armMaster.getSensorCollection().getQuadraturePosition() / 2048.0) * 180;
    run = 0;
    RobotMap.arm.disengageBrake();
  }

  @Override
  protected void execute() {
    if (RobotMap.armMaster.getSensorCollection().isFwdLimitSwitchClosed()) {
      RobotMap.armMaster.getSensorCollection().setQuadraturePosition(RobotConfig.armMaxEncoderTicks, RobotConfig.timeOut);
    }
    if (RobotMap.armMaster.getSensorCollection().isRevLimitSwitchClosed()) {
      RobotMap.armMaster.getSensorCollection().setQuadraturePosition(0, RobotConfig.timeOut);
    }
    currentAngle = -(RobotMap.armMaster.getSensorCollection().getQuadraturePosition() / 2048.0) * 180;

    if (startingAngle < endpoint) {
      if (currentAngle + angleTolerance < endpoint) {
        System.out.println("Power: " + -minPower + crateMultiplier * (-cosMultiplier * Math.cos((-currentAngle * Math.PI) / 180)));
        //RobotMap.arm.setPower(-minPower + crateMultiplier * (-cosMultiplier * Math.cos((-currentAngle * Math.PI) / 180)));
      } else {
        run++;
        RobotMap.arm.engageBrake();
        RobotMap.arm.stopMotors();
      }
    } else if (startingAngle > endpoint) {
      if (currentAngle - angleTolerance > endpoint) {
        System.out.println("Power: " + minPower + crateMultiplier * (-cosMultiplier * Math.cos((-currentAngle * Math.PI) / 180)));
        //RobotMap.arm.setPower(minPower + crateMultiplier * (-cosMultiplier * Math.cos((-currentAngle * Math.PI) / 180)));
      } else {
        run++;
        RobotMap.arm.engageBrake();
        RobotMap.arm.stopMotors();
      }
    }
    System.out.println("Is finished: " + isFinished());
    System.out.println("Current angle: " + currentAngle);
    System.out.println("Quadrature position: " + RobotMap.armMaster.getSensorCollection().getQuadraturePosition());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (RobotMap.armMaster.getMotorOutputPercent() == 0 && run != 0) || (Math.abs(Timer.getFPGATimestamp() - startTime) > 5.00) || (OI.xButton.get() || OI.aButton.get() || OI.yButton.get() || OI.bButton.get() && Math.abs(Timer.getFPGATimestamp() - startTime) > 0.25);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    RobotMap.arm.engageBrake();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
