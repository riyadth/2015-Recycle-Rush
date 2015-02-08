// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4915.MecanumDrive;
    
import org.usfirst.frc4915.MecanumDrive.subsystems.DriveTrain;
import org.usfirst.frc4915.MecanumDrive.subsystems.Elevator;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;

//TODO decide and finalize the input ports for each sensor

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	/**
	 * DRIVETRAIN
	 */
    public static CANTalon mecanumDriveControls1LeftFront10;
    public static CANTalon mecanumDriveControls1LeftRear11;
    public static CANTalon mecanumDriveControls1RightFront12;
    public static CANTalon mecanumDriveControls1RightRear13;
    public static RobotDrive driveTrainRobotDrive;
    public static final double DEFAULT_MAX_OUTPUT = 950;
    // Gyroscope
    public static Gyro gyro;
    // Distance Sensor
    public static Ultrasonic distanceSensor;
    
    /**
     * ELEVATOR
     */
    public static CANTalon elevatorWinchMotor14;
    //Limit Switches
    // public static DigitalInput limitSwitchBottom; // May be used for elevator as a sensor for testing if at the bottom of elevator
    // public static DigitalInput limitSwitchTop; // May be used for elevator as a sensor for testing if at the top of elevator
    // Potentiometer
    //private static final int SCALE = 1; // TODO find correct scale for the potentiometer
	//public static AnalogPotentiometer potentiometer;
    
    /**
     * GRABBER
     */
    public static DoubleSolenoid mommaSolenoid;
    public static Solenoid babySolenoid;
    
    /**
     * GENERAL SENSORS
     */
    public static BuiltInAccelerometer accelerometer;

    // The Pneumatic Control Module's CAN Node ID. Use 10 for 4915. Use 20 for 9999.
    public final static int PCM_NODE_ID = 20;
    
    public static void init() {
		
	
	    /**
	     * MECANUM WHEEL START
	     */
		mecanumDriveControls1LeftFront10 = new CANTalon(10); 
		mecanumDriveControls1LeftRear11 = new CANTalon(11);
		mecanumDriveControls1RightFront12 = new CANTalon(12);
		mecanumDriveControls1RightRear13 = new CANTalon(13);
		
		changeControlMode(ControlMode.Speed);
		
		driveTrainRobotDrive = new RobotDrive(mecanumDriveControls1LeftFront10, 
				   mecanumDriveControls1LeftRear11,
				   mecanumDriveControls1RightFront12, 
				   mecanumDriveControls1RightRear13);
		// Sets the max output to ???, 10ft per 1 secf -- After testing, we have decided to go with 950.
		driveTrainRobotDrive.setMaxOutput(DEFAULT_MAX_OUTPUT);
		
		driveTrainRobotDrive.setSafetyEnabled(true);
		driveTrainRobotDrive.setExpiration(0.1);
		driveTrainRobotDrive.setSensitivity(0.5);
		
		driveTrainRobotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
		driveTrainRobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
		
		// Gyro instantiation
		gyro = new Gyro(0); //Port numbers need to be decided TODO Setup Gyro on robot
		
		// Distance instantiation
		distanceSensor = new Ultrasonic(0,1); //Port numbers need to be decided
		/**
		 * MECANUM WHEEL END
		 */
		
		/**
		 * ELEVATOR START
		 */
		//ELEVATOR instantiation
		// TODO set limit switch configuration on the winch motor
		elevatorWinchMotor14 = new CANTalon(14);
		elevatorWinchMotor14.changeControlMode(ControlMode.Position);
		elevatorWinchMotor14.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
		elevatorWinchMotor14.setPID(1, 0.002, 1.0, 0.0001, 255, 200, 0); // TODO Determine PID values and Ramp Rate
		// Potentiometer instantiation
		
		// TODO Limit Switches instantiation goes here
		/**
		 * ELEVATOR END
		 */
		
		
		/**
		 * GRABBER START
		 */
		// Double Solenoid instantiation. Wiring: 0 --> Forward channel (extended). 1 --> Reverse channel (retracted).
		mommaSolenoid = new DoubleSolenoid(PCM_NODE_ID, 0, 1); // Uses 10 as the Node ID for the PCM.
		babySolenoid = new Solenoid(PCM_NODE_ID, 2); //Port numbers need to be decided for both solenoids
		/**
		 * GRABBER END
		 */
		
		
		/**
		 * GENERAL SENSORS START
		 */
		// Built in Accelerometer
		accelerometer = new BuiltInAccelerometer();
		accelerometer.startLiveWindowMode();
		/**
		 * SENSORS END
		 */
    }
    
    public static void changeControlMode(ControlMode mode) {
//      Set control mode for the CAN Talon motor controllers
		mecanumDriveControls1LeftFront10.changeControlMode(mode);
		mecanumDriveControls1LeftRear11.changeControlMode(mode);
		mecanumDriveControls1RightFront12.changeControlMode(mode);
		mecanumDriveControls1RightRear13.changeControlMode(mode);

//		Makes sure the Feedback Device is a Quad Encoder
		mecanumDriveControls1LeftFront10.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
	    mecanumDriveControls1LeftRear11.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		mecanumDriveControls1RightFront12.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		mecanumDriveControls1RightRear13.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);

		//TODO confirm that these values are what we want
		//Sets PID Values for the Mecanum Drive Train
		if(mode.equals(ControlMode.Speed)) {
			mecanumDriveControls1LeftFront10.setPID(1, 0.002, 1.0, 0.0001, 255, 200, 0);
			mecanumDriveControls1LeftRear11.setPID(1, 0.002, 1.0, 0.0001, 255, 200, 0);
			mecanumDriveControls1RightFront12.setPID(1, 0.002, 1.0, 0.0001, 255, 200, 0);
			mecanumDriveControls1RightRear13.setPID(1, 0.002, 1.0, 0.0001, 255, 200, 0);
		} else if(mode.equals(ControlMode.Position)) {
			mecanumDriveControls1LeftFront10.setPID(0.4, 0.002, 1.0, 0.0001, 255, 50, 0);
			mecanumDriveControls1LeftRear11.setPID(0.4, 0.002, 1.0, 0.0001, 255, 50, 0);
			mecanumDriveControls1RightFront12.setPID(0.4, 0.002, 1.0, 0.0001, 255, 50, 0);
			mecanumDriveControls1RightRear13.setPID(0.4, 0.002, 1.0, 0.0001, 255, 50, 0);
		}

    }
}
