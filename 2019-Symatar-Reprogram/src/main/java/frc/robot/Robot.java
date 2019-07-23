package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.commands.teleopcommands.*;
import frc.robot.commands.universalcommands.*;

public class Robot extends TimedRobot {
  public ArcadeDrive driveTrain = new ArcadeDrive();
  public RobotConfig config = new RobotConfig();
  public static StopMotors stopMotors = new StopMotors();
  public ClapperCommand clapperCommand = new ClapperCommand();
  public TeleopArmControl teleopArm = new TeleopArmControl();

  @Override
  public void robotInit() {
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    config.teleopConfig();
    driveTrain.start();
    clapperCommand.start();
    teleopArm.start();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }
}
