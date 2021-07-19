package frc.robot.controls;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

public class ControlMap{
    //Controller objects
    public static XboxController driver = new XboxController(Constants.DRIVER_PORT);
    public static XboxController gunner = new XboxController(Constants.GUNNER_PORT);

    public static double direction_x = ControlMap.driver.getRawAxis(0);
    public static double direction_y = ControlMap.driver.getRawAxis(1);
    public static double rotation = ControlMap.driver.getRawAxis(2);
}