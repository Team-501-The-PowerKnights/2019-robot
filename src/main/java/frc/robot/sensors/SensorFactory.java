/**
 * Copyright (c) 2015-2019 Team 501 - The PowerKnights. All Rights Reserved.
 * Open Source Software - May be modified and shared by FRC teams. The code must
 * be accompanied by the Team 501 BSD license file in the root directory of the
 * project. You may also obtain a copy of it from the following:
 * http://www.opensource.org/licenses/bsd-license.php.
 *
 * See (Git) repository metadata for author and revision history for this file.
 **/

package frc.robot.sensors;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.managers.PropertiesManager;
import frc.robot.managers.PropertyNames;
import frc.robot.sensors.impl.CameraSensor;
import frc.robot.sensors.impl.FloorDistanceSensor;
import frc.robot.sensors.impl.GyroSensor;
import frc.robot.telemetry.TelemetryManager;
import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;


/**
 * Add your docs here.
 */
public class SensorFactory
{

   /** Our classes' logger **/
   private static final Logger logger =
      RioLogger.getLogger( SensorFactory.class.getName() );


   public static List< ISensor > constructSensors()
   {
      ArrayList< ISensor > sensors = new ArrayList< ISensor >();

      TelemetryManager tlmMgr = TelemetryManager.getInstance();

      PropertiesManager propsMgr;

      SmartDashboard.putBoolean( TelemetryNames.Gyro.status, false );
      {
         propsMgr = new PropertiesManager( PropertyNames.Gyro.name );
         boolean realSensor =
            propsMgr.getBoolean( PropertyNames.Gyro.useRealSensor );

         if ( realSensor )
         {
            GyroSensor.constructInstance();
            ISensor ss = GyroSensor.getInstance();
            tlmMgr.addProvider( ss );
            sensors.add( ss );
         }
         else
         {
            logger.warn( "stub GyroSensor being instantiated" );
            frc.robot.sensors.stubs.GyroSensor.constructInstance();
         }
      }

      SmartDashboard.putBoolean( TelemetryNames.Camera.status, false );
      {
         propsMgr = new PropertiesManager( PropertyNames.Camera.name );
         boolean realSensor =
            propsMgr.getBoolean( PropertyNames.Camera.useRealSensor );

         if ( realSensor )
         {
            CameraSensor.constructInstance();
            ISensor ss = CameraSensor.getInstance();
            tlmMgr.addProvider( ss );
            sensors.add( ss );
         }
         else
         {
            logger.warn( "stub CameraSensor being instantiated" );
            frc.robot.sensors.stubs.CameraSensor.constructInstance();
         }
      }

      SmartDashboard.putBoolean( TelemetryNames.Floor.status, false );
      {
         propsMgr = new PropertiesManager( PropertyNames.Floor.name );
         boolean realSensor =
            propsMgr.getBoolean( PropertyNames.Floor.useRealSensor );

         if ( realSensor )
         {
            FloorDistanceSensor.constructInstance();
            ISensor ss = FloorDistanceSensor.getInstance();
            tlmMgr.addProvider( ss );
            sensors.add( ss );
         }
         else
         {
            logger.warn( "stub FloorDistanceSensor being instantiated" );
            frc.robot.sensors.stubs.FloorDistanceSensor.constructInstance();
         }
      }

      return sensors;
   }

}
