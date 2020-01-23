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

import riolog.RioLogger;


public class DriveDistanceSimple
   extends DriveBaseCommand
{

   /* Our classes logger */
   private static final Logger logger =
      RioLogger.getLogger( DriveDistanceSimple.class.getName() );

   private final double distance;
   private final double speed;


   public DriveDistanceSimple( double distanceInches, double driveSpeed )
   {
      logger.info( "constructing {}", getName() );

      distance = drive.convertInchesToEncoderClicks( distanceInches );
      speed = ( driveSpeed * 0.4 );
   }


   // Called just before this Command runs the first time
   @Override
   protected void initialize()
   {
      logger.trace( "initializing" );
      super.initialize();

      drive.zeroEncoders();
   }


   // Called repeatedly when this Command is scheduled to run
   @Override
   protected void execute()
   {
      logExecuteStart( logger );

      // drive.setPID(distance, distance);
      drive.driveLeftMotors( speed );
      drive.driveRightMotors( -speed );
   }


   // Make this return true when this Command no longer needs to run execute()
   @Override
   protected boolean isFinished()
   {
      boolean returnValue;

      if ( ( ( drive.getLeftEncoderClicks() + drive.getRightEncoderClicks() )
         / 2 ) >= distance )
      {
         returnValue = true;
      }
      else
      {
         returnValue = false;
      }
      return returnValue;
   }


   // Called once after isFinished returns true
   @Override
   protected void end()
   {
      logger.trace( "ending" );
      super.end();

      drive.stop();
   }

}
