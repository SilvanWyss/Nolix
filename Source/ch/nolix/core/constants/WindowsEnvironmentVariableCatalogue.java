//package declaration
package ch.nolix.core.constants;

//class
/**
 * The {@link WindowsEnvironmentVariableCatalogue}
 * provides environment variables for Microsoft Windows.
 * 
 * Of the {@link WindowsEnvironmentVariableCatalogue} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 20
 */
public final class WindowsEnvironmentVariableCatalogue {
	
	//constant
	public static final String APP_DATA = "APPDATA";
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link WindowsEnvironmentVariableCatalogue} can be created.
	 */
	private WindowsEnvironmentVariableCatalogue() {}
}
