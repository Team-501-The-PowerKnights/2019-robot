/**
 * Copyright (c) 2015-2019 Team 501 - The PowerKnights. All Rights Reserved.
 * Open Source Software - May be modified and shared by FRC teams. The code must
 * be accompanied by the Team 501 BSD license file in the root directory of the
 * project. You may also obtain a copy of it from the following:
 * http://www.opensource.org/licenses/bsd-license.php.
 *
 * See (Git) repository metadata for author and revision history for this file.
 **/

package frc.robot.sensors.stubs;


import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.managers.SensorNames;
import frc.robot.sensors.ISensor;
import frc.robot.telemetry.TelemetryNames;
import riolog.RioLogger;


/**
 * Add your docs here.
 */
public class FloorDistanceSensor
   implements ISensor
{

   /* Our classes logger */
   @SuppressWarnings( "unused" )
   private static final Logger logger =
      RioLogger.getLogger( FloorDistanceSensor.class.getName() );

   /** Singleton instance of class for all to use **/
   private static FloorDistanceSensor ourInstance;
   /** Name of our module **/
   private final static String myName = SensorNames.floorName;


   /**
    * Constructs instance of the sensor. Assumed to be called before any usage
    * of the sensor; and verifies only called once. Allows controlled startup
    * sequencing of the robot and all it's sensors.
    **/
   public static synchronized void constructInstance()
   {
      SmartDashboard.putBoolean( TelemetryNames.Floor.status, false );

      if ( ourInstance != null )
      {
         throw new IllegalStateException( myName + " Already Constructed" );
      }
      ourInstance = new FloorDistanceSensor();
   }


   /**
    * Returns the singleton instance of the sensor in the form of the
    * <i>Interface</i> that is defined for it. If it hasn't been constructed
    * yet, throws an <code>IllegalStateException</code>.
    *
    * @return singleton instance of sensor
    **/
   public static ISensor getInstance()
   {
      if ( ourInstance == null )
      {
         throw new IllegalStateException( myName + " Not Constructed Yet" );
      }
      return ourInstance;
   }


   @Override
   public void updateTelemetry()
   {
   }


   @Override
   public void updatePreferences()
   {
   }


   @Override
   public void disable()
   {
   }

}
