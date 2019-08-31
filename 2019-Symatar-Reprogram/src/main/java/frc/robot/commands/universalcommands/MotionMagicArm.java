package frc.robot.commands.universalcommands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

public class MotionMagicArm extends Command {

  private double motionMagicEndpoint;
  private double kf = 0.01;
  private double kp = 1.75;
  private double ki = 0.00003;
  private double kd = 100;
  private double cosine;
  private double staticFrictionConstant = 0.15;
  private int kSlotIndex = 0;
  private int kPIDLoopIndex = 0;

  public MotionMagicArm(double endpoint) {
    setInterruptible(true);
    motionMagicEndpoint = endpoint;
    requires(RobotMap.arm);
    cosine = staticFrictionConstant * Math.cos(endpoint / Constants.armMaxEncoderTicks * Math.PI);
  }

  @Override
  protected void initialize() {
    RobotMap.armMaster.selectProfileSlot(kSlotIndex, kPIDLoopIndex);
    RobotMap.armMaster.config_kF(kSlotIndex, kf, 0);
    RobotMap.armMaster.config_kP(kSlotIndex, kp, 0);
    RobotMap.armMaster.config_kI(kSlotIndex, ki, 0);
    RobotMap.armMaster.config_kD(kSlotIndex, kd, 0);
    RobotMap.armMaster.configMotionCruiseVelocity(1000, 0);
    RobotMap.armMaster.configMotionAcceleration(500, 0);

    if (RobotMap.armMaster.getSensorCollection().isRevLimitSwitchClosed()) {
      RobotMap.armMaster.getSensorCollection().setQuadraturePosition(Constants.armMaxEncoderTicks, 0);
    }
    if (RobotMap.armMaster.getSensorCollection().isFwdLimitSwitchClosed()) {
      RobotMap.armMaster.getSensorCollection().setQuadraturePosition(0, 0);
    }

    RobotMap.arm.disengageBrake();
    RobotMap.armMaster.set(ControlMode.MotionMagic, motionMagicEndpoint, DemandType.ArbitraryFeedForward, cosine);
  }


  @Override
  protected void execute() {
    if (RobotMap.armMaster.getSensorCollection().isRevLimitSwitchClosed()) {
      RobotMap.armMaster.getSensorCollection().setQuadraturePosition(Constants.armMaxEncoderTicks, 0);
    }
    if (RobotMap.armMaster.getSensorCollection().isFwdLimitSwitchClosed()) {
      RobotMap.armMaster.getSensorCollection().setQuadraturePosition(0, 0);
    }
  }

  @Override
  protected boolean isFinished() {
    return Math.abs(RobotMap.armMaster.getSensorCollection().getQuadraturePosition() - motionMagicEndpoint) < 100;
  }

  @Override
  protected void end() {
    RobotMap.arm.stop();
    RobotMap.arm.engageBrake();
  }

  @Override
  protected void interrupted() {
    end();
  }
}

