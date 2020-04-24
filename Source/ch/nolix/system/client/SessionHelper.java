//package declaration
package ch.nolix.system.client;

//Java import
import java.lang.reflect.Method;

//own imports
import ch.nolix.common.node.Node;
import ch.nolix.common.reflectionHelper.MethodHelper;
import ch.nolix.common.validator.Validator;

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
	
	//visibility-reducing constructor
	private SessionHelper() {}
}
