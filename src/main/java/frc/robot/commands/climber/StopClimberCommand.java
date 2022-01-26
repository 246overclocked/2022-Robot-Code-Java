
package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

/**
 * Stops the {@link Climber} while active
 */
public class StopClimberCommand extends CommandBase {
  private final Climber climber;

  /**
   * @param climber the {@link Climber} to stop
   */
  public StopClimberCommand(Climber climber) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.climber = climber;
    addRequirements(this.climber);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    climber.setSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}