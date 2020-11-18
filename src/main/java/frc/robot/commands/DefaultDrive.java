/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.controls.ControlMap;
import frc.robot.subsystems.DriveTrain;

public class DefaultDrive extends CommandBase {
  public DriveTrain m_drive_train;
  public DefaultDrive(DriveTrain drive_train) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drive_train = drive_train;
    addRequirements(drive_train);

  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
      System.out.println(ControlMap.driver.getName());
      m_drive_train.getInput(); // Fetches Joystick values
      m_drive_train.doDead(); // Sets the DEADZONE value
      m_drive_train.getDriveTrain().driveCartesian(DriveTrain.magnitude, DriveTrain.angle, DriveTrain.rotation);
      System.out.println(DriveTrain.magnitude);
      System.out.println(DriveTrain.angle);
      System.out.println(DriveTrain.rotation);
      System.out.println("------");
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
