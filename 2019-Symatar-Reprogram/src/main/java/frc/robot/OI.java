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
  public static Joystick pilotController = new Joystick(0);
  public static JoystickButton shiftUp = new JoystickButton(pilotController, 6);
  public static JoystickButton shiftDown = new JoystickButton(pilotController, 5);
  public static JoystickButton bButton = new JoystickButton(pilotController, 2);
  public static JoystickButton yButton = new JoystickButton(pilotController, 4);
  public static JoystickButton aButton = new JoystickButton(pilotController, 1);
  public static JoystickButton xButton = new JoystickButton(pilotController, 3);
  public static JoystickButton startButton = new JoystickButton(pilotController, 8);
  public static JoystickButton backButton = new JoystickButton(pilotController, 7);
}
