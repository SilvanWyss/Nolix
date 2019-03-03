//package declaration
package ch.nolix.core.constants;

//own import
import ch.nolix.core.invalidArgumentException.UninstantiableClassException;

//class
/**
 * Of the {@link MultiVariableNameCatalogue} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 30
 */
public final class MultiVariableNameCatalogue {
	
	//constants
	public static final String ELEMENTS = "elements";
	public static final String ENTITIES = "entities";
	public static final String INPUT_VALUES = "input values";
	public static final String OUTPUT_VALUES = "output values";
	public static final String START_VALUES = "start values";
	public static final String TABS = "tabs";
	public static final String VALUES = "values";
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link MultiVariableNameCatalogue} can be created.
	 * 
	 * @throws UninstantiableClassException
	 */
	private MultiVariableNameCatalogue() {
		throw new UninstantiableClassException(MultiVariableNameCatalogue.class);
	}
}
