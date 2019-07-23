package frc.robot.commands.teleopcommands;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;
import frc.robot.commands.universalcommands.PIDArm;

public class TeleopArmControl extends Command {

  private PIDArm mpArm;
  ArmSequence armSeq = new ArmSequence();

  public TeleopArmControl() {
  }

  @Override
  protected void initialize() {
    armSeq.start();
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
      mpArm = new PIDArm(0);
      mpArm.start();
    }
    if (OI.xButton.get()) {
      mpArm = new PIDArm(180);
      mpArm.start();
    }
    if (OI.aButton.get()) {
      mpArm = new PIDArm(60);
      mpArm.start();
    }
    if (OI.yButton.get()) {
      mpArm = new PIDArm(120);
      mpArm.start();
    }
  }

  @Override
  protected boolean isFinished() {
    return !RobotState.isOperatorControl();
  }

  @Override
  protected void end() {
    RobotMap.arm.setPower(0);
    RobotMap.arm.engageBrake();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
