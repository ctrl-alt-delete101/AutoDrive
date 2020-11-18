package frc.robot.controls;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

public class ControlMap{
    //Controller objects
    public static XboxController driver = new XboxController(Constants.DRIVER_PORT);
    public static XboxController gunner = new XboxController(Constants.GUNNER_PORT);

}