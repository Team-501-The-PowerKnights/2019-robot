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


import frc.robot.managers.ModuleNames;
import frc.robot.managers.SensorNames;
import frc.robot.managers.SubsystemNames;


/**
 * Provides a standard way of defining names for the telemetry points used in
 * the program. No code should define or use a hard-coded string outside of the
 * ones defined in this class.
 **/
public class TelemetryNames
{

   public static class Misc
   {

      public static final String codeVersion = "Misc.codeVersion";
      public static final String programmer = "Misc.programmer";

      public static final String prefsName = "Prefs";
      public static final String prefsStatus = prefsName + ".status";

      public static final String propsName = "Props";
      public static final String propsStatus = propsName + ".status";

      public static final String oiName = "OI";
      public static final String oiStatus = oiName + ".status";

      public static final String cameraName = "Camera";
      public static final String cameraStatus = cameraName + ".status";
   }

   public static class PDB
   {

      private static final String name = ModuleNames.pdbName;

      public static final String status = name + ".status";
   }

   public static class Gyro
   {

      private static final String name = SensorNames.gyroName;

      public static final String status = name + ".status";
   }

   public static class Camera
   {

      private static final String name = SensorNames.cameraName;

      public static final String status = name + ".status";
   }

   public static class Floor
   {

      private static final String name = SensorNames.floorName;

      public static final String status = name + ".status";
      public static final String value = name + ".value";
      public static final String ready = name + ".ready";
   }

   public static class HMI
   {

      private static final String name = SubsystemNames.hmiName;

      public static final String rawSpeed = name + ".rawSpeed";
      public static final String rawTurn = name + ".rawTurn";
      public static final String turbo = name + ".turbo";
      public static final String crawl = name + ".crawl";

      public static final String speed = name + ".speed";
      public static final String turn = name + ".turn";
      public static final String constrained = name + ".constrained";

      public static final String liftSpeed = name + ".liftSpeed";
      public static final String elbowSpeed = name + ".elbowSpeed";
      public static final String clawSpeed = name + ".clawSpeed";
      public static final String winchSpeed = name + ".winchSpeed";
   }

   public static class Drive
   {

      private static final String name = SubsystemNames.driveName;

      public static final String status = name + ".status";
      public static final String leftSpeed = name + ".leftSpeed";
      public static final String rightSpeed = name + ".rightSpeed";
      public static final String leftFrontCount = name + ".leftFrontCount";
      public static final String leftRearCount = name + ".leftRearCount";
      public static final String rightFrontCount = name + ".rightFrontCount";
      public static final String rightRearCount = name + ".rightRearCount";

      public static final String leftFrontVelocity =
         name + ".leftFrontVelocity";
      public static final String rightFrontVelocity =
         name + ".rightFrontVelocity";

   }

   public static class Lift
   {

      private static final String name = SubsystemNames.liftName;

      public static final String status = name + ".status";
      public static final String output = name + ".output";
      public static final String position = name + ".position";
      public static final String pidEnabled = name + ".pidEnabled";
      public static final String pidTarget = name + ".pidTarget";
      public static final String pidError = name + ".pidError";
      public static final String inPosition = name + ".inPosition";
      public static final String velocity = name + ".velocity";

      public static final String calPoint = name + ".calPoint";
      public static final String calError = name + ".calError";
      public static final String calStatus = name + ".calStatus";

      public static final String min = name + ".min";
      public static final String max = name + ".max";

      public static final String ballPickupPoint = name + ".ballPickupPoint";
      public static final String ballLowPoint = name + ".ballLowPoint";
      public static final String ballMidPoint = name + ".ballMidPoint";
      public static final String ballHighPoint = name + ".ballHighPoint";
      public static final String hatchPickupPoint = name + ".hatchPickupPoint";
      public static final String hatchLowPoint = name + ".hatchLowPoint";
      public static final String hatchMidPoint = name + ".hatchMidPoint";
      public static final String hatchHighPoint = name + ".hatchHighPoint";
   }

   public static class Elbow
   {

      private static final String name = SubsystemNames.elbowName;

      public static final String status = name + ".status";
      public static final String output = name + ".output";
      public static final String position = name + ".position";
      public static final String pidEnabled = name + ".pidEnabled";
      public static final String pidTarget = name + ".pidTarget";
      public static final String pidError = name + ".pidError";
      public static final String inPosition = name + ".inPosition";
      public static final String velocity = name + ".velocity";

      public static final String calPoint = name + ".calPoint";
      public static final String calError = name + ".calError";
      public static final String calStatus = name + ".calStatus";

      public static final String min = name + ".min";
      public static final String max = name + ".max";

      public static final String ballPickupPoint = name + ".ballPickupPoint";
      public static final String ballLowPoint = name + ".ballLowPoint";
      public static final String ballMidPoint = name + ".ballMidPoint";
      public static final String ballHighPoint = name + ".ballHighPoint";
      public static final String hatchPickupPoint = name + ".hatchPickupPoint";
      public static final String hatchLowPoint = name + ".hatchLowPoint";
      public static final String hatchMidPoint = name + ".hatchMidPoint";
      public static final String hatchHighPoint = name + ".hatchHighPoint";
   }

   public static class Claw
   {

      private static final String name = SubsystemNames.clawName;

      public static final String status = name + ".status";

      public static final String hatchValue = name + ".hatchValue";
      public static final String hatchLocked = name + ".hatchLocked";
   }

   public static class Climber
   {

      private static final String name = SubsystemNames.climberName;

      public static final String status = name + ".status";
      public static final String output = name + ".output";
      public static final String position = name + ".position";
      public static final String pidEnabled = name + ".pidEnabled";
      public static final String pidTarget = name + ".pidTarget";
      public static final String pidError = name + ".pidError";
      public static final String inPosition = name + ".inPosition";
      public static final String velocity = name + ".velocity";
   }

   public static class Vision
   {

      private static final String name = "Vision";

      public static final String angle = name + ".angle";
      public static final String count = name + ".count";
      public static final String locked = name + ".locked";
      // Set to correspond to whether in 'autonomous' mode or not
      public static final String isSandstorm = name + ".isSandstorm";
   }

   public static class Telemetry
   {

      public static final String name = "Telemetry";

      public static final String status = name + ".status";
   }

   public static class Scheduler
   {

      public static final String name = "Scheduler";

      public static final String status = name + ".status";
      // The current commands running on the robot
      public static final String currentCommands = name + ".currentCommands";
   }

}
