/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.Chassis;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Move forward command that uses the chassis subsystem. Uses inches.
 */
public class MoveForwards extends CommandBase {
  private final Chassis m_Chassis;
  private final double m_givenDistance;

  /**
   * Creates a new MoveForwards.
   *
   * @param subsystem The subsystem used by this command.
   * @param givenDistance The distance to travel in inches.
   */
  public MoveForwards(Chassis subsystem, double givenDistance) {
    m_Chassis = subsystem;
    m_givenDistance = givenDistance;

    addRequirements(m_Chassis);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_Chassis.encoderDriveForward(m_givenDistance);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
}
