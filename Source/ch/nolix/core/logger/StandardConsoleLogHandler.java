//package declaration
package ch.nolix.core.logger;

//class
public final class StandardConsoleLogHandler extends LogHandler {

	//method
	@Override
	public void log(final LogEntry logEntry) {
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
