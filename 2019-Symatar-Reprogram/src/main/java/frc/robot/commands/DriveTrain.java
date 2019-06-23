/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

public class DriveTrain extends Command {

  public boolean highGear = false;

  public DriveTrain() {
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() { 
    if (Math.abs(OI.joystickOne.getRawAxis(1)) > RobotConfig.driverDeadZone) {
      RobotMap.leftDriveLead.set(ControlMode.PercentOutput, OI.joystickOne.getRawAxis(1));
    } else {
      RobotMap.leftDriveLead.set(ControlMode.PercentOutput, 0);
    }
    if (Math.abs(OI.joystickOne.getRawAxis(5)) > RobotConfig.driverDeadZone) {
      RobotMap.rightDriveLead.set(ControlMode.PercentOutput, OI.joystickOne.getRawAxis(5));
    } else {
      RobotMap.rightDriveLead.set(ControlMode.PercentOutput, 0);
    }

    if (highGear && OI.shift.get()) {
      RobotMap.shifters.set(RobotMap.lowGear);
      highGear = false;
      new Wait(0.5).start();
    } else if (!highGear && OI.shift.get()) {
      RobotMap.shifters.set(RobotMap.highGear);
      highGear = true;
      new Wait(0.5).start();
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
    RobotMap.rightDriveLead.set(ControlMode.PercentOutput, 0);
    RobotMap.leftDriveLead.set(ControlMode.PercentOutput, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
