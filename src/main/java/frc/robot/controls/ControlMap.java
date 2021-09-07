package frc.robot.controls;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj2.command.button.JoystickButton;
public class ControlMap{
    // Controller objects
    public static Joystick driver = new Joystick(0);
    public static Joystick gunner = new Joystick(1);
    
    public static double left_Y = driver.getRawAxis(1);
    public static double right_Y = driver.getRawAxis(5);
    // Driver buttons
    //public static JoystickButton driver_button_A        = new JoystickButton(driver,1);
   
}