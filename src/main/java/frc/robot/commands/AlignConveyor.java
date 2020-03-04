/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Intake;

public class AlignConveyor extends CommandBase {
  /**
   * Creates a new AlignConveyor.
   */
  private final Conveyor m_conveyor;

  private long t;
  private long end;

  public AlignConveyor(Conveyor subsystem) {
    m_conveyor = subsystem;
    addRequirements(m_conveyor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Do a timer
    t = System.currentTimeMillis();
    end = t + 300;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    while (System.currentTimeMillis() < end) {
      System.out.println("Align intake");
      m_conveyor.runConveyor(Constants.ConveyorSpeedConstants.alignConveyor);
      System.out.printf("Time is %s and end is %d", t, end);
    }
    end(true);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_conveyor.stopConveyor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
