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

  private final TalonSRX m_talon1 = new TalonSRX(Constants.DriveBase.talon1);
  private final TalonSRX m_talon2 = new TalonSRX(Constants.DriveBase.talon2);
  private final TalonSRX m_talon3 = new TalonSRX(Constants.DriveBase.talon3);
  private final TalonSRX m_talon4 = new TalonSRX(Constants.DriveBase.talon4);

  private Gyro m_gyro = new ADXRS450_Gyro();

  private double m_angleTolerance = 3;
  private double m_speedReduction = 1 / 5;
  private double m_reductionIncrement = 5;
  private double m_reductions = 4;

  private double m_rightEncoderOrigin = 0;

  public void initEncoder() {
    m_rightEncoder.setDistancePerRotation(Constants.DISTANCE_PER_REVOLUTION);
  }

  public void zeroEncoder() {

    m_rightEncoderOrigin = m_rightEncoder.getDistance();
  }

  /** Drives the robot forward until it hits the specified distance. */
  public void encoderDriveForward(double distance) {
    if (m_rightEncoder.getDistance() - m_rightEncoderOrigin < distance) {
      m_talon1.set(ControlMode.PercentOutput, -0.4);
      m_talon2.set(ControlMode.PercentOutput, -0.4);
      m_talon3.set(ControlMode.PercentOutput, 0.4);
      m_talon4.set(ControlMode.PercentOutput, 0.4);
    } else {
      m_talon1.set(ControlMode.PercentOutput, 0);
      m_talon2.set(ControlMode.PercentOutput, 0);
      m_talon3.set(ControlMode.PercentOutput, 0);
      m_talon4.set(ControlMode.PercentOutput, 0);
    }
  }

  /** Prints encoder to System.out. */
  public void readEncoder() {
    System.out.println(m_rightEncoder.getDistance());
  }

  /** Drives at the specified speed for the right and left sides. */
  public void drive(double leftSpeed, double rightSpeed) {
    m_talon1.set(ControlMode.PercentOutput, -leftSpeed);
    m_talon2.set(ControlMode.PercentOutput, -leftSpeed);
    m_talon3.set(ControlMode.PercentOutput, rightSpeed);
    m_talon4.set(ControlMode.PercentOutput, rightSpeed);
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
      m_talon1.set(ControlMode.PercentOutput, -rotationSpeed);
      m_talon2.set(ControlMode.PercentOutput, -rotationSpeed);
      m_talon3.set(ControlMode.PercentOutput, -rotationSpeed);
      m_talon4.set(ControlMode.PercentOutput, -rotationSpeed);

      for (int i = 0; i < m_reductions; i++) {
        if (Math.abs(currentDegrees) > m_speedReduction * rotationDegrees
            + (1 - m_speedReduction) / m_reductionIncrement * rotationDegrees * i) {
          m_talon1.set(ControlMode.PercentOutput, -rotationSpeed * m_speedReduction
              * (m_reductionIncrement - i) / m_reductionIncrement);
          m_talon2.set(ControlMode.PercentOutput, -rotationSpeed * m_speedReduction
              * (m_reductionIncrement - i) / m_reductionIncrement);
          m_talon3.set(ControlMode.PercentOutput, -rotationSpeed * m_speedReduction
              * (m_reductionIncrement - i) / m_reductionIncrement);
          m_talon4.set(ControlMode.PercentOutput, -rotationSpeed * m_speedReduction
              * (m_reductionIncrement - i) / m_reductionIncrement);
        }
      }
    } else {

      m_talon1.set(ControlMode.PercentOutput, 0);
      m_talon2.set(ControlMode.PercentOutput, 0);
      m_talon3.set(ControlMode.PercentOutput, 0);
      m_talon4.set(ControlMode.PercentOutput, 0);

      isDone = true;
      return isDone;
    }
    return isDone;
  }
}
