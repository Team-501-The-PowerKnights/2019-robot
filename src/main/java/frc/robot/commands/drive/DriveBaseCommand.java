/**
 * Copyright (c) 2015-2019 Team 501 - The PowerKnights. All Rights Reserved.
 * Open Source Software - May be modified and shared by FRC teams. The code must
 * be accompanied by the Team 501 BSD license file in the root directory of the
 * project. You may also obtain a copy of it from the following:
 * http://www.opensource.org/licenses/bsd-license.php.
 *
 * See (Git) repository metadata for author and revision history for this file.
 **/

package frc.robot.commands.drive;


import org.slf4j.Logger;

import frc.robot.commands.PKCommand;
import frc.robot.managers.PropertiesManager;
import frc.robot.managers.PropertyNames.Drive;
import frc.robot.subsystems.DriveFactory;
import frc.robot.subsystems.IDriveSubsystem;
import riolog.RioLogger;


/**
 * 
 */
abstract class DriveBaseCommand
   extends PKCommand
{

   /** Our classes' logger **/
   private static final Logger logger =
      RioLogger.getLogger( DriveBaseCommand.class.getName() );

   // Handle to properties for subsystem
   protected static final PropertiesManager propsMgr;
   //

   static
   {
      propsMgr = new PropertiesManager( Drive.name );
   }

   // Handle to subsystem
   protected final IDriveSubsystem drive;


   protected DriveBaseCommand()
   {
      logger.info( "constructing {}", getName() );

      drive = DriveFactory.getInstance();

      requires( DriveFactory.getWpiSubsystem() );
   }

}
