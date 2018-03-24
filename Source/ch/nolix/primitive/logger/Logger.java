//package declaration
package ch.nolix.primitive.logger;

//own import
import ch.nolix.primitive.container.List;

//class
public final class Logger {
	
	//static attributes
	private static boolean active = false;
	private static LogWorker logWorker;
	
	//static attribute
	private static final List<LogHandler> logHandlers =
	new List<LogHandler>(new StandardConsoleLogHandler());

	//static method
	public static void addLogHandler(final LogHandler logHandler) {
		logHandlers.addAtEnd(logHandler);
	}
	
	//static method
	public static synchronized void disable() {
		if (active) {
			active = false;
			logWorker.stop_();
			logWorker = null;
		}
	}
	
	//static method
	public static synchronized void enable() {
		if (!active) {
			active = true;
			logWorker = new LogWorker();
		}
	}
	
	//static method
	public static boolean isActive() {
		return active;
	}
	
	//static method
	public static void logError(final String error) {
		if (active) {
			logWorker.takeLogEntry(
				new LogEntry(
					HarmLevel.ERROR,
					error
				)
			);
		}
	}
	
	//static method
	public static void logError(
		final String valueName,
		final double value,
		final String errorPredicate
	) {
		if (active) {
			logWorker.takeLogEntry(
				new LogEntry(
					HarmLevel.ERROR,
					"The " + valueName + " " + value + " " + errorPredicate + "."
				)
			);
		}
	}
	
	//static method
	public static void logError(
		final String valueName,
		final long value,
		final String errorPredicate
	) {
		if (active) {
			logWorker.takeLogEntry(
				new LogEntry(
					HarmLevel.ERROR,
					"The " + valueName + " " + value + " " + errorPredicate + "."
				)
			);
		}
	}
	
	//static method
	public static void logFatalError(final String fatalError) {
		if (active) {
			logWorker.takeLogEntry(
				new LogEntry(
					HarmLevel.FATAL_ERROR,
					fatalError
				)
			);
		}
	}
	
	//static method
	public static void logInfo(final String info) {
		if (active) {
			logWorker.takeLogEntry(
				new LogEntry(
					HarmLevel.INFO,
					info
				)
			);
		}
	}
	
	//static method
	public static void logInfo(final String valueName, final double value) {
		if (active) {
			logWorker.takeLogEntry(
				new LogEntry(
					HarmLevel.INFO,
					valueName + " " + value
				)
			);
		}
	}
	
	//static method
	public static void logInfo(final String valueName, final long value) {
		if (active) {
			logWorker.takeLogEntry(
				new LogEntry(
					HarmLevel.INFO,
					valueName + " " + value
				)
			);
		}
	}
		
	//static method
	public static void logWarning(final String warning) {
		if (active) {
			logWorker.takeLogEntry(
				new LogEntry(
					HarmLevel.WARNING,
					warning
				)
			);
		}
	}
	
	//static method
	static void takeLogEntry(final LogEntry logEntry) {
		for (final LogHandler lh : logHandlers) {		
			lh.takeLogEntry(logEntry);
		}
	}
	
	//private constructor
	private Logger() {}
}
