/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Conveyor;

/**
 * RunConveyor that runs the conveyor motor.
 */
public class RunConveyor extends CommandBase {
  private final Conveyor m_conveyor;

  /**
   * Creates a new RunConveyor.
   *
   * @param subsystem The subsystem used by this command.
   */
  public RunConveyor(Conveyor subsystem) {
    m_conveyor = subsystem;

    addRequirements(m_conveyor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_conveyor.runConveyor(Constants.ConveyorSpeedConstants.intakeConveyor);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_conveyor.stopConveyor();
  }
}
