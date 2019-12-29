package frc.robot.commands.universalcommands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;
import frc.robot.RobotState.ArmPosition;

public class MotionMagicArm extends Command {

  private double _endpoint;
  private double kf = 0.01;
  private double kp = 1.75;
  private double ki = 0.00003;
  private double kd = 100;
  private double cosine;
  private ArmPosition _pos;
  private double staticFrictionConstant = 0.15;

  public MotionMagicArm(ArmPosition pos) {
    setInterruptible(true);
    requires(RobotMap.arm);
    _pos = pos;
    switch (pos) {
      case fSwitch: _endpoint = Constants.fwdSwitchPreset; break;
      case bSwitch: _endpoint = Constants.revSwitchPreset; break;
      case fGround: _endpoint = 0; break;
      case bGround: _endpoint = Constants.armMaxEncoderTicks; break;
    }
    cosine = Math.cos(Constants.armTicksToRadians(_endpoint));
  }

  @Override
  protected void initialize() {
    RobotMap.armMaster.selectProfileSlot(0, 0);
    RobotMap.armMaster.config_kF(0, kf, 0);
    RobotMap.armMaster.config_kP(0, kp, 0);
    RobotMap.armMaster.config_kI(0, ki, 0);
    RobotMap.armMaster.config_kD(0, kd, 0);
    RobotMap.armMaster.configMotionCruiseVelocity(1000, 0);
    RobotMap.armMaster.configMotionAcceleration(500, 0);

    if (RobotMap.armMaster.getSensorCollection().isRevLimitSwitchClosed()) {
      RobotMap.armMaster.getSensorCollection().setQuadraturePosition(Constants.armMaxEncoderTicks, 0);
    }
    if (RobotMap.armMaster.getSensorCollection().isFwdLimitSwitchClosed()) {
      RobotMap.armMaster.getSensorCollection().setQuadraturePosition(0, 0);
    }

    RobotMap.arm.disengageBrake();
    RobotMap.armMaster.set(ControlMode.MotionMagic, _endpoint, DemandType.ArbitraryFeedForward, cosine * staticFrictionConstant);
    // new BlinkinSetColor(RobotMap.blinkin, 0.13).start();
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
    return Math.abs(RobotMap.armMaster.getSensorCollection().getQuadraturePosition() - _endpoint) < 100;
  }

  @Override
  protected void end() {
    /*
    switch(_pos) {
      case fGround: new BlinkinSetColor(RobotMap.blinkin, 0.57).start(); break;
      case fSwitch: new BlinkinSetColor(RobotMap.blinkin, 0.65).start(); break;
      case bGround: new BlinkinSetColor(RobotMap.blinkin, 0.67).start(); break;
      case bSwitch: new BlinkinSetColor(RobotMap.blinkin, 0.91).start(); break;
    }
    */
    RobotMap.arm.stop();
    RobotMap.arm.engageBrake();
  }

  @Override
  protected void interrupted() {
    end();
  }
}

