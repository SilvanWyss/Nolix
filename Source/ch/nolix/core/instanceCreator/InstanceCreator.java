//package declaration
package ch.nolix.core.instanceCreator;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.functionAPI.IElementTakerElementGetter;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;

//class
/**
 * @author Silvan Wyss
 * @month 2018-06
 * @lines 90
 * @param <I> The type of the inputs of a {@link InstanceCreator}.
 * @param <O> The type of the instances a {@link InstanceCreator} can create.
 */
public final class InstanceCreator<I, O> {
	
	//multi-attribute
	private final List<SingleTypeInstanceCreator<I, O>> singleTypeInstanceCreators = new List<>();

	//method
	/**
	 * Adds a new instance creator to the current {@link InstanceCreator},
	 * that can create instances of the given type and has the given instance creator.
	 * 
	 * @param type
	 * @param instanceCreator
	 * @return the current {@link InstanceCreator}.
	 * @throws InvalidArgumentException if the current {@link InstanceCreator}
	 * can already create instances of the given type.
	 * @throws NullArgumentException if the given instance type is null.
	 * @throws EmptyArgumentException if the given instance type is empty.
	 * @throws NullArgumentException if the given instance creator function is null.
	 */
	public InstanceCreator<I, O> addInstanceCreator(
		final String type,
		final IElementTakerElementGetter<I, O> instanceCreator
	) {
		
		//Checks if the current factory can already instances of the given type.
		if (canCreateInstanceOf(type)) {
			throw
			new InvalidArgumentException(
				this,
				"can already create a '"
				+ type
				+ "'"
			);
		}
		
		singleTypeInstanceCreators.addAtEnd(
			new SingleTypeInstanceCreator<>(type, instanceCreator)
		);
		
		return this;
	}
	
	//method
	/**
	 * @param type
	 * @return true if the current {@link InstanceCreator} can create instances of the given type.
	 */
	public boolean canCreateInstanceOf(final String type) {
		return singleTypeInstanceCreators.contains(ic -> ic.canCreateInstanceOf(type));
	}
	
	//method
	/**
	 * @param type
	 * @param input
	 * @return a new instance from the current {@link InstanceCreator}
	 * that is of the given type and from the given input.
	 * @throws InvalidArgumentException if the current {@link InstanceCreator}
	 * cannot create instances of the given type.
	 */
	public O createInstance(final String type, final I input) {
		
		//Extracts the required instance creator.
		final var instanceCreator =
		singleTypeInstanceCreators
		.getRefFirstOrNull(ic -> ic.canCreateInstanceOf(type));
		
		//Checks if the current factory can create instances of the given type.
		if (instanceCreator == null) {
			throw
			new InvalidArgumentException(
				this,
				"cannot create a '"
				+ type
				+ "'"
			);
		}
		
		return instanceCreator.createInstance(input);
	}
}
