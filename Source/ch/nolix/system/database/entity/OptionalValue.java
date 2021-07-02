//package declaration
package ch.nolix.system.database.entity;

//own imports
import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.common.skillapi.Clearable;
import ch.nolix.techapi.databaseschemaapi.propertytypeapi.PropertyType;

//class
public class OptionalValue<V> extends SingleValue<V> implements Clearable {
	
	//constructor
	public OptionalValue() {}
	
	//constructor
	public OptionalValue(final IElementTaker<V> preSetValueFunction) {
		super(preSetValueFunction);
	}
	
	//method
	@Override
	public void clear() {
		internalClear();
	}
	
	//method
	@Override
	public PropertyType getPropertyKind() {
		return PropertyType.OPTIONAL_VALUE;
	}
	
	//method
	@Override
	public boolean isOptional() {
		return true;
	}
	
	//method
	@Override
	public void assertCanBeSaved() {}
}
