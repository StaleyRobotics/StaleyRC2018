package org.usfirst.frc.team4959.robot.commands.Intake;

import org.usfirst.frc.team4959.robot.Robot;
import org.usfirst.frc.team4959.robot.util.Constants;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Brings in a power cube or spits one out
 */
public class RunIntake extends Command {
	
	private double intakeInPower;
	private double intakeOutPower;

    public RunIntake() {
    	requires(Robot.intake);
    	intakeInPower = Constants.INTAKE_IN_SPEED;
    	intakeOutPower = Constants.INTAKE_OUT_SPEED;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.m_oi.getRightTriggerCont2() > 0.15) {
    		Robot.intake.succBoi(intakeOutPower);
    	}
    	else if(Robot.m_oi.getLeftTriggerCont2() > 0.15) {
    		Robot.intake.succBoi(intakeInPower);
    	}
    	else {
    		Robot.intake.succBoi(0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.succBoi(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
