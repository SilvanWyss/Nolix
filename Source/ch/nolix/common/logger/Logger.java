//package declaration
package ch.nolix.common.logger;

//own import
import ch.nolix.common.independentContainers.List;

//class
public final class Logger {
	
	//static attributes
	private static boolean active = true;
	private static LogWorker logWorker;
	
	//static attribute
	private static final List<LogHandler> logHandlers =
	new List<>(new StandardConsoleLogHandler());

	//static method
	public static void addLogHandler(final LogHandler logHandler) {
		logHandlers.addAtEnd(logHandler);
	}
	
	//static method
	public static synchronized void disable() {
		if (active) {
			
			active = false;
			
			if (logWorker != null) {
				logWorker.stop_();
				logWorker = null;
			}
		}
	}
	
	//static method
	public static synchronized void enable() {
		if (!active) {
			active = true;
		}
	}
	
	//static method
	public static boolean isActive() {
		return active;
	}
	
	//static method
	public static void logError(final Throwable error) {
		if (active) {
			
			if (logWorker == null) {
				logWorker = new LogWorker();
			}
			
			if (error.getMessage() == null || error.getMessage().isBlank()) {
				logWorker.takeLogEntry(new LogEntry(HarmLevel.ERROR, "A " + error.getClass().getName() + " occured."));
			}
			else {
				logWorker.takeLogEntry(new LogEntry(HarmLevel.ERROR, error.getMessage()));
			}
		}
	}
	
	//static method
	public static void logError(final String error) {
		if (active) {
			
			if (logWorker == null) {
				logWorker = new LogWorker();
			}
			
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
			
			if (logWorker == null) {
				logWorker = new LogWorker();
			}
			
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
			
			if (logWorker == null) {
				logWorker = new LogWorker();
			}
			
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
			
			if (logWorker == null) {
				logWorker = new LogWorker();
			}
			
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
			
			if (logWorker == null) {
				logWorker = new LogWorker();
			}
			
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
			
			if (logWorker == null) {
				logWorker = new LogWorker();
			}
			
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
			
			if (logWorker == null) {
				logWorker = new LogWorker();
			}
			
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
			
			if (logWorker == null) {
				logWorker = new LogWorker();
			}
			
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
	
	//static method
	static synchronized void removeLogWorker() {
		logWorker = null;
	}
	
	//access-reducing constructor
	private Logger() {}
}
