/**
 * Copyright (c) 2015-2019 Team 501 - The PowerKnights. All Rights Reserved.
 * Open Source Software - May be modified and shared by FRC teams. The code must
 * be accompanied by the Team 501 BSD license file in the root directory of the
 * project. You may also obtain a copy of it from the following:
 * http://www.opensource.org/licenses/bsd-license.php.
 *
 * See (Git) repository metadata for author and revision history for this file.
 **/

package frc.robot.commands.drive;


import org.slf4j.Logger;

import frc.robot.OI;
import frc.robot.subsystems.ILiftSubsystem;
import frc.robot.subsystems.LiftFactory;
import riolog.RioLogger;


public class DriveManually3
   extends DriveBaseCommand
{

   /* Our classes logger */
   private static final Logger logger =
      RioLogger.getLogger( DriveManually3.class.getName() );

   // Handle to lift (as read-only so don't require)
   ILiftSubsystem lift;

   // Handle to OI for joystick values
   private OI oi;


   public DriveManually3()
   {
      logger.info( "constructing {}", getName() );
   }


   // Called just before this Command runs the first time
   @Override
   protected void initialize()
   {
      logger.debug( "initializing" );
      super.initialize();

      lift = LiftFactory.getInstance();

      oi = OI.getInstance();
   }


   // Called repeatedly when this Command is scheduled to run
   @Override
   protected void execute()
   {
      logExecuteStart( logger );

      double speed = oi.getDriveSpeed();
      double turn = oi.getDriveTurn();

      // TODO - This should really go in Drive Subsystem?
      // TODO - Call should be in Lift Subsystem and generic?
      boolean liftConstrained = ( lift.getPosition() > 185 );
      if ( liftConstrained )
      {
         speed *= 0.70;
         turn *= 0.30;
      }

      drive.drive( speed, turn, liftConstrained );
   }


   // Make this return true when this Command no longer needs to run execute()
   @Override
   protected boolean isFinished()
   {
      return false;
   }

}
