/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.controls.ControlMap;

public class DriveTrain extends SubsystemBase {
  //Initializing talons for mechanum
  private WPI_TalonSRX talon_FL = new WPI_TalonSRX(Constants.TALON_FL);
  private WPI_TalonSRX talon_FR = new WPI_TalonSRX(Constants.TALON_FR);
  private WPI_TalonSRX talon_BL = new WPI_TalonSRX(Constants.TALON_BL);
  private WPI_TalonSRX talon_BR = new WPI_TalonSRX(Constants.TALON_BR);
  private Gyro gyro = new ADXRS450_Gyro(SPI.Port.kMXP);
  private Ultrasonic ultrasonic = new Ultrasonic(Constants.ULTRASONIC_PORTS[0],Constants.ULTRASONIC_PORTS[1]);

  //deadzone
  private final double DEADZONE = 0.2;

  //variables for mechanum drive
  public static double magnitude; // power of the drivetrain
  public static double angle; // The translation angle relative to the robot's body vector
  public static double rotation; // The rotation is the magnitude of the robot's rotation rate

  //variables for Joystick movement
  public static double direction_x; 
  public static double direction_y;

  //Kp val
  public static double kP = 1;


  //making object of mechanumDrive
  private MecanumDrive drivetrain;

  //rotation arr
  double[] rotations = {90.0, 180.0, 90.0};
  int count = 0;
  
  //constructor and setting the wheels to mecanumDrive constructor since it takes those as arguments
  public DriveTrain() {
      drivetrain = new MecanumDrive(talon_FL, talon_BL, talon_FR, talon_BR);
  }

  //Gets the talon ONE LINER 
  public WPI_TalonSRX getTalon(int num){return num == 1 ? talon_FL : num == 2 ? talon_FR : num == 3 ? talon_BL : num == 4 ? talon_BR : null;}
  
  public void getInput() {  // Fetch the Joystick values, apply inversion if neccesary
    direction_y = ControlMap.driver.getRawAxis(1);
    direction_x = ControlMap.driver.getRawAxis(0);
    rotation = ControlMap.driver.getRawAxis(2);
  }

  // Called repeatedly when this Command is scheduled to run
  public void doDead() {  // Calculate the DEADZONE of the direction values
    if(Math.abs(direction_x) < DEADZONE){direction_x = 0;}
    if(Math.abs(direction_y) < DEADZONE){direction_y = 0;}
    if(Math.abs(rotation) < DEADZONE){rotation = 0;}
  }

  //getter for getting the drivetrain
  public MecanumDrive getDriveTrain() {
    return drivetrain;
  }

  public void auto_routine_1(){
    double error = -gyro.getRate();
    if(ultrasonic.getRangeInches() < 4){
      drivetrain.driveCartesian(0, 0, .5, rotations[count]);
      count++;
    }
    if(ultrasonic.getRangeInches() > 4){
      count = 0;
      drivetrain.driveCartesian(0, 0.5 + kP * error, 0, 0);
    }
  }
  //Initialize the default command for a subsystem By default subsystems have no default command, but if they do, 
  //the default command is set with this method.
  public void initDefaultCommand() {
      //neutralMode The desired mode of operation when the Controller output throttle is neutral (ie brake/coast)
      talon_FR.setNeutralMode(NeutralMode.Brake);
      talon_FL.setNeutralMode(NeutralMode.Brake);
      talon_BR.setNeutralMode(NeutralMode.Brake);
      talon_BL.setNeutralMode(NeutralMode.Brake);
  }
  public void robotInit(){
    ultrasonic.setAutomaticMode(true);
  }
}
