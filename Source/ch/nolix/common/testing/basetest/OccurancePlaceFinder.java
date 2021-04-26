//package declaration
package ch.nolix.common.testing.basetest;

//own import
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;

//class
final class OccurancePlaceFinder {
	
	//method
	public <T extends BaseTest> OccurancePlace findOccurancePlaceOfStackTraceInTestClass(
		final StackTraceElement[] stackTrace,
		final Class<T> testClass
	) {
		
		Class<?> lTestClass = testClass;
		while (lTestClass != null) {
			
			for (final var ste : stackTrace) {
				if (ste.getClassName().equals(lTestClass.getName())) {
					return new OccurancePlace(ste.getClassName(), ste.getLineNumber());
				}
			}
													
			lTestClass = lTestClass.getSuperclass();
		}
				
		throw new InvalidArgumentException("The given stack trace does not occur in the given test class.");
	}
	
	//method
	public OccurancePlace findOccurancePlaceOfStackTraceInTest(
		final StackTraceElement[] stackTrace,
		final BaseTest test
	) {
		return findOccurancePlaceOfStackTraceInTestClass(stackTrace, test.getClass());
	}
	
	//method
	public OccurancePlace findOccurancePlaceOfThrowableInTest(final Throwable throwable, final BaseTest test) {
		return findOccurancePlaceOfThrowableInTestClass(throwable, test.getClass());
	}
	
	//method
	public <T extends BaseTest> OccurancePlace findOccurancePlaceOfThrowableInTestClass(
		final Throwable throwable,
		final Class<T> testClass
	) {
		return findOccurancePlaceOfStackTraceInTestClass(throwable.getStackTrace(), testClass);
	}
}
