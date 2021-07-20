package frc.robot.controls;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

public class ControlMap{
    //Controller objects
    public static XboxController driver = new XboxController(Constants.DRIVER_PORT);
    public static XboxController gunner = new XboxController(Constants.GUNNER_PORT);

    //Joystick
    public static double direction_x = ControlMap.driver.getRawAxis(Constants.X_AXIS);
    public static double direction_y = ControlMap.driver.getRawAxis(Constants.Y_AXIS);
    public static double rotation = ControlMap.driver.getRawAxis(Constants.ROTATION_AXIS);
}