package frc.robot.commands.universalcommands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.*;
import frc.utility.PID;

public class MotionMagicMove extends Command {

  private PID angleOrientation;

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
  private double endpoint;
  private double pGainLeft;
  private double pGainRight;


  public MotionMagicMove(double dist, double angle, double cruiseVelocity, int acceleration) {
    cruiseVelocityLeft = cruiseVelocity;
    cruiseVelocityRight = cruiseVelocity;
    initCruiseVelocityLeft = cruiseVelocityLeft;
    initCruiseVelocityRight = cruiseVelocityRight;
    accelerationLeft = acceleration;
    accelerationRight = acceleration;
    endpoint = dist;
    motionMagicEndPoint = ((-endpoint / RobotConfig.wheelCircum) * RobotConfig.encoderTicsPerWheelRotation);
    fGainLeft = 0.132404f;
    fGainRight = fGainLeft - 0.0185f;
    pGainLeft = 0;
    pGainRight = 0;
    desiredAngle = angle;
  }

  @Override
  protected void initialize() {
    RobotMap.leftDriveLead.setSelectedSensorPosition(0, 0, 0);
    RobotMap.rightDriveLead.setSelectedSensorPosition(0, 0, 0);

    RobotMap.shifters.set(RobotMap.lowGear);

    startTime = Timer.getFPGATimestamp();

    angleOrientation = new PID(0, 0, 0);
    angleOrientation.setContinuous(true);
    angleOrientation.setPID(10.0, 0.8, 0);

    angleOrientation.setSetPoint(RobotMap.navx.getAngle() + desiredAngle);
    angleOrientation.setMaxOutput(1500.0);
    angleOrientation.setMinOutput(-1500.0);
    
    RobotMap.rightDriveLead.set(ControlMode.MotionMagic, motionMagicEndPoint);
    RobotMap.leftDriveLead.set(ControlMode.MotionMagic, motionMagicEndPoint);

    RobotMap.leftDriveLead.config_kF(0, fGainLeft, 0);
    RobotMap.rightDriveLead.config_kF(0, fGainRight, 0);

    RobotMap.leftDriveLead.configMotionCruiseVelocity((int)(cruiseVelocityLeft * 4096) / 600, 0);
    RobotMap.rightDriveLead.configMotionCruiseVelocity((int)(cruiseVelocityRight * 4096) / 600, 0);

    RobotMap.rightDriveLead.configMotionAcceleration(accelerationRight * 4096 / 600, 0);
    RobotMap.leftDriveLead.configMotionAcceleration(accelerationLeft * 4096 / 600, 0);
    
    startAngle = RobotMap.navx.getAngle();

    for (TalonSRX t : RobotMap.driveMotors) {
      t.enableVoltageCompensation(true);
    }
  }

  @Override
  protected void execute() {
    angleOrientation.updatePID(RobotMap.navx.getAngle());
    if (motionMagicEndPoint > 0) {
      cruiseVelocityLeft = (float) (initCruiseVelocityLeft + angleOrientation.getResult());
      cruiseVelocityRight = (float) (initCruiseVelocityRight - angleOrientation.getResult());
    } else {
      cruiseVelocityLeft = (float) (initCruiseVelocityLeft - angleOrientation.getResult());
      cruiseVelocityRight = (float) (initCruiseVelocityRight + angleOrientation.getResult());
    }
    RobotMap.leftDriveLead.configMotionCruiseVelocity((int)cruiseVelocityLeft * 4096 / 600, 0);
    RobotMap.rightDriveLead.configMotionCruiseVelocity((int)cruiseVelocityLeft * 4096 / 600, 0);
  }

  @Override
  protected boolean isFinished() {
    if (Math.abs(RobotMap.leftDriveLead.getMotorOutputPercent()) <= 0.075 && Math.abs(RobotMap.rightDriveLead.getMotorOutputPercent()) <= 0.075 && Math.abs(Timer.getFPGATimestamp() - startTime) > 1) {
      return true;
    }
    return false;
  }

  @Override
  protected void end() {
    angleOrientation.setContinuous(false);
    RobotMap.leftDriveLead.set(ControlMode.PercentOutput, 0);
    RobotMap.rightDriveLead.set(ControlMode.PercentOutput, 0);
    for (TalonSRX t : RobotMap.driveMotors) {
      t.enableVoltageCompensation(false);
    }
    SmartDashboard.putNumber("How far right has travelled (ft):", RobotMap.rightDriveLead.getSensorCollection().getQuadraturePosition() * RobotConfig.encoderTicsPerInch / 12);
    SmartDashboard.putNumber("How far left has travelled (ft):", RobotMap.leftDriveLead.getSensorCollection().getQuadraturePosition() * RobotConfig.encoderTicsPerInch / 12);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
