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


import org.slf4j.Logger;

import frc.robot.PKCANEncoder;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;

import riolog.RioLogger;


/**
 * 
 */
public class PKSparkMaxPIDController
   implements IPIDController
{

   /** Our classes' logger **/
   @SuppressWarnings( "unused" )
   private static final Logger logger =
      RioLogger.getLogger( PKSparkMaxPIDController.class.getName() );

   //
   private final CANPIDController pid;
   //
   private final int slotID;

   private final CANSparkMax motor;
   private ControlType controlType;
   //
   private final PKCANEncoder encoder;

   // Spark Max has no access to this
   private double setPoint;


   public PKSparkMaxPIDController( CANSparkMax device, PKCANEncoder encoder )
   {
      this( device, encoder, 0 );
   }


   private PKSparkMaxPIDController( CANSparkMax device, PKCANEncoder encoder,
      int slotID )
   {
      pid = device.getPIDController();
      this.slotID = slotID;
      motor = device;
      controlType = ControlType.kVoltage;
      this.encoder = encoder;

      setP( 0 );
      setI( 0 );
      setD( 0 );
      setF( 0 );
   }


   @Override
   public void setP( double p )
   {
      pid.setP( p, slotID );
   }


   @Override
   public void setI( double i )
   {
      pid.setI( i, slotID );
   }


   @Override
   public void setD( double d )
   {
      pid.setD( d, slotID );
   }


   @Override
   public void setF( double f )
   {
      pid.setFF( f, slotID );
   }


   @Override
   public void setIAccum( double iAccum )
   {
      pid.setIAccum( iAccum );
   }


   @Override
   public void setOutputLimits( double min, double max )
   {
      pid.setOutputRange( min, max, slotID );
   }


   @Override
   public void goToPosition( double setPoint )
   {
      this.setPoint = setPoint;
      controlType = ControlType.kPosition;
      pid.setReference( setPoint, controlType, slotID );
   }


   public double getPosition()
   {
      return encoder.getPosition();
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
      return ( controlType == ControlType.kPosition );
   }


   public void disable()
   {
      controlType = ControlType.kVoltage;
      pid.setReference( 0, controlType, slotID );
   }


   @Override
   public double pidTarget()
   {
      return setPoint;
   }


   @Override
   public double pidError()
   {
      return setPoint - encoder.getPosition();
   }


   @Override
   public double pidOutput()
   {
      return motor.get();
   }

}
