/**
 * Copyright (c) 2015-2019 Team 501 - The PowerKnights. All Rights Reserved.
 * Open Source Software - May be modified and shared by FRC teams. The code must
 * be accompanied by the Team 501 BSD license file in the root directory of the
 * project. You may also obtain a copy of it from the following:
 * http://www.opensource.org/licenses/bsd-license.php.
 *
 * See (Git) repository metadata for author and revision history for this file.
 **/

package frc.robot.commands.elbow;


import org.slf4j.Logger;

import frc.robot.OI;

import riolog.RioLogger;


public class ElbowManualControl
   extends ElbowBaseCommand
{

   /** Our classes' logger **/
   private static final Logger logger =
      RioLogger.getLogger( ElbowManualControl.class.getName() );

   // Handle to the OI
   private OI oi;


   public ElbowManualControl()
   {
      logger.info( "constructing {}", getName() );
   }


   // Called just before this Command runs the first time
   @Override
   protected void initialize()
   {
      logger.trace( "initializing {}", getName() );
      super.initialize();

      oi = OI.getInstance();
   }


   // Called repeatedly when this Command is scheduled to run
   @Override
   protected void execute()
   {
      logExecuteStart( logger );

      double currentPosition = elbow.getPosition();

      // double speed = oi.getElbowSpeed();
      // if ( speed == 0 )
      // {
      //    speed = 0.01;
      // }
      // elbow.setMotor( speed );

      double moveIncrement = oi.getElbowSpeed();

      double goToPosition = currentPosition;

      goToPosition += 4 * moveIncrement;

      elbow.goToPosition( goToPosition );

   }


   // Make this return true when this Command no longer needs to run execute()
   @Override
   protected boolean isFinished()
   {
      return false;
   }

}
