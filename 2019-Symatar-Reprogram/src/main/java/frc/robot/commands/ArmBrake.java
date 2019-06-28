/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.RobotMap;

public class ArmBrake extends Command {
  public ArmBrake() {
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (OI.aButton.get()) {
      RobotMap.armBrake.set(RobotMap.disengageBrake);
      RobotMap.armMaster.set(ControlMode.PercentOutput, -0.20);
    } else if (OI.yButton.get()) {
      RobotMap.armBrake.set(RobotMap.disengageBrake);
      RobotMap.armMaster.set(ControlMode.PercentOutput, 0.20);
    }
    else if (!OI.aButton.get() || !OI.yButton.get()) {
      RobotMap.armBrake.set(RobotMap.engageBrake);
      RobotMap.armMaster.set(ControlMode.PercentOutput, 0);
    }
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
