/**
 * Copyright (c) 2015-2019 Team 501 - The PowerKnights. All Rights Reserved.
 * Open Source Software - May be modified and shared by FRC teams. The code must
 * be accompanied by the Team 501 BSD license file in the root directory of the
 * project. You may also obtain a copy of it from the following:
 * http://www.opensource.org/licenses/bsd-license.php.
 *
 * See (Git) repository metadata for author and revision history for this file.
 **/

package frc.robot.commands.claw;


import org.slf4j.Logger;

import frc.robot.commands.PKCommand;
import frc.robot.managers.PropertiesManager;
import frc.robot.managers.PropertyNames.Claw;
import frc.robot.subsystems.ClawFactory;
import frc.robot.subsystems.IClawSubsystem;

import riolog.RioLogger;


/**
 * 
 */
abstract class ClawBaseCommand
   extends PKCommand
{

   /** Our classes' logger **/
   private static final Logger logger =
      RioLogger.getLogger( ClawBaseCommand.class.getName() );

   // Handle to properties for subsystem
   protected static final PropertiesManager propsMgr;
   static
   {
      propsMgr = new PropertiesManager( Claw.name );
   }

   // Handle to subsystem
   protected final IClawSubsystem claw;


   protected ClawBaseCommand()
   {
      logger.info( "constructing {}", getName() );

      claw = ClawFactory.getInstance();

      requires( ClawFactory.getWpiSubsystem() );
   }

}
