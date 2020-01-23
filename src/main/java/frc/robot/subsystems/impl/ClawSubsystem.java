/**
 * Copyright (c) 2015-2019 Team 501 - The PowerKnights. All Rights Reserved.
 * Open Source Software - May be modified and shared by FRC teams. The code must
 * be accompanied by the Team 501 BSD license file in the root directory of the
 * project. You may also obtain a copy of it from the following:
 * http://www.opensource.org/licenses/bsd-license.php.
 *
 * See (Git) repository metadata for author and revision history for this file.
 **/

package frc.robot.subsystems.impl;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.commands.claw.ClawJoystickControl;
import frc.robot.managers.PropertiesManager;
import frc.robot.managers.PropertyNames;
import frc.robot.managers.SubsystemNames;
import frc.robot.subsystems.IClawSubsystem;
import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;


/**
 * Add your docs here.
 */
public class ClawSubsystem
   extends Subsystem
   implements IClawSubsystem
{

   /** Our classes' logger **/
   private static final Logger logger =
      RioLogger.getLogger( ClawSubsystem.class.getName() );

   /** Singleton instance of class for all to use **/
   private static IClawSubsystem ourInstance;
   /** Name of our subsystem **/
   private final static String myName = SubsystemNames.clawName;


   /**
    * Constructs instance of the subsystem. Assumed to be called before any
    * usage of the subsystem; and verifies only called once. Allows controlled
    * startup sequencing of the robot and all it's subsystems.
    **/
   public static synchronized void constructInstance()
   {
      SmartDashboard.putBoolean( TelemetryNames.Claw.status, false );

      if ( ourInstance != null )
      {
         throw new IllegalStateException( myName + " Already Constructed" );
      }
      ourInstance = new ClawSubsystem();

      SmartDashboard.putBoolean( TelemetryNames.Claw.status, true );
   }


   /**
    * Returns the singleton instance of the subsystem in the form of the
    * <i>Interface</i> that is defined for it. If it hasn't been constructed
    * yet, throws an <code>IllegalStateException</code>.
    *
    * @return singleton instance of subsystem
    **/
   public static IClawSubsystem getInstance()
   {
      if ( ourInstance == null )
      {
         throw new IllegalStateException( myName + " Not Constructed Yet" );
      }
      return ourInstance;
   }


   /**
    * Returns the singleton instance of the subsystem, but in a
    * <code>class</code> that the <i>WPILib</i> interface wants.
    *
    * @return singleton instance of subsystem
    */
   public static Subsystem getWpiSubsystem()
   {
      if ( ourInstance == null )
      {
         throw new IllegalStateException( myName + " Not Constructed Yet" );
      }
      return (Subsystem) ourInstance;
   }

   @SuppressWarnings( "unused" )
   private final Preferences prefs;
   @SuppressWarnings( "unused" )
   private final PropertiesManager propsMgr;

   //
   private final VictorSPX motor;

   private final Servo hatchServo;


   private ClawSubsystem()
   {
      super( myName );
      logger.info( "constructing" );

      prefs = Preferences.getInstance();
      propsMgr = new PropertiesManager( PropertyNames.Claw.name );

      motor = new VictorSPX( 40 );

      hatchServo = new Servo( 4 );

      // Load the preferences for this subsystem
      updatePreferences();
   }


   @Override
   public void initDefaultCommand()
   {
      logger.info( "default command: ClawJoystickControl" );
      setDefaultCommand( new ClawJoystickControl() );
   }


   @Override
   public void periodic()
   {
   }


   @Override
   public void validateCalibration()
   {
      // Nothing here
   }


   @Override
   public void updatePreferences()
   {
      logger.info( "new preferences:" );
   }


   @Override
   public void disable()
   {
      logger.debug( "disabling" );
   }


   @Override
   public void updateTelemetry()
   {
      double position = hatchServo.getPosition();
      SmartDashboard.putNumber( TelemetryNames.Claw.hatchValue, position );
      SmartDashboard.putBoolean( TelemetryNames.Claw.hatchLocked,
         ( position < 0.1 ) );
   }


   @Override
   public void setMotor( double speed )
   {
      motor.set( ControlMode.PercentOutput, speed );
   }


   @Override
   public void open()
   {
      hatchServo.set( 0. / 180. );
   }


   @Override
   public void close()
   {
      hatchServo.set( 90. / 180. );
   }

}
