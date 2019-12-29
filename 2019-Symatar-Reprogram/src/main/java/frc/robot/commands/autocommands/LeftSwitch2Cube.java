package frc.robot.commands.autocommands;

import edu.wpi.first.wpilibj.command.*;
import frc.robot.commands.universalcommands.*;
import frc.robot.commands.universalcommands.ClapperOpenClose.ClapperType;
import frc.robot.commands.universalcommands.IntakeOuttake.IntakeType;
import frc.robot.tools.controlloops.*;
import frc.robot.tools.pathtools.*;
import java.io.File;

public class LeftSwitch2Cube extends CommandGroup {
  private File leftSwitch1 = new File("/home/lvuser/deploy/LeftSwitch1.pf1.csv");
  private File leftSwitchReturn = new File("/home/lvuser/deploy/LeftSwitchReturn.pf1.csv");
  private File towardCube = new File("/home/lvuser/deploy/TowardCube.pf1.csv");
  private File towardSpawn = new File("/home/lvuser/deploy/TowardSpawn.pf1.csv");
  private File leftSwitch2 = new File("/home/lvuser/deploy/LeftSwitch2.pf1.csv");

  public LeftSwitch2Cube() {
    addSequential(new PurePursuitController(new PathSetup(leftSwitch1, true), 2, 1, true, true));
    addSequential(new IntakeOuttake(0.5, IntakeType.outtake));
    addSequential(new PurePursuitController(new PathSetup(leftSwitchReturn, false), 2, 1, true, true));
    addSequential(new ClapperOpenClose(ClapperType.open));
    addSequential(new PurePursuitController(new PathSetup(towardCube, true), 1, 1, true, true));
    addSequential(new IntakeOuttake(2, IntakeType.intake));
    addSequential(new ClapperOpenClose(ClapperType.open));
    addSequential(new PurePursuitController(new PathSetup(towardSpawn, true), 1, 1, true, true));
    addSequential(new PurePursuitController(new PathSetup(leftSwitch2, true), 2, 1, true, true));
    addSequential(new IntakeOuttake(0.5, IntakeType.outtake));
  }
}
