//package declaration
package ch.nolix.system.database.databaseadapter;

import ch.nolix.system.database.entity.ValueClassBox;

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
