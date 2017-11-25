//package declaration
package ch.nolix.tech.resource;

//own imports
import ch.nolix.core.bases.NamedElement;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.AccessorContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.container.Pair;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.ErrorPredicate;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;

//class
/**
 * Of a resource, a certain amount can be possessed.
 * A resource has a determined name.
 * A resource can have base resources.
 * A resource can serve as any of its base resources.
 * A resource is not mutable. 
 * 
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 170
 */
public final class Resource extends NamedElement {
	
	//multiple attribute
	//The base resources are the resources this resource can serve as.
	public final AccessorContainer<Resource> baseResources;

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
		
		//Sets the base resources of this recource.
		baseResources = new AccessorContainer<Resource>();
	}
	
	//constructor
	/**
	 * Creates new resource with the given name and base resources.
	 * 
	 * @param name
	 * @param baseResources
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws InvalidArgumentException if the given name
	 * equals the name of one of the given base resources.
	 * @throws InvalidArgumentException if one of the given base resources
	 * is a sub resource of another of the given base resources.
	 */
	public Resource(final String name, final Resource... baseResources) {
		
		//Calls constructor of the base class.
		super(name);
		
		final List<Resource> internalBaseResources = new List<Resource>();
		
		//Iterates the given base resources.
		for (final Resource br : baseResources) {
			
			//Checks if the given name does not equal the name of the current base resource.
			if (hasSameNameAs(br)) {
				throw new InvalidArgumentException(
					new ArgumentName(VariableNameCatalogue.NAME),
					new Argument(name),
					new ErrorPredicate(
						"equals the name of the given base resource " + br.getName()
					)
				);
			}
			
			internalBaseResources.addAtEnd(br);
		}
		
		//Checks if none of the given base resources
		//is a sub resource of another of the given base resources.
		if (internalBaseResources.contains((sr1, sr2) -> sr1.isSubResourceOf(sr2))) {
			
			final Pair<Resource, Resource> pair
			= internalBaseResources.getRefFirst((sr1, sr2) -> sr1.isSubResourceOf(sr2));
			
			throw new InvalidArgumentException(
				new ArgumentName(VariableNameCatalogue.RESOURCE),
				new Argument(pair.getRefElement1()),
				new ErrorPredicate("is a sub resource of " + pair.getRefElement2())
			);
		}
		
		this.baseResources = new AccessorContainer<Resource>(internalBaseResources);
	}
	
	//method
	/**
	 * @return the number of base resources of this resource.
	 */
	public final int getBaseResourceCount() {
		return getBaseResources().getElementCount();
	}
	
	//method
	/**
	 * @return the base resources of this resource.
	 */
	public final AccessorContainer<Resource> getBaseResources() {
		return baseResources;
	}
	
	//method
	/**
	 * @param resource
	 * @return true if this resource is a base resource of the given resource.
	 */
	public final boolean isBaseResourceOf(final Resource resource) {
		return resource.isSubResourceOf(this);
	}
	
	//method
	/**
	 * @param resource
	 * @return true if this resource is a direct base resource of the given resource.
	 */
	public final boolean isDirectBaseResourceOf(final Resource resource) {
		return resource.isDirectSubResourceOf(this);
	}
	
	//method
	/**
	 * @param resource
	 * @return true if this resource is a direct sub resource of the given resource.
	 */
	public final boolean isDirectSubResourceOf(final Resource resource) {
		return getBaseResources().contains(resource);
	}
	
	//method
	/**
	 * @param resource
	 * @return true if this resource is a sub resource of the given resource.
	 */
	public final boolean isSubResourceOf(final Resource resource) {
		
		//Handles the case that this resource is a direct sub resource of the given resource.
		if (isDirectSubResourceOf(resource)) {
			return true;
		}
		
		//Handles the case that this resource is no direct sub resource of the given resource.
		
			//Iterates the base resources of this resource.
			for (final Resource br : getBaseResources()) {
				
				//Checks if the current base resource is a sub resource of the given resource.
				if (br.isSubResourceOf(resource)) {
					return true;
				}
			}
		
		return false;
	}
}
