package frc.robot.sensors;

import com.kauailabs.navx.frc.AHRS;

public class Navx {

    private double navXAngle = 0;
    private double originalAngle;
    private double originalYaw;
    private AHRS imu;

    public Navx(AHRS navx) {
        imu = navx;
        originalAngle = navx.getAngle();
        originalYaw = navx.getYaw();
    }

    public double currentAngle() {
        return imu.getAngle() - originalAngle;
    }

    public double currentYaw() {
        return imu.getYaw() - originalYaw;
    }

    public boolean isMoving() {
        return imu.isMoving();
    }

    public boolean isOn() {
        return imu.isConnected();
    }

    public boolean isMagCalibrated() {
        return imu.isMagnetometerCalibrated();
    }

    public boolean isAutoCalibrating() {
        return imu.isCalibrating();
    }

    public boolean isMagInterference() {
        return imu.isMagneticDisturbance();
    }

    public void softResetAngle(double angle) {
        originalAngle = angle;
    }

    public void softResetYaw(double yaw) {
        originalYaw = yaw;  
    }
}