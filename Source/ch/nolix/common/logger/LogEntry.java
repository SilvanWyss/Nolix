//package declaration
package ch.nolix.common.logger;

//class
public final class LogEntry {

	//attribute
	private final String message;
	private final HarmLevel harmLevel;
	private final long creationTimeInMilliseconds;
	
	//constructor
	public LogEntry(final HarmLevel harmLevel, final String message) {
		
		creationTimeInMilliseconds = System.currentTimeMillis();
		
		if (harmLevel == null) {
			this.harmLevel = HarmLevel.ERROR;
		}
		else {
			this.harmLevel = harmLevel;
		}
		
		if (message == null) {
			this.message = "Error.";
		}
		else {
			this.message = message;
		}
	}
	
	//method
	public long getCreationTimeInMilliseconds() {
		return creationTimeInMilliseconds;
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
