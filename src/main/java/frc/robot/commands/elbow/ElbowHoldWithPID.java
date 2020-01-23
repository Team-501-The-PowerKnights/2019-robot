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

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import riolog.RioLogger;


/**
 * Add your docs here.
 */
public class ElbowHoldWithPID
   extends ElbowBaseCommand
{

   /** Our classes' logger **/
   private static final Logger logger =
      RioLogger.getLogger( ElbowManualControl.class.getName() );

   // Handle to the OI
   private OI oi;


   public ElbowHoldWithPID()
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

      // elbow.holdAtPosition();
   }


   @Override
   protected void execute()
   {
      logExecuteStart( logger );
   }


   @Override
   protected boolean isFinished()
   {
      return ( oi.getElbowSpeed() != 0 );
   }

   // Command to start as replacement for this one
   // TODO - Figure out Java's AutoCloseable problem
   private Command command;


   @Override
   protected void end()
   {
      logger.trace( "ending {}", getName() );

      elbow.setMotor( 0 );

      command = new ElbowJoystickControl();
      command.start();

      super.end();
   }

}
