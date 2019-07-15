package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

public class TeleopArmControl extends Command {

  private MPArm mpArm;

  public TeleopArmControl() {
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    mpArm = new MPArm(60, 0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (!mpArm.isRunning()) {
      if (OI.bButton.get()) {
        mpArm = new MPArm(0, 0);
      }
      if (OI.xButton.get()) {
        mpArm = new MPArm(180, 0);
      }
      if (OI.aButton.get()) {
        mpArm = new MPArm(60, 15);
      }
      if (OI.yButton.get()) {
        mpArm = new MPArm(120, 15);
      }
      mpArm.start();
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
    end();
  }
}
