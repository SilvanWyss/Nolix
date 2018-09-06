//package declaration
package ch.nolix.tech.resource;

//own imports
import ch.nolix.core.bases.NamedElement;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.primitive.invalidArgumentException.Argument;
import ch.nolix.primitive.invalidArgumentException.ArgumentName;
import ch.nolix.primitive.invalidArgumentException.ErrorPredicate;
import ch.nolix.primitive.invalidArgumentException.InvalidArgumentException;
import ch.nolix.techAPI.resourceAPI.IResource;
import ch.nolix.core.container.List;

//class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 160
 */
public final class Resource extends NamedElement implements IResource {
	
	//multi-attribute
	public final ReadContainer<IResource> baseResources;

	//constructor
	/**
	 * Creates a new {@link Resource} with the given name.
	 * 
	 * @param name
	 * @throws NullArgumentException if the given name is not an instance.
	 * @throws EmptyArgumentException if the given name is empty.
	 */
	public Resource(final String name) {
		
		//Calls constructor of the base class.
		super(name);
		
		//Sets the base resources of the current resource.
		baseResources = new ReadContainer<IResource>();
	}
	
	//constructor
	/**
	 * Creates a new {@link Resource} with the given name and base resources.
	 * 
	 * @param name
	 * @param baseResources
	 * @throws NullArgumentException if the given name is not an instance.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws InvalidArgumentException if the given name
	 * equals the name of one of the given base resources.
	 * @throws InvalidArgumentException if one of the given base resources
	 * is a sub resource of another of the given base resources.
	 */
	public Resource(final String name, final IResource... baseResources) {
		
		//Calls constructor of the base class.
		super(name);
		
		final var internalBaseResources = new List<IResource>();
		
		//Iterates the given base resources.
		for (final var br : baseResources) {
			
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
			
			final var pair
			= internalBaseResources.getRefFirst((sr1, sr2) -> sr1.isSubResourceOf(sr2));
			
			throw new InvalidArgumentException(
				new ArgumentName(VariableNameCatalogue.RESOURCE),
				new Argument(pair.getRefElement1()),
				new ErrorPredicate("is a sub resource of " + pair.getRefElement2())
			);
		}
		
		this.baseResources = new ReadContainer<IResource>(internalBaseResources);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final int getBaseResourceCount() {
		return getBaseResources().getElementCount();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final ReadContainer<IResource> getBaseResources() {
		return baseResources;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final boolean isBaseResourceOf(final IResource resource) {
		return resource.isSubResourceOf(this);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final boolean isDirectBaseResourceOf(final IResource resource) {
		return resource.isDirectSubResourceOf(this);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final boolean isDirectSubResourceOf(final IResource resource) {
		return getBaseResources().contains(resource);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final boolean isSubResourceOf(final IResource resource) {
		
		//Handles the case that the current resource is a direct sub resource of the given resource.
		if (isDirectSubResourceOf(resource)) {
			return true;
		}
		
		//Handles the case that the current resource is no direct sub resource of the given resource.
			//Iterates the base resources of the current resource.
			for (final var br : getBaseResources()) {
				
				//Checks if the current base resource is a sub resource of the given resource.
				if (br.isSubResourceOf(resource)) {
					return true;
				}
			}
		
		return false;
	}
}
