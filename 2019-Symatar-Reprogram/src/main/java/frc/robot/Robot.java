package frc.robot;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.teleopcommands.*;
import frc.robot.commands.universalcommands.*;
import frc.robot.sensors.VisionCamera;

public class Robot extends TimedRobot {
  public ArcadeDrive driveTrain = new ArcadeDrive();
  public RobotConfig config = new RobotConfig();
  public static StopMotors stopMotors = new StopMotors();
  public ClapperCommand clapperCommand = new ClapperCommand();
  public static VisionCamera visionCamera;
  public static SerialPort jevois;
  private boolean hasCamera = false;

  @Override
  public void robotInit() {
    try {
      jevois = new SerialPort(115200, Port.kUSB1);
      if (jevois.getBytesReceived() > 2) {
        hasCamera = true;
      } else {
        hasCamera = true;
      }
    } catch (Exception ex) {
      hasCamera = false;
    }
    visionCamera = new VisionCamera(Robot.jevois);
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putBoolean("Is the JeVois online?", hasCamera);
    SmartDashboard.putNumber("Angle", visionCamera.getAngle());
    SmartDashboard.putNumber("Distance", visionCamera.getDistance());
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
