/**
 * Copyright (c) 2015-2019 Team 501 - The PowerKnights. All Rights Reserved.
 * Open Source Software - May be modified and shared by FRC teams. The code must
 * be accompanied by the Team 501 BSD license file in the root directory of the
 * project. You may also obtain a copy of it from the following:
 * http://www.opensource.org/licenses/bsd-license.php.
 *
 * See (Git) repository metadata for author and revision history for this file.
 **/

package frc.robot.modules;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.managers.PropertiesManager;
import frc.robot.managers.PropertyNames;
import frc.robot.modules.impl.PDBModule;
import frc.robot.telemetry.TelemetryManager;
import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;


/**
 * Add your docs here.
 */
public class ModuleFactory
{

   /** Our classes' logger **/
   private static final Logger logger =
      RioLogger.getLogger( ModuleFactory.class.getName() );


   public static List< IModule > constructModules()
   {
      ArrayList< IModule > modules = new ArrayList< IModule >();

      TelemetryManager tlmMgr = TelemetryManager.getInstance();

      PropertiesManager propsMgr;

      SmartDashboard.putBoolean( TelemetryNames.PDB.status, false );
      {
         propsMgr = new PropertiesManager( PropertyNames.PDB.name );
         boolean realModule =
            propsMgr.getBoolean( PropertyNames.PDB.useRealModule );

         if ( realModule )
         {
            PDBModule.constructInstance();
            IModule ss = PDBModule.getInstance();
            tlmMgr.addProvider( ss );
            modules.add( ss );
         }
         else
         {
            logger.warn( "stub PDBModule being instantiated" );
            frc.robot.modules.stubs.PDBModule.constructInstance();
         }
      }

      return modules;
   }

}
