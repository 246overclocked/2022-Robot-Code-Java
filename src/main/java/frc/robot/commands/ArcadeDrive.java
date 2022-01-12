// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.Supplier;

public class ArcadeDrive extends CommandBase {
/** Creates a new ArcadeDrive. */
  private final Drivetrain drivetrain;
  private final Supplier<Double> xSpeed;
  private final Supplier<Double> zRotate;

  public ArcadeDrive(Drivetrain drivetrain, Supplier<Double> xSpeed,Supplier<Double> zRotate) {
    this.drivetrain = drivetrain;
    this.xSpeed = xSpeed;
    this.zRotate = zRotate;
    addRequirements(drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.arcadeDrive(xSpeed.get(), zRotate.get());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
