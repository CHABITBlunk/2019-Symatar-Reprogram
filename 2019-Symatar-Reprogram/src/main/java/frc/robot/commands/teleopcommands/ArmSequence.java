package frc.robot.commands.teleopcommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.universalcommands.PIDArm;

public class ArmSequence extends CommandGroup {
  public ArmSequence() {
    addSequential(new PIDArm(0));
    addSequential(new WaitCommand(1));
    addSequential(new PIDArm(180));
    addSequential(new WaitCommand(1));
    addSequential(new PIDArm(60));
    addSequential(new WaitCommand(1));
    addSequential(new PIDArm(120));
  }
}
