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
import org.usfirst.frc4915.MecanumDrive.commands.GenericTestCommand;
import org.usfirst.frc4915.MecanumDrive.commands.MoveStraightPositionModeCommand;
import org.usfirst.frc4915.MecanumDrive.subsystems.DriveTrain;
import org.usfirst.frc4915.MecanumDrive.subsystems.Elevator;
import org.usfirst.frc4915.MecanumDrive.subsystems.Grabber;
import org.usfirst.frc4915.debuggersystem.CustomDebugger;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	Command autonomousCommand;

	Preferences preferences;
	double testPreferencesItemOne;
	double testPreferencesItemTwo;

	SendableChooser autonomousProgramChooser;

	public static OI oi;
	public static DriveTrain driveTrain;
	public static Elevator elevator;
	public static Grabber grabber;
	public static CustomDebugger debugger = new CustomDebugger();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		RobotMap.init();

		preferences = Preferences.getInstance();
		driveTrain = new DriveTrain();
		elevator = new Elevator();
		grabber = new Grabber();
		// OI must be constructed after subsystems. If the OI creates Commands
		// (which it very likely will), subsystems are not guaranteed to be
		// constructed yet. Thus, their requires() statements may grab null
		// pointers. Bad news. Don't move it.
		oi = new OI();
		RobotMap.gyro.reset();

		testPreferencesItemOne = preferences.getDouble("TestOne", 123.4);
		testPreferencesItemOne = preferences.getDouble("TestTwo", 456.7);

		autonomousProgramChooser = new SendableChooser();
		autonomousProgramChooser.addDefault("Autonomous Program One", new GenericTestCommand(10, "Running program one!"));
		autonomousProgramChooser.addObject("Autonomous Program Two", new GenericTestCommand(20, "Running program two!"));

		SmartDashboard.putData("Autonomous Program", autonomousProgramChooser);

		// Test for sending messages to smart dashboard
		SendUserMessage.displayMessage();
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		// Use the selected autonomous command
		// autonomousCommand = (Command) autonomousProgramChooser.getSelected();
		double desiredDistrance = preferences.getDouble("DesiredDistance", 9.0);
		autonomousCommand = new MoveStraightPositionModeCommand(desiredDistrance);

		autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		// long now = Instant.now().toEpochMilli();
		// System.out.println("LeftFront Position: encVelocity | setPoint," +
		// now +","+ RobotMap.mecanumDriveControls1LeftFront10.getEncVelocity()
		// +"," + RobotMap.mecanumDriveControls1LeftFront10.getSetpoint() );
		// System.out.println("LeftRear Position: encVelocity | setPoint," + now
		// +","+ RobotMap.mecanumDriveControls1LeftRear11.getEncVelocity() +","
		// + RobotMap.mecanumDriveControls1LeftRear11.getSetpoint() );
		// System.out.println("RightFront Position: encVelocity | setPoint," +
		// now +","+ RobotMap.mecanumDriveControls1RightFront12.getEncVelocity()
		// +"," + RobotMap.mecanumDriveControls1RightFront12.getSetpoint() );
		// System.out.println("RightRear Position: encVelocity | setPoint," +
		// now +","+ RobotMap.mecanumDriveControls1RightRear13.getEncVelocity()
		// +"," + RobotMap.mecanumDriveControls1RightRear13.getSetpoint() );

		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
