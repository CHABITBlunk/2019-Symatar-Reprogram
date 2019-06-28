package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.*;
import frc.robot.commands.*;

public class Clapper extends Subsystem {

  @Override
  public void initDefaultCommand() {
  }

  public void closeClapper() {
    RobotMap.rightIntakePiston.set(RobotMap.openRightIntake);
    RobotMap.leftIntakePiston.set(RobotMap.openLeftIntake);
  }

  public void openClapper() {
    RobotMap.rightIntakePiston.set(RobotMap.closeRightIntake);
    RobotMap.leftIntakePiston.set(RobotMap.closeLeftIntake);
  }
  
  public void outtake() {
    if (OI.pilotController.getRawAxis(3) >= 0.5) {
      new Outtake().start();
    }
  }

  public void intake() {
    if (OI.pilotController.getRawAxis(2) >= 0.5) {
      new Intake().start();
    }
  }
}
