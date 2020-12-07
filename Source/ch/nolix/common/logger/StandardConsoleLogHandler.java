//package declaration
package ch.nolix.common.logger;

//class
public final class StandardConsoleLogHandler extends LogHandler {
	
	//method
	@Override
	protected void log(final LogEntry logEntry) {
		switch (logEntry.getHarmLevel()) {
			case INFO:
			case WARNING:
				System.out.println(logEntry.toString());
				break;
			case ERROR:
			case FATAL_ERROR:
				System.err.println(logEntry.toString());
				break;
		}
	}
}
