//package declaration
package ch.nolix.tech.resource;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.validator.Validator;
import ch.nolix.techapi.resourceapi.IResource;

//class
/**
 * A {@link Resource} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2017-09-27
 * @lines 160
 */
public final class Resource implements IResource {
	
	//attribute
	private final String name;
	
	//multi-attribute
	public final ReadContainer<IResource> baseResources;
	
	//constructor
	/**
	 * Creates a new {@link Resource} with the given name.
	 * 
	 * @param name
	 * @throws ArgumentIsNullException if the given name is null.
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
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws InvalidArgumentException if the given name
	 * equals the name of one of the given baseResources.
	 * @throws InvalidArgumentException if one of the given baseResources
	 * is a sub resource of another of the given base resources.
	 */
	public Resource(final String name, final IResource... baseResources) {
		
		//Calls other constructor.
		this(name, ReadContainer.forArray(baseResources));
	}
	
	//constructor
	/**
	 * Creates a new {@link Resource} with the given name and base resources.
	 * 
	 * @param name
	 * @param baseResources
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws InvalidArgumentException if the given name
	 * equals the name of one of the given baseResources.
	 * @throws InvalidArgumentException if one of the given base resources
	 * is a sub resource of another of the given baseResources.
	 */
	public Resource(final String name, final Iterable<IResource> baseResources) {
		
		Validator.assertThat(name).thatIsNamed(VariableNameCatalogue.NAME).isNotBlank();
		
		this.name = name;
		
		final var internalBaseResources = new LinkedList<IResource>();
		
		//Iterates the given baseResources.
		for (final var br : baseResources) {
			
			//Asserts that the given name does not equal the name of the current baseResource.
			if (name.equals(br.getName())) {
				throw new InvalidArgumentException(
					VariableNameCatalogue.NAME,
					name,					
					"equals the name of the given base resource"
				);
			}
			
			internalBaseResources.addAtEnd(br);
		}
		
		//Asserts that none of the given baseResources
		//is a sub Resource of another of the given baseResources.
		if (internalBaseResources.contains((sr1, sr2) -> sr1.isSubResourceOf(sr2))) {
			
			final var pair
			= internalBaseResources.getRefFirst((sr1, sr2) -> sr1.isSubResourceOf(sr2));
			
			throw new InvalidArgumentException(
				VariableNameCatalogue.RESOURCE,
				pair.getRefElement1(),
				"is a sub resource of " + pair.getRefElement2()
			);
		}
		
		this.baseResources = ReadContainer.forIterable(internalBaseResources);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getBaseResourceCount() {
		return getBaseResources().getElementCount();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return name;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReadContainer<IResource> getBaseResources() {
		return baseResources;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isDirectSubResourceOf(final IResource resource) {
		return getBaseResources().contains(resource);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSubResourceOf(final IResource resource) {
		
		//Handles the case that the current Resource is a direct sub Resource of the given resource.
		if (isDirectSubResourceOf(resource)) {
			return true;
		}
		
		//Handles the case that the current Resource is not a direct sub Resource of the given resource.
		return baseResources.contains(br -> br.isSubResourceOf(resource));
	}
}
