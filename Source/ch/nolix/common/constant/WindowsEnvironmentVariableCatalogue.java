//package declaration
package ch.nolix.common.constant;

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
	
	//constructor
	/**
	 * Prevents that an instance of the {@link WindowsEnvironmentVariableCatalogue} can be created.
	 */
	private WindowsEnvironmentVariableCatalogue() {}
}
