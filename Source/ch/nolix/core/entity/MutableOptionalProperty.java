//package declaration
package ch.nolix.core.entity;

//own imports
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.functionAPI.IElementTaker;
import ch.nolix.core.functionAPI.IElementTakerElementGetter;
import ch.nolix.core.skillAPI.Clearable;
import ch.nolix.core.specificationAPI.Specified;

//class
/**
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 80
 * @param <V> The value of an optional property.
 */
public final class MutableOptionalProperty<V extends Specified>
extends SingleProperty<V>
implements Clearable<MutableOptionalProperty<V>> {
		
	//constructor
	/**
	 * Creates a new optional property
	 * with the given name, setter method and value creator.
	 * 
	 * @param name
	 * @param setterMethod
	 * @param valueCreator
	 * @param specificationCreator
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws NullArgumentException if the given setter method is null.
	 * @throws NullArgumentException if the given value creator is null.
	 * @throws NullArgumentException if the given specification creator is null.
	 */
	public MutableOptionalProperty(
		final String name,
		final IElementTaker<V> setterMethod,
		final IElementTakerElementGetter<DocumentNodeoid, V> valueCreator,
		final IElementTakerElementGetter<V, DocumentNode> specificationCreator
	) {
		
		//Calls constructor of the base class.
		super(name, setterMethod, valueCreator, specificationCreator);
	}
	
	//method
	/**
	 * Removes the value of this property.
	 * 
	 * @return this optional property.
	 */
	@Override
	public MutableOptionalProperty<V> clear() {
		
		//Calls method of the base class.
		super.clear();
		
		return this;
	}
	
	//method
	/**
	 * @return true if this mutable optional property is mutable.
	 */
	@Override
	public boolean isMutable() {
		return true;
	}
	
	//method
	/**
	 * @return true if this mutable optional property is optional.
	 */
	@Override
	public boolean isOptional() {
		return true;
	}
	
	//method
	/**
	 * @throws ArgumentMissesAttributeException if the current {@link MutableOptionalProperty} is empty.
	 */
	@Override
	public void supposeHasValue() {
		
		//Calls method of the base class.
		super.supposeHasValue();
	}
}
