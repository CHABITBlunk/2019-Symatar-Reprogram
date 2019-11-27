package frc.robot.commands.autocommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.tools.controlloops.PurePursuitController;
import frc.robot.tools.pathtools.PathList;

public class AutoPathfinder extends CommandGroup {
  private PathList pathList = new PathList();
  public AutoPathfinder() {
    addSequential(new PurePursuitController(pathList.curvedTest, 0.8, 1, true, true));
    addSequential(new PurePursuitController(pathList.curvedTestReturn, 0.8, 1, true, true));
  }
}
