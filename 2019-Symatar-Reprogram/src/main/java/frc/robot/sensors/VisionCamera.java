package frc.robot.sensors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import edu.wpi.first.hal.util.UncleanStatusException;

public class VisionCamera {
   
    JSONParser parser = new JSONParser();
    SerialPort port;
    public String sanitizedString = "nothing";
    public double lastParseTime;
    private double distance;
    private double angle;
    private double badAngle = -100.0;
    private double badDistance = -11.0;
   
   
    public VisionCamera(SerialPort jevois) {
       port = jevois;
    }

    public void updateVision() {
        try {
            String unsanitizedString = getString();
            String jsonString = unsanitizedString.substring(unsanitizedString.indexOf('{'), unsanitizedString.indexOf('}') + 1);
            double tryDistance = badDistance;
            double tryAngle = badAngle;
            if (jsonString != null) {
                tryDistance = parseDistance(jsonString);
                tryAngle = parseAngle(jsonString);
            }
            SmartDashboard.putString("testnum", jsonString);
            if (tryAngle != badAngle) {
                distance = tryDistance;
                angle = tryAngle;
                lastParseTime = Timer.getFPGATimestamp();
            }
        } catch (Exception ex) {
        }
    }   

    public double parseAngle(String jsonString) {
        try {
            Object object = parser.parse(jsonString);
            JSONObject jsonObject = (JSONObject) object;
            if (jsonObject != null) {
                double angleString = (double) jsonObject.get("Angle");
                return Double.valueOf(angleString);
            }
        } catch (ParseException ex) {
        } catch (UncleanStatusException ex) {
        } catch (ClassCastException ex) {
        }
        return badAngle;
    }

    public double parseDistance(String jsonString) {
        try {
            Object object = parser.parse(jsonString);
            JSONObject jsonObject = (JSONObject) object;
            if (jsonObject != null) {
                double angleString = (double) jsonObject.get("Distance");
                return Double.valueOf(angleString);
            }
        } catch (ParseException ex) {
        } catch (UncleanStatusException ex) {
        } catch (ClassCastException ex) {
        }
        return badDistance;
    }

    public double getDistance() {
        return distance;
    }

    public double getAngle() {
        return angle;
    }

    public String getString() {
        try {
            if (port.getBytesReceived() > 2) {
                String unsanitizedString = port.readString();
                if (unsanitizedString.length() > 5 && !unsanitizedString.isBlank() && !unsanitizedString.isEmpty()) {
                    sanitizedString = unsanitizedString;
                }
            }
        } catch (Exception e) {
        }
        return sanitizedString;
    }
}