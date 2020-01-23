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

import frc.robot.commands.climber.ClimberExtendLevel3;
import frc.robot.commands.drive.DriveBrakeOn;
import frc.robot.commands.drive.DriveStop;
import frc.robot.commands.drive.DriveTimeDelay;

import riolog.RioLogger;


public class ClimbUpLevel3Pose
   extends PKBaseCommandGroup
{

   /* Our classes logger */
   private static final Logger logger =
      RioLogger.getLogger( ClimbUpLevel3Pose.class.getName() );


   public ClimbUpLevel3Pose()
   {
      logger.info( "constructing {}", getName() );

      // Set brake on drive now in end game
      addSequential( new DriveBrakeOn() );

      addSequential( new ClimberExtendLevel3() );
      addSequential( new DriveTimeDelay( 1 ) );
      addSequential( new DriveStop() );
   }

}
