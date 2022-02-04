// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.IntakeConstants;

/**
 * A subsystem that controls the intake on a robot.
 */
public class Intake extends SubsystemBase {
  private final DoubleSolenoid topSolenoid;
  private final DoubleSolenoid bottomSolenoid;
  private final CANSparkMax intakeMotor;

  /**
   * Constructs an Intake with a {@link CANSparkMax} at the given CAN ID and a
   * {@link DoubleSolenoid} with the given forward and reverse channels.
   *
   * @param intakeMotorID The CAN ID of the intake motor
   * @param topForwardChannel The forward channel of the top solenoid
   * @param topReverseChannel The reverse channel of the top solenoid
   * @param bottomForwardChannel The forward channel of the bottom solenoid
   * @param bottomReverseChannel The reverse channel of the bottom solenoid
   */
  public Intake(int intakeMotorID, int topForwardChannel, int topReverseChannel,
      int bottomForwardChannel, int bottomReverseChannel) {
    this(intakeMotorID, MotorType.kBrushless, topForwardChannel, topReverseChannel,
        bottomForwardChannel, bottomReverseChannel);
  }

  /**
   * Constructs an Intake with a {@link CANSparkMax} with the given CAN ID and motor type and a
   * {@link DoubleSolenoid} with the given forward and reverse channels.
   *
   * @param intakeMotorID The CAN ID of the intake motor
   * @param motorType The motor type of the intake motor
   * @param topForwardChannel The forward channel of the top solenoid
   * @param topReverseChannel The reverse channel of the top solenoid
   * @param bottomForwardChannel The forward channel of the bottom solenoid
   * @param bottomReverseChannel The reverse channel of the bottom solenoid
   */
  public Intake(int intakeMotorID, MotorType motorType, int topForwardChannel,
      int topReverseChannel, int bottomForwardChannel, int bottomReverseChannel) {
    topSolenoid =
        new DoubleSolenoid(PneumaticsModuleType.REVPH, topForwardChannel, topReverseChannel);
    bottomSolenoid =
        new DoubleSolenoid(PneumaticsModuleType.REVPH, bottomForwardChannel, bottomReverseChannel);
    intakeMotor = new CANSparkMax(intakeMotorID, motorType);
    intakeMotor.setIdleMode(IdleMode.kBrake);
  }

  /**
   * Sets the intake motor speed.
   * 
   * @param newSpeed The speed of the intake motor
   * @throws IllegalStateException if trying to set speed while retracted
   */
  public void setSpinSpeed(double newSpeed) throws IllegalStateException {
    if (isRetracted() && newSpeed != 0) {
      throw new IllegalStateException();
    }
    intakeMotor.set(newSpeed);
  }

  /**
   * Detects if the intake is retracted.
   */
  public boolean isRetracted() {
    return getTopExtension() == DoubleSolenoid.Value.kReverse
        && getBottomExtension() == DoubleSolenoid.Value.kReverse;
  }

  /**
   * Detects if the intake is extended.
   */
  public boolean isExtended() {
    return getTopExtension() == DoubleSolenoid.Value.kForward
        && getBottomExtension() == DoubleSolenoid.Value.kForward;
  }

  /**
   * Detects if the intake is in neutral extension.
   */
  public boolean isNeutralExtension() {
    return getTopExtension() == DoubleSolenoid.Value.kOff
        && getBottomExtension() == DoubleSolenoid.Value.kOff;
  }

  /**
   * Returns the extension state of the top solenoid.
   */
  public DoubleSolenoid.Value getTopExtension() {
    return topSolenoid.get();
  }

  /**
   * Returns the extension state of the bottom solenoid.
   */
  public DoubleSolenoid.Value getBottomExtension() {
    return bottomSolenoid.get();
  }

  /**
   * Retracts the intake and sets spin speed to 0.
   */
  public Command getRetractionCommand() {
    return new SequentialCommandGroup(
        new InstantCommand(() -> bottomSolenoid.set(DoubleSolenoid.Value.kReverse)),
        new WaitCommand(IntakeConstants.INTAKE_SOLENOIDS_DELAY_TIME),
        new InstantCommand(() -> topSolenoid.set(DoubleSolenoid.Value.kReverse)));
  }

  /**
   * Sets the intake to Neutral extension.
   */
  public Command getNeutralExtensionCommand() {
    return new SequentialCommandGroup(
        new InstantCommand(() -> bottomSolenoid.set(DoubleSolenoid.Value.kOff)),
        new InstantCommand(() -> topSolenoid.set(DoubleSolenoid.Value.kOff)));
  }

  /**
   * Extends the intake.
   */
  public Command getExtensionCommand() {
    return new SequentialCommandGroup(
        new InstantCommand(() -> topSolenoid.set(DoubleSolenoid.Value.kForward)),
        new WaitCommand(IntakeConstants.INTAKE_SOLENOIDS_DELAY_TIME),
        new InstantCommand(() -> bottomSolenoid.set(DoubleSolenoid.Value.kForward)));
  }
}
