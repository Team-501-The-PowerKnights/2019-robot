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

import frc.robot.commands.PKCommand;
import frc.robot.managers.PropertiesManager;
import frc.robot.managers.PropertyNames.Elbow;
import frc.robot.subsystems.ElbowFactory;
import frc.robot.subsystems.ElbowSetPoints;
import frc.robot.subsystems.IElbowSubsystem;

import riolog.RioLogger;


/**
 * 
 */
abstract class ElbowBaseCommand
   extends PKCommand
{

   /** Our classes' logger **/
   private static final Logger logger =
      RioLogger.getLogger( ElbowBaseCommand.class.getName() );

   // Handle to properties for subsystem
   protected static final PropertiesManager propsMgr;
   //
   protected static final double minPosition;
   protected static final double maxPosition;

   static
   {
      propsMgr = new PropertiesManager( Elbow.name );

      minPosition = ElbowSetPoints.min;
      maxPosition = ElbowSetPoints.max;
   }

   // Handle to subsystem
   protected final IElbowSubsystem elbow;


   protected ElbowBaseCommand()
   {
      logger.info( "constructing {}", getName() );

      elbow = ElbowFactory.getInstance();

      requires( ElbowFactory.getWpiSubsystem() );
   }

}
