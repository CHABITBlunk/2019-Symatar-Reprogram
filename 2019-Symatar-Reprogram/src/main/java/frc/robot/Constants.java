package frc.robot;

public class Constants {
  // Wheel and encoder presets
  public static double lowGearEncoderToWheelRatio = 7.81;
  public static double encoderTicsPerShaftRotation = 4096;
  public static double wheelDiam = 6.0; 
  public static double wheelCircum = Math.PI * wheelDiam;
  public static double encoderTicksPerWheelRotation = lowGearEncoderToWheelRatio * encoderTicsPerShaftRotation;
  public static double encoderTicksPerInch = wheelCircum / encoderTicksPerWheelRotation;

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

  // Pathfinder stuff
  public static double maxVelocity = 14;
  public static double maxAccel = 7;
  public static double maxJerk = 3.5;
  public static double baseDistance = 2.7;

  // Conversion methods
  public static double armTicksToRadians(double ticks) {
    return (ticks / armMaxEncoderTicks) * Math.PI;
  }

  public static int fpsToTicksPer100Ms(double fps) {
    return  feetToTicks(fps / 10);
  }
  
  public static double ticksPer100MsToFps(int ticksPer100Ms) {
    return ticksToFeet(ticksPer100Ms * 10);
  }

  public static double ticksToFeet(int ticks) {
    return ticks * encoderTicksPerInch / 12;
  }

  public static int feetToTicks(double feet) {
    return (int) (feet / encoderTicksPerInch * 12);
  }
}
