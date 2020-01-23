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


import frc.robot.telemetry.ITelemetryProvider;


/**
 * 
 **/
public interface ISubsystem
   extends ITelemetryProvider
{

   /**
    * Called to validate that the calibration values in the properties match the
    * physical values from the subsystem itself.
    **/
   public void validateCalibration();


   /**
    * Called to update any preferences associated with the subsystem. This will
    * be used at a minimum to update any PID values.
    **/
   public void updatePreferences();


   /**
    * Disable any active components in the subsystem.
    **/
   public void disable();

}
