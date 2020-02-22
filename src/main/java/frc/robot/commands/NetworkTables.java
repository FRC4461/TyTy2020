/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class NetworkTables extends CommandBase {
  /**
   * Declaring the NetworkTable.
   */
  private NetworkTableEntry m_xEntry;
  private NetworkTableEntry m_yEntry;
  private double m_y = 0;
  private double m_x = 0;
  

  /**
   * Initializing the NetworkTable and linking member variables with the x and y values. 
   */
  public void robotInit() {
    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    NetworkTable table = inst.getTable("datatable");
    m_xEntry = table.getEntry("X");
    m_yEntry = table.getEntry("Y");
  }
  /**
   * Making the variable changeable in teleop. 
   */

  public void teleopPeriodic() {
    m_xEntry.setDouble(m_x);
    m_yEntry.setDouble(m_y);
    m_x += 0.05;
    m_y += 1;
  }
}


