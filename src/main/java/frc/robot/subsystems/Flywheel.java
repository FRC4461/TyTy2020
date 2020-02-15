/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.DutyCycleEncoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Add your docs here.
 */
public class Flywheel extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  //private DutyCycleEncoder hurachesEncoder = new DutyCycleEncoder(0);

  private static final TalonSRX talon5 = new TalonSRX(Constants.Shooter.talon5);
  private double frontEncoderOrigin = 1;

  public void ShootFlyWheel () {
    talon5.set(ControlMode.PercentOutput, 0.7); 
  } 

  public void stopFlyWheel(){
    System.out.println("STOP THE FREAKING FLYWHEEL");
    talon5.set(ControlMode.PercentOutput,0);
  }
}
