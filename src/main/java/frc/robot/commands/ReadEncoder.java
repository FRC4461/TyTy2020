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
 * ReadEncoder command that only gets the value of the encoder. Used just for testing.
 */
public class ReadEncoder extends CommandBase {
  private final Chassis m_chassis;

  /**
   * Creates a new ReadEncoder.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ReadEncoder(Chassis subsystem) {
    m_chassis = subsystem;

    addRequirements(m_chassis);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_chassis.readEncoder();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
}
