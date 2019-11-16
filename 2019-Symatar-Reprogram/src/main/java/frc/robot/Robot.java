package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.commands.autocommands.AutoPathfinder;
import frc.robot.commands.teleopcommands.*;
import frc.robot.commands.universalcommands.*;

public class Robot extends TimedRobot {
  public ArcadeDrive driveTrain = new ArcadeDrive();
  public RobotConfig config = new RobotConfig();
  public ClapperCommand clapperCommand = new ClapperCommand();

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
    RobotMap.drive.initVelocityPIDs();
    RobotMap.drive.startAutoOdometry(0, 0, 0);
    new AutoPathfinder().start();
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
  }

  @Override
  public void teleopPeriodic() {
    OI.xButton.whenPressed(new MotionMagicArm(0));
    OI.bButton.whenPressed(new MotionMagicArm(Constants.armMaxEncoderTicks));
    OI.aButton.whenPressed(new MotionMagicArm(Constants.fwdSwitchPreset));
    OI.yButton.whenPressed(new MotionMagicArm(Constants.revSwitchPreset));
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }
}
