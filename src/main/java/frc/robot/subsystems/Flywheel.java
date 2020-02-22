/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Flywheel subsystem.
 */
public class Flywheel extends SubsystemBase {
  private static final TalonSRX m_flywheelMotor = new TalonSRX(Constants.Shooter.FLYWHEEL_MOTOR_ID);
  private double m_flywheelSpeed = 0;

  /**
   * Constructor for Flywheel.
   */
  public Flywheel(double flywheelSpeed) {
    m_flywheelSpeed = flywheelSpeed;
  }

  public void runFlyWheel() {
    m_flywheelMotor.set(ControlMode.PercentOutput, m_flywheelSpeed);
  }

  public void stopFlyWheel() {
    m_flywheelMotor.set(ControlMode.PercentOutput, 0);
  }
}
