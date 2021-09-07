// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.MedianFilter;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class DefaultDrive extends CommandBase {
  /** Creates a new DefaultDrive. */

  //default drive object
  private DriveTrain m_drivetrain;

  //threshold for ultrasonic and conversion factor
  private static final double kThresh = 12.0;
  private static final double kValueToInches = 0.125;

  //pid output for arcade drive
  private double pid_output;

  //Analog input for ultrasonic and also filters(using Median Filter iirc its very smooth)
  private final MedianFilter ultra_filter = new MedianFilter(5);
  private final AnalogInput ultrasonic = new AnalogInput(Constants.ULTRASONIC_PORT);
  
  public DefaultDrive(DriveTrain drivetrain) {
    m_drivetrain = drivetrain;
    addRequirements(drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //setting the setpoint to the threshold (converting it to inches)
    m_drivetrain.m_controller.setGoal(kThresh * kValueToInches);

    //calculating the pid output for returned ultrasonic output however that goes through a filter to cancel out the noise that will appear
    pid_output = m_drivetrain.m_controller.calculate(ultra_filter.calculate(ultrasonic.getVoltage()));

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //executing the arcadeDrive command. (THEORETICALLY IT SHOULD WORK????)
    //this should allow the controller to move forward until it sees something within a 12 inch threshold
    m_drivetrain.m_drive.arcadeDrive(pid_output, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
