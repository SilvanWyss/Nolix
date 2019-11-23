//package declaration
package ch.nolix.system.databaseAdapter;

import ch.nolix.system.entity.ValueClassBox;

//abstract class
public abstract class ValueTypeSQLHelper<V> {
	
	//attribute
	protected final ValueClassBox<V> valueClassBox;
	
	//constructor
	public ValueTypeSQLHelper(final ValueClassBox<V> valueType) {
		this.valueClassBox = valueType;
	}
	
	//abstract method
	public abstract String getSQLDatatype();
}
