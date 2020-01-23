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


import com.revrobotics.CANEncoder;

import org.slf4j.Logger;

import riolog.RioLogger;


/**
 * 
 **/
public class PKCANEncoder
{

   /** Our classes' logger **/
   @SuppressWarnings( "unused" )
   private static final Logger logger =
      RioLogger.getLogger( PKCANEncoder.class.getName() );

   //
   private final CANEncoder canEncoder;

   //
   private double currentValue;
   //
   private int invert;


   public PKCANEncoder( CANEncoder canEncoder )
   {
      this.canEncoder = canEncoder;
   }


   public void zero()
   {
      currentValue = canEncoder.getPosition();
   }


   public void setInverted( boolean isInverted )
   {
      if ( isInverted )
      {
         invert = -1;
      }
      else
      {
         invert = 1;
      }
   }


   public double getPosition()
   {
      double position = ( canEncoder.getPosition() - currentValue );
      position *= invert;
      return position;
   }


   public double getVelocity()
   {
      double velocity = canEncoder.getVelocity();
      return velocity;
   }

}
