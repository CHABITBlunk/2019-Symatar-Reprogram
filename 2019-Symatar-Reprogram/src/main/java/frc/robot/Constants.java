package frc.robot;

public class Constants {
  // Wheel and encoder presets
  public static double highGearEncoderToWheelRatio = 7.5;
  public static double lowGearEncoderToWheelRatio = 7.2;
  public static double encoderTicsPerShaftRotation = 4096;
  public static double wheelDiam = 6.0; 
  public static double wheelCircum = Math.PI * wheelDiam;
  public static double encoderTicsPerWheelRotation = highGearEncoderToWheelRatio * encoderTicsPerShaftRotation;
  public static double encoderTicsPerInch = wheelCircum / encoderTicsPerWheelRotation;

  // Drive train current/voltage limits
  public static double voltageControlMax = 11.0;
  public static int driveMotorContinuousCurrent = 1;
  public static int driveMotorPeakCurrent = 60;
  public static int driveMotorPeakCurrentDuration = 100;
  public static boolean teleop_enableDriveCurrentLimit = true;

  // Arm position presets
  public static int armMaxEncoderTicks = -2200;
  public static int revSwitchPreset = -700;
  public static int fwdSwitchPreset = armMaxEncoderTicks - revSwitchPreset;
    
  // Misc
  public static double driverDeadZone = 0.15;
  public static int timeOut = 1;

  //Integer IDs
  public static int rightDriveLeadID = 2;
  public static int leftDriveLeadID = 7;

  public static int rightDriveFollowerOneID = 1;
  public static int rightDriveFollowerTwoID = 3;
  public static int leftDriveFollowerOneID = 6; 
  public static int leftDriveFollowerTwoID = 8;

  public static int leftIntakeID = 9;
  public static int rightIntakeID = 10;

  public static int armMasterID = 5;
  public static int armFollowerID = 4;
}
