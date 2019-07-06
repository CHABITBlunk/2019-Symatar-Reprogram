package frc.utility;

import edu.wpi.first.wpilibj.Joystick;

public class XboxController {

    private Joystick joystick;

    // Controller Buttons
    public static final int A = 1;
    public static final int B = 2;
    public static final int X = 3;
    public static final int Y = 4;
    public static final int LBUMPER = 5;
    public static final int RBUMPER = 6;
    public static final int BACK = 7;
    public static final int START = 8;
    public static final int LSTICK = 9;
    public static final int RSTICK = 10;

    // Controller Axes
    public static final int LSTICKX = 0;
    public static final int LSTICKY = 1;
    public static final int LTRIGGER = 2;
    public static final int RTRIGGER = 3;
    public static final int RSTICKX = 4;
    public static final int RSTICKY = 5;

    // Controller D-Pad POV hat
    public static final int DPAD_POV = 0;

    public XboxController(int joystickId) {
        joystick = new Joystick(joystickId);
    }

    public boolean a() {
        return joystick.getRawButton(A);
    }

    public boolean b() {
        return joystick.getRawButton(B);
    }

    public boolean x() {
        return joystick.getRawButton(X);
    }

    public boolean y() {
        return joystick.getRawButton(Y);
    }

    public boolean rightBumper() {
        return joystick.getRawButton(RBUMPER);
    }

    public boolean leftBumper() {
        return joystick.getRawButton(LBUMPER);
    }

    public boolean back() {
        return joystick.getRawButton(BACK);
    }

    public boolean start() {
        return joystick.getRawButton(START);
    }

    public boolean lStick() {
        return joystick.getRawButton(LSTICK);
    }

    public boolean rStick() {
        return joystick.getRawButton(RSTICK);
    }

    public double rStickX() {
        return joystick.getRawAxis(RSTICKX);
    }

    public double rStickY() {
        return joystick.getRawAxis(RSTICKY);
    }

    public double lStickX() {
        return joystick.getRawAxis(LSTICKX);
    }

    public double lStickY() {
        return joystick.getRawAxis(LSTICKY);
    }

    public double rTrigger() {
        return joystick.getRawAxis(RTRIGGER);
    }

    public double lTrigger() {
        return joystick.getRawAxis(LTRIGGER);
    }
    public int dPad() {
        return joystick.getPOV(DPAD_POV);
    }
}
