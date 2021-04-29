//package declaration
package ch.nolix.common.testing.basetest;

//Java import
import java.lang.reflect.Method;

//own import
import ch.nolix.common.independent.independentcontainer.List;

//class
final class StackTraceElementFinder {
	
	//method
	public List<StackTraceElement> getStackTraceElementsOfRunningMethod(final Method method) {
		
		final var stackTraceElements = new List<StackTraceElement>();
		
		for (var st : Thread.getAllStackTraces().values()) {
			for (var ste : st) {
				if (ste.getMethodName().equals(method.getName())) {
					stackTraceElements.addAtEnd(ste);
				}
			}
		}
		
		return stackTraceElements;
	}
}
