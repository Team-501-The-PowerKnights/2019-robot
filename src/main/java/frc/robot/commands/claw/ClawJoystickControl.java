/**
 * Copyright (c) 2015-2019 Team 501 - The PowerKnights. All Rights Reserved.
 * Open Source Software - May be modified and shared by FRC teams. The code must
 * be accompanied by the Team 501 BSD license file in the root directory of the
 * project. You may also obtain a copy of it from the following:
 * http://www.opensource.org/licenses/bsd-license.php.
 *
 * See (Git) repository metadata for author and revision history for this file.
 **/

package frc.robot.commands.claw;


import org.slf4j.Logger;

import frc.robot.OI;

import riolog.RioLogger;


public class ClawJoystickControl
   extends ClawBaseCommand
{

   /** Our classes' logger **/
   private static final Logger logger =
      RioLogger.getLogger( ClawJoystickControl.class.getName() );

   // Handle to the OI
   private OI oi;


   public ClawJoystickControl()
   {
      logger.info( "constructing {}", getName() );
   }


   // Called just before this Command runs the first time
   @Override
   protected void initialize()
   {
      logger.debug( "initializing {}", getName() );
      super.initialize();

      oi = OI.getInstance();
   }


   // Called repeatedly when this Command is scheduled to run
   @Override
   protected void execute()
   {
      logExecuteStart( logger );

      double speed = oi.getClawSpeed();
      speed += 0.35;
      claw.setMotor( speed );
   }


   // Make this return true when this Command no longer needs to run execute()
   @Override
   protected boolean isFinished()
   {
      return false;
   }


   // Called once after isFinished returns true
   @Override
   protected void end()
   {
      logger.debug( "ending {}", getName() );

      claw.setMotor( 0.0 );

      super.end();
   }

}
