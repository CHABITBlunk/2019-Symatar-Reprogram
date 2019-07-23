package frc.robot.commands.autocommands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;
import frc.utility.PID;

public class MotionMagicMoveFwd extends Command {

  private PID angleOrientation;
  private float nativeUnitsPerCycleLeft;
  private float nativeUnitsPerCycleRight;

  private float fGainLeft;
  private float fGainRight;
  private double motionMagicEndPoint;
  private double startTime;
  private double cruiseVelocityLeft;
  private double cruiseVelocityRight;
  private int accelerationLeft;
  private int accelerationRight;
  private double initCruiseVelocityLeft = cruiseVelocityLeft;
  private double initCruiseVelocityRight = cruiseVelocityRight;
  private double startAngle;
  private double desiredAngle;
  private double pGainLeft;
  private double pGainRight;


  public MotionMagicMoveFwd(double dist, double angle, double cruiseVelocity, int acceleration) {
    cruiseVelocityLeft = cruiseVelocity;
    cruiseVelocityRight = cruiseVelocity;
    initCruiseVelocityLeft = cruiseVelocityLeft;
    initCruiseVelocityRight = cruiseVelocityRight;
    accelerationLeft = acceleration;
    accelerationRight = acceleration;
    motionMagicEndPoint = ((dist / RobotConfig.wheelCircum) * RobotConfig.encoderTicsPerWheelRotation);

    fGainLeft = 0.046458f;
    fGainRight = fGainLeft;
    pGainLeft = 0;
    pGainRight = 0;
    desiredAngle = angle;
  }

  @Override
  protected void initialize() {
    RobotMap.shifters.set(RobotMap.highGear);

    startTime = Timer.getFPGATimestamp();
    RobotMap.leftDriveLead.configVoltageCompSaturation(RobotConfig.voltageControlMax, 10);
    RobotMap.
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
