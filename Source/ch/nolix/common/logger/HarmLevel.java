//package declaration
package ch.nolix.common.logger;

//enum
public enum HarmLevel {
	INFO,
	WARNING,
	ERROR,
	FATAL_ERROR;
	
	//method
	public final boolean isHigherThan(final HarmLevel harmLevel) {
		
		switch (this) {
			case INFO:
				return false;
			case WARNING:
				return (harmLevel == INFO);
			case ERROR:
				return (harmLevel == INFO || harmLevel == WARNING);
			case FATAL_ERROR:
				return (harmLevel == INFO || harmLevel == WARNING || harmLevel == ERROR);
		}
		
		return false;
	}
	
	//method
	public final boolean isLowerThan(final HarmLevel harmLevel) {
		return harmLevel.isHigherThan(this);
	}
}
