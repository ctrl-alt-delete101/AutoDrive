// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.controls.ControlMap;
import frc.robot.subsystems.DriveTrain;

public class DefaultDrive extends CommandBase {
  /** Creates a new DefaultDrive. */
  public DriveTrain drive_train;
  public DefaultDrive(DriveTrain drive_train) {
    this.drive_train = drive_train;
    addRequirements(drive_train);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.drive_train.driveMecanum(ControlMap.direction_y, ControlMap.direction_x, ControlMap.rotation);
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
