/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6560.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6560.robot.subsystems.DriveTrain;
import org.usfirst.frc.team6560.robot.subsystems.DriveTrainOne;
import org.usfirst.frc.team6560.robot.subsystems.Elevator;
import org.usfirst.frc.team6560.robot.subsystems.ElevatorPistons;
import org.usfirst.frc.team6560.robot.subsystems.Grabber;
import org.usfirst.frc.team6560.robot.subsystems.HatchThing;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static OI oi;
	
	public static DriveTrainOne driveTrain;
	public static Elevator elevator;
	public static Grabber grabber;
	public static HatchThing hatchThing;
	
	public static NetworkTableInstance nt;
	public static ElevatorPistons elevatorPistons;

	public DifferentialDrive motorDrive;
	public static Preferences prefs;

	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		nt = NetworkTableInstance.getDefault();

		CameraServer.getInstance().startAutomaticCapture();
		CameraServer.getInstance().startAutomaticCapture();
		driveTrain = new DriveTrainOne();
		elevator = new Elevator();
		grabber = new Grabber();
		hatchThing = new HatchThing();
		elevatorPistons = new ElevatorPistons();
		prefs = Preferences.getInstance();
		oi = new OI();

		Compressor c = new Compressor(0);
		c.setClosedLoopControl(true);
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {


		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
		
	
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {


		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}				
	}

	
	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		
		 Scheduler.getInstance().run();
			}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
	
	public static void initializeMotorManual(WPI_TalonSRX motor) {
		initializeMotorManual(motor, 2);
	}
	
	public static void initializeMotorManual(WPI_TalonSRX motor, double ramp) {
		motor.config_kF(0, 0);
		motor.config_kP(0, 0);
		motor.config_kD(0, 0);
		motor.config_kI(0, 0);
	    	    
		motor.configOpenloopRamp(ramp, 100);
	}
}
