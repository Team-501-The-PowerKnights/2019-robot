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

import frc.robot.commands.PKCommand;
import frc.robot.managers.PropertiesManager;
import frc.robot.managers.PropertyNames.Lift;
import frc.robot.subsystems.ILiftSubsystem;
import frc.robot.subsystems.LiftFactory;
import frc.robot.subsystems.LiftSetPoints;

import riolog.RioLogger;


/**
 * 
 */
abstract class LiftBaseCommand
   extends PKCommand
{

   /** Our classes' logger **/
   private static final Logger logger =
      RioLogger.getLogger( LiftBaseCommand.class.getName() );

   // Handle to properties for subsystem
   protected static final PropertiesManager propsMgr;
   //
   protected static final double minPosition;
   protected static final double maxPosition;

   static
   {
      propsMgr = new PropertiesManager( Lift.name );

      minPosition = LiftSetPoints.min;
      maxPosition = LiftSetPoints.max;
   }

   // Handle to subsystem
   protected final ILiftSubsystem lift;


   protected LiftBaseCommand()
   {
      logger.info( "constructing {}", getName() );

      lift = LiftFactory.getInstance();

      requires( LiftFactory.getWpiSubsystem() );
   }

}
