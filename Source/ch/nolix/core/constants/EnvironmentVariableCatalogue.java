//package declaration
package ch.nolix.core.constants;

//class
/**
 * The {@link EnvironmentVariableCatalogue} provides environment variables for Microsoft Windows.
 * Of the {@link EnvironmentVariableCatalogue} no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 20
 */
public final class EnvironmentVariableCatalogue {
	
	//constant
	public static final String APP_DATA = "APPDATA";
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link EnvironmentVariableCatalogue} can be created.
	 */
	private EnvironmentVariableCatalogue() {}
}
