/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;

/**
 * Rotate command that rotates the robot by degrees. Uses chassis and gyro.
 */
public class Rotate extends CommandBase {
  private final Chassis m_chassis;
  private final double m_rotation;

  boolean m_isDone = false;

  /**
   * Creates a new Rotate.
   *
   * @param subsystem The subsystem used by this command.
   * @param rotation  Rotation angle in degrees.
   */
  public Rotate(Chassis subsystem, double rotation) {
    m_chassis = subsystem;
    m_rotation = rotation;

    addRequirements(m_chassis);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_chassis.zeroGyro();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_isDone = m_chassis.turn(.35, m_rotation);
  }

  @Override
  public boolean isFinished() {
    return m_isDone;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
}
