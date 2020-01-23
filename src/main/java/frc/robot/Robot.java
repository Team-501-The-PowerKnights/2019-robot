/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
/**
 * Copyright (c) 2015-2019 Team 501 - The PowerKnights. All Rights Reserved.
 * Open Source Software - May be modified and shared by FRC teams. The code must
 * be accompanied by the Team 501 BSD license file in the root directory of the
 * project. You may also obtain a copy of it from the following:
 * http://www.opensource.org/licenses/bsd-license.php.
 *
 * See (Git) repository metadata for author and revision history for this file.
 **/

package frc.robot;


import java.util.List;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.commands.DoNothing;
import frc.robot.managers.PreferencesInitializer;
import frc.robot.managers.PropertiesManager;
import frc.robot.modules.IModule;
import frc.robot.modules.ModuleFactory;
import frc.robot.sensors.ISensor;
import frc.robot.sensors.SensorFactory;
import frc.robot.subsystems.ISubsystem;
import frc.robot.subsystems.SubsystemFactory;
import frc.robot.telemetry.SchedulerProvider;
import frc.robot.telemetry.TelemetryManager;
import frc.robot.telemetry.TelemetryNames.Misc;
import frc.robot.telemetry.TelemetryNames.Vision;

import riolog.RioLogger;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot
   extends TimedRobot
{

   /* Our classes logger */
   private static final Logger logger =
      RioLogger.getLogger( Robot.class.getName() );

   private SendableChooser< Command > autoChooser;
   private Command autoCommand;

   private TelemetryManager tlmMgr;

   //
   private List< IModule > modules;
   //
   private List< ISensor > sensors;
   //
   private List< ISubsystem > subsystems;

   // Flag for having completed autonomous part of match
   private boolean autonomousComplete;
   // Flag for having completed operator control part of match
   private boolean teleopComplete;


   /**
    * This function is run once when the robot is first started up.
    */
   @Override
   public void robotInit()
   {
      logger.info( "initializing" );

      // Make sure Preferences are initialized
      intializePreferences();

      // Make sure Properties file exists and can be parsed
      initializeProperties();

      // Create telemetry manager
      TelemetryManager.constructInstance();
      tlmMgr = TelemetryManager.getInstance();

      // Create command manager
      SchedulerProvider.constructInstance();
      tlmMgr.addProvider( SchedulerProvider.getInstance() );

      // Initialize all the modules
      modules = ModuleFactory.constructModules();
      // Initialize all the sensors
      sensors = SensorFactory.constructSensors();
      // Initialize all the subsystems
      subsystems = SubsystemFactory.constructSubsystems();

      // Initialize the OI "subsystem"
      OI.constructInstance();
      tlmMgr.addProvider( OI.getInstance() );

      createAutoChooser();

      autonomousComplete = false;
      teleopComplete = false;

      SmartDashboard.putBoolean( Vision.isSandstorm, false );
   }


   private void intializePreferences()
   {
      PreferencesInitializer.initalize();
      PreferencesInitializer.dumpPreferences();
      SmartDashboard.putBoolean( Misc.prefsStatus, true );
   }


   private void initializeProperties()
   {
      PropertiesManager tProps = new PropertiesManager( "" );
      tProps.listProperties();
      SmartDashboard.putBoolean( Misc.propsStatus, true );
   }


   private void createAutoChooser()
   {
      autoChooser = new SendableChooser<>();

      autoChooser.setDefaultOption( "Do Nothing", new DoNothing() );

      // SmartDashboard.putData( "Auto Mode", autoChooser );
   }


   /**
    * This function is called every robot packet, no matter the mode.
    *
    * <p>
    * This runs after the mode specific mode periodic functions, but before
    * LiveWindow and SmartDashboard integrated updating.
    */
   @Override
   public void robotPeriodic()
   {
      tlmMgr.sendTelemetry();
   }


   /**
    * This function is called once each time the robot enters Disabled mode.
    */
   @Override
   public void disabledInit()
   {
      logger.info( "disabling" );

      SmartDashboard.putBoolean( Vision.isSandstorm, false );

      validateCalibrations();

      if ( autonomousComplete && teleopComplete )
      {
         logger.info( "match complete" );

         logMatchData();

         logVisionData();
      }
   }


   /**
    * Calls each subsystem to validate their calibration and update the
    * appropriate telemetry points.
    */
   private void validateCalibrations()
   {
      for ( ISubsystem s : subsystems )
      {
         s.validateCalibration();
      }
   }


   /**
    * Log the data associated with the match to the tail of the log file. This
    * allows us to easily determine whether it is a real match, and what match
    * it was.
    **/
   private void logMatchData()
   {
      final DriverStation ds = DriverStation.getInstance();
      logger.info( "EventName:     {}", ds.getEventName() );
      logger.info( "MatchType:     {}", ds.getMatchType() );
      logger.info( "MatchNumber:   {}", ds.getMatchNumber() );
      logger.info( "ReplayNumber:  {}", ds.getReplayNumber() );
      logger.info( "Alliance:      {}", ds.getAlliance() );
      logger.info( "Location:      {}", ds.getLocation() );
   }


   /**
    * Log the data associated with the vision to the tail of the log file.
    **/
   private void logVisionData()
   {
      logger.info( "vision data:" );
      logger.info( "{}:  {}", Vision.angle,
         SmartDashboard.getNumber( Vision.angle, 0 ) );
      logger.info( "{}:  {}", Vision.count,
         SmartDashboard.getNumber( Vision.count, 0 ) );
   }


   /**
    * This function is called periodically during Disabled mode.
    */
   @Override
   public void disabledPeriodic()
   {
      Scheduler.getInstance().run();
   }


   /**
    * This function is called once each time the robot enters Autonomous mode.
    */
   @Override
   public void autonomousInit()
   {
      logger.info( "initializing" );
      autonomousComplete = true;

      // Update the preferences
      for ( IModule m : modules )
      {
         m.updatePreferences();
      }
      for ( ISensor s : sensors )
      {
         s.updatePreferences();
      }
      for ( ISubsystem s : subsystems )
      {
         s.updatePreferences();
      }

      SmartDashboard.putBoolean( Vision.isSandstorm, true );

      autoCommand = autoChooser.getSelected();

      /*
       * String autoSelected = SmartDashboard.getString("Auto Selector",
       * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand =
       * new MyAutoCommand(); break; case "Default Auto": default:
       * autonomousCommand = new ExampleCommand(); break; }
       */

      // schedule the autonomous command (example)
      if ( autoCommand != null )
      {
         autoCommand.start();
      }
   }


   /**
    * This function is called periodically during Autonomous mode.
    */
   @Override
   public void autonomousPeriodic()
   {
      Scheduler.getInstance().run();
   }


   /**
    * This function is called once each time the robot enters Teleop mode.
    */
   @Override
   public void teleopInit()
   {
      logger.info( "initializing" );
      teleopComplete = true;

      // Update the preferences
      for ( IModule m : modules )
      {
         m.updatePreferences();
      }
      for ( ISensor s : sensors )
      {
         s.updatePreferences();
      }
      for ( ISubsystem s : subsystems )
      {
         s.updatePreferences();
      }

      // This makes sure that the autonomous stops running when
      // teleop starts running. If you want the autonomous to
      // continue until interrupted by another command, remove
      // this line or comment it out.
      if ( autoCommand != null )
      {
         autoCommand.cancel();
      }
   }


   /**
    * This function is called periodically during Teleop mode.
    */
   @Override
   public void teleopPeriodic()
   {
      Scheduler.getInstance().run();
   }


   /**
    * This function is called once each time the robot enters Test mode.
    */
   @Override
   public void testInit()
   {
      logger.info( "initializing" );

      // Update the preferences
      for ( IModule m : modules )
      {
         m.updatePreferences();
      }
      for ( ISensor s : sensors )
      {
         s.updatePreferences();
      }
      for ( ISubsystem s : subsystems )
      {
         s.updatePreferences();
      }
   }


   /**
    * This function is called periodically during Test mode.
    */
   @Override
   public void testPeriodic()
   {
   }

}
