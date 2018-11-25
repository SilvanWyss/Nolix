//package declaration
package ch.nolix.techAPI.resourceAPI;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.skillAPI.Named;

//interface
/**
 * Of a {@link IResource}, a certain amount can be possessed.
 * A {@link IResource} has a determined name.
 * A {@link IResource} can have base resources.
 * A {@link IResource} can serve as any of its base resources.
 * A {@link IResource} is not mutable. 
 * 
 * @author Silvan Wyss
 * @month 2018-08
 * @lines 60
 */
public interface IResource extends Named {
		
	//abstract method
	/**
	 * @return the number of base resources of the current {@link IResource}.
	 */
	public abstract int getBaseResourceCount();
	
	//abstract method
	/**
	 * @return the base resources of the current {@link IResource}.
	 */
	public abstract IContainer<IResource> getBaseResources();
	
	//abstract method
	/**
	 * @param resource
	 * @return true if the current {@link IResource} is a base resource of the given resource.
	 */
	public abstract boolean isBaseResourceOf(final IResource resource);
	
	//abstract method
	/**
	 * @param resource
	 * @return true if the current {@link IResource} is a direct base resource of the given resource.
	 */
	public abstract boolean isDirectBaseResourceOf(final IResource resource);
	
	//abstract method
	/**
	 * @param resource
	 * @return true if the current {@link IResource} is a direct sub resource of the given resource.
	 */
	public abstract boolean isDirectSubResourceOf(final IResource resource);
	
	//abstract method
	/**
	 * @param resource
	 * @return true if the current {@link IResource} is a sub resource of the given resource.
	 */
	public abstract boolean isSubResourceOf(final IResource resource);
}
