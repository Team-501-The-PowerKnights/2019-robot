/**
 * Copyright (c) 2015-2019 Team 501 - The PowerKnights. All Rights Reserved.
 * Open Source Software - May be modified and shared by FRC teams. The code must
 * be accompanied by the Team 501 BSD license file in the root directory of the
 * project. You may also obtain a copy of it from the following:
 * http://www.opensource.org/licenses/bsd-license.php.
 *
 * See (Git) repository metadata for author and revision history for this file.
 **/

package frc.robot.telemetry;


/**
 * Defines the interface used to provide telemetry to the dashboard. The one
 * method will be called on a periodic basis from the higher control flow.
 */
public interface ITelemetryProvider
{

   /**
    * Update the dashboard with whatever telemetry is associated with this
    * subsystem. It should include any lower level parts as well (e.g., PIDs).
    **/
   public void updateTelemetry();

}
