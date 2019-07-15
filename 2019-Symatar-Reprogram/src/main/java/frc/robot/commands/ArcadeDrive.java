package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotConfig;
import frc.robot.RobotMap;

public class ArcadeDrive extends Command {
	private double deadZone = 0.15;
	private double turn = 0;
	private double throttle = 0;
	private double povValue;
	private double ratio = 0;
	private double sensitivity;
	private double leftPower;
	private double rightPower;
	private boolean highGear = false;;
	public ArcadeDrive() {
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		throttle = OI.pilotController.getRawAxis(1)*0.5; 
		ratio = Math.abs(throttle);
		povValue = OI.pilotController.getPOV();
		
		if(Math.abs(OI.pilotController.getRawAxis(4))>deadZone) {	
			turn = OI.pilotController.getRawAxis(4);
		}
		else {
			turn = 0;
		}
		if(Math.abs(throttle)>0.01){
			leftPower = (throttle - (sensitivity*turn*ratio));
			rightPower = (throttle + (sensitivity*turn*ratio));
			RobotConfig.setDriveMotorsCoast();
		}
		else{
			leftPower = (turn)*sensitivity;
			rightPower = (-turn)*sensitivity; 
		}
		leftPower = throttle +(-turn);
		rightPower= throttle +(turn);
		if(Math.abs(leftPower)>1) {
			leftPower = (leftPower/Math.abs(leftPower));
			rightPower = Math.abs(rightPower/leftPower)*(rightPower/Math.abs(rightPower));
		}
		else if(Math.abs(rightPower)>1) {
			rightPower = (rightPower/Math.abs(rightPower));
			leftPower = Math.abs(leftPower/rightPower)*(leftPower/Math.abs(leftPower));
		}
		if(RobotMap.shifters.get() == RobotMap.highGear) {
			sensitivity =1.25;
			RobotConfig.setDriveMotorsCoast();

		}
		else if(RobotMap.shifters.get() == RobotMap.lowGear) {
			sensitivity =0.95;
			RobotConfig.setDriveMotorsCoast();
		}
		if (RobotMap.shifters.get() == RobotMap.highGear) highGear = true;

		if(OI.shiftDown.get() && highGear) {
			RobotMap.shifters.set(RobotMap.lowGear);
			highGear = false;
		}
		if (OI.shiftUp.get() && !highGear) {
			RobotMap.shifters.set(RobotMap.highGear);
			highGear = true;
		}
		RobotMap.leftDriveLead.set(ControlMode.PercentOutput, leftPower);
		RobotMap.rightDriveLead.set(ControlMode.PercentOutput, rightPower);
	}

	@Override
	protected boolean isFinished() {
		return RobotState.isDisabled();
	}

	@Override
	protected void end() {
		Robot.stopMotors.stopDriveTrainMotors();
	}

	@Override
	protected void interrupted() {
		end();
	}
}