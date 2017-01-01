/*
 * file:	FunctionManager.java
 * author:	Silvan Wyss
 * month:	2016-12
 * lines:	10
 */

//package declaration
package ch.nolix.common.util;

//own imports
import ch.nolix.common.constants.StringManager;
import ch.nolix.common.functional.IElementTakerElementGetter;

//class
/**
 * This class provides some general functions.
 */
public final class FunctionManager {

	//function
	public final static IElementTakerElementGetter<Object, Object> IDENTITY
	= (o) -> {
		return o;
	};
	
	//function
	public final static IElementTakerElementGetter<Object, Object> NULL
	= (o) -> {
		return null;
	};
	
	//function
	public final static IElementTakerElementGetter<Object, String> TO_STRING
	= (o) -> {
		
		//Handles the case if the given object is null.
		if (o == null) {
			return StringManager.NULL_NAME;
		}
		
		//Handles the case if the given object is not null.
		return o.toString();
	};
}
