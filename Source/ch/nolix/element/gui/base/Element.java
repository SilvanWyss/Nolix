//package declaration
package ch.nolix.element.gui.base;

//own imports
import ch.nolix.element.elementapi.IElement;

//class
/**
 * @author Silvan Wyss
 * @date 2021-06-06
 * @lines 50
 * @param <E> is the type of a {@link Element}.
 */
public abstract class Element<E extends Element<E>> implements IElement<E> {
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean equals(final Object object) {
		
		if (object == null) {
			return false;
		}		
		
		if (getClass() != object.getClass()) {
			return false;
		}
		
		return equals((Element<?>)object);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {
		return getSpecification().hashCode();
	}
	
	//method
	/**
	 * @param element
	 * @return true if the current {@link Element} equals the given element.
	 */
	private boolean equals(final Element<?> element) {
		return getSpecification().equals(element.getSpecification());
	}
}
