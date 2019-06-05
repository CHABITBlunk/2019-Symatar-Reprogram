/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  //Integer IDs
  public static int rightDriveLeadID = 2;
  public static int leftDriveLeadID = 7;

  public static int rightDriveFollowerOneID = 1;
  public static int rightDriveFollowerTwoID = 2;
  public static int leftDriveFollowerOneID = 6; 
  public static int leftDriveFollowerTwoID = 8;

  public static int leftIntakeID = 9;
  public static int rightIntakeID = 10;
  //TalonSRX
  public static TalonSRX leftDriveLead = new TalonSRX(leftDriveLeadID);
  public static TalonSRX rightDriveLead = new TalonSRX(rightDriveLeadID);
  public static TalonSRX rightDriveFollowerOne = new TalonSRX(rightDriveFollowerOneID);
  public static TalonSRX rightDriveFollowerTwo = new TalonSRX(rightDriveFollowerTwoID);
  public static TalonSRX leftDriveFollowerOne = new TalonSRX(leftDriveFollowerOneID);
  public static TalonSRX leftDriveFollowerTwo = new TalonSRX(leftDriveFollowerTwoID);

  public static TalonSRX leftIntake = new TalonSRX(leftIntakeID);
  public static TalonSRX rightIntake = new TalonSRX(rightIntakeID);

  //navX
  public static AHRS navx = new AHRS(I2C.Port.kMXP);

  //CANifier
  public static CANifier canifier = new CANifier(0);

  //Single and double solenoids (pistons) and their in/out values
  public static DoubleSolenoid shifters = new DoubleSolenoid(0, 1);
  public static DoubleSolenoid.Value lowGear = DoubleSolenoid.Value.kForward;
}
