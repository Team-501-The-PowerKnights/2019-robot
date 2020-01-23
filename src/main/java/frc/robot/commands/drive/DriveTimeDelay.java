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

import edu.wpi.first.wpilibj.Timer;

import riolog.RioLogger;


public class DriveTimeDelay
   extends DriveBaseCommand
{

   /** Our classes' logger **/
   private static final Logger logger =
      RioLogger.getLogger( DriveTimeDelay.class.getName() );

   // delay in seconds
   private final double timeDelay;
   //
   private final Timer timer;


   /**
    * 
    * 
    * @param timeDelay - delay in seconds.
    */
   public DriveTimeDelay( double timeDelay )
   {
      logger.info( "constructing {}", getName() );

      this.timeDelay = timeDelay;
      timer = new Timer();
   }


   // Called just before this Command runs the first time
   @Override
   protected void initialize()
   {
      logger.debug( "initializing" );
      super.initialize();

      timer.reset();
      timer.start();
   }


   // Called repeatedly when this Command is scheduled to run
   @Override
   protected void execute()
   {
      logExecuteStart( logger );
   }


   // Make this return true when this Command no longer needs to run execute()
   @Override
   protected boolean isFinished()
   {
      return timer.hasPeriodPassed( timeDelay );
   }


   // Called once after isFinished returns true
   @Override
   protected void end()
   {
      logger.trace( "ending" );

      timer.stop();

      super.end();
   }

}
