package frc.robot.commands.teleopcommands;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

public class ClapperCommand extends Command {

  public ClapperCommand() {
    requires(RobotMap.clapper);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if (OI.driverController.getRawAxis(2) >= 0.5) {
      RobotMap.clapper.outtake();
    } else if (OI.driverController.getRawAxis(3) >= 0.5) {
      RobotMap.clapper.intake();
    } else {
      RobotMap.clapper.stopMotors();
    }
    if (OI.startButton.get()) {
      RobotMap.clapper.openClapper();
    }
    else{
      RobotMap.clapper.closeClapper();
    }
  }

  @Override
  protected boolean isFinished() {
    return !RobotState.isOperatorControl();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
