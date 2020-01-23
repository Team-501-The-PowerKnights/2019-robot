/**
 * Copyright (c) 2015-2019 Team 501 - The PowerKnights. All Rights Reserved.
 * Open Source Software - May be modified and shared by FRC teams. The code must
 * be accompanied by the Team 501 BSD license file in the root directory of the
 * project. You may also obtain a copy of it from the following:
 * http://www.opensource.org/licenses/bsd-license.php.
 *
 * See (Git) repository metadata for author and revision history for this file.
 **/

package frc.robot;


import org.slf4j.Logger;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;
import riolog.RioLogger;


/**
 * This class provides an interface for converting the raw values returned by
 * the POV into a <code>boolean</code> <code>Button</code> value.
 */
public class POVButton
   extends Button
{

   /** Our classes' logger **/
   @SuppressWarnings( "unused" )
   private static final Logger logger =
      RioLogger.getLogger( POVButton.class.getName() );

   //
   private final GenericHID joystick;
   //
   private final int buttonPosition;


   public POVButton( GenericHID joystick, int buttonPosition )
   {
      this.joystick = joystick;
      this.buttonPosition = buttonPosition;
   }


   @Override
   public boolean get()
   {
      return ( joystick.getPOV() == buttonPosition );
   }

}
