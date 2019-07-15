package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

public class TeleopArmControl extends Command {

  private MPArm mpArm;

  public TeleopArmControl() {
  }

  @Override
  protected void initialize() {
    mpArm = new MPArm(60, 0);
  }

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

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
