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
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.PKCANEncoder;
import frc.robot.managers.PreferenceNames;
import frc.robot.managers.PropertiesManager;
import frc.robot.managers.PropertyNames;
import frc.robot.managers.SubsystemNames;
import frc.robot.pids.IPIDController;
import frc.robot.pids.PKSparkMaxPIDController;
import frc.robot.subsystems.IClimberSubsystem;
import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;


/**
 * 
 */
public class ClimberSubsystem
   extends Subsystem
   implements IClimberSubsystem
{

   /** Our classes' logger **/
   private static final Logger logger =
      RioLogger.getLogger( ClimberSubsystem.class.getName() );

   /** Singleton instance of class for all to use **/
   private static IClimberSubsystem ourInstance;
   /** Name of our subsystem **/
   private final static String myName = SubsystemNames.climberName;


   /**
    * Constructs instance of the subsystem. Assumed to be called before any
    * usage of the subsystem; and verifies only called once. Allows controlled
    * startup sequencing of the robot and all it's subsystems.
    **/
   public static synchronized void constructInstance()
   {
      SmartDashboard.putBoolean( TelemetryNames.Climber.status, false );

      if ( ourInstance != null )
      {
         throw new IllegalStateException( myName + " Already Constructed" );
      }
      ourInstance = new ClimberSubsystem();

      SmartDashboard.putBoolean( TelemetryNames.Climber.status, false );
   }


   /**
    * Returns the singleton instance of the subsystem in the form of the
    * <i>Interface</i> that is defined for it. If it hasn't been constructed
    * yet, throws an <code>IllegalStateException</code>.
    *
    * @return singleton instance of subsystem
    **/
   public static IClimberSubsystem getInstance()
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

   /** Motor in use on the climber **/
   private CANSparkMax motor;
   private PKCANEncoder encoder;
   private IPIDController pid;
   private double pidMinOutput;
   private double pidMaxOutput;

   private double setPoint;

   private VictorSP winch;


   private ClimberSubsystem()
   {
      super( myName );
      logger.info( "constructing" );

      prefs = Preferences.getInstance();
      propsMgr = new PropertiesManager( PropertyNames.Climber.name );

      // motor config
      motor = new CANSparkMax( 50, MotorType.kBrushless );
      // encoder config
      encoder = new PKCANEncoder( motor.getEncoder() );
      encoder.setInverted( false );
      // create a PID for the pair
      pid = new PKSparkMaxPIDController( motor, encoder );

      pidMinOutput = -0.80;
      pidMaxOutput = 0.80;
      pid.setOutputLimits( pidMinOutput, pidMaxOutput );

      // motor for the winch (to pull 'foot' forward)
      winch = new VictorSP( 0 );

      // Load the preferences for this subsystem
      updatePreferences();
   }


   @Override
   public void initDefaultCommand()
   {
      // logger.info( "default command: Climber?????" );
      // Set the default command for a subsystem here.
      // setDefaultCommand(new MySpecialCommand());
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
      double v;
      v = prefs.getDouble( PreferenceNames.Climber.pid_P, 0.0 );
      logger.info( "{} = {}", PreferenceNames.Climber.pid_P, v );
      pid.setP( v );
      v = prefs.getDouble( PreferenceNames.Climber.pid_I, 0.0 );
      logger.info( "{} = {}", PreferenceNames.Climber.pid_I, v );
      pid.setI( v );
      v = prefs.getDouble( PreferenceNames.Climber.pid_D, 0.0 );
      logger.info( "{} = {}", PreferenceNames.Climber.pid_D, v );
      pid.setD( v );
      v = prefs.getDouble( PreferenceNames.Climber.pid_F, 0.0 );
      logger.info( "{} = {}", PreferenceNames.Climber.pid_F, v );
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
      SmartDashboard.putNumber( TelemetryNames.Climber.position,
         getPosition() );
      SmartDashboard.putNumber( TelemetryNames.Climber.velocity,
         getVelocity() );
      SmartDashboard.putNumber( TelemetryNames.Climber.output, getOutput() );
      SmartDashboard.putNumber( TelemetryNames.Climber.pidTarget, setPoint );
      SmartDashboard.putNumber( TelemetryNames.Climber.pidError, getError() );
      SmartDashboard.putBoolean( TelemetryNames.Climber.inPosition,
         inPosition() );
      SmartDashboard.putBoolean( TelemetryNames.Climber.pidEnabled,
         pid.pidEnabled() );
   }


   @Override
   public void setMotor( double speed )
   {
      logger.error( "Not implemented" );
   }


   @Override
   public void stop()
   {
      setMotor( 0.0 );
   }


   @Override
   public void enableControl()
   {
      motor.setIdleMode( IdleMode.kBrake );

      setPoint = getPosition();
      pid.goToPosition( setPoint );
   }


   @Override
   public void disableControl()
   {
      pid.disable();

      motor.setIdleMode( IdleMode.kCoast );
   }


   @Override
   public void goUpToPosition( double setPoint )
   {
      goToPosition( setPoint );
   }


   @Override
   public void dropDownToPosition( double setPoint )
   {
      goToPosition( setPoint );
   }


   @Override
   public void windWinch( double speed )
   {
      winch.set( speed );
   }


   @Override
   public void stopWinch()
   {
      windWinch( 0.0 );
   }


   @Override
   public void goToPosition( double position )
   {
      // TODO - Validate against real-PID implementation
      this.setPoint = position;
      pid.goToPosition( setPoint );
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
