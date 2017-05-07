//package declaration
package ch.nolix.common.zetaValidator;

//own import
import ch.nolix.common.invalidArgumentException.NullArgumentException;

//abstract package-visible class
/**
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 40
 * @param <E> - The type of the elements of a element container mediator.
 */
abstract class ElementContainerMediator<E> {

	//attribute
	private final Iterable<E> arguments;
	
	//constructor
	/**
	 * Creates new element container mediator with the given arguments.
	 * 
	 * @param arguments
	 * @throws NullArgumentException if the given arguments is null.
	 */
	public ElementContainerMediator(final Iterable<E> arguments) {
		
		//Checks if the given arguments is not null.
		if (arguments == null) {
			throw new NullArgumentException("arguments");
		}
		
		this.arguments = arguments;
	}
	
	//method
	/**
	 * @return the arguments of hits element container mediator.
	 */
	protected final Iterable<E> getRefArguments() {
		return arguments;
	}
}
