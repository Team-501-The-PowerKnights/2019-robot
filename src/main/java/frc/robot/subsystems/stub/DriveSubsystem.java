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

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Preferences;
import frc.robot.managers.PropertiesManager;
import frc.robot.managers.PropertyNames;
import frc.robot.managers.SubsystemNames;
import frc.robot.subsystems.IDriveSubsystem;
import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;


/**
 * Add your docs here.
 **/
public class DriveSubsystem
   extends BaseStubSubsystem
   implements IDriveSubsystem
{

   /** Our classes' logger **/
   private static final Logger logger =
      RioLogger.getLogger( DriveSubsystem.class.getName() );

   /** Singleton instance of class for all to use **/
   private static IDriveSubsystem ourInstance;
   /** Name of our subsystem **/
   private static final String myName = SubsystemNames.driveName;


   /**
    * Constructs instance of the drive subsystem. Assumed to be called before
    * any usage of the subsystem; and verifies only called once. Allows
    * controlled startup sequencing of the robot and all it's subsystems.
    **/
   public static synchronized void constructInstance()
   {
      SmartDashboard.putBoolean( TelemetryNames.Drive.status, false );

      if ( ourInstance != null )
      {
         throw new IllegalStateException( myName + " Already Constructed" );
      }
      ourInstance = new DriveSubsystem();

      SmartDashboard.putBoolean( TelemetryNames.Drive.status, false );
   }


   /**
    * Returns the singleton instance of the subsystem in the form of the
    * <i>Interface</i> that is defined for it. If it hasn't been constructed
    * yet, throws an <code>IllegalStateException</code>.
    *
    * @return singleton instance of subsystem
    **/
   public static IDriveSubsystem getInstance()
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


   private DriveSubsystem()
   {
      super( myName );
      logger.info( "constructing" );

      prefs = Preferences.getInstance();
      propsMgr = new PropertiesManager( PropertyNames.Drive.name );
   }


   @Override
   public void driveLeftMotors( double speed )
   {
   }


   @Override
   public void driveRightMotors( double speed )
   {
   }


   @Override
   public void drive( double speed, double turn )
   {
   }


   @Override
   public void drive( double speed, double turn, boolean constrained )
   {
   }


   @Override
   public void zeroEncoders()
   {
   }


   @Override
   public double getLeftEncoderClicks()
   {
      return 0.0;
   }


   @Override
   public double getRightEncoderClicks()
   {
      return 0.0;
   }


   @Override
   public void setPIDPosition( double leftSet, double rightSet )
   {
   }


   @Override
   public void setPIDVelocity( double leftSet, double rightSet )
   {
   }


   @Override
   public double convertInchesToEncoderClicks( double inches )
   {
      return 0.0;
   }


   @Override
   public void setBrake( boolean brakeOn )
   {
   }


   @Override
   public void stop()
   {
   }

}
