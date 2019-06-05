/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public static Joystick joystickOne = new Joystick(0);

  public static JoystickButton armForwardIntake = new JoystickButton(joystickOne, 3);
  public static JoystickButton armReverseIntake = new JoystickButton(joystickOne, 2);
  public static JoystickButton armForwardShoot = new JoystickButton(joystickOne, 4);
  public static JoystickButton armReverseShoot = new JoystickButton(joystickOne, 1);

  public static JoystickButton softOuttake = new JoystickButton(joystickOne, 6);
}
