//package declaration
package ch.nolix.core.databaseAdapter;

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
