/**
 * Copyright (c) 2015-2019 Team 501 - The PowerKnights. All Rights Reserved.
 * Open Source Software - May be modified and shared by FRC teams. The code must
 * be accompanied by the Team 501 BSD license file in the root directory of the
 * project. You may also obtain a copy of it from the following:
 * http://www.opensource.org/licenses/bsd-license.php.
 *
 * See (Git) repository metadata for author and revision history for this file.
 **/

package frc.robot.commands.lift;


import org.slf4j.Logger;

import riolog.RioLogger;


/**
 * 
 */
public class LiftGoToIndexedPosition
   extends LiftBaseCommand
{

   /** Our classes' logger **/
   private static final Logger logger =
      RioLogger.getLogger( LiftGoToIndexedPosition.class.getName() );

   //
   private final double target;

   private double count = 0;


   public LiftGoToIndexedPosition( double target )
   {
      logger.info( "constructing {}", getName() );

      this.target = target;
   }


   // Called just before this Command runs the first time
   @Override
   protected void initialize()
   {
      logger.debug( "initializing {}", getName() );
      super.initialize();

      lift.goToPosition( target );

      count = 0;
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
      count++;
      logger.debug( "Count: " + count );
      return ( lift.inPosition() ) || ( count >= 250 );
   }

}
