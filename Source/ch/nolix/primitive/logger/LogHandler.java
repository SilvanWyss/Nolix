//package declaration
package ch.nolix.primitive.logger;

//abstract class
public abstract class LogHandler {

	//default value
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
			throw new RuntimeException("The given min harm level is not an instance.");
		}
		
		this.minHarmLevel = minHarmLevel;
	}
	
	//method
	public HarmLevel getMinHarmLevel() {
		return minHarmLevel;
	}
	
	//abstract method
	public abstract void log(LogEntry logEntry);
	
	//package-visible method
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
