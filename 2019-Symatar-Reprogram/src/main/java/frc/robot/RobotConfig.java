/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * Add your docs here.
 */
public class RobotConfig {

    public static double gearRatio = 7.5;
    public static double encoderTicsPerShaftRotation = 4096;
    public static double encoderTicsPerWheelRotation = gearRatio * encoderTicsPerShaftRotation;
    public static double wheelDiam = 6.0;
    public static double wheelCircum = Math.PI * wheelDiam;
    public static double voltageControlMax = 11.0;
    public static int driveMotorContinuousCurrent = 1;
    public static int driveMotorPeakCurrent = 60;
    public static int driveMotorPeakCurrentDuration = 100;
    public static boolean enableDriveCurrentLimit = true;
    public static double driverDeadZone = 0.10;
    public static int timeOut = 4;

    public RobotConfig() {
        setStartingConfig();
    }

    public void setStartingConfig() {
        //Setting master-slave motors
        RobotMap.rightDriveFollowerOne.set(ControlMode.Follower, RobotMap.rightDriveLeadID);
        RobotMap.rightDriveFollowerTwo.set(ControlMode.Follower, RobotMap.rightDriveLeadID);
        RobotMap.leftDriveFollowerOne.set(ControlMode.Follower, RobotMap.leftDriveLeadID);
        RobotMap.leftDriveFollowerTwo.set(ControlMode.Follower, RobotMap.leftDriveLeadID);

        //Setting motor inversions
        RobotMap.rightDriveLead.setInverted(true);
        RobotMap.rightDriveFollowerOne.setInverted(true);
        RobotMap.rightDriveFollowerTwo.setInverted(true);
        RobotMap.leftDriveLead.setInverted(false);
        RobotMap.leftDriveFollowerOne.setInverted(false);
        RobotMap.leftDriveFollowerTwo.setInverted(false);
        RobotMap.leftIntake.setInverted(false);
        RobotMap.rightIntake.setInverted(false);

        //Set motors to coast mode
        RobotMap.rightDriveLead.setNeutralMode(NeutralMode.Coast);
        RobotMap.leftDriveLead.setNeutralMode(NeutralMode.Coast);
        RobotMap.rightDriveFollowerOne.setNeutralMode(NeutralMode.Coast);
        RobotMap.rightDriveFollowerTwo.setNeutralMode(NeutralMode.Coast);
        RobotMap.leftDriveFollowerOne.setNeutralMode(NeutralMode.Coast);
        RobotMap.leftDriveFollowerTwo.setNeutralMode(NeutralMode.Coast);

        for (TalonSRX talon : RobotMap.driveMotors) {
            talon.configContinuousCurrentLimit(driveMotorContinuousCurrent, timeOut);
            talon.configPeakCurrentDuration(driveMotorPeakCurrentDuration, timeOut);
            talon.configPeakCurrentLimit(driveMotorPeakCurrent, timeOut);
            talon.enableCurrentLimit(enableDriveCurrentLimit);
        }
        RobotMap.leftDriveLead.configVoltageCompSaturation(voltageControlMax, 10);
        RobotMap.leftDriveLead.enableVoltageCompensation(false);
        RobotMap.leftDriveLead.configVoltageMeasurementFilter(32, 10);
        RobotMap.rightDriveLead.configVoltageCompSaturation(RobotConfig.voltageControlMax, 10);
    	RobotMap.rightDriveLead.enableVoltageCompensation(false); 
        RobotMap.rightDriveLead.configVoltageMeasurementFilter(32, 10);
    }

    public void teleopConfig() {
        RobotMap.leftDriveLead.enableVoltageCompensation(false);
        RobotMap.rightDriveLead.enableVoltageCompensation(false);
        RobotMap.rightDriveLead.configOpenloopRamp(0.0, 0);
        RobotMap.leftDriveLead.configOpenloopRamp(0.0, 0);
    }
}
