//package declaration
package ch.nolix.techAPI.resourceAPI;

//own imports
import ch.nolix.common.attributeAPI.Named;
import ch.nolix.common.containers.IContainer;

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
	
	//method declaration
	/**
	 * @return the number of base resources of the current {@link IResource}.
	 */
	public abstract int getBaseResourceCount();
	
	//method declaration
	/**
	 * @return the base resources of the current {@link IResource}.
	 */
	public abstract IContainer<IResource> getBaseResources();
	
	//method
	/**
	 * @param resource
	 * @return true if the current {@link IResource} is a base resource of the given resource.
	 */
	public default boolean isBaseResourceOf(final IResource resource) {
		return resource.isSubResourceOf(this);
	}
	
	//method
	/**
	 * @param resource
	 * @return true if the current {@link IResource} is a direct base resource of the given resource.
	 */
	public default boolean isDirectBaseResourceOf(final IResource resource) {
		return resource.isDirectSubResourceOf(this);
	}
	
	//method declaration
	/**
	 * @param resource
	 * @return true if the current {@link IResource} is a direct sub resource of the given resource.
	 */
	public abstract boolean isDirectSubResourceOf(final IResource resource);
	
	//method declaration
	/**
	 * @param resource
	 * @return true if the current {@link IResource} is a sub resource of the given resource.
	 */
	public abstract boolean isSubResourceOf(final IResource resource);
}
