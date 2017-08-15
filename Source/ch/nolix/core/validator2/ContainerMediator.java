//package declaration
package ch.nolix.core.validator2;

//own import
import ch.nolix.core.invalidArgumentException.EmptyArgumentException;

//class
/**
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 10
 */
public final class ContainerMediator extends ArgumentMediator<Iterable<?>> {

	//package-visible constructor
	/**
	 * Creates new container mediator with the given argument.
	 * 
	 * @param argument
	 */
	ContainerMediator(final Iterable<?> argument) {
		
		//Calls constructor of the base class.
		super(argument);
	}
	
	//package-visible constructor
	/**
	 * Creates new container mediator with the given argument.
	 * 
	 * @param argument
	 * @throws NullArgumentException if the given argument is null.
	 */
	ContainerMediator(final Object[] argument) {
		
		//Calls constructor of the base class.
		super(ArrayHelper.createIterable(argument));
	}

	public ContainerMediator(double[] argument) {
		super(ArrayHelper.createIterable(argument));
	}

	//method
	/**
	 * @throws NullArgumentException if the argument of this container mediator is null.
	 * @throws EmptyArgumentException if the argument of this container mediator is empty.
	 */
	public void isNotEmpty() {
		
		//Checks if the argument of this container mediator is not null.
		isNotNull();
		
		//Checks if the argument of this container mediator is not empty.
		if (!getRefArgument().iterator().hasNext()) {
			throw new EmptyArgumentException();
		}
	}
	
	public NamedContainerMediator thatIsNamed(final String name) {
		return new NamedContainerMediator(name, getRefArgument());
	}
}
