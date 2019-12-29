package frc.robot.sensors.pwm;

import edu.wpi.first.wpilibj.Counter;

public class UltrasonicSensor {

    private Counter _counter;
    private double _slope;
    private double _intercept;

    public UltrasonicSensor(Counter counter) {
        _counter = counter;
        _counter.setSemiPeriodMode(true);
        _counter.reset();
        _slope = 1;
        _intercept = 0;
    }

    public UltrasonicSensor(Counter counter, double slope) {
        this(counter);
        _slope = slope;
        _intercept = 0;
    }

    public UltrasonicSensor(Counter counter, double slope, double intercept) {
        this(counter, slope);
        _intercept = intercept;
    }

    public double getDistance() {
        double dist = ((_counter.getPeriod()  * 3200.19047619) - _intercept) / _slope;
        if (dist > 0) {
            return dist;
        } else {
            return -1;
        }
    }

    public boolean isConnected() {
        if (_counter.getPeriod() > 1) {
            return false;
        } else {
            return true;
        }
    }
}
