package frc.robot.commands.autocommands;

import edu.wpi.first.wpilibj.command.*;
import frc.robot.*;
import frc.robot.tools.controlloops.*;
import frc.robot.commands.universalcommands.*;
import frc.robot.tools.pathtools.*;
import frc.robot.commands.universalcommands.IntakeOuttake.IntakeType;
import java.io.File;


public class RightSwitch2Cube extends CommandGroup {

  private File rightSwitch1 = new File("/home/lvuser/deploy/RightSwitch1.pf1.csv");
  private File rightSwitchReturn = new File("/home/lvuser/deploy/RightSwitchReturn.pf1.csv");
  private File towardCube = new File("/home/lvuser/deploy/TowardCube.pf1.csv");
  private File towardSpawn = new File("/home/lvuser/deploy/TowardSpawn.pf1.csv");
  private File rightSwitch2 = new File("/home/lvuser/deploy/RightSwitch2.pf1.csv");

  public RightSwitch2Cube() {
    addSequential(new PurePursuitController(new PathSetup(rightSwitch1, true), 1, 1, true, true));
    addSequential(new IntakeOuttake(0.5, IntakeType.outtake));
    addSequential(new PurePursuitController(new PathSetup(rightSwitchReturn, true), 1, 1, true, true));
    addSequential(new PurePursuitController(new PathSetup(towardCube, true), 1, 1, true, true));
    RobotMap.clapper.openClapper();
    addSequential(new IntakeOuttake(2, IntakeType.intake));
    RobotMap.clapper.closeClapper();
    addSequential(new PurePursuitController(new PathSetup(towardSpawn, true), 1, 1, true, true));
    addSequential(new PurePursuitController(new PathSetup(rightSwitch2, true), 1, 1, true, true));
    addSequential(new IntakeOuttake(0.5, IntakeType.outtake));
  }
}
