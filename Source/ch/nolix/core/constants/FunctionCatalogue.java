//package declaration
package ch.nolix.core.constants;

//own imports
import ch.nolix.core.functionAPI.IElementTakerBooleanGetter;
import ch.nolix.core.functionAPI.IElementTakerDoubleGetter;
import ch.nolix.core.functionAPI.IElementTakerElementGetter;
import ch.nolix.core.functionAPI.IFunction;

//class
/**
 * The {@link FunctionCatalogue} provides functions.
 * Of the {@link FunctionCatalogue} no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 80
 */
public final class FunctionCatalogue {
	
	//function
	/**
	 * This function does not do anything.
	 */
	public static final IFunction EMPTY_FUNCTION =
	() -> {};
	
	//function
	/**
	 * This function returns false for a given object.
	 */
	public static final IElementTakerBooleanGetter<Object> FALSE_FUNCTION =
	o -> false;
	
	//function
	/**
	 * This function returns a given object.
	 */
	public static final IElementTakerElementGetter<Object, Object> IDENTITY_FUNCTION =
	o -> o;
	
	//function
	/**
	 * This function returns null for a given object.
	 */
	public static final IElementTakerElementGetter<Object, Object> NULL_FUNCTION =
	o -> null;
	
	//function
	/**
	 * This function returns a string that represents the given object.
	 */
	public static final IElementTakerElementGetter<Object, String> TO_STRING_FUNCTION =
	o -> {
		
		//Handles the case that the given object is null.
		if (o == null) {
			return StringCatalogue.NULL_NAME;
		}
		
		//Handles the case that the given object is not null.
		return o.toString();
	};
	
	//function
	/**
	 * This function returns true for a given object.
	 */
	public static final IElementTakerBooleanGetter<Object> TRUE_FUNCTION =
	o -> true;
	
	//function
	/**
	 * This function returns 0.0 for a given input object.
	 */
	public static final IElementTakerDoubleGetter<Object> ZERO_FUNCTION =
	o -> 0.0;
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link FunctionCatalogue} can be created.
	 */
	private FunctionCatalogue() {}
}
