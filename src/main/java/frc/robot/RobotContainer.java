/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ReadEncoder;
import frc.robot.commands.RunConveyor;
import frc.robot.commands.RunIntake;
import frc.robot.commands.ShootFlywheel;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.Intake;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final double m_normalFlywheelSpeed = 0.85;
  private final double m_turboFlywheelSpeed = 1;
  private final Chassis m_chassis = new Chassis();
  private final Flywheel m_normalFlywheel = new Flywheel(m_normalFlywheelSpeed);
  private final Flywheel m_turboFlywheel = new Flywheel(m_turboFlywheelSpeed);
  private final Conveyor m_conveyor = new Conveyor();
  private final Intake m_intake = new Intake();
  // private final Drive m_driveCommand = new Drive(m_chassis);
  private final ReadEncoder m_encoderReadCommand = new ReadEncoder(m_chassis);

  public static final Joystick leftJoystick = new Joystick(Constants.leftJoystick);
  public static final Joystick rightJoystick = new Joystick(Constants.rightJoystick);
  public static final Button normalButton = new JoystickButton(leftJoystick, 2);
  public static final Button turboButton = new JoystickButton(leftJoystick, 3);
  public static final Button conveyorButton = new JoystickButton(rightJoystick, 2);
  public static final Button intakeButton = new JoystickButton(rightJoystick, 1);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    
    // Configure the button bindings
    configureButtonBindings();
    // m_chassis.setDefaultCommand(m_driveCommand);
    m_chassis.setDefaultCommand(m_encoderReadCommand);

    
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    normalButton.whileHeld(new ShootFlywheel(m_normalFlywheel));
    turboButton.whileHeld(new ShootFlywheel(m_turboFlywheel));
    conveyorButton.whileHeld(new RunConveyor(m_conveyor));
    intakeButton.whileHeld(new RunIntake(m_intake));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }
}
