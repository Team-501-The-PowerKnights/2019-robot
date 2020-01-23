/**
 * Copyright (c) 2015-2019 Team 501 - The PowerKnights. All Rights Reserved.
 * Open Source Software - May be modified and shared by FRC teams. The code must
 * be accompanied by the Team 501 BSD license file in the root directory of the
 * project. You may also obtain a copy of it from the following:
 * http://www.opensource.org/licenses/bsd-license.php.
 *
 * See (Git) repository metadata for author and revision history for this file.
 **/

package frc.robot.utils;


import org.slf4j.Logger;

import riolog.RioLogger;


/**
 * Class to smooth out ....
 */
public class PositionCounter
{

   /** Our classes' logger **/
   @SuppressWarnings( "unused" )
   private static final Logger logger =
      RioLogger.getLogger( PositionCounter.class.getName() );

   // Target count for satisfying constraint
   private final double targetCount;
   // Current count of consecutive 'true'
   private double inPositionCount;


   public PositionCounter( double targetCount )
   {
      this.targetCount = targetCount;

      inPositionCount = 0;
   }


   public boolean isReallyDone( boolean isFinished )
   {
      if ( !isFinished )
      {
         inPositionCount = 0;
         return false;
      }

      inPositionCount++;
      // logger.trace( "inPositionCount = {}", inPositionCount );
      return ( inPositionCount >= targetCount );
   }

}
