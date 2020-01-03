package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.sensors.i2c.*;
import frc.robot.sensors.pwm.*;
import frc.robot.subsystems.*;

public class RobotMap {
  
  // Arm limit switch
  public static AnalogInput analog = new AnalogInput(0);
  public static DigitalInput armLimit = new DigitalInput(0);

  // Relays/LED Controllers
  public static PWM blinkin = new PWM(2);

  // PWM sensors
  private static Counter counter = new Counter(1);
  public static UltrasonicSensor sensor = new UltrasonicSensor(counter, 0.49, 0.96); // slope: 0.12, intercept: 0.96

  // TalonSRX
  public static TalonSRX leftDriveLead = new TalonSRX(Constants.leftDriveLeadID);
  public static TalonSRX rightDriveLead = new TalonSRX(Constants.rightDriveLeadID);
  public static TalonSRX rightDriveFollowerOne = new TalonSRX(Constants.rightDriveFollowerOneID);
  public static TalonSRX rightDriveFollowerTwo = new TalonSRX(Constants.rightDriveFollowerTwoID);
  public static TalonSRX leftDriveFollowerOne = new TalonSRX(Constants.leftDriveFollowerOneID);
  public static TalonSRX leftDriveFollowerTwo = new TalonSRX(Constants.leftDriveFollowerTwoID);

  public static TalonSRX leftIntake = new TalonSRX(Constants.leftIntakeID);
  public static TalonSRX rightIntake = new TalonSRX(Constants.rightIntakeID);

  public static TalonSRX armMaster = new TalonSRX(Constants.armMasterID);
  public static TalonSRX armFollower = new TalonSRX(Constants.armFollowerID);

  // navX
  public static Navx navx = new Navx(new AHRS(I2C.Port.kMXP));

  // Subsystems
  public static Arm arm = new Arm();
  public static Clapper clapper = new Clapper();
  public static DriveTrain drive = new DriveTrain();

  // Pistons and their in/out values
  public static DoubleSolenoid shifters = new DoubleSolenoid(0, 1);
  public static DoubleSolenoid.Value lowGear = DoubleSolenoid.Value.kForward;
  public static DoubleSolenoid.Value highGear = DoubleSolenoid.Value.kReverse;
  
  public static DoubleSolenoid rightIntakePiston = new DoubleSolenoid(2, 3);
  public static DoubleSolenoid.Value closeRightIntake = DoubleSolenoid.Value.kReverse;
  public static DoubleSolenoid.Value openRightIntake = DoubleSolenoid.Value.kForward;

  public static DoubleSolenoid leftIntakePiston = new DoubleSolenoid(6, 7);
  public static DoubleSolenoid.Value closeLeftIntake = DoubleSolenoid.Value.kForward;
  public static DoubleSolenoid.Value openLeftIntake = DoubleSolenoid.Value.kReverse;

  public static DoubleSolenoid armBrake = new DoubleSolenoid(4, 5);
  public static DoubleSolenoid.Value disengageBrake = Value.kReverse;
  public static DoubleSolenoid.Value engageBrake = Value.kForward;

  // Arrays of motors
  public static TalonSRX[] driveMotors = {
    leftDriveLead,
    rightDriveLead,
    leftDriveFollowerOne,
    leftDriveFollowerTwo,
    rightDriveFollowerOne,
    rightDriveFollowerTwo
  };

  public static TalonSRX[] intakeMotors = {
    leftIntake,
    rightIntake
  };

  public static TalonSRX[] armMotors = {
    armMaster,
    armFollower
  };
  
  public static TalonSRX[] driveMotorLeads = {
    leftDriveLead,
    rightDriveLead
  };

  public static TalonSRX[] allMotors = {
    leftDriveLead,
    rightDriveLead,
    leftDriveFollowerOne,
    leftDriveFollowerTwo,
    rightDriveFollowerOne,
    rightDriveFollowerTwo,
    leftIntake,
    rightIntake,
    armMaster,
    armFollower
  };
}
