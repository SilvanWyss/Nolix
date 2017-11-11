//package declaration
package ch.nolix.element.GUIoid;

//own imports
import ch.nolix.core.interfaces.Clearable;
import ch.nolix.core.interfaces.Closable;
import ch.nolix.element.configurationElement.ConfigurationElement;

//abstract class
/**
 * A GUI can have a configuration.
 * A GUI is configurable.
 * A GUI is clearable.
 * A GUI is closable.
 * 
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 30
 * @param <G> The type of a GUI.
 */
public abstract class GUIoid<G extends GUIoid<G>>
extends ConfigurationElement<G>
implements Clearable<G>, Closable {
	
	//method
	/**
	 * @return true if this GUIoid has the given role.
	 */
	public final boolean hasRole(final String role) {
		return false;
	}
}
