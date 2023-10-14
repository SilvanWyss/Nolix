//package declaration
package ch.nolix.core.errorcontrol.logger;

//class
public abstract class LogHandler {
	
	//constant
	public static final HarmLevel DEFAULT_MIN_HARM_LEVEL = HarmLevel.INFO;
	
	//optional attribute
	private final HarmLevel minHarmLevel;
	
	//constructor
	protected LogHandler() {
		this(DEFAULT_MIN_HARM_LEVEL);
	}
	
	//constructor
	protected LogHandler(final HarmLevel minHarmLevel) {
		
		if (minHarmLevel == null) {
			throw new IllegalArgumentException("The given min harm level is null.");
		}
		
		this.minHarmLevel = minHarmLevel;
	}
	
	//method
	public final HarmLevel getMinHarmLevel() {
		return minHarmLevel;
	}
	
	//method
	public final boolean wouldLog(final LogEntry logEntry) {
		return (logEntry != null && !logEntry.getHarmLevel().isLowerThan(getMinHarmLevel()));
	}
	
	//method declaration
	protected abstract void log(final LogEntry logEntry);
	
	//method
	final void takeLogEntry(final LogEntry logEntry) {
		if (wouldLog(logEntry)) {
			logSafely(logEntry);
		}
	}
	
	//method
	private void logSafely(final LogEntry logEntry) {
		try {
			log(logEntry);
		} catch (final Throwable error) {
			System.err.println("An error occured by writing a log entry."); //NOSONAR: This is a logger.
		}
	}
}
