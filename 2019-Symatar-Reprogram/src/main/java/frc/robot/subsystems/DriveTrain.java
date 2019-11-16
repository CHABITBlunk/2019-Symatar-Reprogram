package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.ButtonMap;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.sensors.DriveEncoder;
import frc.robot.tools.controlloops.PID;
import frc.robot.tools.pathtools.Odometry;

public class DriveTrain extends Subsystem {

  private double deadZone = 0.15;
	private double turn =0;
	private double throttel = 0;
	private double povValue;
	private double ratio = 0;
	private double sensitivity;
	private double minTurnFactor = 0.4;
  public static DriveEncoder leftMainDrive = new DriveEncoder(RobotMap.leftDriveLead,RobotMap.leftDriveLead.getSelectedSensorPosition(0));
  public static DriveEncoder rightMainDrive = new DriveEncoder(RobotMap.rightDriveLead,RobotMap.rightDriveLead.getSelectedSensorPosition(0));
	private double speed;
  private double f = 0.1461;
  private double p = 0.11;
  private double i = 0.002;
  private double d = 5;
	private int profile = 0;
	private Odometry autoOdometry;
	private PID alignmentPID;
	private double alignmentP = 0.011;
	private double alignmentI= 0.000;
	private double alignmentD;
	
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
	}
	public void startAutoOdometry(double x, double y, double theta){
		autoOdometry = new Odometry(false);
		autoOdometry.setX(x);
		autoOdometry.setY(y);
		autoOdometry.setTheta(theta);
		autoOdometry.start();
	};
	public double getDriveTrainX(){
		return autoOdometry.getX();
	}
	public double getDriveTrainY(){
		return autoOdometry.getY();
	}
	public double getDriveTrainHeading(){
		return autoOdometry.gettheta();
	}

	public void setDriveTrainX(double x){
		 autoOdometry.setX(x);
	}
	public void setDriveTrainY(double y){
		 autoOdometry.setY(y);
	}
	public void setDriveTrainHeading(double theta){
		 autoOdometry.setTheta(theta);
	}
	public void setOdometryReversed(boolean reversed){
		 autoOdometry.setReversed(reversed);
	}

  public void setLowGear(){
    RobotMap.shifters.set(RobotMap.lowGear);
  }
	public void initVelocityPIDs(){
    RobotMap.leftDriveLead.selectProfileSlot(profile, 0);
    RobotMap.leftDriveLead.config_kF(profile, f, 0);
    RobotMap.leftDriveLead.config_kP(profile, p, 0);
    RobotMap.leftDriveLead.config_kI(profile, i, 0);
    RobotMap.leftDriveLead.config_kD(profile, d, 0);
    RobotMap.leftDriveLead.set(ControlMode.Velocity, Constants.fpsToTicksPer100Ms(speed));
    RobotMap.rightDriveLead.selectProfileSlot(profile, 0);
    RobotMap.rightDriveLead.config_kF(profile, f, 0);
    RobotMap.rightDriveLead.config_kP(profile, p, 0);
    RobotMap.rightDriveLead.config_kI(profile, i, 0);
    RobotMap.rightDriveLead.config_kD(profile, d, 0);
    RobotMap.rightDriveLead.set(ControlMode.Velocity, Constants.fpsToTicksPer100Ms(speed));
	}
	public void initAlignmentPID(){
		alignmentPID = new PID(alignmentP, alignmentI, alignmentD);
		alignmentPID.setMaxOutput(0.4);
		alignmentPID.setMinOutput(-0.4);
    alignmentPID.setSetPoint(0);
	}
  public void setHighGear(){
    RobotMap.shifters.set(RobotMap.highGear);
  }
	public void arcadeDrive(){
		double leftPower;
		double rightPower;
		double differential;
		
		throttel = ButtonMap.getDriveThrottle(); 
		if(throttel ==0){
			throttel = 0.001;
		}
		ratio = Math.abs(1/throttel);
		povValue = ButtonMap.getPOV();
		turn = ButtonMap.getRotation();
		differential = (turn*ratio*sensitivity) + Math.abs(minTurnFactor*turn);

		leftPower = (throttel - (differential));
		rightPower = (throttel + (differential));
	
		if(Math.abs(leftPower)>1) {
			rightPower = Math.abs(rightPower/leftPower)*Math.signum(rightPower);
			leftPower = Math.signum(leftPower);
		}
		else if(Math.abs(rightPower)>1) {
			leftPower = Math.abs(leftPower/rightPower)*Math.signum(leftPower);
			rightPower = Math.signum(rightPower);
		}
    RobotMap.leftDriveLead.set(ControlMode.PercentOutput, leftPower);
    RobotMap.rightDriveLead.set(ControlMode.PercentOutput, rightPower);
		if(ButtonMap.shiftDown()){
			setLowGear();
		}
		else if(ButtonMap.shiftUp()) {
			setHighGear();
		}
		if(RobotMap.shifters.get() == RobotMap.highGear) {
				sensitivity =1;
		}
		else if(RobotMap.shifters.get() == RobotMap.lowGear) {
				sensitivity =1;
    }
	}
	public void Stop(){
		RobotMap.leftDriveLead.set(ControlMode.PercentOutput, 0);
		RobotMap.rightDriveLead.set(ControlMode.PercentOutput, 0);
	}	
	public void setLeftSpeed(double speed){
		RobotMap.leftDriveLead.set(ControlMode.Velocity, Constants.fpsToTicksPer100Ms(speed));
	}
	public void setRightSpeed(double speed){
    RobotMap.rightDriveLead.set(ControlMode.Velocity, Constants.fpsToTicksPer100Ms(speed));

	}
	public void setLeftPercent(double percent){
		RobotMap.leftDriveLead.set(ControlMode.PercentOutput, percent);
	}
	public void setRightPercent(double percent){
		RobotMap.rightDriveLead.set(ControlMode.PercentOutput, percent);
	}
  public void stopDriveTrainMotors(){
    for(TalonSRX talon : RobotMap.driveMotorLeads){
        talon.set(ControlMode.PercentOutput, 0);
    }
  }
}