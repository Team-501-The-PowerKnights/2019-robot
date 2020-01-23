/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package frc.robot;


import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.RobotDriveBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * Add your docs here.
 */
public class Team501DifferentialDrive
   extends RobotDriveBase
{

   public static final double kDefaultQuickStopThreshold = 0.2;
   public static final double kDefaultQuickStopAlpha = 0.1;

   private static int instances;

   private final SpeedController leftSC;
   private final SpeedController rightSC;

   private double m_rightSideInvertMultiplier = -1.0;
   // private boolean m_reported;
   private final boolean squareInputs = true;


   public Team501DifferentialDrive( SpeedController leftMotor,
      SpeedController rightMotor )
   {
      // verify(leftMotor, rightMotor);
      leftSC = leftMotor;
      rightSC = rightMotor;
      addChild( leftSC );
      addChild( rightSC );
      instances++;
      setName( "Team501DifferentialDrive", instances );
   }


   public void arcadeDrive( double xSpeed, double zRotation )
   {
      // if (!m_reported) {
      // HAL.report(tResourceType.kResourceType_RobotDrive, 2,
      // tInstances.kRobotDrive2_DifferentialArcade);
      // m_reported = true;
      // }

      xSpeed = limit( xSpeed );
      xSpeed = applyDeadband( xSpeed, m_deadband );

      zRotation = limit( zRotation );
      zRotation = applyDeadband( zRotation, m_deadband );

      // Square the inputs (while preserving the sign) to increase fine control
      // while permitting full power.
      if ( squareInputs )
      {
         xSpeed = Math.copySign( xSpeed * xSpeed, xSpeed );
         zRotation = Math.copySign( zRotation * zRotation, zRotation );
      }

      double leftMotorOutput;
      double rightMotorOutput;

      double maxInput = Math.copySign(
         Math.max( Math.abs( xSpeed ), Math.abs( zRotation ) ), xSpeed );

      if ( xSpeed >= 0.0 )
      {
         // First quadrant, else second quadrant
         if ( zRotation >= 0.0 )
         {
            leftMotorOutput = maxInput;
            rightMotorOutput = xSpeed - zRotation;
         }
         else
         {
            leftMotorOutput = xSpeed + zRotation;
            rightMotorOutput = maxInput;
         }
      }
      else
      {
         // Third quadrant, else fourth quadrant
         if ( zRotation >= 0.0 )
         {
            leftMotorOutput = xSpeed + zRotation;
            rightMotorOutput = maxInput;
         }
         else
         {
            leftMotorOutput = maxInput;
            rightMotorOutput = xSpeed - zRotation;
         }
      }

      double leftSetValue = ( limit( leftMotorOutput ) * m_maxOutput );
      double rightSetValue = ( limit( rightMotorOutput ) * m_maxOutput
         * m_rightSideInvertMultiplier );
      SmartDashboard.putNumber( "leftSetValue", leftSetValue );
      SmartDashboard.putNumber( "rightSetValue", rightSetValue );
      leftSC.set( leftSetValue );
      rightSC.set( rightSetValue );
   }


   @Override
   public void initSendable( SendableBuilder builder )
   {

   }


   @Override
   public void stopMotor()
   {

   }


   @Override
   public String getDescription()
   {
      return null;
   }
   
}
