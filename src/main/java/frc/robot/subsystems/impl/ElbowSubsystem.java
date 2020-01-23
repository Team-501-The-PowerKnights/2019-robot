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


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.PKCANEncoder;
import frc.robot.commands.elbow.ElbowHoldWithPID;
import frc.robot.commands.elbow.ElbowJoystickControl;
import frc.robot.commands.elbow.ElbowManualControl;
import frc.robot.managers.PreferenceNames;
import frc.robot.managers.PropertiesManager;
import frc.robot.managers.PropertyNames;
import frc.robot.managers.SubsystemNames;
import frc.robot.pids.IPIDController;
import frc.robot.pids.PKSparkMaxPIDController;
import frc.robot.subsystems.IElbowSubsystem;
import frc.robot.subsystems.ElbowSetPoints;
import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;


public class ElbowSubsystem
   extends Subsystem
   implements IElbowSubsystem
{

   /** Our classes' logger **/
   private static final Logger logger =
      RioLogger.getLogger( ElbowSubsystem.class.getName() );

   /** Singleton instance of class for all to use **/
   private static IElbowSubsystem ourInstance;
   /** Name of our subsystem **/
   private final static String myName = SubsystemNames.elbowName;


   /**
    * Constructs instance of the subsystem. Assumed to be called before any
    * usage of the subsystem; and verifies only called once. Allows controlled
    * startup sequencing of the robot and all it's subsystems.
    **/
   public static synchronized void constructInstance()
   {
      SmartDashboard.putBoolean( TelemetryNames.Elbow.status, false );

      if ( ourInstance != null )
      {
         throw new IllegalStateException( myName + " Already Constructed" );
      }
      ourInstance = new ElbowSubsystem();

      SmartDashboard.putBoolean( TelemetryNames.Elbow.status, true );
   }


   /**
    * Returns the singleton instance of the subsystem in the form of the
    * <i>Interface</i> that is defined for it. If it hasn't been constructed
    * yet, throws an <code>IllegalStateException</code>.
    *
    * @return singleton instance of subsystem
    **/
   public static IElbowSubsystem getInstance()
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

   private static final double inPositionThreshold = 0.15;

   private final Preferences prefs;
   @SuppressWarnings( "unused" )
   private final PropertiesManager propsMgr;

   private final CANSparkMax motor;

   private final PKCANEncoder encoder;

   private final IPIDController pid;

   private double setPoint;


   private ElbowSubsystem()
   {
      super( myName );
      logger.info( "constructing" );

      prefs = Preferences.getInstance();
      propsMgr = new PropertiesManager( PropertyNames.Elbow.name );

      motor = new CANSparkMax( 43, MotorType.kBrushless );
      motor.setInverted( false );

      encoder = new PKCANEncoder( motor.getEncoder() );
      encoder.setInverted( false );

      pid = new PKSparkMaxPIDController( motor, encoder );
      // This causes reset to 0 for Elbow
      // pid.setOutputLimits( 0.3, -0.3 );
      pid.setOutputLimits( 0.12, -0.12);
      pid.setIAccum( 25 );

      // Load the preferences for this subsystem
      updatePreferences();
   }


   @Override
   public void initDefaultCommand()
   {
      setDefaultCommand( new ElbowJoystickControl() );
      // setDefaultCommand( new ElbowHoldWithPID() );
   }


   @Override
   public void periodic()
   {
   }


   @Override
   public void validateCalibration()
   {
      logger.info( "calibration:" );
      double vProperties = ElbowSetPoints.cal;
      double vSensor = getPosition();
      double deltaValue = vProperties - vSensor;
      double deltaPercent = deltaValue / vProperties;
      boolean deltaStatus = ( deltaPercent < ElbowSetPoints.calPercentValid );
      logger.info( "props={} vs. sensor={}, delta={} [{}%], status = {}",
         vProperties, vSensor, deltaValue, deltaPercent, deltaStatus );
      SmartDashboard.putNumber( TelemetryNames.Elbow.calError, deltaValue );
      SmartDashboard.putBoolean( TelemetryNames.Elbow.calStatus, deltaStatus );
   }


   @Override
   public void updatePreferences()
   {
      logger.info( "new preferences:" );
      double v;
      v = prefs.getDouble( PreferenceNames.Elbow.pid_P, 0.0 );
      logger.info( "{} = {}", PreferenceNames.Elbow.pid_P, v );
      pid.setP( v );
      v = prefs.getDouble( PreferenceNames.Elbow.pid_I, 0.0 );
      logger.info( "{} = {}", PreferenceNames.Elbow.pid_I, v );
      pid.setI( v );
      v = prefs.getDouble( PreferenceNames.Elbow.pid_D, 0.0 );
      logger.info( "{} = {}", PreferenceNames.Elbow.pid_D, v );
      pid.setD( v );
      v = prefs.getDouble( PreferenceNames.Elbow.pid_F, 0.0 );
      logger.info( "{} = {}", PreferenceNames.Elbow.pid_F, v );
      pid.setF( v );
   }


   @Override
   public void disable()
   {
      logger.debug( "disabling" );
   }


   @Override
   public void updateTelemetry()
   {

      SmartDashboard.putNumber( TelemetryNames.Elbow.position, getPosition() );
      SmartDashboard.putNumber( TelemetryNames.Elbow.velocity, getVelocity() );
      SmartDashboard.putNumber( TelemetryNames.Elbow.output, getOutput() );
      SmartDashboard.putNumber( TelemetryNames.Elbow.pidTarget, setPoint );
      SmartDashboard.putNumber( TelemetryNames.Elbow.pidError, getError() );
      SmartDashboard.putBoolean( TelemetryNames.Elbow.inPosition,
         inPosition() );
      SmartDashboard.putBoolean( TelemetryNames.Elbow.pidEnabled,
         pid.pidEnabled() );
   }


   @Override
   public void setMotor( double speed )
   {
      motor.set( speed );
   }


   @Override
   public void stop()
   {
      setMotor( 0.0 );
   }


   @Override
   public void zeroEncoder()
   {
      encoder.zero();
   }


   @Override
   public void goToPosition( double position )
   {
      setPoint = position;
      pid.goToPosition( position );
   }


   @Override
   public double getPosition()
   {
      double retValue = encoder.getPosition();
      return retValue;
   }


   @Override
   public double getVelocity()
   {
      double retValue = encoder.getVelocity();
      return retValue;
   }


   @Override
   public double getOutput()
   {
      double retValue = pid.pidOutput();
      return retValue;
   }


   @Override
   public boolean inPosition()
   {
      return pid.inPosition( inPositionThreshold );
   }


   @Override
   public double getError()
   {
      double error = pid.pidError();
      return error;
   }

}
