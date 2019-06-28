//package declaration
package ch.nolix.core.constants;

//own imports
import ch.nolix.core.functionAPI.IElementTakerBooleanGetter;
import ch.nolix.core.functionAPI.IElementTakerDoubleGetter;
import ch.nolix.core.functionAPI.IElementTakerElementGetter;
import ch.nolix.core.functionAPI.IElementTakerLongGetter;
import ch.nolix.core.functionAPI.IFunction;
import ch.nolix.core.functionAPI.I2ElementTakerBooleanGetter;

//class
/**
 * Of the {@link FunctionCatalogue} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 120
 */
public final class FunctionCatalogue {
	
	//function
	/**
	 * This function does not do anything.
	 */
	public static final IFunction EMPTY =
	() -> {};
	
	//function
	/**
	 * This function returns true if 2 given objects are equal.
	 */
	public static final I2ElementTakerBooleanGetter<Object> EQUALITY =
	(o1, o2) -> o1 != null ? o1.equals(o2) : o2 == null;
	
	//function
	/**
	 * This function returns false for a given object.
	 */
	public static final IElementTakerBooleanGetter<Object> FALSE =
	o -> false;
	
	//function
	/**
	 * This function returns the hash code of a given object.
	 */
	public static final IElementTakerLongGetter<Object> HASH_CODE =
	o -> o.hashCode();
	
	//function
	/**
	 * This function returns true if 2 given objects are the same.
	 */
	public static final I2ElementTakerBooleanGetter<Object> IDENTITY =
	(o1, o2) -> o1 == o2;
	
	//function
	/**
	 * This function returns null for a given object.
	 */
	public static final IElementTakerElementGetter<Object, Object> NULL =
	o -> null;
	
	//function
	/**
	 * This function returns 1.0 for a given object.
	 */
	public static final IElementTakerDoubleGetter<Object> ONE =
	o -> 1.0;
	
	//function
	/**
	 * This function returns a given object.
	 */
	public static final IElementTakerElementGetter<Object, Object> SELF =
	o -> o;
	
	//function
	/**
	 * This function returns the String representation of a given object.
	 */
	public static final IElementTakerElementGetter<Object, String> TO_STRING =
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
	public static final IElementTakerBooleanGetter<Object> TRUE =
	o -> true;
	
	//function
	/**
	 * This function returns the type of a given object.
	 */
	public static final IElementTakerElementGetter<Class<?>, Object> TYPE =
	o -> o.getClass();
	
	//function
	/**
	 * This function returns 0.0 for a given object.
	 */
	public static final IElementTakerDoubleGetter<Object> ZERO =
	o -> 0.0;
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link FunctionCatalogue} can be created.
	 */
	private FunctionCatalogue() {}
}
