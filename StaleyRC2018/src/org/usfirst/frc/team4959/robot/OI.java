/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4959.robot;
//Change?

import org.usfirst.frc.team4959.robot.commands.Climber.RunWinchMotor;
import org.usfirst.frc.team4959.robot.commands.Drive.ShifterToggle;
import org.usfirst.frc.team4959.robot.commands.Elevator.SetElevatorPosition;
import org.usfirst.frc.team4959.robot.commands.Intake.IntakePistonToggle;
import org.usfirst.frc.team4959.robot.util.Constants;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick xboxController;
	public static Joystick xboxControllertwo;
	
	SetElevatorPosition bottomPosition = new SetElevatorPosition(Constants.ELEVATOR_BOTTOM_ELEVATION);
	SetElevatorPosition switchPosition = new SetElevatorPosition(Constants.ELEVATOR_SWITCH_ELEVATION);
	SetElevatorPosition lowScalePosition = new SetElevatorPosition(Constants.ELEVATOR_LOW_SCALE_ELEVATION);
	SetElevatorPosition midScalePosition = new SetElevatorPosition(Constants.ELEVATOR_MID_SCALE_ELEVATION);
	SetElevatorPosition highScalePosition = new SetElevatorPosition(Constants.ELEVATOR_HIGH_SCALE_ELEVATION);

	public OI() {

		// ***** X-Box Controller *****
		xboxController = new Joystick(RobotMap.XBOX_PORT);
		xboxControllertwo = new Joystick(RobotMap.XBOX_TWO_PORT);

		// Toggle for shifting between high and low gear
		Button shifterToggle = new JoystickButton(xboxController, RobotMap.B_BUTTON);
		shifterToggle.whenPressed(new ShifterToggle());
		
		Button intakePistonToggle = new JoystickButton(xboxControllertwo, RobotMap.RIGHT_BUMPER);
		intakePistonToggle.whenPressed(new IntakePistonToggle());

		// Sets the elevator's position to bottom elevation and cancels any other elevation command occuring
		Button bottomElevation = new JoystickButton(xboxControllertwo, RobotMap.A_BUTTON);
		bottomElevation.cancelWhenPressed(switchPosition);
		bottomElevation.cancelWhenPressed(lowScalePosition);
		bottomElevation.cancelWhenPressed(midScalePosition);
		bottomElevation.cancelWhenPressed(highScalePosition);
		bottomElevation.whenPressed(bottomPosition);
		
		// Sets the elevator's position to switch elevation and cancels any other elevation command occuring
		Button switchElevation = new JoystickButton(xboxControllertwo, RobotMap.B_BUTTON);
		switchElevation.cancelWhenPressed(bottomPosition);
		switchElevation.cancelWhenPressed(lowScalePosition);
		switchElevation.cancelWhenPressed(midScalePosition);
		switchElevation.cancelWhenPressed(highScalePosition);
		switchElevation.whenPressed(switchPosition);
		
		// Sets the elevator's position to low scale elevation and cancels any other elevation command occuring
		Button lowScaleElevation = new JoystickButton(xboxControllertwo, RobotMap.Y_BUTTON);
		lowScaleElevation.cancelWhenPressed(bottomPosition);
		lowScaleElevation.cancelWhenPressed(switchPosition);
		lowScaleElevation.cancelWhenPressed(midScalePosition);
		lowScaleElevation.cancelWhenPressed(highScalePosition);
		lowScaleElevation.whenPressed(lowScalePosition);
		
		// Sets the elevator's position to mid scale elevation and cancels any other elevation command occuring
		Button midScaleElevation = new JoystickButton(xboxControllertwo, RobotMap.START_BUTTON);
		midScaleElevation.cancelWhenPressed(bottomPosition);
		midScaleElevation.cancelWhenPressed(switchPosition);
		midScaleElevation.cancelWhenPressed(lowScalePosition);
		midScaleElevation.cancelWhenPressed(highScalePosition);
		midScaleElevation.whenPressed(midScalePosition);
		
		// Sets the elevator's position to high scale elevation and cancels any other elevation command occuring
		Button highScaleElevation = new JoystickButton(xboxControllertwo, RobotMap.BACK_BUTTON);
		highScaleElevation.cancelWhenPressed(bottomPosition);
		highScaleElevation.cancelWhenPressed(switchPosition);
		highScaleElevation.cancelWhenPressed(lowScalePosition);
		highScaleElevation.cancelWhenPressed(midScalePosition);
		highScaleElevation.whenPressed(highScalePosition);
		
		// Stops any elevation command to allow player to use joystick control on the elevator. 
		Button stopElevator = new JoystickButton(xboxControllertwo, RobotMap.X_BUTTON);
		stopElevator.cancelWhenPressed(bottomPosition);
		stopElevator.cancelWhenPressed(switchPosition);
		stopElevator.cancelWhenPressed(lowScalePosition);
		stopElevator.cancelWhenPressed(midScalePosition);
		stopElevator.cancelWhenPressed(highScalePosition);
		
		Button winch = new JoystickButton(xboxControllertwo, RobotMap.RIGHT_BUMPER);
		winch.whileHeld(new RunWinchMotor(1));
	}
	
	// ***** Getters for X-Box controller(s) raw axis *****

	// Controller 1
	public double getLeftStickXCont1() {
		return xboxController.getRawAxis(RobotMap.LEFT_X_AXIS);
	}
	
	public double getLeftStickYCont1() {
		return xboxController.getRawAxis(RobotMap.LEFT_Y_AXIS);
	}
	
	public double getRightStickYCont1() {
		return xboxController.getRawAxis(RobotMap.RIGHT_Y_AXIS);
	}
	
	public double getLeftTriggerCont1() {
		return xboxController.getRawAxis(RobotMap.LEFT_TRIGGER);
	}
	
	public double getRightTriggerCont1() {
		return xboxController.getRawAxis(RobotMap.RIGHT_TRIGGER);
	}

	// Controller 2
	public double getRightStickYCont2() {
		return xboxControllertwo.getRawAxis(RobotMap.RIGHT_Y_AXIS);
	}

	public double getLeftStickYCont2() {
		return xboxControllertwo.getRawAxis(RobotMap.LEFT_Y_AXIS);
	}
	
	public double getLeftTriggerCont2() {
		return xboxControllertwo.getRawAxis(RobotMap.LEFT_TRIGGER);
	}
	
	public double getRightTriggerCont2() {
		return xboxControllertwo.getRawAxis(RobotMap.RIGHT_TRIGGER);
	}
}
