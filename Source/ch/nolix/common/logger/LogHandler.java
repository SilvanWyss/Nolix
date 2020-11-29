//package declaration
package ch.nolix.common.logger;

//class
public abstract class LogHandler {

	//constant
	public static final HarmLevel DEFAULT_MIN_HARM_LEVEL = HarmLevel.INFO;
	
	//optional attribute
	private final HarmLevel minHarmLevel;
	
	//constructor
	public LogHandler() {
		this(DEFAULT_MIN_HARM_LEVEL);
	}
	
	//constructor
	public LogHandler(final HarmLevel minHarmLevel) {
		
		if (minHarmLevel == null) {
			throw new IllegalArgumentException("The given min harm level is null.");
		}
		
		this.minHarmLevel = minHarmLevel;
	}
	
	//method
	public HarmLevel getMinHarmLevel() {
		return minHarmLevel;
	}
	
	//method declaration
	public abstract void log(LogEntry logEntry);
	
	//method
	final void takeLogEntry(final LogEntry logEntry) {
		switch (getMinHarmLevel()) {
			case INFO:
				logSafely(logEntry);
				break;
			case WARNING:
				switch (logEntry.getHarmLevel()) {
					case INFO:
						break;
					default:
						logSafely(logEntry);
				}
				break;
			case ERROR:
				switch (logEntry.getHarmLevel()) {
					case INFO:
					case WARNING:
						break;
					default:
						logSafely(logEntry);
				}
				break;
			case FATAL_ERROR:
				switch (logEntry.getHarmLevel()) {
					case INFO:
					case WARNING:
					case ERROR:
						break;
					default:
						logSafely(logEntry);
				}
		}
	}
	
	//method
	private void logSafely(final LogEntry logEntry) {
		try {
			log(logEntry);
		}
		catch (final Exception exception) {}
	}
}
