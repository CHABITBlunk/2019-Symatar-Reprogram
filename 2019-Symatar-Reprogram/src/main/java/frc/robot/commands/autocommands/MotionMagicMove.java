package frc.robot.commands.autocommands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

public class MotionMagicMove extends Command {

  private double endpoint;
  private float kFRight = (float) (0.9 * 1023) / 8750;
  private double kP = 1;
  private double kI = 0;
  private double kD = 0;
  private int kSlotIndex = 0;
  private int kPIDLoopIndex = 0;

  public MotionMagicMove(double feet) {
    setInterruptible(true);
    endpoint = (feet * 12) * Constants.encoderTicksPerInch;
  }

  @Override
  protected void initialize() {
    RobotMap.leftDriveLead.setSelectedSensorPosition(0, 0, 0);
    RobotMap.rightDriveLead.setSelectedSensorPosition(0, 0, 0);
    RobotMap.leftDriveLead.selectProfileSlot(kSlotIndex, kPIDLoopIndex);
    RobotMap.rightDriveLead.selectProfileSlot(kSlotIndex, kPIDLoopIndex);
    RobotMap.leftDriveLead.config_kF(kSlotIndex, kFRight);
    RobotMap.rightDriveLead.config_kF(kSlotIndex, kFRight);
    RobotMap.leftDriveLead.config_kP(kSlotIndex, kP);
    RobotMap.rightDriveLead.config_kP(kSlotIndex, kP);
    RobotMap.leftDriveLead.config_kI(kSlotIndex, kI);
    RobotMap.rightDriveLead.config_kI(kSlotIndex, kI);
    RobotMap.leftDriveLead.config_kD(kSlotIndex, kD);
    RobotMap.rightDriveLead.config_kD(kSlotIndex, kD);
    RobotMap.leftDriveLead.configMotionCruiseVelocity(8750, 0);
    RobotMap.rightDriveLead.configMotionCruiseVelocity(8750, 0);
    RobotMap.leftDriveLead.configMotionAcceleration(4375, 0);
    RobotMap.rightDriveLead.configMotionAcceleration(4375, 0);
    RobotMap.leftDriveLead.set(ControlMode.MotionMagic, endpoint);
    RobotMap.rightDriveLead.set(ControlMode.MotionMagic, endpoint);
  }

  @Override
  protected void execute() {

  }

  @Override
  protected boolean isFinished() {
    return RobotMap.leftDriveLead.isMotionProfileFinished() && RobotMap.rightDriveLead.isMotionProfileFinished();
  }

  @Override
  protected void end() {
    RobotMap.leftDriveLead.set(ControlMode.PercentOutput, 0);
    RobotMap.rightDriveLead.set(ControlMode.PercentOutput, 0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
