/**
 * Copyright (c) 2015-2019 Team 501 - The PowerKnights. All Rights Reserved.
 * Open Source Software - May be modified and shared by FRC teams. The code must
 * be accompanied by the Team 501 BSD license file in the root directory of the
 * project. You may also obtain a copy of it from the following:
 * http://www.opensource.org/licenses/bsd-license.php.
 *
 * See (Git) repository metadata for author and revision history for this file.
 **/

package frc.robot.pids;


/**
 * 
 */
public interface IPIDController
{

   public void setP( double p );


   public void setI( double i );


   public void setD( double d );


   public void setF( double f );


   public void setIAccum( double iAccum );


   public void setOutputLimits( double min, double max );


   public void goToPosition( double setPoint );


   public double getPosition();


   public boolean inPosition( double threshold );


   public boolean pidEnabled();


   public void disable();


   public double pidTarget();


   public double pidError();


   public double pidOutput();

}
