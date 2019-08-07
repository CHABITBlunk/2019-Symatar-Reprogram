package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.teleopcommands.*;
import frc.robot.commands.universalcommands.*;
import frc.robot.commands.autocommands.*;

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
    SmartDashboard.putNumber("How far right has travelled (ft):", RobotMap.rightDriveLead.getSensorCollection().getQuadraturePosition() * RobotConfig.encoderTicsPerInch / 12);
    SmartDashboard.putNumber("How far left has travelled (ft):", -RobotMap.leftDriveLead.getSensorCollection().getQuadraturePosition() * RobotConfig.encoderTicsPerInch / 12);
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
    new BasicAuto().start();
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
