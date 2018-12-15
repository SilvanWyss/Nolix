//package declaration
package ch.nolix.system.resource;

//own imports
import ch.nolix.core.bases.NamedElement;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.systemAPI.resourceAPI.IResource;
import ch.nolix.core.container.List;

//class
/**
 * A {@link Resource} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 150
 */
public final class Resource extends NamedElement implements IResource {
	
	//multi-attribute
	public final ReadContainer<IResource> baseResources;
	
	//constructor
	/**
	 * Creates a new {@link Resource} with the given name.
	 * 
	 * @param name
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 */
	public Resource(final String name) {
		
		//Calls other constructor.
		this(name, new ReadContainer<IResource>());
	}
	
	//constructor
	/**
	 * Creates a new {@link Resource} with the given name and base resources.
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
	public Resource(final String name, final IResource... baseResources) {
		
		//Calls other constructor.
		this(name, new ReadContainer<IResource>(baseResources));
	}
	
	//constructor
	/**
	 * Creates a new {@link Resource} with the given name and base resources.
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
	public Resource(final String name, final Iterable<IResource> baseResources) {
		
		//Calls constructor of the base class.
		super(name);
		
		final var internalBaseResources = new List<IResource>();
		
		//Iterates the given base resources.
		for (final var br : baseResources) {
			
			//Checks if the given name does not equal the name of the current base resource.
			if (name.equals(br.getName())) {
				throw new InvalidArgumentException(
					VariableNameCatalogue.NAME,
					name,					
					"equals the name of the given base resource"
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
				VariableNameCatalogue.RESOURCE,
				pair.getRefElement1(),
				"is a sub resource of " + pair.getRefElement2()
			);
		}
		
		this.baseResources = new ReadContainer<IResource>(internalBaseResources);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getBaseResourceCount() {
		return getBaseResources().getSize();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final ReadContainer<IResource> getBaseResources() {
		return baseResources;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isDirectSubResourceOf(final IResource resource) {
		return getBaseResources().contains(resource);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isSubResourceOf(final IResource resource) {
		
		//Handles the case that the current resource is a direct sub resource of the given resource.
		if (isDirectSubResourceOf(resource)) {
			return true;
		}
		
		//Handles the case that the current resource is no direct sub resource of the given resource.
		return baseResources.contains(br -> br.isSubResourceOf(resource));
	}
}
