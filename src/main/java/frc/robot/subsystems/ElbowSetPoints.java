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
public class ElbowSetPoints
{

   /* Our classes logger */
   private static final Logger logger =
      RioLogger.getLogger( ElbowSetPoints.class.getName() );

   public static final double calPercentValid = 0.05;

   public static final double cal;

   public static final double min;
   public static final double max;

   public static final double up;
   public static final double down;

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
         new PropertiesManager( PropertyNames.Elbow.name );

      cal = propsMgr.getDouble( PropertyNames.Elbow.calPosition );

      min = cal + -140;
      max = cal + 220;

      up = cal + 1;
      down = cal + -15;

      ballPickup = cal + 33;
      ballLow = cal + 199;
      ballMid = cal + 199;
      ballHigh = cal + 173;

      hatchPickup = cal - 11.6;
      hatchLow = cal + 126;
      hatchMid = cal + 126;
      hatchHigh = cal + 126;

      logger.info( "set points for {}:", PropertyNames.Elbow.name );
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

      SmartDashboard.putNumber( TelemetryNames.Elbow.calPoint, cal );
      SmartDashboard.putNumber( TelemetryNames.Elbow.calError, 0.0 );
      SmartDashboard.putNumber( TelemetryNames.Elbow.min, min );
      SmartDashboard.putNumber( TelemetryNames.Elbow.max, max );
      SmartDashboard.putNumber( TelemetryNames.Elbow.ballPickupPoint,
         ballPickup );
      SmartDashboard.putNumber( TelemetryNames.Elbow.ballLowPoint, ballLow );
      SmartDashboard.putNumber( TelemetryNames.Elbow.ballMidPoint, ballMid );
      SmartDashboard.putNumber( TelemetryNames.Elbow.ballHighPoint, ballHigh );
      SmartDashboard.putNumber( TelemetryNames.Elbow.hatchPickupPoint,
         hatchPickup );
      SmartDashboard.putNumber( TelemetryNames.Elbow.hatchLowPoint, hatchLow );
      SmartDashboard.putNumber( TelemetryNames.Elbow.hatchMidPoint, hatchMid );
      SmartDashboard.putNumber( TelemetryNames.Elbow.hatchHighPoint,
         hatchHigh );
   }

}
