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
 * An example command that uses an example subsystem.
 */
public class Rotate extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Chassis m_Chassis;
  private final double m_rotation;

  boolean isDone = false;
  /**
   * Creates a new EncoderDrive.
   *
   * @param subsystem The subsystem used by this command.
   * @param rotation Distance in inches to drive.
   */
  public Rotate(Chassis subsystem, double rotation) {
    m_Chassis = subsystem;
    m_rotation = rotation;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_Chassis);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      m_Chassis.zeroGyro();
    }


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      isDone = m_Chassis.turn(.35, m_rotation);
    }

  @Override
  public boolean isFinished() {
      return isDone;
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
}

