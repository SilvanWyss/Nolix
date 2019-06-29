//package declaration
package ch.nolix.system.databaseAdapter;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.skillAPI.Clearable;

//class
public final class MultiProperty<V>
extends DataPropertyoid<V>
implements Clearable<MultiProperty<V>> {
	
	//multi-attribute
	private final List<V> values = new List<>();
	
	//method
	public void addUntypedValue(final V value) {
		addValue(value);
	}
	
	//method
	public void addValue(final V value) {
		
		values.addAtEnd(value);
		
		internal_noteUpdate();
	}
	
	//method
	@SuppressWarnings("unchecked")
	public void addValue(final V... values) {
		
		this.values.addAtEnd(values);
		
		internal_noteUpdate();
	}
	
	//method
	public void addValues(final Iterable<V> values) {
		
		this.values.addAtEnd(values);
		
		internal_noteUpdate();
	}
	
	//method
	@Override
	public MultiProperty<V> clear() {
		
		internal_clear();
		
		return this;
	}
	
	//method
	public List<DocumentNode> getAttributes0() {
		return getValues().to(v -> DocumentNode.createFromString(v.toString()));
	}
	
	//method
	@Override
	public PropertyoidType<V> getPropertyType() {
		return new MultiPropertyType<>(getValueClass());
	}
	
	//method
	public ReadContainer<V> getValues() {
		return new ReadContainer<>(values);
	}

	//method
	@Override
	public boolean isEmpty() {
		return values.isEmpty();
	}
	
	//method
	@Override
	protected void internal_clear() {
		values.clear();
	}
	
	//method
	@Override
	protected List<Object> internal_getValues() {
		return new List<>(values);
	}
	
	//method
	@Override
	@SuppressWarnings("unchecked")
	protected void internal_setValue(final Object value) {
		clear();
		values.addAtEnd((V)value);
	}
	
	//method
	@Override
	@SuppressWarnings("unchecked")
	protected void internal_setValues(final Iterable<Object> values) {
		clear();
		values.forEach(v -> addValue((V)v));
	}
}
