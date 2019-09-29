//package declaration
package ch.nolix.system.databaseAdapter;

//own import
import ch.nolix.common.attributeAPI.Named;

//class
public final class ValueClassBox<V> implements Named {
	
	//attributes
	private final Class<V> valueClass;
	
	//package-visible constructor
	ValueClassBox(final Class<V> valueClass) {		
		this.valueClass = valueClass;
	}
	
	//method
	@Override
	public String getName() {
		return valueClass.getSimpleName();
	}
	
	//method
	public Class<V> getValueClass() {
		return valueClass;
	}
}
