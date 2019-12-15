//package declaration
package ch.nolix.system.databaseAdapter;

import ch.nolix.system.entity.ValueClassBox;

//class
public abstract class ValueTypeSQLHelper<V> {
	
	//attribute
	protected final ValueClassBox<V> valueClassBox;
	
	//constructor
	public ValueTypeSQLHelper(final ValueClassBox<V> valueType) {
		this.valueClassBox = valueType;
	}
	
	//method declaration
	public abstract String getSQLDatatype();
}
