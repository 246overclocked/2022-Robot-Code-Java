// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final double SIMPLE_AUTON_SPEED = 0.2; // PLACEHOLDER
  public static final double SIMPLE_AUTON_RUNTIME = 5.0; // PLACEHOLDER
  public static final double MEDIUM_AUTON_OUTAKE_RUNTIME = 5.0; // PLACEHOLDER

  /**
   * Stores constants related to driver controls, SmartDashboard and other IO (Input/Output).
   */
  public final class IOConstants {
    public static final int PRIMARY_DRIVER_JOYSTICK_PORT = 0;
    public static final int SECONDARY_DRIVER_JOYSTICK_PORT = 1;

    public static final int INTAKE_BUTTON_NUMBER = 0; // PLACEHOLDER

    public static final int OUTTAKE_BUTTON_NUMBER = 1; // PLACEHOLDER
    public static final int TOWER_BUTTON_NUMBER = 1; // PLACEHOLDER
    public static final int CLIMBER_UP_BUTTON_NUMBER = 1; // PLACEHOLDER
    public static final int CLIMBER_DOWN_BUTTON_NUMBER = 1; // PLACEHOLDER
  }

  /**
   * Stores constants related to the DriveBase.
   */
  public final class DriveConstants {
    public static final int DRIVE_CURRENT_LIMIT = 60;
    public static final int DRIVE_TRIGGER_THRESHOLD = 60;
    public static final int DRIVE_TRIGGER_THRESHOLD_TIME = 2;
  }

  /**
   * Stores constants related to the Intake.
   */
  public final class IntakeConstants {
    public static final double INTAKE_SPEED = 1.0; // PLACEHOLDER

    public static final int INTAKE_MOTOR_ID = 0; // PLACEHOLDER
    public static final int INTAKE_TOP_FORWARD_CHANNEL = 0; // PLACEHOLDER
    public static final int INTAKE_TOP_REVERSE_CHANNEL = 1; // PLACEHOLDER
    public static final int INTAKE_BOTTOM_FORWARD_CHANNEL = 0; // PLACEHOLDER
    public static final int INTAKE_BOTTOM_REVERSE_CHANNEL = 1; // PLACEHOLDER

    public static final double INTAKE_SOLENOIDS_DELAY_TIME = 1.0; // PLACEHOLDER

    public static final int INTAKE_CURRENT_LIMIT = 40;
    public static final int INTAKE_TRIGGER_THRESHOLD = 40;
    public static final int INTAKE_TRIGGER_THRESHOLD_TIME = 2;
  }

  /**
   * Stores constants related to the Outtake.
   */
  public final class OuttakeConstants {
    public static final double OUTTAKE_SPEED = 0.5; // PLACEHOLDER
    public static final int OUTTAKE_MOTOR_ID1 = 1; // PLACEHOLDER
    public static final int OUTTAKE_MOTOR_ID2 = 2; // PLACEHOLDER

    public static final int OUTTAKE_CURRENT_LIMIT = 40;
  }

  /**
   * Stores constants related to the Tower.
   */
  public final class TowerConstants {
    public static final double TOWER_SPEED = 1.0; // PLACEHOLDER
    public static final int TOP_LEFT_TOWER_MOTOR_ID = 0; // PLACEHOLDER
    public static final int BOTTOM_LEFT_TOWER_MOTOR_ID = 1; // PLACEHOLDER
    public static final int TOP_RIGHT_TOWER_MOTOR_ID = 2; // PLACEHOLDER
    public static final int BOTTOM_RIGHT_TOWER_MOTOR_ID = 3; // PLACEHOLDER

    public static final int TOWER_CURRENT_LIMIT = 25;
  }

  /**
   * Stores constants related to the Climber.
   */
  public final class ClimberConstants {
    public static final int LEFT_CLIMBER_MOTOR_ID = 50; // PLACEHOLDER
    public static final int RIGHT_CLIMBER_MOTOR_ID = 51; // PLACEHOLDER
    public static final double CLIMBER_SPEED = 1.0; // PLACEHOLDER

    public static final int CLIMBER_CURRENT_LIMIT = 60;
    public static final int CLIMBER_TRIGGER_THRESHOLD = 60;
    public static final int CLIMBER_TRIGGER_THRESHOLD_TIME = 2;
  }

}
