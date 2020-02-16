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
 * Conveyor belt subsystem. Mechanism that takes the ball from the intake to the flywheel.
 */
public class Conveyor extends SubsystemBase {
  private final TalonSRX m_conveyorMotor = new TalonSRX(Constants.Shooter.CONVEYOR_MOTOR_ID);

  public void runConveyor() {
    m_conveyorMotor.set(ControlMode.PercentOutput, 0.5);
  }

  public void stopConveyor() {
    m_conveyorMotor.set(ControlMode.PercentOutput, 0);
  }
}
