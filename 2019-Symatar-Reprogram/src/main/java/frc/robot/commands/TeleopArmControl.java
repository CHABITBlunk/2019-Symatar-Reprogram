package frc.robot.commands;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

public class TeleopArmControl extends Command {

  private MPArm mpArm;

  public TeleopArmControl() {
  }

  @Override
  protected void initialize() {
    mpArm = new MPArm(60, 15);
    mpArm.start();
  }

  @Override
  protected void execute() {
    /* Arm button map:
      X goes to the air-tank side in intake/vault position
      B goes to the battery side in intake/vault position
      A goes to the air-tank side in switch position
      Y goes to the battery side in switch position
    */

    if (OI.bButton.get()) {
      mpArm = new MPArm(0, 5);
      mpArm.start();
    }
    if (OI.xButton.get()) {
      mpArm = new MPArm(180, 5);
      mpArm.start();
    }
    if (OI.aButton.get()) {
      mpArm = new MPArm(60, 15);
      mpArm.start();
    }
    if (OI.yButton.get()) {
      mpArm = new MPArm(120, 15);
      mpArm.start();
    }
  }

  @Override
  protected boolean isFinished() {
    return !RobotState.isOperatorControl();
  }

  @Override
  protected void end() {
    RobotMap.arm.disengageBrake();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
