package frc.robot.commands;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

public class MotionProfiledArmControl extends Command {
  private MPArm mpArm;
  public MotionProfiledArmControl() {
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    mpArm = new MPArm(60);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (RobotMap.armMaster.getSensorCollection().isRevLimitSwitchClosed()) {
      RobotMap.armMaster.getSensorCollection().setQuadraturePosition(RobotConfig.armMaxEncoderTicks, RobotConfig.timeOut);
    }

    if (RobotMap.armMaster.getSensorCollection().isFwdLimitSwitchClosed()) {
      RobotMap.armMaster.getSensorCollection().setQuadraturePosition(0, RobotConfig.timeOut);
    }

    if (OI.pilotController.getPOV() != -1) {
      switch (OI.pilotController.getPOV()) {
        case 45:
          mpArm = new MPArm(60);
          break;
        case 135:
          mpArm = new MPArm(120);
          break;
        case 0:
          mpArm = new MPArm(0);
          break;
        case 180:
          mpArm = new MPArm(180);
          break;
        default:
        break;
      }
      mpArm.start();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return !RobotState.isOperatorControl();
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
