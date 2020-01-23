/**
 * Copyright (c) 2015-2019 Team 501 - The PowerKnights. All Rights Reserved.
 * Open Source Software - May be modified and shared by FRC teams. The code must
 * be accompanied by the Team 501 BSD license file in the root directory of the
 * project. You may also obtain a copy of it from the following:
 * http://www.opensource.org/licenses/bsd-license.php.
 *
 * See (Git) repository metadata for author and revision history for this file.
 **/

package frc.robot.telemetry;


import java.util.ArrayList;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import riolog.RioLogger;


/**
 * Add your docs here.
 */
public class TelemetryManager
{

   /** Our classes' logger **/
   @SuppressWarnings( "unused" )
   private static final Logger logger =
      RioLogger.getLogger( TelemetryManager.class.getName() );

   /** Singleton instance of class for all to use **/
   private static TelemetryManager ourInstance;
   /** Name of our subsystem **/
   private final static String myName = TelemetryNames.Telemetry.name;

   // The list of telemetry providers
   private final ArrayList< ITelemetryProvider > providerList;


   /**
    * Constructs instance of the subsystem. Assumed to be called before any
    * usage of the subsystem; and verifies only called once. Allows controlled
    * startup sequencing of the robot and all it's subsystems.
    **/
   public static synchronized void constructInstance()
   {
      SmartDashboard.putBoolean( TelemetryNames.Telemetry.status, false );

      if ( ourInstance != null )
      {
         throw new IllegalStateException( myName + " Already Constructed" );
      }
      ourInstance = new TelemetryManager();

      SmartDashboard.putBoolean( TelemetryNames.Telemetry.status, true );
   }


   /**
    * Returns the singleton instance of the subsystem in the form of the
    * <i>Interface</i> that is defined for it. If it hasn't been constructed
    * yet, throws an <code>IllegalStateException</code>.
    *
    * @return singleton instance of subsystem
    **/
   public static TelemetryManager getInstance()
   {
      if ( ourInstance == null )
      {
         throw new IllegalStateException( myName + " Not Constructed Yet" );
      }
      return ourInstance;
   }


   private TelemetryManager()
   {
      providerList = new ArrayList< ITelemetryProvider >();
   }


   public void addProvider( ITelemetryProvider provider )
   {
      if ( provider != null )
      {
         providerList.add( provider );
      }
   }

   private int counter = 0;


   // This should get called from robotPeriodic() in robot
   public void sendTelemetry()
   {
      if ( counter >= 10 )
      {
         counter = 0;

         for ( ITelemetryProvider provider : providerList )
         {
            provider.updateTelemetry();
         }
      }
      else
      {
         counter++;
      }
   }

}
