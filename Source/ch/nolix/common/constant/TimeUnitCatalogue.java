//package declaration
package ch.nolix.common.constant;

//class
/**
 * Of the {@link TimeUnitCatalogue} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 50
 */
public final class TimeUnitCatalogue {
	
	//constants for microseconds
	public static final int NANOSECONDS_PER_MICROSECOND = 1000;
	
	//constants for milliseconds
	public static final int MICROSECONDS_PER_MILLISECOND = 1000;
	public static final int NANOSECONDS_PER_MILLISECOND = 1_000_000;
	
	//constants for seconds
	public static final int MILLISECONDS_PER_SECOND = 1000;
	public static final int MICROSECONDS_PER_SECOND = 1_000_000;
	public static final int NANOSECONDS_PER_SECOND = 1_000_000_000;
	
	//constants for minutes
	public static final int SECONDS_PER_MINUTE = 60;
	public static final int MILLISECONDS_PER_MINUTE = 60000;
	public static final int MICROSECONDS_PER_MINUTE = 60_000_000;
	public static final long NANOSECONDS_PER_MINUTE = 60_000_000_000L;
	
	//constants for hours
	public static final int MINUTES_PER_HOUR = 60;
	public static final int SECONDS_PER_HOUR = 3600;
	public static final int MILLISECONDS_PER_HOUR = 3_600_000;
	public static final long MICROSECONDS_PER_HOUR = 3_600_000_000L;
	public static final long NANOSECONDS_PER_HOUR = 3_600_000_000_000L;
	
	//constants for days
	public static final int HOURS_PER_DAY = 24;
	public static final int MINUTES_PER_DAY = 1440;
	public static final int SECONDS_PER_DAY = 86400;
	public static final int MILLISECONDS_PER_DAY = 86_400_000;
	public static final long MICROSECONDS_PER_DAY = 86_400_000_000L;
	public static final long NANOSECONDS_PER_DAY = 86_400_000_000_000L;
	
	//constructor
	/**
	 * Avoids that an instance of the {@link TimeUnitCatalogue} can be created.
	 */
	private TimeUnitCatalogue() {}
}
