//package declaration
package ch.nolix.system.databaseAdapter;

//own import
import ch.nolix.core.bases.NamedElement;

//class
public final class ValueClassBox<V> extends NamedElement {
	
	//attribute
	private final Class<V> valueClass;
	
	//package-visible constructor
	ValueClassBox(final Class<V> valueClass) {
		
		super(valueClass.getSimpleName());
		
		this.valueClass = valueClass;
	}
	
	//method
	public Class<V> getValueClass() {
		return valueClass;
	}
}
