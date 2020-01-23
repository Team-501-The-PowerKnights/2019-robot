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
import frc.robot.utils.PositionCounter;
import riolog.RioLogger;


public class ElbowJoystickControl
   extends ElbowBaseCommand
{

   /** Our classes' logger **/
   private static final Logger logger =
      RioLogger.getLogger( ElbowJoystickControl.class.getName() );

   // Handle to the OI
   private OI oi;

   // Counter to make sure we are really 'stopped'
   private final PositionCounter countOfNoInput;


   public ElbowJoystickControl()
   {
      logger.info( "constructing {}", getName() );

      // We wait 0.1 seconds (5 @ 20 msec loop)
      countOfNoInput = new PositionCounter( 5 );
   }


   // Called just before this Command runs the first time
   @Override
   protected void initialize()
   {
      logger.debug( "initializing {}", getName() );
      super.initialize();

      oi = OI.getInstance();
   }

   // Current/last speed from HMI (to share between methods)
   private double speed;


   // Called repeatedly when this Command is scheduled to run
   @Override
   protected void execute()
   {
      logExecuteStart( logger );

      speed = oi.getElbowSpeed();

      if ( speed == 0.0 )
      {
         speed = 0.02;
      }
      
      elbow.setMotor( speed );
   }


   // Make this return true when this Command no longer needs to run execute()
   @Override
   protected boolean isFinished()
   {
      return countOfNoInput.isReallyDone( ( speed == 0.0 ) );
   }


   // Called once after isFinished returns true
   @Override
   protected void end()
   {
      logger.debug( "ending {}", getName() );

      elbow.setMotor( 0.0 );

      super.end();
   }

}
