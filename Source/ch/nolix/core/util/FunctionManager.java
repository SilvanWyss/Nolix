/*
 * file:	FunctionManager.java
 * author:	Silvan Wyss
 * month:	2016-12
 * lines:	10
 */

//package declaration
package ch.nolix.core.util;

//own imports
import ch.nolix.core.constants.StringManager;
import ch.nolix.core.functional.IElementTakerElementGetter;

//class
/**
 * This class provides some general functions.
 */
public final class FunctionManager {

	//function
	public static final IElementTakerElementGetter<Object, Object> IDENTITY
	= (o) -> {
		return o;
	};
	
	//function
	public static final IElementTakerElementGetter<Object, Object> NULL
	= (o) -> {
		return null;
	};
	
	//function
	public static final IElementTakerElementGetter<Object, String> TO_STRING
	= (o) -> {
		
		//Handles the case if the given object is null.
		if (o == null) {
			return StringManager.NULL_NAME;
		}
		
		//Handles the case if the given object is not null.
		return o.toString();
	};
}
