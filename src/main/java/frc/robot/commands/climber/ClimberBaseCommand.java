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

import frc.robot.commands.PKCommand;
import frc.robot.managers.PropertiesManager;
import frc.robot.managers.PropertyNames.Climber;
import frc.robot.subsystems.ClimberFactory;
import frc.robot.subsystems.IClimberSubsystem;
import riolog.RioLogger;


/**
 * 
 */
abstract class ClimberBaseCommand
   extends PKCommand
{

   /** Our classes' logger **/
   private static final Logger logger =
      RioLogger.getLogger( ClimberBaseCommand.class.getName() );

   // Handle to properties for subsystem
   protected static final PropertiesManager propsMgr;

   static
   {
      propsMgr = new PropertiesManager( Climber.name );
   }

   // Handle to subsystem
   protected final IClimberSubsystem climber;


   protected ClimberBaseCommand()
   {
      logger.info( "constructing {}", getName() );

      climber = ClimberFactory.getInstance();

      requires( ClimberFactory.getWpiSubsystem() );
   }

}
