//package declaration
package ch.nolix.core.databaseAdapter;

//own import
import ch.nolix.core.bases.NamedElement;

//class
public final class ValueType<V> extends NamedElement {

	//attribute
	private final Class<V> valueClass;
	
	//constructor
	public ValueType(final Class<V> valueClass) {
		
		super(valueClass.getSimpleName());
		
		this.valueClass = valueClass;
	}
	
	//method
	public Class<V> getValueClass() {
		return valueClass;
	}
}
