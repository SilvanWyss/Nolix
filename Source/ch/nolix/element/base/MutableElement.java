//package declaration
package ch.nolix.element.base;

//Java imports
import java.lang.reflect.Field;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.elementapi.IMutableElement;

//class
/**
 * @author Silvan Wyss
 * @date 2017-10-29
 * @lines 220
 * @param <ME> is the type of a {@link MutableElement}.
 */
public abstract class MutableElement<ME extends MutableElement<ME>> implements IMutableElement<ME> {
	
	//multi-attribute
	private LinkedList<Property> properties;
	
	//method
	/**
	 * Adds or changes the given attribute to the current {@link IMutableElement}.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	@Override
	public final void addOrChangeAttribute(final BaseNode attribute) {
		
		//Iterates the properties of the current MutableElement.
		for (final var p : getRefProperties()) {
			
			//Handles the case that the current Property has added or changed the given attribute.
			if (p.addedOrChangedAttribute(attribute)) {
				return;
			}
		}
		
		//Handles the case that the current Mutable cannot have the given attribute.
		throw new InvalidArgumentException(this, "cannot not have a " + attribute.getHeader());
	}
	
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
		
		return equals((MutableElement<?>)object);
	}
		
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void fillUpAttributesInto(final LinkedList<Node> list) {
		
		//Iterates the properties of the current MutableElement.
		for (final var p : getRefProperties()) {
			
			//Fills up the attribute info of the current Property into the given list.
			p.fillUpAttributesInto(list);
		}
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
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		return getSpecification().toString();
	}
	
	//method
	/**
	 * @param illegalAccessException
	 * @return a new {@link IllegalAccessError} for the given illegalAccessException.
	 */
	private IllegalAccessError createIllegalAccessErrorFor(final IllegalAccessException illegalAccessException) {
		
		final var message = illegalAccessException.getMessage();
		
		if (message == null || message.isBlank()) {
			throw new IllegalAccessError();
		}
		
		throw new IllegalAccessError(message);
	}
	
	//method
	/**
	 * @param mutableElement
	 * @return true if the current {@link MutableElement} equals the given mutableElement.
	 */
	private boolean equals(final MutableElement<?> mutableElement) {
		return getSpecification().equals(mutableElement.getSpecification());
	}
	
	//method
	/**
	 * Lets the current {@link MutableElement} extract the {@link Property} from the given field if
	 * the given field stores a {@link Property}.
	 * 
	 * @param field
	 */
	private void extractPotentialPropertyFrom(final Field field) {
		
		//Handles the case that the given field is a Property.
		if (Property.class.isAssignableFrom(field.getType())) {
			extractPropertyFrom(field);
		}
	}
	
	//method
	/**
	 * Extracts the properties of the current {@link MutableElement}.
	 */
	private void extractProperties() {
		
		properties = new LinkedList<>();
		
		//Iterates the classes of the current MutableElement.
		Class<?> lClass = getClass();
		while (lClass != null) {
			extractPropertiesFrom(lClass);
			lClass = lClass.getSuperclass();
		}
	}
	
	//method
	/**
	 * Extracts the {@link Property}s of the {@link MutableElement} that are from the given pClass.
	 * 
	 * @param pClass
	 */
	private void extractPropertiesFrom(final Class<?> pClass) {
		
		//Iterates the fields of the given pClass.
		for (final var f : pClass.getDeclaredFields()) {
			extractPotentialPropertyFrom(f);
		}
	}
	
	//method
	/**
	 * Extracts the {@link Property}s of the current {@link MutableElement} if they are not extracted yet.
	 */
	private void extractPropertiesIfNotExtracted() {
		if (!hasExtractedProperties()) {
			extractProperties();
		}
	}
	
	//method
	/**
	 * Extracts the {@link Property} of the {@link MutableElement} that is from the given field.
	 * 
	 * @param field
	 * @throws IllegalAccessError if the given field is not accessible.
	 */
	private void extractPropertyFrom(final Field field) {
		try {
			
			field.setAccessible(true);
			
			final var property = (Property)(field.get(this));
			
			//Asserts that the corresponding Property is not null.
			Validator.assertThat(property).isOfType(MutableValue.class);
			
			properties.addAtEnd(property);
		} catch (final IllegalAccessException illegalAccessException) {
			throw createIllegalAccessErrorFor(illegalAccessException);	
		}
	}
	
	//method
	/**
	 * @return the {@link Property}s of the current {@link MutableElement}.
	 */
	private IContainer<Property> getRefProperties() {
		
		extractPropertiesIfNotExtracted();
		
		return properties;
	}
	
	//method
	/**
	 * @return true if the {@link Property}s of the current {@link MutableElement} are extracted.
	 */
	private boolean hasExtractedProperties() {
		return (properties != null);
	}
}
