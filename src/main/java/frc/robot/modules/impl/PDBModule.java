/**
 * Copyright (c) 2015-2019 Team 501 - The PowerKnights. All Rights Reserved.
 * Open Source Software - May be modified and shared by FRC teams. The code must
 * be accompanied by the Team 501 BSD license file in the root directory of the
 * project. You may also obtain a copy of it from the following:
 * http://www.opensource.org/licenses/bsd-license.php.
 *
 * See (Git) repository metadata for author and revision history for this file.
 **/

package frc.robot.modules.impl;


import org.slf4j.Logger;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.managers.ModuleNames;
import frc.robot.managers.PreferenceNames;
import frc.robot.managers.PropertiesManager;
import frc.robot.modules.IModule;
import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;


/**
 * Add your docs here.
 */
public class PDBModule
   implements IModule
{

   /** Our classes' logger **/
   private static final Logger logger =
      RioLogger.getLogger( PDBModule.class.getName() );

   /** Singleton instance of class for all to use **/
   private static PDBModule ourInstance;
   /** Name of our module **/
   private final static String myName = ModuleNames.pdbName;


   /**
    * Constructs instance of the module. Assumed to be called before any usage
    * of the module; and verifies only called once. Allows controlled startup
    * sequencing of the robot and all it's modules.
    **/
   public static synchronized void constructInstance()
   {
      SmartDashboard.putBoolean( TelemetryNames.PDB.status, false );

      if ( ourInstance != null )
      {
         throw new IllegalStateException( myName + " Already Constructed" );
      }
      ourInstance = new PDBModule();

      SmartDashboard.putBoolean( TelemetryNames.PDB.status, true );
   }


   /**
    * Returns the singleton instance of the module in the form of the
    * <i>Interface</i> that is defined for it. If it hasn't been constructed
    * yet, throws an <code>IllegalStateException</code>.
    *
    * @return singleton instance of module
    **/
   public static IModule getInstance()
   {
      if ( ourInstance == null )
      {
         throw new IllegalStateException( myName + " Not Constructed Yet" );
      }
      return ourInstance;
   }

   @SuppressWarnings( "unused" )
   private final Preferences prefs;
   @SuppressWarnings( "unused" )
   private final PropertiesManager propsMgr;


   private PDBModule()
   {
      logger.info( "constructing" );

      prefs = Preferences.getInstance();
      propsMgr = new PropertiesManager( PreferenceNames.PDB.name );
   }


   @Override
   public void updatePreferences()
   {

   }


   @Override
   public void disable()
   {

   }


   @Override
   public void updateTelemetry()
   {

   }

}
