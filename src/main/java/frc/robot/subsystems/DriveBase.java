
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import overclocked.stl.motorcontrol.OverclockedDifferentialDrive;

/**
 * A subsystem that controls the drive train (aka chassis) on a robot.
 */
public class DriveBase extends SubsystemBase {

  private final WPI_TalonFX leftFrontMotor;
  private final WPI_TalonFX leftBackMotor;
  private final WPI_TalonFX rightFrontMotor;
  private final WPI_TalonFX rightBackMotor;

  private NeutralMode motorNeutralMode;

  private final OverclockedDifferentialDrive differentialDrive;

  /**
   * Constructs a DriveBase with a {@link TalonFX} at each of the given CAN IDs.
   *
   * @param leftFrontId The CAN ID of the Left Front motor
   * @param leftBackId The CAN ID of the Left Back motor
   * @param rightFrontId The CAN ID of the Right Front motor
   * @param rightBackId The CAN ID of the Right Back motor
   */
  public DriveBase(int leftFrontId, int leftBackId, int rightFrontId, int rightBackId) {
    leftFrontMotor = new WPI_TalonFX(leftFrontId);
    leftFrontMotor.setInverted(TalonFXInvertType.Clockwise);
    leftBackMotor = new WPI_TalonFX(leftBackId);
    leftBackMotor.setInverted(TalonFXInvertType.Clockwise);

    rightFrontMotor = new WPI_TalonFX(rightFrontId);
    rightFrontMotor.setInverted(TalonFXInvertType.CounterClockwise);
    rightBackMotor = new WPI_TalonFX(rightBackId);
    rightBackMotor.setInverted(TalonFXInvertType.CounterClockwise);

    leftFrontMotor.configSupplyCurrentLimit(
        new SupplyCurrentLimitConfiguration(true, DriveConstants.CURRENT_LIMIT,
            DriveConstants.TRIGGER_THRESHOLD,
            DriveConstants.TRIGGER_THRESHOLD_TIME));
    leftBackMotor.configSupplyCurrentLimit(
        new SupplyCurrentLimitConfiguration(true, DriveConstants.CURRENT_LIMIT,
            DriveConstants.TRIGGER_THRESHOLD,
            DriveConstants.TRIGGER_THRESHOLD_TIME));
    rightFrontMotor.configSupplyCurrentLimit(
        new SupplyCurrentLimitConfiguration(true, DriveConstants.CURRENT_LIMIT,
            DriveConstants.TRIGGER_THRESHOLD,
            DriveConstants.TRIGGER_THRESHOLD_TIME));
    rightBackMotor.configSupplyCurrentLimit(
        new SupplyCurrentLimitConfiguration(true, DriveConstants.CURRENT_LIMIT,
            DriveConstants.TRIGGER_THRESHOLD,
            DriveConstants.TRIGGER_THRESHOLD_TIME));


    setBrakingMode(NeutralMode.Brake);

    differentialDrive =
        new OverclockedDifferentialDrive(
            new MotorControllerGroup(leftFrontMotor, leftBackMotor),
            new MotorControllerGroup(rightFrontMotor, rightBackMotor),
            DriveConstants.ACCELERATION_RATE_LIMIT);

    CommandScheduler.getInstance().registerSubsystem(this);
  }

  /**
   * Toggles the {@link NeutralMode} between Coast and Brake.
   */
  public void toggleBrakingMode() {
    switch (motorNeutralMode) {
      case Brake:
        setBrakingMode(NeutralMode.Coast);
        return;
      case Coast:
      default:
        setBrakingMode(NeutralMode.Brake);
        return;
    }
  }

  /**
   * Sets the braking mode to the given {@link NeutralMode}.
   *
   * @param mode The {@link NeutralMode} to set the motors to
   */
  public void setBrakingMode(NeutralMode mode) {
    leftFrontMotor.setNeutralMode(mode);
    leftBackMotor.setNeutralMode(mode);
    rightFrontMotor.setNeutralMode(mode);
    rightBackMotor.setNeutralMode(mode);
    motorNeutralMode = mode;
  }

  /**
   * Sets the motor speeds to 0.
   */
  public void stopDrive() {
    differentialDrive.stopMotor();
  }

  /**
   * Drives the motors using arcade drive controls.
   *
   * @param linearSpeed The linear speed
   * @param angularSpeed The angular speed
   */
  public void arcadeDrive(double linearSpeed, double angularSpeed) {
    differentialDrive.arcadeDrive(linearSpeed, angularSpeed);
  }

  /**
   * Drives the motors using tank drive controls.
   *
   * @param leftSpeed The left speed
   * @param rightSpeed The right speed
   */
  public void tankDrive(double leftSpeed, double rightSpeed) {
    differentialDrive.tankDrive(leftSpeed, rightSpeed);
  }
}
