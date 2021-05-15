//package declaration
package ch.nolix.businessapi.resourceapi;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.common.container.IContainer;

//interface
/**
 * Of a {@link IResource}, a certain amount can be possessed.
 * A {@link IResource} has a determined name.
 * A {@link IResource} can have base resources.
 * A {@link IResource} can serve as any of its base resources.
 * A {@link IResource} is not mutable. 
 * 
 * @author Silvan Wyss
 * @date 2018-08-25
 * @lines 60
 */
public interface IResource extends Named {
	
	//method declaration
	/**
	 * @return the number of base resources of the current {@link IResource}.
	 */
	int getBaseResourceCount();
	
	//method declaration
	/**
	 * @return the base resources of the current {@link IResource}.
	 */
	IContainer<IResource> getBaseResources();
	
	//method
	/**
	 * @param resource
	 * @return true if the current {@link IResource} is a base resource of the given resource.
	 */
	default boolean isBaseResourceOf(final IResource resource) {
		return resource.isSubResourceOf(this);
	}
	
	//method
	/**
	 * @param resource
	 * @return true if the current {@link IResource} is a direct base resource of the given resource.
	 */
	default boolean isDirectBaseResourceOf(final IResource resource) {
		return resource.isDirectSubResourceOf(this);
	}
	
	//method declaration
	/**
	 * @param resource
	 * @return true if the current {@link IResource} is a direct sub resource of the given resource.
	 */
	boolean isDirectSubResourceOf(final IResource resource);
	
	//method declaration
	/**
	 * @param resource
	 * @return true if the current {@link IResource} is a sub resource of the given resource.
	 */
	boolean isSubResourceOf(final IResource resource);
}
