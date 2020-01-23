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


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.slf4j.Logger;

import riolog.RioLogger;


/**
 * 
 */
public class PKTalonPIDController
   implements IPIDController
{

   /** Our classes' logger **/
   @SuppressWarnings( "unused" )
   private static final Logger logger =
      RioLogger.getLogger( PKTalonPIDController.class.getName() );

   private static final int canTimeout = 30; // msec

   //
   private final TalonSRX pid;
   //
   private final int pidIndex;


   public PKTalonPIDController( TalonSRX device )
   {
      this( device, 0 );
   }


   private PKTalonPIDController( TalonSRX device, int pidIndex )
   {
      pid = device;
      this.pidIndex = pidIndex;

      setP( 0 );
      setI( 0 );
      setD( 0 );
      setF( 0 );
   }


   @Override
   public void setP( double p )
   {
      pid.config_kP( pidIndex, p, canTimeout );
   }


   @Override
   public void setI( double i )
   {
      pid.config_kI( pidIndex, i, canTimeout );
   }


   @Override
   public void setD( double d )
   {
      pid.config_kD( pidIndex, d, canTimeout );
   }


   @Override
   public void setF( double f )
   {
      pid.config_kF( pidIndex, f, canTimeout );
   }


   @Override
   public void setIAccum( double iAccum )
   {
      pid.configMaxIntegralAccumulator( pidIndex, iAccum, canTimeout );
   }


   @Override
   public void setOutputLimits( double min, double max )
   {
      pid.configPeakOutputForward( max, canTimeout );
      pid.configPeakOutputReverse( min, canTimeout );
   }


   @Override
   public void goToPosition( double setPoint )
   {
      // logger.debug( "goToPosition: {}", setPoint );
      pid.set( ControlMode.Position, setPoint );
   }


   public double getPosition()
   {
      return pid.getSelectedSensorPosition();
   }


   @Override
   public boolean inPosition( double threshold )
   {
      if ( !pidEnabled() )
      {
         return false;
      }

      double errorFraction = Math.abs( pidError() / pidTarget() );
      return ( errorFraction < threshold );
   }


   @Override
   public boolean pidEnabled()
   {
      return ( pid.getControlMode() == ControlMode.Position );
   }


   @Override
   public void disable()
   {
      pid.set( ControlMode.Disabled, 0 );
   }


   @Override
   public double pidTarget()
   {
      return ( pid.getClosedLoopTarget( pidIndex ) );
   }


   @Override
   public double pidError()
   {
      return ( pid.getClosedLoopError( pidIndex ) );
   }


   @Override
   public double pidOutput()
   {
      return ( pid.getMotorOutputPercent() );
   }

}
