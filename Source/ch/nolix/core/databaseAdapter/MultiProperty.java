//package declaration
package ch.nolix.core.databaseAdapter;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.interfaces.Clearable;
import ch.nolix.core.specification.StandardSpecification;

//class
public final class MultiProperty<V>
extends DataPropertyoid<V>
implements Clearable<MultiProperty<V>> {
	
	//multi-attribute
	private final List<V> values = new List<V>();
	
	//method
	public void addValue(final V value) {
		
		values.addAtEnd(value);
		
		noteUpdate();
	}
	
	//method
	@SuppressWarnings("unchecked")
	public void addValue(final V... values) {
		
		this.values.addAtEnd(values);
		
		noteUpdate();
	}
	
	//method
	public void addValues(final Iterable<V> values) {
		
		this.values.addAtEnd(values);
		
		noteUpdate();
	}
	
	//method
	public MultiProperty<V> clear() {
		
		values.clear();
		
		return this;
	}
	
	//method
	public List<StandardSpecification> getAttributes() {
		return getValues().to(v -> new StandardSpecification(v.toString()));
	}
	
	//method
	public PropertyoidType<V> getPropertyType() {
		return new MultiPropertyType<V>(getValueClass());
	}
	
	//method
	public ReadContainer<V> getValues() {
		return new ReadContainer<V>(values);
	}

	//method
	public boolean isEmpty() {
		return values.isEmpty();
	}
}
