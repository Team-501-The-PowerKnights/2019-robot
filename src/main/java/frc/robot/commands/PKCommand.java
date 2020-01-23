/**
 * Copyright (c) 2015-2019 Team 501 - The PowerKnights. All Rights Reserved.
 * Open Source Software - May be modified and shared by FRC teams. The code must
 * be accompanied by the Team 501 BSD license file in the root directory of the
 * project. You may also obtain a copy of it from the following:
 * http://www.opensource.org/licenses/bsd-license.php.
 *
 * See (Git) repository metadata for author and revision history for this file.
 **/

package frc.robot.commands;


import org.slf4j.Logger;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import riolog.RioLogger;


/**
 * 
 */
public abstract class PKCommand
   extends Command
{

   /* Our classes logger */
   private static final Logger logger =
      RioLogger.getLogger( PKCommand.class.getName() );

   // Handle to the preferences
   protected final Preferences prefs;
   // Flag for whether the first execution has happened
   private boolean executeOnce;


   protected PKCommand()
   {
      logger.info( "constructing {}", getName() );

      prefs = Preferences.getInstance();
   }


   // Called just before this Command runs the first time
   @Override
   protected void initialize()
   {
      logger.debug( "initializing {}", getName() );

      executeOnce = false;
   }


   protected void logExecuteStart( Logger logger )
   {
      if ( !executeOnce )
      {
         executeOnce = true;
         logger.trace( "first execution of {}", getName() );
      }
   }


   // Called once after isFinished returns true
   @Override
   protected void end()
   {
      logger.debug( "ending {}", getName() );
   }


   // Called when another command which requires one or more of the same
   // subsystems is scheduled to run
   @Override
   protected void interrupted()
   {
      logger.debug( "interrupted {}", getName() );
      super.interrupted(); // calls end()
   }

}
