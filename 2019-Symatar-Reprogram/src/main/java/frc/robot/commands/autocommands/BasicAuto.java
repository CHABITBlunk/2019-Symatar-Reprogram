package frc.robot.commands.autocommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.commands.universalcommands.*;

public class BasicAuto extends CommandGroup {
  public BasicAuto() {
    addSequential(new HighGearMotionMagicMove(85, RobotMap.navx.getAngle(), 3050, 5000));
  }
}