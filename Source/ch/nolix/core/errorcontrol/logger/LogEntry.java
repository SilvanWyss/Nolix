//package declaration
package ch.nolix.core.errorcontrol.logger;

//class
public final class LogEntry {
	
	//static method
	public static LogEntry withHarmLevelAndMessage(final HarmLevel harmLevel, final String message) {
		return new LogEntry(harmLevel, message);
	}
	
	//attribute
	private final String message;
	
	//attribute
	private final HarmLevel harmLevel;
	
	//attribute
	private final long creationTimeInMillisecondsSince1970;
	
	//constructor
	private LogEntry(final HarmLevel harmLevel, final String message) {
		
		creationTimeInMillisecondsSince1970 = System.currentTimeMillis();
		
		if (harmLevel == null) {
			this.harmLevel = HarmLevel.ERROR;
		} else {
			this.harmLevel = harmLevel;
		}
		
		if (message == null) {
			this.message = "Error.";
		} else {
			this.message = message;
		}
	}
	
	//method
	public long getCreationTimeInMillisecondsSince1970() {
		return creationTimeInMillisecondsSince1970;
	}
	
	//method
	public HarmLevel getHarmLevel() {
		return harmLevel;
	}
	
	//method
	public String getMessage() {
		return message;
	}
	
	//method
	@Override
	public String toString() {
		return (getHarmLevel() + ": " + getMessage());
	}
}
