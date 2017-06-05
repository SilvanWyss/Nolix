//package declaration
package ch.nolix.core.constants;

//class
/**
 * Of this class no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 40
 */
public final class TimeUnitManager {
	
	//constants for milliseconds
	public static final int MICROSECONDS_PER_MILLISECOND = 1000;
	
	//constants for seconds
	public static final int MILLISECONDS_PER_SECOND = 1000;
	public static final int MICROSECONDS_PER_SECOND = 1000000;
	
	//constants for minutes
	public static final int SECONDS_PER_MINUTE = 60;
	public static final int MILLISECONDS_PER_MINUTE = 60000;
	public static final int MICROSECONDS_PER_MINUTE = 60000000;
	
	//constants for hours
	public static final int MINUTES_PER_HOUR = 60;
	public static final int SECONDS_PER_HOUR = 3600;
	public static final int MILLISECONDS_PER_HOUR = 3600000;
	public static final long MICROSECONDS_PER_HOUR = 3600000000l;
	
	//constants for days
	public static final int HOURS_PER_DAY = 24;
	public static final int MINUTES_PER_DAY = 1440;
	public static final int SECONDS_PER_DAY = 86400;
	public static final int MILLISECONDS_PER_DAY = 86400000;
	public static final long MICROSECONDS_PER_DAY = 86400000000l;
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private TimeUnitManager() {}
}
