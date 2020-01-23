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


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.telemetry.TelemetryNames;
import frc.robot.telemetry.TelemetryManager;

import riolog.RioLogger;


/**
 * 
 **/
public class SubsystemFactory
{

   /** Our classes' logger **/
   private static final Logger logger =
      RioLogger.getLogger( SubsystemFactory.class.getName() );


   public static List< ISubsystem > constructSubsystems()
   {
      logger.info( "constructing" );

      ArrayList< ISubsystem > subsystems = new ArrayList< ISubsystem >();

      TelemetryManager tlmMgr = TelemetryManager.getInstance();

      SmartDashboard.putBoolean( TelemetryNames.Drive.status, false );
      {
         DriveFactory.constructInstance();
         ISubsystem ss = DriveFactory.getInstance();
         tlmMgr.addProvider( ss );
         subsystems.add( ss );
      }

      SmartDashboard.putBoolean( TelemetryNames.Lift.status, false );
      {
         LiftFactory.constructInstance();
         ISubsystem ss = LiftFactory.getInstance();
         tlmMgr.addProvider( ss );
         subsystems.add( ss );
      }

      SmartDashboard.putBoolean( TelemetryNames.Elbow.status, false );
      {
         ElbowFactory.constructInstance();
         ISubsystem ss = ElbowFactory.getInstance();
         tlmMgr.addProvider( ss );
         subsystems.add( ss );
      }

      SmartDashboard.putBoolean( TelemetryNames.Claw.status, false );
      {
         ClawFactory.constructInstance();
         ISubsystem ss = ClawFactory.getInstance();
         tlmMgr.addProvider( ss );
         subsystems.add( ss );
      }

      SmartDashboard.putBoolean( TelemetryNames.Climber.status, false );
      {
         ClimberFactory.constructInstance();
         ISubsystem ss = ClimberFactory.getInstance();
         tlmMgr.addProvider( ss );
         subsystems.add( ss );
      }

      return subsystems;
   }

}
