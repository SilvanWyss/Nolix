//package declaration
package ch.nolix.core.testoid;

//Java imports
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//package-visible class
final class TestCaseRunner extends Thread {
	
	//attributes
	private final Testoid parentTest;
	private boolean finished = false;
	private long startTime = System.currentTimeMillis();
	private long runTimeInMilliseconds = 0;
	private final Method method;
	
	//optional attribute
	private Throwable fatalError;
	
	//constructor
	public TestCaseRunner(final Testoid parentTest, final Method method) {
		
		this.parentTest = parentTest;
		this.method = method;
		
		start();
	}
	
	//method
	public Throwable getFatalError() {
		
		if (fatalError == null) {
			throw new RuntimeException("The current TestCaseRunner does not have a fatal error.");
		}
		
		return fatalError;
	}
	
	//method
	public long getRuntimeInMilliseconds() {
		
		if (!finished) {
			runTimeInMilliseconds = System.currentTimeMillis() - startTime;
		}
		
		return runTimeInMilliseconds;
	}
	
	//method
	public boolean hasFatalError() {
		return (fatalError != null);
	}
	
	//method
	public boolean isFinished() {
		return finished;
	}
	
	//method
	@Override
	public void run() {
		try {
			method.invoke(parentTest, (Object[])new Class[0]);
		}
		catch (final IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
			fatalError = exception;
		}
		finally {
			finished = true;
		}
	}
	
	public void stop2() {
		interrupt();
		finished = true;
		fatalError = new RuntimeException("Reached timeout");
	}
}
