/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import frc.robot.commands.AutoMazeSolve;
import frc.robot.commands.DefaultDrive;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain m_drive_train = new DriveTrain();
  private final AutoMazeSolve m_autoroutine = new AutoMazeSolve(m_drive_train);
  private final DefaultDrive c_drive = new DefaultDrive(m_drive_train);

  public RobotContainer() {
    configureButtonBindings();
    configureDefaultCommands();
  }
  private void configureButtonBindings() {
  }
  
  private void configureDefaultCommands() {
    m_drive_train.setDefaultCommand(c_drive);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoroutine;
  }
}
