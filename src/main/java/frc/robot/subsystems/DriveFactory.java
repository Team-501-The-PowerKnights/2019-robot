/**
 * Copyright (c) 2015-2019 Team 501 - The PowerKnights. All Rights Reserved.
 * Open Source Software - May be modified and shared by FRC teams. The code must
 * be accompanied by the Team 501 BSD license file in the root directory of the
 * project. You may also obtain a copy of it from the following:
 * http://www.opensource.org/licenses/bsd-license.php.
 *
 * See (Git) repository metadata for author and revision history for this file.
 **/

package frc.robot.subsystems;


import org.slf4j.Logger;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.managers.PropertiesManager;
import frc.robot.managers.PropertyNames;
import frc.robot.managers.SubsystemNames;
import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;


/**
 * 
 */
public class DriveFactory
{

   /** Our classes' logger **/
   private static final Logger logger =
      RioLogger.getLogger( DriveFactory.class.getName() );

   /** Singleton instance of class for all to use **/
   private static IDriveSubsystem ourInstance;
   /** Name of our subsystem **/
   private static final String myName = SubsystemNames.driveName;


   /**
    * Constructs instance of the subsystem. Assumed to be called before any
    * usage of the subsystem; and verifies only called once. Allows controlled
    * startup sequencing of the robot and all it's subsystems.
    **/
   public static synchronized void constructInstance()
   {
      SmartDashboard.putBoolean( TelemetryNames.Drive.status, false );

      if ( ourInstance != null )
      {
         throw new IllegalStateException( myName + " Already Constructed" );
      }

      PropertiesManager propsMgr =
         new PropertiesManager( PropertyNames.Drive.name );
      boolean realSubsystem =
         propsMgr.getBoolean( PropertyNames.Drive.useRealSubsystem );

      if ( realSubsystem )
      {
         logger.info( "constructing real {} subsystem", myName );
         frc.robot.subsystems.impl.DriveSubsystem.constructInstance();
         ourInstance = frc.robot.subsystems.impl.DriveSubsystem.getInstance();
      }
      else
      {
         logger.warn( "constructing stub {} subsystem", myName );
         frc.robot.subsystems.stub.DriveSubsystem.constructInstance();
         ourInstance = frc.robot.subsystems.stub.DriveSubsystem.getInstance();
      }

      SmartDashboard.putBoolean( TelemetryNames.Drive.status, true );
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

}
