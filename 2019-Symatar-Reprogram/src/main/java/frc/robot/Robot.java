package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.RobotState.ArmPosition;
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
    // System.out.println(RobotMap.sensor.getDistance());
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
    // new BlinkinSetColor(RobotMap.blinkin, 0.75).start();
  }

  @Override
  public void teleopPeriodic() {
    OI.xButton.whenPressed(new MotionMagicArm(ArmPosition.fGround));
    OI.bButton.whenPressed(new MotionMagicArm(ArmPosition.bGround));
    OI.aButton.whenPressed(new MotionMagicArm(ArmPosition.fSwitch));
    OI.yButton.whenPressed(new MotionMagicArm(ArmPosition.bSwitch));
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }
}
