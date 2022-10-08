//package declaration
package ch.nolix.system.element.mutableelement;

//Java imports
import java.lang.reflect.Field;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.main.Element;
import ch.nolix.systemapi.elementapi.mainuniversalapi.IMutableElement;

//class
/**
 * @author Silvan Wyss
 * @date 2017-10-29
 * @param <ME> is the type of a {@link MutableElement}.
 */
public abstract class MutableElement<ME extends MutableElement<ME>> extends Element implements IMutableElement {
	
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
	public final void addOrChangeAttribute(final INode<?> attribute) {
		
		//Iterates the properties of the current MutableElement.
		for (final var p : getRefProperties()) {
			
			//Handles the case that the current Property has added or changed the given attribute.
			if (p.addedOrChangedAttribute(attribute)) {
				return;
			}
		}
		
		//Handles the case that the current Mutable cannot have the given attribute.
		throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "cannot not have a " + attribute.getHeader());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void addOrChangeAttribute(final String... attributes) {
		
		//Iterates the given attributes.
		for (final var a : attributes) {
			addOrChangeAttribute(Node.fromString(a));
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final IContainer<INode<?>> getAttributes() {
		
		final var attributes = new LinkedList<INode<?>>();
		
		//Iterates the properties of the current MutableElement.
		for (final var p : getRefProperties()) {
			
			//Fills up the attributes of the current Property.
			p.fillUpAttributesInto(attributes);
		}
		
		return attributes;
	}
	
	//method
	/**
	 * Resets the current {@link MutableElement} from the file with the given filePath.
	 * 
	 * @param filePath
	 * @throws InvalidArgumentException if the given filePath is not valid.
	 */
	public final void resetFromFileWithFilePath(final String filePath) {
		resetFromSpecification(Node.fromFile(filePath));
	}
	
	//method
	/**
	 * @return the current {@link MutableElement} as concrete {@link MutableElement}.
	 */
	@SuppressWarnings("unchecked")
	protected final ME asConcrete() {
		return (ME)this;
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
			GlobalValidator.assertThat(property).isOfType(MutableValue.class);
			
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
