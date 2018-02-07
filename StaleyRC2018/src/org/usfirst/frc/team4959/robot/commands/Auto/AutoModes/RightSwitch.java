package org.usfirst.frc.team4959.robot.commands.Auto.AutoModes;

import org.usfirst.frc.team4959.robot.commands.Auto.AutoCommands.AutoDropSequence;
import org.usfirst.frc.team4959.robot.commands.Auto.AutoCommands.Delay;
import org.usfirst.frc.team4959.robot.commands.Auto.AutoCommands.DriveTurn;
import org.usfirst.frc.team4959.robot.commands.Auto.AutoCommands.GyroTurning;
import org.usfirst.frc.team4959.robot.commands.Elevator.SetElevatorPosition;
import org.usfirst.frc.team4959.robot.util.Constants;
import org.usfirst.frc.team4959.robot.util.FieldDimensions;
import org.usfirst.frc.team4959.robot.util.PlateColorChecker;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Start on the right side of the field and aim to put a cube in the right side
 * of the switch. If the switch is not on our side, place the power cube in the
 * correct scale side.
 * 
 * DriveTurn(Inches, Power, Turn, Time) GyroTurning(Angle)
 */
public class RightSwitch extends CommandGroup {

	public RightSwitch() {

		// If right switch is ours
		if (PlateColorChecker.rightSwitchColor()) {
			addParallel(new SetElevatorPosition(Constants.ELEVATOR_SWITCH_ELEVATION)); // Raises elevator to position to
			addSequential(new DriveTurn(FieldDimensions.DS_TO_SWITCH, 0.85, 0, 2)); // Drive Forward to switch
			addSequential(new AutoDropSequence()); // Drop power cube into switch
			addSequential(new Delay(1.0));
			addSequential(new DriveTurn(-30, -0.75, 0, 2)); // Back off the switch
			// Turn to around the switch
		}

		// If left switch is ours
		else {
			addParallel(new SetElevatorPosition(Constants.ELEVATOR_HIGH_SCALE_ELEVATION));
			addSequential(new DriveTurn(FieldDimensions.HALF_DS_TO_SWITCH, 0.99, 0, 1)); // Goes straight
			addSequential(new DriveTurn(50, 0.8, 0.75, 3)); // Moves forward while turning right
			addSequential(new DriveTurn(42, 0.8, -0.75, 3)); // Moves forward while turning left to straighten back out
			addSequential(new DriveTurn(30, 0.9, 0, 0.5)); // Move past the switch

			// If the right scale is ours
			if (PlateColorChecker.rightScaleColor()) {
				addSequential(new DriveTurn(75, 1, 0, 2)); // Move all the way to the scale
				addSequential(new GyroTurning(-90, 1)); // Turn towards the scale
				addSequential(new AutoDropSequence()); // Place the power cube
				addSequential(new DriveTurn(-30, -0.5, 0, 1)); // Back off the scale
			}
			// If the left scale is ours
			else {
				addSequential(new DriveTurn(30, 0.9, 0, 0.5)); // Move to between the switch and scale
				addSequential(new DriveTurn(20, 0.8, -0.6, 1)); // Turn left
				addSequential(new DriveTurn(60, 1, 0, 2)); // Drive to the left side of the scale
				addSequential(new GyroTurning(90, 1)); // Turn towards the scale
				addSequential(new AutoDropSequence()); // Place the power cube
				addSequential(new DriveTurn(-30, -0.5, 0, 2)); // Back off the scale
			}
		}
	}
}