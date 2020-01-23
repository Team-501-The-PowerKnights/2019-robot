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

import frc.robot.utils.PositionCounter;
import riolog.RioLogger;


/**
 * 
 */
public class ElbowGoToIndexedPosition
   extends ElbowBaseCommand
{

   /** Our classes' logger **/
   private static final Logger logger =
      RioLogger.getLogger( ElbowGoToIndexedPosition.class.getName() );

   //
   private final double indexOfPosition;

   private final PositionCounter counter;


   public ElbowGoToIndexedPosition( double elbowPosition )
   {
      logger.info( "constructing {}", getName() );

      this.indexOfPosition = elbowPosition;

      counter = new PositionCounter( 25 );
   }


   // Called just before this Command runs the first time
   @Override
   protected void initialize()
   {
      logger.debug( "initializing {}", getName() );
      super.initialize();

      elbow.goToPosition( indexOfPosition );
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
      return counter.isReallyDone( elbow.inPosition() );
      // return true;
   }

}
