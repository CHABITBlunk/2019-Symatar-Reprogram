package frc.robot.commands.universalcommands;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;

public class BlinkinSetColor extends Command {

  private PWM _blinkin;
  private double _power;

  public BlinkinSetColor(PWM blinkin, double power) {
    _blinkin = blinkin;
    _power = power;
  }
  
  @Override
  protected void initialize() {
    _blinkin.setSpeed(_power);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
