//package declaration
package ch.nolix.core.errorcontrol.logger;

//class
public final class StandardConsoleLogHandler extends LogHandler {
	
	//method
	@Override
	protected void log(final LogEntry logEntry) {
		switch (logEntry.getHarmLevel()) {
			case INFO, WARNING:
				System.out.println(logEntry.toString());
				break;
			case ERROR, FATAL_ERROR:
				System.err.println(logEntry.toString());
				break;
		}
	}
}
