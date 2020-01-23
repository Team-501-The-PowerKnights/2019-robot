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
 * Add your docs here.
 **/
public interface IDriveSubsystem
   extends ISubsystem
{

   public void driveLeftMotors( double speed );


   public void driveRightMotors( double speed );


   public void stop();


   /**
    * 
    * @param hmiSpeed
    * @param hmiTurn
    */
   public void drive( double hmiSpeed, double hmiTurn );


   /**
    * 
    * @param hmiSpeed
    * @param hmiTurn
    * @param constrained
    */
   public void drive( double hmiSpeed, double hmiTurn, boolean constrained );


   public void zeroEncoders();


   public double getLeftEncoderClicks();


   public double getRightEncoderClicks();


   public void setPIDPosition( double leftSet, double rightSet );


   public void setPIDVelocity( double leftSet, double rightSet );


   public double convertInchesToEncoderClicks( double inches );


   public void setBrake( boolean brakeOn );

}
