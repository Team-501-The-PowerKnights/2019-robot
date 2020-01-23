/**
 * Copyright (c) 2015-2019 Team 501 - The PowerKnights. All Rights Reserved.
 * Open Source Software - May be modified and shared by FRC teams. The code must
 * be accompanied by the Team 501 BSD license file in the root directory of the
 * project. You may also obtain a copy of it from the following:
 * http://www.opensource.org/licenses/bsd-license.php.
 *
 * See (Git) repository metadata for author and revision history for this file.
 **/

package frc.robot.subsystems.stub;


import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.subsystems.ISubsystem;


/**
 * Provide a set of defaults so the stubs don't have to worry about an
 * implementation.
 */
public abstract class BaseStubSubsystem
   extends Subsystem
   implements ISubsystem
{

   public BaseStubSubsystem( String name )
   {
      super( name );
   }


   @Override
   public void initDefaultCommand()
   {
   }


   @Override
   public void periodic()
   {
   }


   @Override
   public void updateTelemetry()
   {
   }


   @Override
   public void validateCalibration()
   {
   }


   @Override
   public void updatePreferences()
   {
   }


   @Override
   public void disable()
   {
   }

}
