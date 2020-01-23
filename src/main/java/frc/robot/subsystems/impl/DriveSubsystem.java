/**
 * Copyright (c) 2015-2019 Team 501 - The PowerKnights. All Rights Reserved.
 * Open Source Software - May be modified and shared by FRC teams. The code must
 * be accompanied by the Team 501 BSD license file in the root directory of the
 * project. You may also obtain a copy of it from the following:
 * http://www.opensource.org/licenses/bsd-license.php.
 *
 * See (Git) repository metadata for author and revision history for this file.
 **/

package frc.robot.subsystems.impl;


import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.PKCANEncoder;
import frc.robot.commands.drive.DriveManually3;
import frc.robot.managers.PropertiesManager;
import frc.robot.managers.PropertyNames;
import frc.robot.managers.SubsystemNames;
import frc.robot.pids.IPIDController;
import frc.robot.pids.PKSparkMaxPIDController;
import frc.robot.subsystems.IDriveSubsystem;
import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;


/**
 * Add your docs here.
 **/
public class DriveSubsystem
   extends Subsystem
   implements IDriveSubsystem
{

   /** Our classes' logger **/
   private static final Logger logger =
      RioLogger.getLogger( DriveSubsystem.class.getName() );

   /** Singleton instance of class for all to use **/
   private static IDriveSubsystem ourInstance;
   /** Name of our subsystem **/
   private static final String myName = SubsystemNames.driveName;


   /**
    * Constructs instance of the drive subsystem. Assumed to be called before
    * any usage of the subsystem; and verifies only called once. Allows
    * controlled startup sequencing of the robot and all it's subsystems.
    **/
   public static synchronized void constructInstance()
   {
      SmartDashboard.putBoolean( TelemetryNames.Drive.status, false );

      if ( ourInstance != null )
      {
         throw new IllegalStateException( myName + " Already Constructed" );
      }
      ourInstance = new DriveSubsystem();

      SmartDashboard.putBoolean( TelemetryNames.Drive.status, true );
   }


   /**
    * Returns the singleton instance of the subsystem in the form of the
    * <i>Interface</i> that is defined for it. If it hasn't been constructed
    * yet, throws an <code>IllegalStateException</code>.
    *
    * @return singleton instance of subsystem
    **/
   public static IDriveSubsystem getInstance()
   {
      if ( ourInstance == null )
      {
         throw new IllegalStateException( myName + " Not Constructed Yet" );
      }
      return ourInstance;
   }


   /**
    * Returns the singleton instance of the subsystem, but in a
    * <code>class</code> that the <i>WPILib</i> interface wants.
    *
    * @return singleton instance of subsystem
    */
   public static Subsystem getWpiSubsystem()
   {
      if ( ourInstance == null )
      {
         throw new IllegalStateException( myName + " Not Constructed Yet" );
      }
      return (Subsystem) ourInstance;
   }

   @SuppressWarnings( "unused" )
   private final Preferences prefs;
   @SuppressWarnings( "unused" )
   private final PropertiesManager propsMgr;

   private final CANSparkMax leftFrontMotor;
   private final CANSparkMax leftRearMotor;
   private final CANSparkMax rightFrontMotor;
   private final CANSparkMax rightRearMotor;

   private final PKCANEncoder leftFrontCANEncoder;
   private final PKCANEncoder rightFrontCANEncoder;
   private final PKCANEncoder leftRearCANEncoder;
   private final PKCANEncoder rightRearCANEncoder;

   private final IPIDController leftPIDController;
   private double leftP;
   private double leftI;
   private double leftD;
   private double leftF;
   private double leftMinOutput;
   private double leftMaxOutput;
   private final IPIDController rightPIDController;
   private double rightP;
   private double rightI;
   private double rightD;
   private double rightF;
   private double rightMinOutput;
   private double rightMaxOutput;

   private DriveHelper helper;

   @SuppressWarnings( "unused" )
   private AHRS ahrs;


   private DriveSubsystem()
   {
      super( myName );
      logger.info( "constructing" );

      prefs = Preferences.getInstance();
      propsMgr = new PropertiesManager( PropertyNames.Drive.name );

      // motor construction
      leftFrontMotor = new CANSparkMax( 20, MotorType.kBrushless );
      leftRearMotor = new CANSparkMax( 21, MotorType.kBrushless );
      rightFrontMotor = new CANSparkMax( 22, MotorType.kBrushless );
      rightRearMotor = new CANSparkMax( 23, MotorType.kBrushless );

      // motor configuration
      // leftRearMotor.follow( ExternalFollower.kFollowerDisabled, 0 );
      // rightRearMotor.follow( ExternalFollower.kFollowerDisabled, 0 );
      leftRearMotor.follow( leftFrontMotor );
      rightRearMotor.follow( rightFrontMotor );
      leftFrontMotor.setInverted( false );
      rightFrontMotor.setInverted( false );

      // encoder stuff
      leftFrontCANEncoder = new PKCANEncoder( leftFrontMotor.getEncoder() );
      leftRearCANEncoder = new PKCANEncoder( leftRearMotor.getEncoder() );
      rightFrontCANEncoder = new PKCANEncoder( rightFrontMotor.getEncoder() );
      rightRearCANEncoder = new PKCANEncoder( rightRearMotor.getEncoder() );
      leftFrontCANEncoder.setInverted( false );
      leftRearCANEncoder.setInverted( false );
      rightFrontCANEncoder.setInverted( true );
      rightRearCANEncoder.setInverted( true );

      // PIDController stuff
      leftPIDController =
         new PKSparkMaxPIDController( leftFrontMotor, leftFrontCANEncoder );
      rightPIDController =
         new PKSparkMaxPIDController( rightFrontMotor, rightFrontCANEncoder );
      leftP = 0.1;
      leftI = 0.0;
      leftD = 0.0;
      leftF = 0.075;
      leftPIDController.setP( leftP );
      leftPIDController.setI( leftI );
      leftPIDController.setD( leftD );
      leftPIDController.setF( leftF );
      leftMinOutput = -0.075;
      leftMaxOutput = 0.075;
      leftPIDController.setOutputLimits( leftMinOutput, leftMaxOutput );
      rightP = 0.1;
      rightI = 0.0;
      rightD = 0.0;
      rightF = 0.075;
      rightPIDController.setP( rightP );
      rightPIDController.setI( rightI );
      rightPIDController.setD( rightD );
      rightPIDController.setF( rightF );
      rightMinOutput = -0.075;
      rightMaxOutput = 0.075;
      rightPIDController.setOutputLimits( rightMinOutput, rightMaxOutput );

      helper = new DriveHelper();

      // navBoard - FOR NOW only
      try
      {
         ahrs = new AHRS( SPI.Port.kMXP );
      }
      catch ( final RuntimeException ex )
      {

      }

      // Load the preferences for this subsystem
      updatePreferences();
   }


   @Override
   public void initDefaultCommand()
   {
      logger.info( "default command: Manual Drive" );
      setDefaultCommand( new DriveManually3() );
   }


   @Override
   public void periodic()
   {
   }


   @Override
   public void validateCalibration()
   {
      // Nothing here
   }


   @Override
   public void updatePreferences()
   {
      logger.info( "new preferences:" );

      // Turn off motor breaks if at all set
      // (this runs to init each mode change)
      setBrake( false );
   }


   @Override
   public void disable()
   {
      logger.debug( "disabling" );
   }


   @Override
   public void updateTelemetry()
   {
      SmartDashboard.putNumber( TelemetryNames.HMI.speed, driveSpeed );
      SmartDashboard.putNumber( TelemetryNames.HMI.turn, driveTurn );
      SmartDashboard.putBoolean( TelemetryNames.HMI.constrained,
         driveConstrained );

      SmartDashboard.putNumber( TelemetryNames.Drive.leftSpeed, leftSpeed );
      SmartDashboard.putNumber( TelemetryNames.Drive.rightSpeed, rightSpeed );

      SmartDashboard.putNumber( TelemetryNames.Drive.leftFrontCount,
         leftFrontCANEncoder.getPosition() );
      SmartDashboard.putNumber( TelemetryNames.Drive.leftRearCount,
         leftRearCANEncoder.getPosition() );
      SmartDashboard.putNumber( TelemetryNames.Drive.rightFrontCount,
         rightFrontCANEncoder.getPosition() );
      SmartDashboard.putNumber( TelemetryNames.Drive.rightRearCount,
         rightRearCANEncoder.getPosition() );

      SmartDashboard.putNumber( TelemetryNames.Drive.leftFrontVelocity,
         leftFrontCANEncoder.getVelocity() );
      SmartDashboard.putNumber( TelemetryNames.Drive.rightFrontVelocity,
         rightFrontCANEncoder.getVelocity() );
   }


   @Override
   public void driveLeftMotors( double speed )
   {
      leftFrontMotor.set( speed );
      leftRearMotor.set( speed );
   }


   @Override
   public void driveRightMotors( double speed )
   {
      rightFrontMotor.set( -speed );
      rightRearMotor.set( speed );
   }


   @Override
   public void stop()
   {
      leftFrontMotor.set( 0.0 );
      leftRearMotor.set( 0.0 );
      rightFrontMotor.set( 0.0 );
      rightRearMotor.set( 0.0 );
   }

   private boolean driveConstrained;
   private double driveSpeed;
   private double driveTurn;
   private double leftSpeed;
   private double rightSpeed;


   @Override
   public void drive( double speed, double turn )
   {
      drive( speed, turn, false );
   }

   private final double quickTurnThreshold = 0.2;


   @Override
   public void drive( double speed, double turn, boolean constrained )
   {
      // Save off passed values for telemetry
      driveSpeed = speed;
      driveTurn = turn;
      driveConstrained = constrained;

      // boolean quickTurn = ( driveSpeed < quickTurnThreshold
      // && driveSpeed > -quickTurnThreshold );
      boolean quickTurn = ( Math.abs( driveSpeed ) < quickTurnThreshold );
      DriveSignal driveSignal =
         helper.cheesyDrive( driveSpeed, driveTurn, quickTurn, false );

      arcadeDrive( driveSignal );
   }


   private void arcadeDrive( DriveSignal driveSignal )
   {
      // Save values for telemetry
      leftSpeed = driveSignal.getLeft();
      rightSpeed = driveSignal.getRight();

      leftFrontMotor.set( leftSpeed );
      rightFrontMotor.set( -rightSpeed );
   }


   @Override
   public void zeroEncoders()
   {
      leftFrontCANEncoder.zero();
      leftRearCANEncoder.zero();
      rightFrontCANEncoder.zero();
      rightRearCANEncoder.zero();
   }


   @Override
   public double getLeftEncoderClicks()
   {
      double leftEncoderCount = ( ( leftFrontCANEncoder.getPosition()
         + leftRearCANEncoder.getPosition() ) / 2 );
      return leftEncoderCount;
   }


   @Override
   public double getRightEncoderClicks()
   {
      double rightEncoderCount = ( ( rightFrontCANEncoder.getPosition()
         + rightRearCANEncoder.getPosition() ) / 2 );
      return rightEncoderCount;
   }


   @Override
   public void setPIDPosition( double leftSet, double rightSet )
   {
      leftSet *= 1;
      rightSet *= -1;
      leftPIDController.goToPosition( leftSet );
      rightPIDController.goToPosition( rightSet );
   }


   @Override
   public void setPIDVelocity( double leftSet, double rightSet )
   {
      leftSet *= 1;
      rightSet *= -1;

      // FIXME - Implement Velocity with PKSparkMaxPIDController
      // leftPIDController.setReference( leftSet, ControlType.kVelocity );
      // rightPIDController.setReference( rightSet, ControlType.kVelocity );
   }


   @Override
   public double convertInchesToEncoderClicks( double inches )
   {
      double encoderClicks = ( inches * 0.6 );
      return encoderClicks;
   }


   @Override
   public void setBrake( boolean brakeOn )
   {
      IdleMode mode = IdleMode.kCoast;

      if ( brakeOn )
      {
         mode = IdleMode.kBrake;
      }

      leftFrontMotor.setIdleMode( mode );
      leftRearMotor.setIdleMode( mode );
      rightFrontMotor.setIdleMode( mode );
      rightRearMotor.setIdleMode( mode );
   }

}
