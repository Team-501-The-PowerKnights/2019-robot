/**
 * Copyright (c) 2015-2019 Team 501 - The PowerKnights. All Rights Reserved.
 * Open Source Software - May be modified and shared by FRC teams. The code must
 * be accompanied by the Team 501 BSD license file in the root directory of the
 * project. You may also obtain a copy of it from the following:
 * http://www.opensource.org/licenses/bsd-license.php.
 *
 * See (Git) repository metadata for author and revision history for this file.
 **/

package frc.robot.managers;


import org.slf4j.Logger;

import edu.wpi.first.wpilibj.Preferences;
import frc.robot.managers.PreferenceNames.*;
import riolog.RioLogger;


/**
 * Provides a consistent way to initialize the <code>Preferences</code>. If they
 * already exist, nothing happens. If they don't, it will be created with a
 * nominal default.
 */
public class PreferencesInitializer
{

   /* Our classes logger */
   private static final Logger logger =
      RioLogger.getLogger( PreferencesInitializer.class.getName() );

   private static final Preferences prefs = Preferences.getInstance();


   public static void initalize()
   {
      /*
       * HMI
       */

      /*
       * Drive
       */
      if ( !prefs.containsKey( Drive.pid_P ) )
      {
         logger.warn( "{} doesn't exist; creating with default", Drive.pid_P );
         prefs.putDouble( Drive.pid_P, 0.0 );
      }
      if ( !prefs.containsKey( Drive.pid_I ) )
      {
         logger.warn( "{} doesn't exist; creating with default", Drive.pid_I );
         prefs.putDouble( Drive.pid_I, 0.0 );
      }
      if ( !prefs.containsKey( Drive.pid_D ) )
      {
         logger.warn( "{} doesn't exist; creating with default", Drive.pid_D );
         prefs.putDouble( Drive.pid_D, 0.0 );
      }
      if ( !prefs.containsKey( Drive.pid_F ) )
      {
         logger.warn( "{} doesn't exist; creating with default", Drive.pid_F );
         prefs.putDouble( Drive.pid_F, 0.0 );
      }

      /*
       * Lift
       */
      if ( !prefs.containsKey( Lift.pid_P ) )
      {
         logger.warn( "{} doesn't exist; creating with default", Lift.pid_P );
         prefs.putDouble( Lift.pid_P, 0.0 );
      }
      if ( !prefs.containsKey( Lift.pid_I ) )
      {
         logger.warn( "{} doesn't exist; creating with default", Lift.pid_I );
         prefs.putDouble( Lift.pid_I, 0.0 );
      }
      if ( !prefs.containsKey( Lift.pid_D ) )
      {
         logger.warn( "{} doesn't exist; creating with default", Lift.pid_D );
         prefs.putDouble( Lift.pid_D, 0.0 );
      }
      if ( !prefs.containsKey( Lift.pid_F ) )
      {
         logger.warn( "{} doesn't exist; creating with default", Lift.pid_F );
         prefs.putDouble( Lift.pid_F, 0.0 );
      }

      /*
       * Elbow
       */
      if ( !prefs.containsKey( Elbow.pid_P ) )
      {
         logger.warn( "{} doesn't exist; creating with default", Elbow.pid_P );
         prefs.putDouble( Elbow.pid_P, 0.0 );
      }
      if ( !prefs.containsKey( Elbow.pid_I ) )
      {
         logger.warn( "{} doesn't exist; creating with default", Elbow.pid_I );
         prefs.putDouble( Elbow.pid_I, 0.0 );
      }
      if ( !prefs.containsKey( Elbow.pid_D ) )
      {
         logger.warn( "{} doesn't exist; creating with default", Elbow.pid_D );
         prefs.putDouble( Elbow.pid_D, 0.0 );
      }
      if ( !prefs.containsKey( Elbow.pid_F ) )
      {
         logger.warn( "{} doesn't exist; creating with default", Elbow.pid_F );
         prefs.putDouble( Elbow.pid_F, 0.0 );
      }

      /*
       * Claw
       */

      /*
       * Climber
       */
      if ( !prefs.containsKey( Climber.pid_P ) )
      {
         logger.warn( "{} doesn't exist; creating with default",
            Climber.pid_P );
         prefs.putDouble( Climber.pid_P, 0.0 );
      }
      if ( !prefs.containsKey( Climber.pid_I ) )
      {
         logger.warn( "{} doesn't exist; creating with default",
            Climber.pid_I );
         prefs.putDouble( Climber.pid_I, 0.0 );
      }
      if ( !prefs.containsKey( Climber.pid_D ) )
      {
         logger.warn( "{} doesn't exist; creating with default",
            Climber.pid_D );
         prefs.putDouble( Climber.pid_D, 0.0 );
      }
      if ( !prefs.containsKey( Climber.pid_F ) )
      {
         logger.warn( "{} doesn't exist; creating with default",
            Climber.pid_F );
         prefs.putDouble( Climber.pid_F, 0.0 );
      }

   }


   public static final void dumpPreferences()
   {
      logger.info( "Preferences:" );

      /*
       * HMI
       */

      /*
       * Drive
       */
      logger.info( "  {}={}", Drive.pid_P,
         prefs.getDouble( Drive.pid_P, 0.0 ) );
      logger.info( "  {}={}", Drive.pid_I,
         prefs.getDouble( Drive.pid_I, 0.0 ) );
      logger.info( "  {}={}", Drive.pid_D,
         prefs.getDouble( Drive.pid_D, 0.0 ) );
      logger.info( "  {}={}", Drive.pid_F,
         prefs.getDouble( Drive.pid_F, 0.0 ) );

      /*
       * Lift
       */
      logger.info( "  {}={}", Lift.pid_P, prefs.getDouble( Lift.pid_P, 0.0 ) );
      logger.info( "  {}={}", Lift.pid_I, prefs.getDouble( Lift.pid_I, 0.0 ) );
      logger.info( "  {}={}", Lift.pid_D, prefs.getDouble( Lift.pid_D, 0.0 ) );
      logger.info( "  {}={}", Lift.pid_F, prefs.getDouble( Lift.pid_F, 0.0 ) );

      /*
       * Elbow
       */
      logger.info( "  {}={}", Elbow.pid_P,
         prefs.getDouble( Elbow.pid_P, 0.0 ) );
      logger.info( "  {}={}", Elbow.pid_I,
         prefs.getDouble( Elbow.pid_I, 0.0 ) );
      logger.info( "  {}={}", Elbow.pid_D,
         prefs.getDouble( Elbow.pid_D, 0.0 ) );
      logger.info( "  {}={}", Elbow.pid_F,
         prefs.getDouble( Elbow.pid_F, 0.0 ) );

      /*
       * Claw
       */

   }

}
