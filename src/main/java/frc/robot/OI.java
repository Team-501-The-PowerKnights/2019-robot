/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
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


import org.slf4j.Logger;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.commands.BallHighLevelPose;
import frc.robot.commands.BallLowLevelPose;
import frc.robot.commands.BallMidLevelPose;
import frc.robot.commands.BallPickupPose;
import frc.robot.commands.ClimbRetractPose;
import frc.robot.commands.ClimbUpLevel2Pose;
import frc.robot.commands.ClimbUpLevel3Pose;
import frc.robot.commands.HatchPickupPose;
import frc.robot.commands.SubsystemsHomePose;
import frc.robot.commands.claw.ClawClose;
import frc.robot.commands.claw.ClawOpen;
import frc.robot.commands.climber.ClimberDisableControl;
import frc.robot.commands.climber.ClimberEnableControl;
import frc.robot.commands.climber.ClimberWinchJoystickControl;
import frc.robot.managers.PropertiesManager;
import frc.robot.managers.PropertyNames.HMI;
import frc.robot.telemetry.ITelemetryProvider;
import frc.robot.telemetry.TelemetryNames;
import frc.robot.telemetry.TelemetryNames.Misc;

import riolog.RioLogger;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
   implements ITelemetryProvider
{

   /** Our classes' logger **/
   private static final Logger logger =
      RioLogger.getLogger( OI.class.getName() );

   /** Singleton instance of class for all to use **/
   private static OI ourInstance;
   /** Name of our subsystem **/
   private final static String myName = Misc.oiName;


   /**
    * Constructs instance of the subsystem. Assumed to be called before any
    * usage of the subsystem; and verifies only called once. Allows controlled
    * startup sequencing of the robot and all it's subsystems.
    **/
   public static synchronized void constructInstance()
   {
      SmartDashboard.putBoolean( Misc.oiStatus, false );

      if ( ourInstance != null )
      {
         throw new IllegalStateException( myName + " Already Constructed" );
      }
      ourInstance = new OI();

      SmartDashboard.putBoolean( Misc.oiStatus, true );
   }


   /**
    * Returns the singleton instance of the subsystem in the form of the
    * <i>Interface</i> that is defined for it. If it hasn't been constructed
    * yet, throws an <code>IllegalStateException</code>.
    *
    * @return singleton instance of subsystem
    **/
   public static OI getInstance()
   {
      if ( ourInstance == null )
      {
         throw new IllegalStateException( myName + " Not Constructed Yet" );
      }
      return ourInstance;
   }

   @SuppressWarnings( "unused" )
   private final Preferences prefs;
   private final PropertiesManager propsMgr;

   private final Joystick driverStick;

   public final Button turboButton;
   public final Button crawlButton;

   private final Button climberRetractButton;
   private final Button climberLevel3Button;
   private final Button climbLevel2Button;

   private final Button climberControlButton;

   // private final Button winchButton;

   private final Joystick operatorStick;

   // private final Button ballPickupButton;
    private final Button ballLowButton;
   // private final Button ballMidButton;
   // private final Button ballHighButton;

   private final Button hatchButton;

   // private final Button hatchPositionButton;

   // private final Button elbowUpButton;
   // private final Button elbowDownButton;

   // private final Button hatchPickupButton;
   // private final Button hatchLowLevelButton;
   // private final Button hatchMidLevelButton;
   // private final Button hatchHighLevelButton;

   // private final Button hatchRetractButton;

   private final Button climbRetractButton;


   public OI()
   {
      logger.info( "constructing" );

      prefs = Preferences.getInstance();
      propsMgr = new PropertiesManager( HMI.name );

      /*
       * Driver Controller
       */
      driverStick = new Joystick( 0 );

      turboButton = new JoystickButton( driverStick, 6 );
      // crawlButton = new JoystickButton( driverStick, 5 );
      crawlButton = new JoystickButton( driverStick, 5 );

      /*
       * Climber
       */
      climberRetractButton = new POVButton( driverStick, 0 );
      climberRetractButton.whileHeld( new ClimbRetractPose() );

      climberLevel3Button = new POVButton( driverStick, 180 );
      climberLevel3Button.whileHeld( new ClimbUpLevel3Pose() );
      // And enable the winch controls
      climberLevel3Button.whenPressed( new ClimberWinchJoystickControl() );

      climbLevel2Button = new POVButton( driverStick, 270 );
      climbLevel2Button.whileHeld( new ClimbUpLevel2Pose() );
      // And enable the winch controls
      climbLevel2Button.whenPressed( new ClimberWinchJoystickControl() );

      climberControlButton = new POVButton( driverStick, 90 );
      climberControlButton.whileHeld( new ClimberDisableControl() );
      climberControlButton.whenReleased( new ClimberEnableControl() );

      // winchButton = new JoystickButton( driverStick, 7 );
      // winchButton.whenPressed( new ClimberWinchWind() );
      // winchButton.whenReleased( new ClimberWinchStop() );

      /*
       * Operator Controller
       */
      operatorStick = new Joystick( 1 );

      // ballPickupButton = new JoystickButton( operatorStick, 3 );
      // ballPickupButton.whenPressed( new BallPickupPose() );
       ballLowButton = new JoystickButton( operatorStick, 1 );
       ballLowButton.whenPressed( new BallLowLevelPose() );
      // ballMidButton = new JoystickButton( operatorStick, 2 );
      // ballMidButton.whenPressed( new BallMidLevelPose() );
      // ballHighButton = new JoystickButton( operatorStick, 4 );
      // ballHighButton.whenPressed( new BallHighLevelPose() );

      hatchButton = new JoystickButton( operatorStick, 6 );
      hatchButton.whenPressed( new ClawClose() );
      hatchButton.whenReleased( new ClawOpen() );

      // hatchPositionButton = new JoystickButton( operatorStick, 3 );
      // hatchPositionButton.whenPressed( new HatchPickupPose() );

      // elbowUpButton = new POVButton( operatorStick, 0 );
      // elbowUpButton
      // .whenPressed( new ElbowGoToIndexedPosition( ElbowSetPoints.up ) );
      // elbowDownButton = new POVButton( operatorStick, 180 );
      // elbowDownButton
      // .whenPressed( new ElbowGoToIndexedPosition( ElbowSetPoints.down ) );

      // hatchPickupButton = new POVButton( operatorStick, 90 );
      // hatchPickupButton.whenPressed( new HatchPickupPose() );
      // hatchLowLevelButton = new POVButton( operatorStick, 180 );
      // hatchLowLevelButton.whenPressed( new HatchLowLevelPose() );
      // hatchMidLevelButton = new POVButton( operatorStick, 270 );
      // hatchMidLevelButton.whenPressed( new HatchMidLevelPose() );
      // hatchHighLevelButton = new POVButton( operatorStick, 0 );
      // hatchHighLevelButton.whenPressed( new HatchHighLevelPose() );

      // hatchRetractButton = new JoystickButton( operatorStick, 5 );
      // hatchRetractButton.whenPressed( new WristDropHatchToRetract() );
      // TODO - Use a lift command for hatch retraction

      climbRetractButton = new JoystickButton( operatorStick, 7 );
      climbRetractButton.whenPressed( new SubsystemsHomePose() );
   }


   @Override
   public void updateTelemetry()
   {
      SmartDashboard.putNumber( TelemetryNames.HMI.rawSpeed,
         getRawDriveSpeed() );
      SmartDashboard.putNumber( TelemetryNames.HMI.rawTurn, getRawDriveTurn() );

      SmartDashboard.putBoolean( TelemetryNames.HMI.turbo, turboButton.get() );
      SmartDashboard.putBoolean( TelemetryNames.HMI.crawl, crawlButton.get() );

      SmartDashboard.putNumber( TelemetryNames.HMI.liftSpeed, getLiftSpeed() );
      SmartDashboard.putNumber( TelemetryNames.HMI.elbowSpeed,
         getElbowSpeed() );
      SmartDashboard.putNumber( TelemetryNames.HMI.winchSpeed,
         getWinchSpeed() );
   }


   public double getRawDriveSpeed()
   {
      return getDriverLeftY();
   }


   public double getDriveSpeed()
   {
      double hmiSpeed = getRawDriveSpeed();
      double calcSpeed;
      if ( turboButton.get() )
      {
         calcSpeed = hmiSpeed * 1.00;
      }
      else if ( crawlButton.get() )
      {
         calcSpeed = hmiSpeed * 0.15;
      }
      else
      {
         calcSpeed = hmiSpeed * 0.70;
      }
      return calcSpeed;
   }


   public double getRawDriveTurn()
   {
      return getDriverRightX();
   }


   public double getDriveTurn()
   {
      double hmiTurn = getRawDriveTurn();
      double calcTurn;
      if ( turboButton.get() )
      {
         calcTurn = hmiTurn * 0.50;
      }
      else if ( crawlButton.get() )
      {
         calcTurn = hmiTurn * 0.15;
      }
      else
      {
         calcTurn = hmiTurn * 0.40;
      }
      return calcTurn;
   }


   public double getLiftSpeed()
   {
      return getOperatorLeftY();
   }


   public double getElbowSpeed()
   {
      double speed = getOperatorRightY();
      speed *= 0.15;
      return speed;
   }


   public double getClawSpeed()
   {
      double speed = 0.0;
      if ( getOperatorLeftTrigger() > 0 )
      {
         speed = -getOperatorLeftTrigger();
      }
      else if ( getOperatorRightTrigger() > 0 )
      {
         speed = getOperatorRightTrigger();
      }
      return speed;
   }


   public double getWinchSpeed()
   {
      double speed = 0.0;
      if ( getDriverLeftTrigger() > 0 )
      {
         speed = -getDriverLeftTrigger();
      }
      else if ( getDriverRightTrigger() > 0 )
      {
         speed = getDriverRightTrigger();
      }
      return speed;
   }

   // public boolean areElbowButtonsPressed()
   // {
   // return ( elbowUpButton.get() || elbowDownButton.get() );
   // }


   // Methods to return axes on the drive controller
   public double getDriverLeftY()
   {
      double retValue = driverStick.getRawAxis( 1 );
      retValue = -retValue;
      return retValue;
   }


   public double getDriverLeftX()
   {
      double retValue = driverStick.getRawAxis( 0 );
      return retValue;
   }


   public double getDriverRightY()
   {
      double retValue =
         driverStick.getRawAxis( propsMgr.getInt( "liftManualAxis" ) );
      retValue = -retValue;
      return retValue;
   }


   public double getDriverRightX()
   {
      double retValue = driverStick.getRawAxis( 4 );
      return retValue;
   }


   public double getDriverRightTrigger()
   {
      double retValue = driverStick.getRawAxis( 2 );
      return retValue;
   }


   public double getDriverLeftTrigger()
   {
      double retValue = driverStick.getRawAxis( 3 );
      return retValue;
   }


   public double getOperatorLeftY()
   {
      double retValue = operatorStick.getRawAxis( 1 );
      retValue = -fixDeadband( retValue );
      return retValue;
   }


   public double getOperatorLeftX()
   {
      double retValue = operatorStick.getRawAxis( 0 );
      return retValue;
   }


   public double getOperatorRightY()
   {
      double retValue = operatorStick.getRawAxis( 5 );
      retValue = -fixDeadband( retValue );
      return retValue;
   }


   public double getOperatorRightX()
   {
      double retValue = operatorStick.getRawAxis( 4 );
      return retValue;
   }


   public double getOperatorRightTrigger()
   {
      double retValue = operatorStick.getRawAxis( 2 );
      return retValue;
   }


   public double getOperatorLeftTrigger()
   {
      double retValue = operatorStick.getRawAxis( 3 );
      return retValue;
   }


   private double fixDeadband( double value )
   {
      if ( Math.abs( value ) < 0.05 )
      {
         return 0;
      }
      else
      {
         return value;
      }
   }

}

/*
 * import frc.robot.commands.elbow.ElbowGoToHighPosition; import
 * frc.robot.commands.elbow.ElbowGoToMidPosition; import
 * frc.robot.commands.elbow.ElbowGoToPickupPosition; import
 * frc.robot.commands.elbow.ElbowHoldInPlace; import
 * frc.robot.commands.elbow.ElbowManualControl; import
 * frc.robot.commands.lift.LiftGoToHighPosition; import
 * frc.robot.commands.lift.LiftGoToLowPosition; import
 * frc.robot.commands.lift.LiftGoToMidPosition; import
 * frc.robot.commands.lift.LiftHoldInPlace; import
 * frc.robot.commands.lift.LiftManualControl; import
 */

// public final Button liftLowButton;
// public final Button liftMidButton;
// public final Button liftHighButton;
// public final Button liftManualOverrideButton;

// private final Button elbowHighButton;
// private final Button elbowMidButton;
// private final Button elbowLowButton;
// private final Button elbowManualOverrideButton;

// private final Joystick testStick;
// private final Joystick tempStick;

/*
 * Lift
 */
// liftLowButton = new JoystickButton( operatorStick,
// propsMgr.getInt( HMI.liftLowButton ) );
// liftLowButton.whenPressed( new LiftGoToLowPosition() );

// liftMidButton = new JoystickButton( operatorStick,
// propsMgr.getInt( HMI.liftMidButton ) );
// liftMidButton.whenPressed( new LiftGoToMidPosition() );

// liftHighButton = new JoystickButton( operatorStick,
// propsMgr.getInt( HMI.liftHighButton ) );
// liftHighButton.whenPressed( new LiftGoToHighPosition() );

// liftManualOverrideButton = new JoystickButton( operatorStick,
// 1/* pm.getInt("liftManualOverrideButton") */ );
// liftManualOverrideButton.whileHeld( new LiftManualControl() );
// liftManualOverrideButton.whenReleased( new LiftHoldInPlace() );

/*
 * Elbow
 */

// elbowHighButton = new POVButton( operatorStick, 0 );
// elbowHighButton.whenPressed( new ElbowGoToHighPosition() );
// elbowMidButton = new POVButton( operatorStick, 90 );
// elbowMidButton.whenPressed( new ElbowGoToMidPosition() );
// elbowLowButton = new POVButton( operatorStick, 180 );
// elbowLowButton.whenPressed( new ElbowGoToPickupPosition() );
// elbowManualOverrideButton = new JoystickButton( operatorStick, 6 );
// elbowManualOverrideButton.whileHeld( new ElbowManualControl() );
// elbowManualOverrideButton.whenReleased( new ElbowHoldInPlace() );

/*
 * Claw
 */

/*
 * Elbow + Wrist Poses
 */
// ballPickupButton = new POVButton( operatorStick, 180 );
// hatchPickupButton = new POVButton( operatorStick, 90 );
// ballEjectButton = new POVButton( operatorStick, 0 );
// hatchEjectButton = new POVButton( operatorStick, 270 );
// ballPickupButton.whenPressed( new GoToBallPickupPose() );
// hatchPickupButton.whenPressed( new GoToHatchPickupPose() );
// ballEjectButton.whenPressed( new GoToBallEjectPose() );
// hatchEjectButton.whenPressed( new GoToHatchEjectPose() );

/*
 * Test Controller
 */
// testStick = new Joystick( 2 );

/*
 * Elbow
 */

// elbowHighButton = new POVButton( testStick, 0 );
// elbowHighButton.whenPressed( new ElbowGoToHighPosition() );
// elbowMidButton = new POVButton( testStick, 90 );
// elbowMidButton.whenPressed( new ElbowGoToMidPosition() );
// elbowLowButton = new POVButton( testStick, 180 );
// elbowLowButton.whenPressed( new ElbowGoToPickupPosition() );
// elbowManualOverrideButton = new JoystickButton( testStick, 5 );
// elbowManualOverrideButton.whileHeld( new ElbowManualControl() );
// elbowManualOverrideButton.whenReleased( new ElbowHoldInPlace() );
