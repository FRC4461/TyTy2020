/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import java.util.concurrent.TimeUnit;

/**
 * Drivebase subsytem.
 */
public class Chassis extends SubsystemBase {
  private DutyCycleEncoder m_rightEncoder = new DutyCycleEncoder(0);

  private final TalonSRX m_backLeft = new TalonSRX(Constants.DriveBase.backLeft);
  private final TalonSRX m_backRight = new TalonSRX(Constants.DriveBase.backRight);
  private final TalonSRX m_frontLeft = new TalonSRX(Constants.DriveBase.frontLeft);
  private final TalonSRX m_frontRight = new TalonSRX(Constants.DriveBase.frontRight);

  private Gyro m_gyro = new ADXRS450_Gyro();

  private double m_angleTolerance = 3;
  private double m_speedReduction = 1 / 5;
  private double m_reductionIncrement = 5;
  private double m_reductions = 4;

  private double m_rightEncoderOrigin = 0;

  /** Drives at the specified speed for the right and left sides. */
  public void drive(double leftSpeed, double rightSpeed) {
    m_backLeft.set(ControlMode.PercentOutput, -leftSpeed);
    m_frontLeft.set(ControlMode.PercentOutput, -leftSpeed);
    m_backRight.set(ControlMode.PercentOutput, rightSpeed);
    m_frontRight.set(ControlMode.PercentOutput, rightSpeed);
  }

  public void initEncoder() {
    m_rightEncoder.setDistancePerRotation(Constants.DISTANCE_PER_REVOLUTION);
  }

  public void zeroEncoder() {

    m_rightEncoderOrigin = m_rightEncoder.getDistance();
  }

  /** Drives the robot forward until it hits the specified distance. */
  public void encoderDriveForward(double distance) {
    if (m_rightEncoder.getDistance() - m_rightEncoderOrigin < distance) {
      m_backLeft.set(ControlMode.PercentOutput, -0.4);
      m_backRight.set(ControlMode.PercentOutput, -0.4);
      m_frontLeft.set(ControlMode.PercentOutput, 0.4);
      m_frontRight.set(ControlMode.PercentOutput, 0.4);
    } else {
      m_frontLeft.set(ControlMode.PercentOutput, 0);
      m_backRight.set(ControlMode.PercentOutput, 0);
      m_frontLeft.set(ControlMode.PercentOutput, 0);
      m_frontRight.set(ControlMode.PercentOutput, 0);
    }
  }

  /** Prints encoder to System.out. */
  public void readEncoder() {
    System.out.println(m_rightEncoder.getDistance());
  }

  /** Resets the gyro to 0 and calibrates it. */
  public void initializeGyro() {
    m_gyro.calibrate();

    try {
      TimeUnit.SECONDS.sleep(4);
    } catch (Exception e) {
      System.out.println(e);
    }
    m_gyro.reset();
  }

  /** Resets the gyro to 0. */
  public void zeroGyro() {
    m_gyro.reset();
  }

  /** Function for turning. */
  public boolean turn(double rotationSpeed, double rotationDegrees) {
    boolean isDone = false;
    double currentDegrees = m_gyro.getAngle();
    System.out.println(m_gyro.getAngle());
    if (Math.abs(currentDegrees) < rotationDegrees * (1 - m_angleTolerance)) {
      m_backLeft.set(ControlMode.PercentOutput, -rotationSpeed);
      m_backRight.set(ControlMode.PercentOutput, -rotationSpeed);
      m_frontLeft.set(ControlMode.PercentOutput, -rotationSpeed);
      m_frontRight.set(ControlMode.PercentOutput, -rotationSpeed);

      for (int i = 0; i < m_reductions; i++) {
        if (Math.abs(currentDegrees) > m_speedReduction * rotationDegrees
            + (1 - m_speedReduction) / m_reductionIncrement * rotationDegrees * i) {
          m_backLeft.set(ControlMode.PercentOutput, -rotationSpeed * m_speedReduction
              * (m_reductionIncrement - i) / m_reductionIncrement);
          m_backRight.set(ControlMode.PercentOutput, -rotationSpeed * m_speedReduction
              * (m_reductionIncrement - i) / m_reductionIncrement);
          m_frontLeft.set(ControlMode.PercentOutput, -rotationSpeed * m_speedReduction
              * (m_reductionIncrement - i) / m_reductionIncrement);
          m_frontRight.set(ControlMode.PercentOutput, -rotationSpeed * m_speedReduction
              * (m_reductionIncrement - i) / m_reductionIncrement);
        }
      }
    } else {

      m_backLeft.set(ControlMode.PercentOutput, 0);
      m_backRight.set(ControlMode.PercentOutput, 0);
      m_frontLeft.set(ControlMode.PercentOutput, 0);
      m_frontRight.set(ControlMode.PercentOutput, 0);

      isDone = true;
      return isDone;
    }
    return isDone;
  }
}
