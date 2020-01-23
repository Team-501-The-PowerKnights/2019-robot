/**
 * Copyright (c) 2015-2019 Team 501 - The PowerKnights. All Rights Reserved.
 * Open Source Software - May be modified and shared by FRC teams. The code must
 * be accompanied by the Team 501 BSD license file in the root directory of the
 * project. You may also obtain a copy of it from the following:
 * http://www.opensource.org/licenses/bsd-license.php.
 *
 * See (Git) repository metadata for author and revision history for this file.
 **/

package frc.robot.sensors.impl;


import org.slf4j.Logger;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.managers.PreferenceNames;
import frc.robot.managers.PropertiesManager;
import frc.robot.managers.SensorNames;
import frc.robot.sensors.ISensor;
import frc.robot.telemetry.TelemetryNames;
import riolog.RioLogger;


/**
 * 
 */
public class FloorDistanceSensor
   implements ISensor
{

   /* Our classes logger */
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

      SmartDashboard.putBoolean( TelemetryNames.Floor.status, true );
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

   @SuppressWarnings( "unused" )
   private final Preferences prefs;
   @SuppressWarnings( "unused" )
   private final PropertiesManager propsMgr;

   private static final int floorPosition = 800;

   private final AnalogInput sensor;


   public FloorDistanceSensor()
   {
      logger.info( "constructing" );

      prefs = Preferences.getInstance();
      propsMgr = new PropertiesManager( PreferenceNames.Floor.name );

      sensor = new AnalogInput( 0 );

      sensor.setAverageBits( 10 );
   }


   @Override
   public void updateTelemetry()
   {
      int value = sensor.getAverageValue();
      SmartDashboard.putNumber( TelemetryNames.Floor.value, value );
      boolean ready = ( value > floorPosition );
      SmartDashboard.putBoolean( TelemetryNames.Floor.ready, ready );
   }


   @Override
   public void updatePreferences()
   {
      // Nothing here
   }


   @Override
   public void disable()
   {
      // Nothing here
   }

}
