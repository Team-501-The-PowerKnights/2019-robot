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


import org.slf4j.Logger;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.PKCANEncoder;
import frc.robot.commands.lift.LiftManualControl;
import frc.robot.managers.PreferenceNames;
import frc.robot.managers.PropertiesManager;
import frc.robot.managers.PropertyNames;
import frc.robot.managers.SubsystemNames;
import frc.robot.pids.IPIDController;
import frc.robot.pids.PKSparkMaxPIDController;
import frc.robot.subsystems.ILiftSubsystem;
import frc.robot.subsystems.LiftSetPoints;
import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;


/**
 * Add your docs here.
 */
public class LiftSubsystem
   extends Subsystem
   implements ILiftSubsystem
{

   /** Our classes' logger **/
   private static final Logger logger =
      RioLogger.getLogger( LiftSubsystem.class.getName() );

   /** Singleton instance of class for all to use **/
   private static ILiftSubsystem ourInstance;
   /** Name of our subsystem **/
   private static final String myName = SubsystemNames.liftName;


   /**
    * Constructs instance of the lift subsystem. Assumed to be called before any
    * usage of the subsystem; and verifies only called once. Allows controlled
    * startup sequencing of the robot and all it's subsystems.
    **/
   public static synchronized void constructInstance()
   {
      SmartDashboard.putBoolean( TelemetryNames.Lift.status, false );

      if ( ourInstance != null )
      {
         throw new IllegalStateException( myName + " Already Constructed" );
      }
      ourInstance = new LiftSubsystem();

      SmartDashboard.putBoolean( TelemetryNames.Lift.status, true );
   }


   /**
    * Returns the singleton instance of the subsystem in the form of the
    * <i>Interface</i> that is defined for it. If it hasn't been constructed
    * yet, throws an <code>IllegalStateException</code>.
    *
    * @return singleton instance of subsystem
    **/
   public static ILiftSubsystem getInstance()
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

   private final Preferences prefs;
   @SuppressWarnings( "unused" )
   private final PropertiesManager propsMgr;

   private static final double inPositionThreshold = 0.05;

   /** Motor in use on the lift **/
   private CANSparkMax motor;
   private PKCANEncoder encoder;
   private double liftMinOutput;
   private double liftMaxOutput;
   private IPIDController pid;

   private double setPoint;


   private LiftSubsystem()
   {
      super( myName );
      logger.info( "constructing" );

      prefs = Preferences.getInstance();
      propsMgr = new PropertiesManager( PropertyNames.Lift.name );

      // motor config
      motor = new CANSparkMax( 30, MotorType.kBrushless );
      // encoder config
      encoder = new PKCANEncoder( motor.getEncoder() );
      encoder.setInverted( false );
      // create a PID for the pair
      pid = new PKSparkMaxPIDController( motor, encoder );

      liftMinOutput = -1;
      liftMaxOutput = 1;
      pid.setOutputLimits( liftMinOutput, liftMaxOutput );

      // Load the preferences for this subsystem
      updatePreferences();
   }


   @Override
   public void initDefaultCommand()
   {
      logger.info( "default command: LiftManualControl" );
      setDefaultCommand( new LiftManualControl() );
   }


   @Override
   public void periodic()
   {
   }


   @Override
   public void validateCalibration()
   {
      logger.info( "calibration:" );
      double vProperties = LiftSetPoints.cal;
      double vSensor = getPosition();
      double deltaValue = vProperties - vSensor;
      double deltaPercent = deltaValue / vProperties;
      boolean deltaStatus = ( deltaPercent < LiftSetPoints.calPercentValid );
      logger.info( "props={} vs. sensor={}, delta={} [{}%], status = {}",
         vProperties, vSensor, deltaValue, deltaPercent, deltaStatus );
      SmartDashboard.putNumber( TelemetryNames.Lift.calError, deltaValue );
      SmartDashboard.putBoolean( TelemetryNames.Lift.calStatus, deltaStatus );
   }


   @Override
   public void updatePreferences()
   {
      logger.info( "new preferences:" );
      double v;
      v = prefs.getDouble( PreferenceNames.Lift.pid_P, 0.0 );
      logger.info( "{} = {}", PreferenceNames.Lift.pid_P, v );
      pid.setP( v );
      v = prefs.getDouble( PreferenceNames.Lift.pid_I, 0.0 );
      logger.info( "{} = {}", PreferenceNames.Lift.pid_I, v );
      pid.setI( v );
      v = prefs.getDouble( PreferenceNames.Lift.pid_D, 0.0 );
      logger.info( "{} = {}", PreferenceNames.Lift.pid_D, v );
      pid.setD( v );
      v = prefs.getDouble( PreferenceNames.Lift.pid_F, 0.0 );
      logger.info( "{} = {}", PreferenceNames.Lift.pid_F, v );
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
      SmartDashboard.putNumber( TelemetryNames.Lift.position, getPosition() );
      SmartDashboard.putNumber( TelemetryNames.Lift.velocity, getVelocity() );
      SmartDashboard.putNumber( TelemetryNames.Lift.output, getOutput() );
      SmartDashboard.putNumber( TelemetryNames.Lift.pidTarget, setPoint );
      SmartDashboard.putNumber( TelemetryNames.Lift.pidError, getError() );
      SmartDashboard.putBoolean( TelemetryNames.Lift.inPosition, inPosition() );
      SmartDashboard.putBoolean( TelemetryNames.Lift.pidEnabled,
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
   public void goToPosition( double encoderPosition )
   {
      setPoint = encoderPosition;
      pid.goToPosition( encoderPosition );
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
