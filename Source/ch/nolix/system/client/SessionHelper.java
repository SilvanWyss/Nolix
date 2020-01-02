//package declaration
package ch.nolix.system.client;

//Java import
import java.lang.reflect.Method;

//own imports
import ch.nolix.common.node.Node;
import ch.nolix.common.reflectionHelpers.MethodHelper;
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
		.suppose(dataMethod)
		.thatIsNamed("data method")
		.hasAnnotation(DataMethod.class)
		.andHasParametersOfTypeOnly(String.class)
		.andHasReturnType(Node.class);
	}
	
	//static method
	public static void validateRunMethod(final Method runMethod) {
		Validator
		.suppose(runMethod)
		.thatIsNamed("run method")
		.hasAnnotation(RunMethod.class)
		.andHasParametersOfTypeOnly(String.class)
		.andDoesNotHaveReturnType();
	}
	
	//access-reducing constructor
	private SessionHelper() {}
}
