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
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.Conveyor;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.Intake;
import frc.robot.commands.ShootFlywheel;
import frc.robot.commands.ReadEncoder;
import frc.robot.commands.RunConveyor;
import frc.robot.commands.RunIntake;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Chassis m_chassis = new Chassis();
  private final Flywheel m_flywheel = new Flywheel();
  private final Conveyor m_conveyor = new Conveyor();
  private final Intake m_intake = new Intake();
  // private final Drive m_driveCommand = new Drive(m_chassis);
  private final ReadEncoder m_encoderReadCommand = new ReadEncoder(m_chassis);

  public final static Joystick leftJoystick = new Joystick(Constants.leftJoystick);
  public final static Joystick rightJoystick = new Joystick(Constants.rightJoystick);
  public final static Button button2 = new JoystickButton(leftJoystick, 2);
  public final static Button conveyorButton = new JoystickButton(rightJoystick, 2);
  public final static Button intakeButton = new JoystickButton(rightJoystick, 1);

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
    button2.whileHeld(new ShootFlywheel(m_flywheel));
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
