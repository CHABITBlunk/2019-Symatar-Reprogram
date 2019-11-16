package frc.robot;


/**
 * Add your docs here.
 */
public class ButtonMap {
    public static double getDriveThrottle(){
        return -OI.driverController.getRawAxis(1);
    } 
    public static double getRotation(){
        return OI.driverController.getRawAxis(4);
    }
    public static int getPOV(){
        return OI.driverController.getPOV();
    }
    public static boolean shiftUp(){
        return OI.shiftUp.get();
    }
    public static boolean shiftDown(){
        return OI.shiftUp.get();
    }
    public static boolean quickTurn(){
        return OI.driverController.getRawAxis(3)>0.5;
    }
}