/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.Constants;

/**
 * Conveyor belt subsystem. Mechanism that takes the ball from the intake to the flywheel.
 */
public class Conveyor extends SubsystemBase {
  private final TalonSRX conveyorMotor = new TalonSRX(Constants.Shooter.CONVEYOR_MOTOR_ID);

  public void runConveyor() {
    conveyorMotor.set(ControlMode.PercentOutput, 0.5);
  }

  public void stopConveyor() {
    conveyorMotor.set(ControlMode.PercentOutput, 0);
  }
}
