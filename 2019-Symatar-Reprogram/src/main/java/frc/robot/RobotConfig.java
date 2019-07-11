package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class RobotConfig {

    public static double gearRatio = 7.5;
    public static double encoderTicsPerShaftRotation = 4096;
    public static double encoderTicsPerWheelRotation = gearRatio * encoderTicsPerShaftRotation;
    public static double wheelDiam = 6.0;
    public static double wheelCircum = Math.PI * wheelDiam;
    public static double voltageControlMax = 11.0;
    public static int armMaxEncoderTicks = -2100;
    public static int armStartEncoderTicks = -722;
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

        RobotMap.armFollower.set(ControlMode.Follower, RobotMap.armMasterID);

        RobotMap.armMaster.setNeutralMode(NeutralMode.Brake);
        RobotMap.armFollower.setNeutralMode(NeutralMode.Brake);

        RobotMap.armMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        RobotMap.armMaster.setSelectedSensorPosition(0, 0, 0);
        RobotMap.armMaster.getSensorCollection().setAnalogPosition(0, 0);
        RobotMap.armMaster.setSensorPhase(true);
        
        RobotMap.armMaster.configNominalOutputForward(0, 10);
        RobotMap.armMaster.configNominalOutputReverse(0, 10);
        RobotMap.armMaster.configPeakOutputForward(1, 10);
        RobotMap.armMaster.configPeakOutputReverse(-1, 10);

        //Setting motor inversions
        RobotMap.rightDriveLead.setInverted(true);
        RobotMap.rightDriveFollowerOne.setInverted(true);
        RobotMap.rightDriveFollowerTwo.setInverted(true);
        RobotMap.leftDriveLead.setInverted(false);
        RobotMap.leftDriveFollowerOne.setInverted(false);
        RobotMap.leftDriveFollowerTwo.setInverted(false);
        RobotMap.leftIntake.setInverted(false);
        RobotMap.rightIntake.setInverted(false);

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
        setDriveMotorsCoast();
        RobotMap.armBrake.set(RobotMap.engageBrake);
    }

    public static void setDriveMotorsCoast() {
        RobotMap.rightDriveLead.setNeutralMode(NeutralMode.Coast);
        RobotMap.leftDriveLead.setNeutralMode(NeutralMode.Coast);
        RobotMap.rightDriveFollowerOne.setNeutralMode(NeutralMode.Coast);
        RobotMap.rightDriveFollowerTwo.setNeutralMode(NeutralMode.Coast);
        RobotMap.leftDriveFollowerOne.setNeutralMode(NeutralMode.Coast);
        RobotMap.leftDriveFollowerTwo.setNeutralMode(NeutralMode.Coast);
    }

    public static void setDriveMotorsBrake() {
        RobotMap.rightDriveLead.setNeutralMode(NeutralMode.Brake);
        RobotMap.leftDriveLead.setNeutralMode(NeutralMode.Brake);
        RobotMap.rightDriveFollowerOne.setNeutralMode(NeutralMode.Brake);
        RobotMap.rightDriveFollowerTwo.setNeutralMode(NeutralMode.Brake);
        RobotMap.leftDriveFollowerOne.setNeutralMode(NeutralMode.Brake);
        RobotMap.leftDriveFollowerTwo.setNeutralMode(NeutralMode.Brake);
    }
}
