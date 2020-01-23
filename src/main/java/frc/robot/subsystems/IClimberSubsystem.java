/**
 * Copyright (c) 2015-2019 Team 501 - The PowerKnights. All Rights Reserved.
 * Open Source Software - May be modified and shared by FRC teams. The code must
 * be accompanied by the Team 501 BSD license file in the root directory of the
 * project. You may also obtain a copy of it from the following:
 * http://www.opensource.org/licenses/bsd-license.php.
 *
 * See (Git) repository metadata for author and revision history for this file.
 **/

package frc.robot.subsystems;


/**
 * 
 */
public interface IClimberSubsystem
   extends IPIDSubsystem
{

   /**
    * Generic <code>set</code> method for a motor. Should not be used except for
    * debugging or initial prototyping; the specific actions should be used
    * instead.
    *
    * @param speed
    */
   public void setMotor( double speed );


   /**
    * Stops the motor (by setting the speed to 0.0).
    */
   public void stop();


   public void enableControl();


   public void disableControl();


   /**
    * 
    * @param setPoint
    */
   public void goUpToPosition( double setPoint );


   /**
    * 
    * @param setPoint
    */
   public void dropDownToPosition( double setPoint );


   /**
    * Raise the climber to it's <i>up</i> position.
    */
   default void goUp()
   {
      goUpToPosition( 168.0 );
   }


   /**
    * Lower the climber to it's <i>down</i> position.
    */
   default void dropDown()
   {
      dropDownToPosition( 10.0 );
   }


   public void windWinch( double speed );


   public void stopWinch();

}
