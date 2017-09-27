//package declaration
package ch.nolix.tech.resource;

//own imports
import ch.nolix.core.bases.NamedElement;
import ch.nolix.core.container.AccessorContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.ErrorPredicate;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;

//class
/**
 * An resource is a piece of something that has a value.
 * Of an resource, a certain amount can be possessed.
 * An resource has a determined name.
 * An resource can have super resources.
 * An resource serves as any of its super resources.
 * An resource is not mutable. 
 * 
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 150
 */
public class Resource extends NamedElement {
	
	//multiple attribute
	//The super resources are the resources this resource can serve as.
	public final AccessorContainer<Resource> superResources;

	//constructor
	/**
	 * Creates new resource with the given name.
	 * 
	 * @param name
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 */
	public Resource(final String name) {
		
		//Calls constructor of the base class.
		super(name);
		
		superResources = new AccessorContainer<Resource>();
	}
	
	//constructor
	/**
	 * Creates new resource with the given name and resources.
	 * 
	 * @param name
	 * @param superAssets
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws InvalidArgumentException if the given name
	 * equals the name of one of the given super resources.
	 */
	public Resource(final String name, final Resource... superAssets) {
		
		//Calls constructor of the base class.
		super(name);
		
		final List<Resource> internalSuperResources = new List<Resource>();
		
		//Iterates the given resources.
		for (final Resource sa : superAssets) {
			
			//Checks if the given name equals the name of the current resource.
			if (hasSameNameAs(sa)) {
				throw new InvalidArgumentException(
					new ArgumentName("name"),
					new Argument(name),
					new ErrorPredicate(
						"equals the name of the given super resource " + sa.getName()
					)
				);
			}
			
			internalSuperResources.addAtEnd(sa);
		}
		
		this.superResources = new AccessorContainer<Resource>(internalSuperResources);
	}
	
	//method
	/**
	 * @return the number of super resources of this resource.
	 */
	public final int getSuperResourceCount() {
		return getSuperResources().getElementCount();
	}
	
	//method
	/**
	 * @return the super resources of this resource.
	 */
	public final AccessorContainer<Resource> getSuperResources() {
		return superResources;
	}
	
	//method
	/**
	 * @param resource
	 * @return true if this resource is a direct sub resource of the given resource.
	 */
	public final boolean isDirectSubResourceOf(final Resource resource) {
		return getSuperResources().contains(resource);
	}
	
	//method
	/**
	 * @param resource
	 * @return true if this resource is a direct super resource of the given resource.
	 */
	public final boolean isDirectSuperResourceOf(final Resource resource) {
		return resource.isDirectSubResourceOf(this);
	}
	
	//method
	/**
	 * @param resource
	 * @return true if this resource is a sub resource of the given resource.
	 */
	public final boolean isSubResourceOf(final Resource resource) {
		
		//Handles the case if this resource is a direct sub resource of the given resource.
		if (isDirectSubResourceOf(resource)) {
			return true;
		}
		
		//Handles the case if this resource is no direct sub resource of the given resource.
			//Iterates the super resources of this resource.
			for (final Resource sa : getSuperResources()) {
				
				//Checks if the current super resource is a sub resource of the given resource.
				if (sa.isSubResourceOf(resource)) {
					return true;
				}
			}
		
		return false;
	}
	
	//method
	/**
	 * @param resource
	 * @return true if this resource is a super resource of the given resource.
	 */
	public final boolean isSuperResourceOf(final Resource resource) {
		return resource.isSubResourceOf(this);
	}
}
