//package declaration
package ch.nolix.system.client.base;

//Java import
import java.lang.reflect.Method;

import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.reflectionhelper.MethodHelper;

//class
final class SessionHelper {
	
	//static method
	public static boolean isDataMethod(final Method method) {
		return (method != null && MethodHelper.methodHasAnnotation(method, DataMethod.class));
	}
	
	//static method
	public static boolean isRunMethod(final Method method) {		
		return (method != null && MethodHelper.methodHasAnnotation(method, RunMethod.class));
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
	
	//visibility-reduced constructor
	private SessionHelper() {}
}
