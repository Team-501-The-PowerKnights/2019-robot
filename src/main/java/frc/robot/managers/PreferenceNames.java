/**
 * Copyright (c) 2015-2019 Team 501 - The PowerKnights. All Rights Reserved.
 * Open Source Software - May be modified and shared by FRC teams. The code must
 * be accompanied by the Team 501 BSD license file in the root directory of the
 * project. You may also obtain a copy of it from the following:
 * http://www.opensource.org/licenses/bsd-license.php.
 *
 * See (Git) repository metadata for author and revision history for this file.
 **/

package frc.robot.managers;


/**
 * Provides a standard way of defining names for the <code>Preferences</code>
 * used in the program. No code should define or use a hard-coded string outside
 * of the ones defined in this class.
 **/
public class PreferenceNames
{

   public static class PDB
   {

      public static final String name = ModuleNames.pdbName;
   }

   public static class Gyro
   {

      public static final String name = SensorNames.gyroName;
   }

   public static class Camera
   {

      public static final String name = SensorNames.cameraName;
   }

   public static class Floor
   {

      public static final String name = SensorNames.floorName;
   }

   public static class HMI
   {

      public static final String name = SubsystemNames.hmiName;
   }

   public static class Drive
   {

      public static final String name = SubsystemNames.driveName;
      public static final String pid_P = name + ".pid_P";
      public static final String pid_I = name + ".pid_I";
      public static final String pid_D = name + ".pid_D";
      public static final String pid_F = name + ".pid_F";
   }

   public static class Lift
   {

      public static final String name = SubsystemNames.liftName;
      public static final String pid_P = name + ".pid_P";
      public static final String pid_I = name + ".pid_I";
      public static final String pid_D = name + ".pid_D";
      public static final String pid_F = name + ".pid_F";
   }

   public static class Elbow
   {

      public static final String name = SubsystemNames.elbowName;
      public static final String pid_P = name + ".pid_P";
      public static final String pid_I = name + ".pid_I";
      public static final String pid_D = name + ".pid_D";
      public static final String pid_F = name + ".pid_F";
   }

   public static class Claw
   {

      public static final String name = SubsystemNames.clawName;
   }

   public static class Climber
   {

      public static final String name = SubsystemNames.climberName;
      public static final String pid_P = name + ".pid_P";
      public static final String pid_I = name + ".pid_I";
      public static final String pid_D = name + ".pid_D";
      public static final String pid_F = name + ".pid_F";
   }

}
