package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

public class ManualArm extends Command {

  public ManualArm() {
    
  }

  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (OI.aButton.get()) {
      RobotMap.arm.moveRev();
    }
    if (OI.yButton.get()) {
      RobotMap.arm.moveFwd();
    } else if (!OI.aButton.get() && !OI.yButton.get()) {
      RobotMap.arm.stopMotors();
      RobotMap.arm.engageBrake();
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
