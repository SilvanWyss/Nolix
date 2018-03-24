//package declaration
package ch.nolix.primitive.logger;

//own import
import ch.nolix.primitive.container.List;

//package-visible class
final class LogWorker extends Thread {
	
	//attribute
	private boolean active = true;
	
	//multiple attribute
	private final List<LogEntry> logEntries = new List<LogEntry>();
	
	//constructor
	public LogWorker() {
		start();
	}
	
	//method
	public void run() {
		
		boolean idle = false;
		long startTimeOfLastIdleInMilliseconds = -1;
		
		while (active) {
			if (containsLogEntries()) {
				idle = false;
				Logger.takeLogEntry(getAndRemoveNextLogEntry());
			}
			else {
				
				if (!idle) {
					idle = true;
					startTimeOfLastIdleInMilliseconds = System.currentTimeMillis();
				}
				
				if (System.currentTimeMillis() > startTimeOfLastIdleInMilliseconds + 1000) {
					active = false;
				}
			}
		}
		
		Logger.removeLogWorker();
	}
	
	//method
	public synchronized boolean containsLogEntries() {
		return !logEntries.isEmpty();
	}
	
	//method
	public synchronized void stop_() {
		active = false;
	}
	
	//method
	public synchronized void takeLogEntry(final LogEntry logEntry) {
		logEntries.addAtBegin(logEntry);
	}
	
	//method
	private synchronized LogEntry getAndRemoveNextLogEntry() {
		final LogEntry logEntry = logEntries.getRefFirst();
		logEntries.removeFirst();
		return logEntry;
	}
}
