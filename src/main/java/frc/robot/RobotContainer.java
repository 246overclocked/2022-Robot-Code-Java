// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.IOConstants;
import frc.robot.commands.auton.MediumAutonCommand;
import frc.robot.commands.auton.SimpleAutonCommand;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Outtake;
import frc.robot.commands.outtake.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveBase driveBase = new DriveBase(0, 1, 2, 3); // TODO: remove dummy port values
  private final Outtake outtake = new Outtake(Constants.OuttakeConstants.OUTTAKE_MOTOR_ID);

  private final Joystick primaryDriverJoystick =
      new Joystick(IOConstants.PRIMARY_DRIVER_JOYSTICK_PORT);

  private final Joystick secondaryDriverJoystick =
      new Joystick(IOConstants.SECONDARY_DRIVER_JOYSTICK_PORT);
  private final JoystickButton outtakeButton =
      new JoystickButton(secondaryDriverJoystick,
          Constants.IOConstants.OUTTAKE_BUTTON_NUMBER);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    configureSmartDash();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    /**
     * Code to add Outtake support in teleop.
     */
    outtakeButton
        .whenActive(new RunOuttakeCommand(outtake, Constants.OuttakeConstants.OUTTAKE_SPEED))
        .whenInactive(new StopOuttakeCommand(outtake));
  }

  // A simple auto routine.
  private final Command simpleAuto =
      new SimpleAutonCommand(driveBase, Constants.SIMPLE_AUTON_SPEED,
          Constants.SIMPLE_AUTON_RUNTIME);

  // A medium auto routine.
  private final Command mediumAuto =
      new MediumAutonCommand(driveBase, outtake, Constants.OuttakeConstants.OUTTAKE_SPEED,
          Constants.SIMPLE_AUTON_SPEED, Constants.MEDIUM_AUTON_OUTAKE_RUNTIME,
          Constants.SIMPLE_AUTON_RUNTIME);

  private final SendableChooser<Command> autonChooser = new SendableChooser<>();

  /**
   * Use this method to run tasks that configure sendables and other smartdashboard items.
   */
  private void configureSmartDash() {
    // Add commands to the autonomous command chooser
    autonChooser.setDefaultOption("Simple Auton", simpleAuto);
    autonChooser.addOption("Medium Auto", mediumAuto);

    // Put the chooser on the dashboard
    SmartDashboard.putData(autonChooser);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return autonChooser.getSelected();
  }
}
