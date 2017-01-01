/*
 * file:	MethodHelper.java
 * author:	Silvan Wyss
 * month:	28.02.2016
 * lines:	40
 */

//package declaration
package ch.nolix.common.helper;

//Java imports
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

//class
/**
 * This class provides some methods to handle methods.
 */
public class MethodHelper {

	//static method
	/**
	 * @param method
	 * @return true if all parameters of the given method are strings
	 */
	public final static boolean allParametersOfMethodAreStrings(Method method) {
		
		for (Parameter p: method.getParameters()) {
			if (!p.getType().getSimpleName().equals(String.class.getSimpleName())) {
				return false;
			}
		}
		
		return true;
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private MethodHelper() {}
}
