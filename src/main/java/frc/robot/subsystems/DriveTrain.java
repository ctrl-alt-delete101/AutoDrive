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
  public final WPI_TalonSRX talon_FL = new WPI_TalonSRX(Constants.TALON_FL);
  public final WPI_TalonSRX talon_FR = new WPI_TalonSRX(Constants.TALON_FR);
  public final WPI_TalonSRX talon_BL = new WPI_TalonSRX(Constants.TALON_BL);
  public final WPI_TalonSRX talon_BR = new WPI_TalonSRX(Constants.TALON_BR);

  private Gyro gyro = new ADXRS450_Gyro(SPI.Port.kMXP);
  private Ultrasonic ultrasonic = new Ultrasonic(Constants.ULTRASONIC_PORTS[0],Constants.ULTRASONIC_PORTS[1]);

  //deadzone
  private final double DEADZONE = 0.1;

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

  public void driveMecanum(double y, double x, double zRot){
    if(Math.abs(x) < DEADZONE){x = 0;}
    if(Math.abs(y) < DEADZONE){y = 0;}
    if(Math.abs(zRot) < DEADZONE){zRot = 0;}
    drivetrain.driveCartesian(y, x, zRot);
  }

  //Gets the talon ONE LINER 
  public WPI_TalonSRX getTalon(int num){return num == 0 ? talon_FL : num == 1 ? talon_FR : num == 2 ? talon_BL : num == 3 ? talon_BR : null;}
  
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

  public void periodic(){
    ultrasonic.setAutomaticMode(true);
  }
}
