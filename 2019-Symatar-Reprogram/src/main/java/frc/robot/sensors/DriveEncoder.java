package frc.robot.sensors;
import frc.robot.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class DriveEncoder {
	private TalonSRX masterTalon;
	private int startingValue;
	
	public DriveEncoder(TalonSRX talon, int startingValue) {
		masterTalon = talon;
	}
	public double getEncoderValue() {
		return masterTalon.getSelectedSensorPosition(0)-startingValue;
	}
	public double getEncoderVelocity(){
		return masterTalon.getSelectedSensorVelocity(0);
	}
	public double getDistance(){
		return ((((getEncoderValue())/Constants.encoderTicksPerWheelRotation)*Constants.wheelCircum)/12);
	}
	public double getVelocity(){
		return (((((getEncoderVelocity()*10))/Constants.encoderTicksPerWheelRotation)*Constants.wheelCircum)/12);
	}
	public void softReset(){
		startingValue = masterTalon.getSelectedSensorPosition(0);
	}
}