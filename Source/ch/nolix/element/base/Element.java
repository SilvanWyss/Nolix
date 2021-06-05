//package declaration
package ch.nolix.element.base;

//Java imports
import java.lang.reflect.Field;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.functionapi.IBooleanGetter;
import ch.nolix.common.functionapi.IElementGetter;
import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.common.functionapi.IElementTakerElementGetter;
import ch.nolix.element.elementapi.IElement;

//class
/**
 * @author Silvan Wyss
 * @date 2017-10-29
 * @lines 310
 * @param <E> is the type of a {@link Element}.
 */
public abstract class Element<E extends Element<E>> implements IElement<E> {
	
	//attribute
	private boolean extractedProperties;
	
	//multi-attribute
	private final LinkedList<Property> properties = new LinkedList<>();
	
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
		
		final var entity = (Element<?>)object;
		return getSpecification().equals(entity.getSpecification());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		
		//Iterates the properties of the current Element.
		for (final var p : getRefProperties()) {
			
			//Fills up the attributes of the current Property into the given list.
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
	 * Adds or changes the given attribute to the current {@link Element}.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	protected final void internalAddOrChangeAttribute(final BaseNode attribute) {
		
		for (final var p : getRefProperties()) {
			if (p.addedOrChangedAttribute(attribute)) {
				return;
			}
		}
		
		throw new InvalidArgumentException(this, "cannot not have a " + attribute.getHeaderInQuotes());
	}
	
	//method
	/**
	 * Registers a {@link MultiValue} at the current {@link Element}.
	 * 
	 * @param <V> is the type of the value of the registered {@link MultiValue}.
	 * @param name
	 * @param adder
	 * @param getter
	 * @param valueCreator
	 * @param specificationCreator
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given adder is null.
	 * @throws ArgumentIsNullException if the given getter is null.
	 * @throws ArgumentIsNullException if the given valueCreator is null.
	 * @throws ArgumentIsNullException if the given specificationCreator is null.
	 */
	protected final <V> void registerMultiProperty(
		final String name,
		final IElementTaker<V> adder,
		final IElementGetter<IContainer<V>> getter,
		final IElementTakerElementGetter<BaseNode, V> valueCreator,
		final IElementTakerElementGetter<V, Node> specificationCreator
	) {
		properties.addAtEnd(new MultiPropertyExtractor<>(name, adder, getter, valueCreator, specificationCreator));
	}
	
	//method
	/**
	 * Registers a single {@link Property} at the current {@link Element}.
	 * 
	 * @param name
	 * @param setter
	 * @param getter
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given setter is null.
	 * @throws ArgumentIsNullException if the given getter is null.
	 */
	protected final void registerSingleProperty(
		final String name,
		final IElementTaker<Node> setter,
		final IElementGetter<Node> getter
	) {
		properties.addAtEnd(
			new MutableValueExtractor<Node>(name, setter, getter, BaseNode::getCopy, BaseNode::getCopy)
		);
	}
	
	//method
	/**
	 * Registers a single {@link Property} at the current {@link Element}.
	 * 
	 * @param <V> is the type of the value of the registered single {@link Property}.
	 * @param name
	 * @param setter
	 * @param valuePresenceChecker
	 * @param getter
	 * @param valueCreator
	 * @param specificationCreator
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given setter is null.
	 * @throws ArgumentIsNullException if the given valuePresenceChecker is null.
	 * @throws ArgumentIsNullException if the given getter is null.
	 * @throws ArgumentIsNullException if the given valueCreator is null.
	 * @throws ArgumentIsNullException if the given specificationCreator is null.
	 */
	protected final <V> void registerSingleProperty(
		final String name,
		final IElementTaker<V> setter,
		final IBooleanGetter valuePresenceChecker,
		final IElementGetter<V> getter,
		final IElementTakerElementGetter<BaseNode, V> valueCreator,
		final IElementTakerElementGetter<V, Node> specificationCreator
	) {
		properties.addAtEnd(
			new MutableOptionalValueExtractor<V>(
				name,
				setter,
				valuePresenceChecker,
				getter,
				valueCreator,
				specificationCreator
			)
		);
	}
	
	//method
	/**
	 * Registers a single {@link Property} at the current {@link Element}.
	 * 
	 * @param <V> is the type of the value of the registered single {@link Property}.
	 * @param name
	 * @param setter
	 * @param getter
	 * @param valueCreator
	 * @param specificationCreator
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given setter is null.
	 * @throws ArgumentIsNullException if the given getter is null.
	 * @throws ArgumentIsNullException if the given valueCreator is null.
	 * @throws ArgumentIsNullException if the given specificationCreator is null.
	 */
	protected final <V> void registerSingleProperty(
		final String name,
		final IElementTaker<V> setter,
		final IElementGetter<V> getter,
		final IElementTakerElementGetter<BaseNode, V> valueCreator,
		final IElementTakerElementGetter<V, Node> specificationCreator
	) {
		properties.addAtEnd(new MutableValueExtractor<V>(name, setter, getter, valueCreator, specificationCreator));
	}
	
	//method
	/**
	 * Extracts the property from the given field, if the given field is a property.
	 * 
	 * @param field
	 */
	private void extractProbableProperty(final Field field) {
		
		//Handles the case that the current field is a property.
		if (Property.class.isAssignableFrom(field.getType())) {
			try {
				
				field.setAccessible(true);
				
				final var property = (Property)(field.get(this));
				
				//Asserts that the current property is not null.
				Validator.assertThat(property).isOfType(MutableValue.class);
				
				properties.addAtEnd(property);
			} catch (final IllegalAccessException illegalAccessException) {
				
				final var message = illegalAccessException.getMessage();
				
				if (message == null || message.isBlank()) {
					throw new IllegalAccessError();
				}
				
				throw new IllegalAccessError(message);
			}
		}
	}
	
	//method
	/**
	 * Extracts the properties of the current {@link Element}.
	 */
	private void extractProperties() {
		
		//Iterates the classes of the current {@link Entity}.
		Class<?> lClass = getClass();
		while (lClass != null) {
			extractProperties(lClass);
			lClass = lClass.getSuperclass();
		}
		
		extractedProperties = true;
	}
	
	//method
	/**
	 * Extracts the properties of the given pClass.
	 * 
	 * @param pClass
	 */
	private void extractProperties(final Class<?> pClass) {
		
		//Iterates the fields of the given class.
		for (final var f : pClass.getDeclaredFields()) {
			extractProbableProperty(f);
		}
	}
	
	//method
	/**
	 * Extracts the properties of the current {@link Element} if they are not extracted yet.
	 */
	private void extractPropertiesIfNotExtracted() {
		if (!propertiesAreExtracted()) {
			extractProperties();
		}
	}
	
	//method
	/**
	 * @return the properties of the current {@link Element}.
	 */
	private IContainer<Property> getRefProperties() {
		
		extractPropertiesIfNotExtracted();
		
		return properties;
	}
	
	//method
	/**
	 * @return true if the properties of the current {@link Element} are extracted.
	 */
	private boolean propertiesAreExtracted() {
		return extractedProperties;
	}
}
