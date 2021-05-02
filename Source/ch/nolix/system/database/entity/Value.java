//package declaration
package ch.nolix.system.database.entity;

import ch.nolix.businessapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.common.errorcontrol.invalidargumentexception.EmptyArgumentException;
//own imports
import ch.nolix.common.functionapi.IElementTaker;

//class
public final class Value<V> extends SingleValue<V> {
	
	//constructor
	public Value() {}
	
	//constructor
	public Value(final IElementTaker<V> valueValidator) {
		super(valueValidator);
	}
	
	//method
	@Override
	public PropertyType getPropertyKind() {
		return PropertyType.VALUE;
	}
	
	//method
	@Override
	public boolean isOptional() {
		return false;
	}
	
	//method
	@Override
	public void assertCanBeSaved() {
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
	}
}
