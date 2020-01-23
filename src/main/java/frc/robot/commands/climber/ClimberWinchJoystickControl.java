/**
 * Copyright (c) 2015-2019 Team 501 - The PowerKnights. All Rights Reserved.
 * Open Source Software - May be modified and shared by FRC teams. The code must
 * be accompanied by the Team 501 BSD license file in the root directory of the
 * project. You may also obtain a copy of it from the following:
 * http://www.opensource.org/licenses/bsd-license.php.
 *
 * See (Git) repository metadata for author and revision history for this file.
 **/

package frc.robot.commands.climber;


import org.slf4j.Logger;

import frc.robot.OI;
import frc.robot.commands.PKCommand;
import frc.robot.subsystems.ClimberFactory;
import frc.robot.subsystems.IClimberSubsystem;

import riolog.RioLogger;


public class ClimberWinchJoystickControl
   extends PKCommand
{

   /** Our classes' logger **/
   private static final Logger logger =
      RioLogger.getLogger( ClimberWinchJoystickControl.class.getName() );

   // Handle to Climber to get to Winch (but without interrupting)
   private final IClimberSubsystem climber;

   // Handle to OI for joystick values
   private OI oi;


   public ClimberWinchJoystickControl()
   {
      // Use requires() here to declare subsystem dependencies
      // eg. requires(chassis);
      logger.info( "constructing {}", getName() );

      climber = ClimberFactory.getInstance();
   }


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

      double speed = oi.getWinchSpeed();
      climber.windWinch( speed );
   }


   // Make this return true when this Command no longer needs to run execute()
   @Override
   protected boolean isFinished()
   {
      return false;
   }

}
