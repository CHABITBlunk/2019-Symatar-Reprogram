package frc.robot.commands.universalcommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;

public class ClapperOpenClose extends Command {
  public static enum ClapperType {
    open, close
  }
  private ClapperType _type;
  public ClapperOpenClose(ClapperType type) {
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    if (_type == ClapperType.open) {
      RobotMap.clapper.openClapper();
    } else {
      RobotMap.clapper.closeClapper();
    }
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
