//package declaration
package ch.nolix.core.errorcontrol.logger;

import ch.nolix.core.independent.container.List;

//class
public final class GlobalLogger {
	
	//static attributes
	private static boolean active = true;
	private static LogWorker logWorker;
	
	//static multi-attribute
	private static final List<LogHandler> logHandlers = new List<>();
	
	//static initialization
	static {
		logHandlers.addAtBegin(new StandardConsoleLogHandler());
		logHandlers.addAtBegin(new FileLogHandler());
	}
	
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
			
			synchronized (GlobalLogger.class) {
				if (logWorker == null) {
					logWorker = new LogWorker();
				}
			}
			
			if (error.getMessage() == null || error.getMessage().isBlank()) {
				logWorker.takeLogEntry(
					LogEntry.withMessageAndHarmLevel("A " + error.getClass().getName() + " occured.", HarmLevel.ERROR)
				);
			} else {
				logWorker.takeLogEntry(
					LogEntry.withMessageAndHarmLevel(error.getMessage(), HarmLevel.ERROR)
				);
			}
		}
	}
	
	//static method
	public static void logError(final String error) {
		if (active) {
			
			synchronized (GlobalLogger.class) {
				if (logWorker == null) {
					logWorker = new LogWorker();
				}
			}
			
			logWorker.takeLogEntry(
				LogEntry.withMessageAndHarmLevel(
					error,
					HarmLevel.ERROR
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
			
			synchronized (GlobalLogger.class) {
				if (logWorker == null) {
					logWorker = new LogWorker();
				}
			}
			
			logWorker.takeLogEntry(
				LogEntry.withMessageAndHarmLevel(
					"The " + valueName + " " + value + " " + errorPredicate + ".",
					HarmLevel.ERROR
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
			
			synchronized (GlobalLogger.class) {
				if (logWorker == null) {
					logWorker = new LogWorker();
				}
			}
			
			logWorker.takeLogEntry(
				LogEntry.withMessageAndHarmLevel(
					"The " + valueName + " " + value + " " + errorPredicate + ".",
					HarmLevel.ERROR
				)
			);
		}
	}
	
	//static method
	public static void logFatalError(final String fatalError) {
		if (active) {
			
			synchronized (GlobalLogger.class) {
				if (logWorker == null) {
					logWorker = new LogWorker();
				}
			}
			
			logWorker.takeLogEntry(
				LogEntry.withMessageAndHarmLevel(
					fatalError,
					HarmLevel.FATAL_ERROR
				)
			);
		}
	}
	
	//static method
	public static void logInfo(final String info) {
		if (active) {
			
			synchronized (GlobalLogger.class) {
				if (logWorker == null) {
					logWorker = new LogWorker();
				}
			}
			
			logWorker.takeLogEntry(
				LogEntry.withMessageAndHarmLevel(
					info,
					HarmLevel.INFO
				)
			);
		}
	}
	
	//static method
	public static void logInfo(final String valueName, final double value) {
		if (active) {
			
			synchronized (GlobalLogger.class) {
				if (logWorker == null) {
					logWorker = new LogWorker();
				}
			}
			
			logWorker.takeLogEntry(
				LogEntry.withMessageAndHarmLevel(
					valueName + " " + value,
					HarmLevel.INFO
				)
			);
		}
	}
	
	//static method
	public static void logInfo(final String valueName, final long value) {
		if (active) {
			
			synchronized (GlobalLogger.class) {
				if (logWorker == null) {
					logWorker = new LogWorker();
				}
			}
			
			logWorker.takeLogEntry(
				LogEntry.withMessageAndHarmLevel(
					valueName + " " + value,
					HarmLevel.INFO
				)
			);
		}
	}
	
	//static method
	public static void logWarning(final String warning) {
		if (active) {
			
			synchronized (GlobalLogger.class) {
				if (logWorker == null) {
					logWorker = new LogWorker();
				}
			}
			
			logWorker.takeLogEntry(
				LogEntry.withMessageAndHarmLevel(
					warning,
					HarmLevel.WARNING
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
	
	//constructor
	private GlobalLogger() {}
}
