//package declaration
package ch.nolix.core.factory;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.functionAPI.IElementTakerElementGetter;
import ch.nolix.primitive.invalidStateException.InvalidStateException;

//class
/**
 * @author Silvan Wyss
 * @month 2018-06
 * @lines 90
 * @param <I> The type of the inputs of a {@link Factory}.
 * @param <O> The type of the instances a {@link Factory} can create.
 */
public final class Factory<I, O> {
	
	//multi-attribute
	private final List<InstanceCreator<I, O>> instanceCreators =
	new List<InstanceCreator<I, O>>();

	//method
	/**
	 * Adds a new instance creator to the current {@link Factory},
	 * that can create instances of the given type and has the given instance creator.
	 * 
	 * @param type
	 * @param instanceCreator
	 * @return the current {@link Factory}.
	 * @throws InvalidStateException if the current {@link Factory}
	 * can already create instances of the given type.
	 * @throws NullArgumentException if the given instance type is not an instance.
	 * @throws EmptyArgumentException if the given instance type is empty.
	 * @throws NullArgumentException if the given instance creator function is not an instance.
	 */
	public Factory<I, O> addInstanceCreator(
		final String type,
		final IElementTakerElementGetter<I, O> instanceCreator
	) {
		
		//Checks if the current factory can already instances of the given type.
		if (canCreateInstanceOf(type)) {
			throw
			new InvalidStateException(
				this,
				"can already create a '"
				+ type
				+ "'"
			);
		}
		
		instanceCreators.addAtEnd(
			new InstanceCreator<>(type, instanceCreator)
		);
		
		return this;
	}
	
	//method
	/**
	 * @param type
	 * @return true if the current {@link Factory} can create instances of the given type.
	 */
	public boolean canCreateInstanceOf(final String type) {
		return instanceCreators.contains(ic -> ic.canCreateInstanceOf(type));
	}
	
	//method
	/**
	 * @param type
	 * @param input
	 * @return a new instance from the current {@link Factory}
	 * that is of the given type and from the given input.
	 * @throws InvalidStateException if the current {@link Factory}
	 * cannot create instances of the given type.
	 */
	public O createInstance(final String type, final I input) {
		
		//Extracts the required instance creator.
		final var instanceCreator =
		instanceCreators
		.getRefFirstOrNull(ic -> ic.canCreateInstanceOf(type));
		
		//Checks if the current factory can create instances of the given type.
		if (instanceCreator == null) {
			throw
			new InvalidStateException(
				this,
				"cannot create a '"
				+ type
				+ "'"
			);
		}
		
		return instanceCreator.createInstance(input);
	}
}
