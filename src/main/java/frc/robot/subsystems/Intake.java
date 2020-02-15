/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
// import com.ctre.phoenix.motorcontrol.LimitSwitchSource;

/**
 * Add your docs here.
 */
public class Intake extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final TalonSRX intakeMotor = new TalonSRX(Constants.Shooter.INTAKE_MOTOR_ID);

  public void runIntake() {
    intakeMotor.set(ControlMode.PercentOutput, 0.5);
  }

  public void stopIntake() {
    intakeMotor.set(ControlMode.PercentOutput, 0);
  }
}
