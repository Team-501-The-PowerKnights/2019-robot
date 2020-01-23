/**
 * Copyright (c) 2015-2019 Team 501 - The PowerKnights. All Rights Reserved.
 * Open Source Software - May be modified and shared by FRC teams. The code must
 * be accompanied by the Team 501 BSD license file in the root directory of the
 * project. You may also obtain a copy of it from the following:
 * http://www.opensource.org/licenses/bsd-license.php.
 *
 * See (Git) repository metadata for author and revision history for this file.
 **/

package riolog;


/**
 * 
 **/
public enum Level
{

   OFF( ch.qos.logback.classic.Level.OFF ),
   ERROR( ch.qos.logback.classic.Level.ERROR ),
   WARN( ch.qos.logback.classic.Level.WARN ),
   INFO( ch.qos.logback.classic.Level.INFO ),
   DEBUG( ch.qos.logback.classic.Level.DEBUG ),
   TRACE( ch.qos.logback.classic.Level.TRACE );

   final ch.qos.logback.classic.Level level;


   private Level( ch.qos.logback.classic.Level level )
   {
      this.level = level;
   }

}
