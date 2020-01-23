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

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.managers.PropertiesManager;
import frc.robot.managers.PropertyNames;
import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;


/**
 * 
 */
public class LiftSetPoints
{

   /* Our classes logger */
   private static final Logger logger =
      RioLogger.getLogger( LiftSetPoints.class.getName() );

   public static final double calPercentValid = 0.05;

   public static final double cal;

   public static final double min;
   public static final double max;

   public static final double ballPickup;
   public static final double ballLow;
   public static final double ballMid;
   public static final double ballHigh;

   public static final double hatchPickup;
   public static final double hatchLow;
   public static final double hatchMid;
   public static final double hatchHigh;

   static
   {
      // Handle to properties for subsystem
      PropertiesManager propsMgr =
         new PropertiesManager( PropertyNames.Lift.name );

      cal = propsMgr.getDouble( PropertyNames.Lift.calPosition );

      min = cal + 0;
      max = cal + 236.1;

      ballPickup = cal + 90.313;
      // ballLow = cal + 17;
      ballLow = 0.02;
      ballMid = cal + 131.831;
      ballHigh = cal + 223.58;

      // hatchPickup = cal + 36;
      hatchPickup = 6.54;
      hatchLow = cal + 62;
      hatchMid = cal + 173.5;
      hatchHigh = cal + 223.58;

      logger.info( "set points for {}:", PropertyNames.Lift.name );
      logger.info( "calPosition = {}", cal );

      logger.info( "min         = {}", min );
      logger.info( "max         = {}", max );

      logger.info( "ballPickup  = {}", ballPickup );
      logger.info( "ballLow     = {}", ballLow );
      logger.info( "ballMid     = {}", ballMid );
      logger.info( "ballHigh    = {}", ballHigh );

      logger.info( "hatchPickup = {}", hatchPickup );
      logger.info( "hatchLow    = {}", hatchLow );
      logger.info( "hatchMid    = {}", hatchMid );
      logger.info( "hatchHigh   = {}", hatchHigh );

      SmartDashboard.putNumber( TelemetryNames.Lift.calPoint, cal );
      SmartDashboard.putNumber( TelemetryNames.Lift.calError, 0.0 );
      SmartDashboard.putNumber( TelemetryNames.Lift.min, min );
      SmartDashboard.putNumber( TelemetryNames.Lift.max, max );
      SmartDashboard.putNumber( TelemetryNames.Lift.ballPickupPoint,
         ballPickup );
      SmartDashboard.putNumber( TelemetryNames.Lift.ballLowPoint, ballLow );
      SmartDashboard.putNumber( TelemetryNames.Lift.ballMidPoint, ballMid );
      SmartDashboard.putNumber( TelemetryNames.Lift.ballHighPoint, ballHigh );
      SmartDashboard.putNumber( TelemetryNames.Lift.hatchPickupPoint,
         hatchPickup );
      SmartDashboard.putNumber( TelemetryNames.Lift.hatchLowPoint, hatchLow );
      SmartDashboard.putNumber( TelemetryNames.Lift.hatchMidPoint, hatchMid );
      SmartDashboard.putNumber( TelemetryNames.Lift.hatchHighPoint, hatchHigh );
   }

}
