//package declaration
package ch.nolix.core.time;

//class
/**
 * Of the {@link TimeUnitCatalogue} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2017-02-05
 */
public final class TimeUnitCatalogue {
	
	//constant
	public static final int NANOSECONDS_PER_MICROSECOND = 1000;
	
	//constant
	public static final int NANOSECONDS_PER_MILLISECOND = 1_000_000;
	
	//constant
	public static final int NANOSECONDS_PER_SECOND = 1_000_000_000;
	
	//constant
	public static final long NANOSECONDS_PER_MINUTE = 60_000_000_000L;
	
	//constant
	public static final long NANOSECONDS_PER_HOUR = 3_600_000_000_000L;
	
	//constant
	public static final long NANOSECONDS_PER_DAY = 86_400_000_000_000L;
	
	//constant
	public static final int MICROSECONDS_PER_MILLISECOND = 1000;
	
	//constant
	public static final int MICROSECONDS_PER_SECOND = 1_000_000;
	
	//constant
	public static final int MICROSECONDS_PER_MINUTE = 60_000_000;
	
	//constant
	public static final long MICROSECONDS_PER_HOUR = 3_600_000_000L;
	
	//constant
	public static final long MICROSECONDS_PER_DAY = 86_400_000_000L;
	
	//constant
	public static final int MILLISECONDS_PER_SECOND = 1000;
	
	//constant
	public static final int MILLISECONDS_PER_MINUTE = 60000;
	
	//constant
	public static final int MILLISECONDS_PER_HOUR = 3_600_000;
	
	//constant
	public static final int MILLISECONDS_PER_DAY = 86_400_000;
	
	//constant
	public static final int SECONDS_PER_MINUTE = 60;
	
	//constant
	public static final int SECONDS_PER_HOUR = 3600;
	
	//constant
	public static final int SECONDS_PER_DAY = 86400;
	
	//constant
	public static final int MINUTES_PER_HOUR = 60;
	
	//constant
	public static final int MINUTES_PER_DAY = 1440;
	
	//constant
	public static final int HOURS_PER_DAY = 24;
	
	//constructor
	/**
	 * Prevents that an instance of the {@link TimeUnitCatalogue} can be created.
	 */
	private TimeUnitCatalogue() {}
}
