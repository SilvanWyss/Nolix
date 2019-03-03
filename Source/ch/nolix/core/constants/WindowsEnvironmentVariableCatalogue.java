//package declaration
package ch.nolix.core.constants;

//own import
import ch.nolix.core.invalidArgumentException.UninstantiableClassException;

//class
/**
 * The {@link WindowsEnvironmentVariableCatalogue}
 * provides environment variables for Microsoft Windows.
 * 
 * Of the {@link WindowsEnvironmentVariableCatalogue} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 30
 */
public final class WindowsEnvironmentVariableCatalogue {
	
	//constant
	public static final String APP_DATA = "APPDATA";
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link WindowsEnvironmentVariableCatalogue} can be created.
	 * 
	 * @throws UninstantiableClassException
	 */
	private WindowsEnvironmentVariableCatalogue() {
		throw new UninstantiableClassException(WindowsEnvironmentVariableCatalogue.class);
	}
}
