//package declaration
package ch.nolix.system.client.base;

//Java imports
import java.lang.reflect.Method;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.reflectionhelper.GlobalMethodHelper;

//class
final class SessionHelper {
	
	//static method
	public static boolean isDataMethod(final Method method) {
		return (method != null && GlobalMethodHelper.methodHasAnnotation(method, DataMethod.class));
	}
	
	//static method
	public static boolean isRunMethod(final Method method) {		
		return (method != null && GlobalMethodHelper.methodHasAnnotation(method, RunMethod.class));
	}
	
	//static method
	public static void validateDataMethod(final Method dataMethod) {
		Validator
		.assertThat(dataMethod)
		.thatIsNamed("data method")
		.hasAnnotation(DataMethod.class)
		.hasParametersOfTypeOnly(String.class)
		.hasReturnType(Node.class);
	}
	
	//static method
	public static void validateRunMethod(final Method runMethod) {
		Validator
		.assertThat(runMethod)
		.thatIsNamed("run method")
		.hasAnnotation(RunMethod.class)
		.hasParametersOfTypeOnly(String.class)
		.doesNotReturnAnything();
	}
	
	//constructor
	private SessionHelper() {}
}
