package org.usfirst.frc.team4959.robot.subsystems;

import org.usfirst.frc.team4959.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem that controls our elevator system that lifts power cubes to certain
 * elevations
 */
public class Elevator extends Subsystem {

	private WPI_TalonSRX talon;
	
	boolean brake = false;

	// PID values
	private final double kP = 0.0353;
	private final double kI = 0;
	private final double kD = 0.2;

	public Elevator() {
		talon = new WPI_TalonSRX(RobotMap.ELEVATOR_MOTOR_PORT);
		// Brake mode if brake is true		Coast Mode if brake is false
		talon.setNeutralMode(brake ? NeutralMode.Brake : NeutralMode.Coast);
		// Configures the sensor hooked up to it to be an encoder. 
		talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		talon.setSensorPhase(false);
		
		talon.configPeakOutputForward(1, 10);
		talon.configPeakOutputReverse(-1, 10);
		talon.configForwardSoftLimitEnable(false, 10);
		talon.configReverseSoftLimitEnable(true, 10);
				
		talon.config_kP(0, kP, 10);
		talon.config_kI(0, kI, 10);
		talon.config_kD(0, kD, 10);
	}

	public void initDefaultCommand() {

	}

	public void setPosition(double position) {
		talon.set(ControlMode.Position, position);
	}
	
	public void zeroPosition() {
		talon.setSelectedSensorPosition(0, 0, 10);
	}
	
	public boolean onTarget() {
		return false; // yolo
	}
	
	public double getPosition() {
		return talon.getSelectedSensorPosition(0);
	}
	
	public void stopElevator() {
		talon.stopMotor();
	}
}
