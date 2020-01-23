/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;


import org.slf4j.Logger;

import frc.robot.commands.PKCommand;
import frc.robot.subsystems.DriveFactory;
import riolog.RioLogger;


public class DriveBrakeOn
  extends PKCommand
{
  /*
   * Don't extend frc.robot.commands.drive.DriveBaseCommand to avoid requiring
   * the drive, so interrupt conflicts don't happen (this command doesn't
   * actually move the subsystem per se)
   */

  /* Our classes logger */
  private static final Logger logger =
    RioLogger.getLogger( DriveBrakeOn.class.getName() );


  public DriveBrakeOn()
  {
    logger.info( "constructing {}", getName() );
  }


  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute()
  {
    logExecuteStart( logger );

    DriveFactory.getInstance().setBrake( true );
  }


  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished()
  {
    return true;
  }

}
