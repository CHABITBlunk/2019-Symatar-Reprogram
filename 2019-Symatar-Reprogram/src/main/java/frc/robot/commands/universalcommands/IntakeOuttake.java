package frc.robot.commands.universalcommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

public class IntakeOuttake extends Command {

  private double waitTime;
  private IntakeType intakeType;
  private double startTime;

  public static enum IntakeType {
    intake, outtake
  }

  public IntakeOuttake(double seconds, IntakeType type) {
    requires(RobotMap.clapper);
    waitTime = seconds;
    intakeType = type;
  }

  @Override
  protected void initialize() {
    startTime = Timer.getFPGATimestamp();
  }

  @Override
  protected void execute() {
    switch(intakeType) {
      case intake:
        RobotMap.clapper.intake();
        break;
      case outtake:
        RobotMap.clapper.outtake();
        break;
      }
  }

  @Override
  protected boolean isFinished() {
    return Timer.getFPGATimestamp() >= startTime + waitTime;
  }

  @Override
  protected void end() {
    StopMotors.stopIntakeMotors();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
