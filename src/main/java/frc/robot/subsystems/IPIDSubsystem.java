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
 * Defines the standard, minimal interface for a <i>subsystem</i> that is based
 * around a PID for control.
 */
public interface IPIDSubsystem
   extends ISubsystem
{

   /**
    * Enables the PID to hold at whatever the current position is when the
    * method is called.
    **/
   default void holdAtPosition()
   {
      double position = getPosition();
      goToPosition( position );
   }


   /**
    * Enables the PID with the <code>setPoint<code> set to the provided value.
    *
    * @param position - the <i>setPoint</i> for the PID
    */
   public void goToPosition( double position );


   /**
    * Gets the current value of the position input to the PID. This is dependent
    * on the sensor for interpretation.
    *
    * @return current input to the PID
    */
   public double getPosition();


   /**
    * Get the current velocity of the position input to the PID.
    *
    * @return
    */
   public double getVelocity();


   /**
    * Get the current value of the (motor) output to the PID.
    * 
    * @return current output of the PID
    */
   public double getOutput();


   /**
    * Returns whether the subsystem is considered "in position" (which would
    * imply that the error is "zero"). This is calculated with a delta around
    * the <code>setPoint</code> if not available directly from the PID.
    *
    * @return <code>true</code> if, and only if, the sensor is indicating that
    *         the <code>setPoint</code> has been reached.
    */
   public boolean inPosition();


   /**
    * Get the error value of the current PID state.
    *
    * @return current error for the PID
    */
   public double getError();

}
