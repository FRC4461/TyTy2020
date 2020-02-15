/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */

public final class Constants {
    public static final int leftJoystick = 0;
    public static final int rightJoystick = 1;
    public static final double DISTANCE_PER_REVOLUTION = 6 * Math.PI;

    public static final class DriveBase {
        public static final int talon1 = 0;
        public static final int talon2 = 1;
        public static final int talon3 = 2;
        public static final int talon4 = 3;
    }

    public static final class Shooter {
        public static final int FLYWHEEL_MOTOR_ID = 4;
        public static final int INTAKE_MOTOR_ID = 5;
        public static final int CONVEYOR_MOTOR_ID = 6;
    }
}
