// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.MedianFilter;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  /** Creates a new DriveTrain. */
  //creating sparks for drivetrain
  private static CANSparkMax spark_fl = new CANSparkMax(Constants.SPARK_FL, MotorType.kBrushless);
  private static CANSparkMax spark_fr = new CANSparkMax(Constants.SPARK_FR, MotorType.kBrushless);
  private static CANSparkMax spark_bl = new CANSparkMax(Constants.SPARK_BL, MotorType.kBrushless);
  private static CANSparkMax spark_br = new CANSparkMax(Constants.SPARK_BR, MotorType.kBrushless);

  //PID constants
  private static double kP = 0.0;
  private static double kI = 0.0;
  private static double kD = 0.0;
    
  //speed controller groups
  SpeedControllerGroup left_command = new SpeedControllerGroup(spark_fl,spark_bl);
  SpeedControllerGroup right_command = new SpeedControllerGroup(spark_fr, spark_br);

  //differential drive class
  public final DifferentialDrive m_drive = new DifferentialDrive(left_command, right_command);

  //making profiled PID controller
  //NOTE: The PIDController is depricated class so do not use that 
  public ProfiledPIDController m_controller = new ProfiledPIDController(kP, kI, kD, new TrapezoidProfile.Constraints(10,20));

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }
}
