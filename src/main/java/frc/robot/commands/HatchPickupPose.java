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

import frc.robot.commands.elbow.ElbowGoToIndexedPosition;
import frc.robot.commands.lift.LiftGoToIndexedPosition;

import frc.robot.subsystems.ElbowSetPoints;
import frc.robot.subsystems.LiftSetPoints;

import riolog.RioLogger;


public class HatchPickupPose
   extends PKBaseCommandGroup
{

   /* Our classes logger */
   private static final Logger logger =
      RioLogger.getLogger( HatchPickupPose.class.getName() );


   /**
    * 
    */
   public HatchPickupPose()
   {
      logger.info( "constructing {}", getName() );

      final double liftPosition = LiftSetPoints.hatchPickup;
      addSequential( new LiftGoToIndexedPosition( liftPosition ) );

      final double elbowPosition = ElbowSetPoints.hatchPickup;
      addSequential( new ElbowGoToIndexedPosition( elbowPosition ) );
      
   }

}
