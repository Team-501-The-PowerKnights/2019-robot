/**
 * Copyright (c) 2015-2019 Team 501 - The PowerKnights. All Rights Reserved.
 * Open Source Software - May be modified and shared by FRC teams. The code must
 * be accompanied by the Team 501 BSD license file in the root directory of the
 * project. You may also obtain a copy of it from the following:
 * http://www.opensource.org/licenses/bsd-license.php.
 *
 * See (Git) repository metadata for author and revision history for this file.
 **/

package frc.robot.subsystems.stub;


import org.slf4j.Logger;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.managers.PropertiesManager;
import frc.robot.managers.PropertyNames;
import frc.robot.managers.SubsystemNames;
import frc.robot.subsystems.ILiftSubsystem;
import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;


/**
 * 
 */
public class LiftSubsystem
   extends BaseStubSubsystem
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

      SmartDashboard.putBoolean( TelemetryNames.Lift.status, false );
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

   @SuppressWarnings( "unused" )
   private final Preferences prefs;
   @SuppressWarnings( "unused" )
   private final PropertiesManager propsMgr;


   private LiftSubsystem()
   {
      super( myName );
      logger.info( "constructing" );

      prefs = Preferences.getInstance();
      propsMgr = new PropertiesManager( PropertyNames.Lift.name );
   }


   @Override
   public void setMotor( double speed )
   {
   }


   @Override
   public void stop()
   {
      setMotor( 0.0 );
   }


   @Override
   public void zeroEncoder()
   {
   }


   @Override
   public void goToPosition( double position )
   {
   }


   @Override
   public double getPosition()
   {
      return 0.0;
   }


   @Override
   public double getVelocity()
   {
      return 0.0;
   }


   @Override
   public double getOutput()
   {
      return 0.0;
   }


   @Override
   public boolean inPosition()
   {
      return false;
   }


   @Override
   public double getError()
   {
      return 0;
   }

}
