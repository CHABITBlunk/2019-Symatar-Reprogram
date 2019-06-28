/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.*;

/**
 * Add your docs here.
 */
public class Arm extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void disengageBrake() {
    RobotMap.armBrake.set(RobotMap.disengageBrake);
  }

  public void engageBrake() {
    RobotMap.armBrake.set(RobotMap.engageBrake);
  }

  public void armMoveForward() {
      disengageBrake();
      RobotMap.armMaster.set(ControlMode.PercentOutput, 0.2);
      if (RobotMap.armLimit.get()) {
        engageBrake();
        RobotMap.armMaster.set(ControlMode.PercentOutput, 0);
      }
  }

  public void armMoveBackward() {
    disengageBrake();
    RobotMap.armMaster.set(ControlMode.PercentOutput, -0.2);
    if (RobotMap.armLimit.get()) {
      engageBrake();
      RobotMap.armMaster.set(ControlMode.PercentOutput, 0);
    }
  }
}
