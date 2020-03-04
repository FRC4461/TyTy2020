/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package frc.robot;

public final class Constants {
  public static final int leftJoystick = 0;
  public static final int rightJoystick = 1;

  public static final double DISTANCE_PER_REVOLUTION = 6 * Math.PI;

  public static final class DriveBase {
    public static final int backRight = 0;
    public static final int frontRight = 1;
    public static final int backLeft = 2;
    public static final int frontLeft = 3;
  }

  public static final class Shooter {
    public static final int FLYWHEEL_MOTOR_ID = 4;
    public static final int INTAKE_MOTOR_ID = 5;
    public static final int CONVEYOR_MOTOR_ID = 6;
  }
}
