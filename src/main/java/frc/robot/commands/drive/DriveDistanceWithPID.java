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


public class DriveDistanceWithPID
   extends DriveBaseCommand
{

   /* Our classes logger */
   private static final Logger logger =
      RioLogger.getLogger( DriveDistanceWithPID.class.getName() );

   @SuppressWarnings( "unused" )
   private final double distance;
   @SuppressWarnings( "unused" )
   private final double speed;


   public DriveDistanceWithPID( double distanceInches, double driveSpeed )
   {
      logger.info( "constructing {}", getName() );

      speed = driveSpeed;
      distance = drive.convertInchesToEncoderClicks( distanceInches );
   }


   // Called repeatedly when this Command is scheduled to run
   @Override
   protected void execute()
   {
      logExecuteStart( logger );

      // FIXME - Not implemented
   }


   // Make this return true when this Command no longer needs to run execute()
   @Override
   protected boolean isFinished()
   {
      return false;
   }

}
