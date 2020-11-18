/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //Talon port numbers
    public static int TALON_FR = 0;
    public static int TALON_FL = 1;
    public static int TALON_BR = 2;
    public static int TALON_BL = 3;

    //DRIVER PORTS 
    public static int DRIVER_PORT = 0;
    public static int GUNNER_PORT = 1;

    //ULTRASONIC PORTS
    public static int[] ULTRASONIC_PORTS = {0,1};

}
