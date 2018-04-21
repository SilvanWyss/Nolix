//package declaration
package ch.nolix.core.databaseAdapter;

//own import
import ch.nolix.core.container.List;
import ch.nolix.core.interfaces.Clearable;

//class
public final class MultiProperty<V>
extends DataPropertyoid<V>
implements Clearable<MultiProperty<V>> {
	
	//multi-attribute
	private final List<V> values = new List<V>();
	
	//method
	public void addValue(final V value) {
		
		values.addAtEnd(value);
		
		noteChange();
	}
	
	//method
	@SuppressWarnings("unchecked")
	public void addValue(final V... values) {
		
		this.values.addAtEnd(values);
		
		noteChange();
	}
	
	//method
	public void addValues(final Iterable<V> values) {
		
		this.values.addAtEnd(values);
		
		noteChange();
	}
	
	//method
	public MultiProperty<V> clear() {
		
		values.clear();
		
		return this;
	}
	
	@Override
	public PropertyoidType<V> getPropertyType() {
		return new MultiPropertyType<V>(getValueClass());
	}

	//method
	public boolean isEmpty() {
		return values.isEmpty();
	}
}
