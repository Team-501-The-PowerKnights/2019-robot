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


import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;

import riolog.RioLogger;


public class PropertiesManager
{

	/* Our classes logger */
	private static final Logger logger =
		RioLogger.getLogger( PropertiesManager.class.getName() );

	/* Default fully qualified file name */
	public static final String defaultFileName = "/home/lvuser/501robot.props";

	/* (Subsystem) prefix to prepend on lookups */
	String searchPrefix;
	/* Loaded properties from file */
	Properties props;

	/* List and set to store properties for telemetry */
	private Set< String > propsNames;
	private List< String > propsList;


	public PropertiesManager( String prefix )
	{
		this( defaultFileName, prefix );
	}


	public PropertiesManager( String fileName, String prefix )
	{
		searchPrefix = prefix;
		logger.debug( "file: {}, prefix: {}", fileName, prefix );

		propsList = new ArrayList< String >();

		try
		{
			FileReader fr = new FileReader( fileName );
			BufferedReader br = new BufferedReader( fr );
			// ps = new PrintStream(fileName);

			props = new Properties();
			props.load( br );
		}
		catch ( IOException ex )
		{
			logger.error( "Can't load properties from file: {} because {}",
				fileName, ex.getMessage() );
			// FIXME - exception stack trace to log file
			ex.printStackTrace();
		}
	}


	public double getDouble( String key )
	{
		double retValue =
			Double.parseDouble( props.getProperty( searchPrefix + "." + key ) );
		return retValue;
	}


	public int getInt( String key )
	{
		int retValue =
			Integer.parseInt( props.getProperty( searchPrefix + "." + key ) );
		return retValue;
	}


	public long getLong( String key )
	{
		long retValue =
			Long.parseLong( props.getProperty( searchPrefix + "." + key ) );
		return retValue;
	}


	public boolean getBoolean( String key )
	{
		boolean retValue =
			Boolean.parseBoolean( props.getProperty( searchPrefix + "." + key ) );
		return retValue;
	}


	public String getString( String key )
	{
		String retValue = props.getProperty( searchPrefix + "." + key );
		return retValue;
	}


	public void listProperties()
	{
		propsNames = props.stringPropertyNames();

		propsList.clear();

		for ( String s : propsNames )
		{
			propsList.add( s );
		}

		Collections.sort( propsList );

		for ( String s : propsList )
		{
			logger.info( s + " = " + props.getProperty( s ) );
		}

	}

}
