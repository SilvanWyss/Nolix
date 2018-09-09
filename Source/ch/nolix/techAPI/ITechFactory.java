//package declaration
package ch.nolix.techAPI;

//own import
import ch.nolix.techAPI.resourceAPI.IResource;

//interface
/**
 * @author Silvan Wyss
 * @month 2018-08
 * @lines 20
 */
public interface ITechFactory {
	
	//abstract method
	/**
	 * @param name
	 * @return a new {@link IResource} with the given name.
	 */
	public abstract IResource createResource(final String name);
	
	/**
	 * @param name
	 * @param baseResources
	 * @return a new {@link IResource} with the given name and base resources.
	 */
	public abstract IResource createResource(final String name, final IResource... baseResources);
}
