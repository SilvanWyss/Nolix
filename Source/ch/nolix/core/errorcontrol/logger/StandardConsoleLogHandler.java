//package declaration
package ch.nolix.core.errorcontrol.logger;

//class
public final class StandardConsoleLogHandler extends LogHandler {
	
	//method
	@Override
	protected void log(final LogEntry logEntry) {
		
		System.out.println(logEntry.toString());
		
		for (final var ail : logEntry.getAdditionalInfoLines()) {
			System.out.println("  "+ ail);
		}
	}
}
