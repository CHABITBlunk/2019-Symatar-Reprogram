package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class RobotConfig {

    public RobotConfig() {
        setStartingConfig();
    }

    public void setStartingConfig() {
        //Setting master-slave motors
        RobotMap.rightDriveFollowerOne.set(ControlMode.Follower, Constants.rightDriveLeadID);
        RobotMap.rightDriveFollowerTwo.set(ControlMode.Follower, Constants.rightDriveLeadID);
        RobotMap.leftDriveFollowerOne.set(ControlMode.Follower, Constants.leftDriveLeadID);
        RobotMap.leftDriveFollowerTwo.set(ControlMode.Follower, Constants.leftDriveLeadID);        

        RobotMap.armFollower.set(ControlMode.Follower, Constants.armMasterID);

        RobotMap.armMaster.setNeutralMode(NeutralMode.Brake);
        RobotMap.armFollower.setNeutralMode(NeutralMode.Brake);

        RobotMap.armMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
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
        RobotMap.armMaster.setInverted(true);
        RobotMap.armFollower.setInverted(true);

        RobotMap.leftDriveLead.configContinuousCurrentLimit(Constants.driveMotorContinuousCurrent, Constants.timeOut);
        RobotMap.leftDriveLead.configPeakCurrentDuration(Constants.driveMotorPeakCurrentDuration, Constants.timeOut);
        RobotMap.leftDriveLead.configPeakCurrentLimit(Constants.driveMotorPeakCurrent, Constants.timeOut);
        RobotMap.leftDriveLead.enableCurrentLimit(Constants.teleop_enableDriveCurrentLimit);
        RobotMap.rightDriveLead.configContinuousCurrentLimit(Constants.driveMotorContinuousCurrent, Constants.timeOut);
        RobotMap.rightDriveLead.configPeakCurrentDuration(Constants.driveMotorPeakCurrentDuration, Constants.timeOut);
        RobotMap.rightDriveLead.configPeakCurrentLimit(Constants.driveMotorPeakCurrent, Constants.timeOut);
        RobotMap.rightDriveLead.enableCurrentLimit(Constants.teleop_enableDriveCurrentLimit);

        RobotMap.leftDriveLead.configVoltageCompSaturation(Constants.voltageControlMax, 10);
        RobotMap.leftDriveLead.enableVoltageCompensation(false);
        RobotMap.leftDriveLead.configVoltageMeasurementFilter(32, 10);
        RobotMap.rightDriveLead.configVoltageCompSaturation(Constants.voltageControlMax, 10);
    	RobotMap.rightDriveLead.enableVoltageCompensation(false); 
        RobotMap.rightDriveLead.configVoltageMeasurementFilter(32, 10);

        for (TalonSRX t : RobotMap.driveMotors) {
            t.getSensorCollection().setQuadraturePosition(0, 0);
            t.configPeakOutputForward(0.9, 0);
        }
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
