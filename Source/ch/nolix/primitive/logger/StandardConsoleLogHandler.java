//package declaration
package ch.nolix.primitive.logger;

//class
public final class StandardConsoleLogHandler extends LogHandler {

	//method
	public void log(final LogEntry logEntry) {
		switch (logEntry.getHarmLevel()) {
			case INFO:
			case WARNING:
				System.out.println(logEntry.getMessage());
				break;
			case ERROR:
			case FATAL_ERROR:
				System.err.println(logEntry.getMessage());
				break;
		}
	}
}
