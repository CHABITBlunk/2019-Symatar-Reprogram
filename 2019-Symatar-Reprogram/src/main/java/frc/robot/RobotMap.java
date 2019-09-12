package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.subsystems.*;

public class RobotMap {

  
  //Arm limit switch
  public static AnalogInput analog = new AnalogInput(0);
  public static DigitalInput armLimit = new DigitalInput(0);

  // Relays
  public static Relay visionRelay = new Relay(0);

  //TalonSRX
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

  //navX
  public static AHRS navx = new AHRS(I2C.Port.kMXP);

  //Subsystems
  public static Arm arm = new Arm();
  public static Clapper clapper = new Clapper();
  public static DriveTrain driveTrain = new DriveTrain();

  //Single and double solenoids (pistons) and their in/out values
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

  //Arrays of motors
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
