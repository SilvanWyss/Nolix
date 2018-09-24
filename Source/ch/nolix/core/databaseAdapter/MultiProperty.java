//package declaration
package ch.nolix.core.databaseAdapter;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.skillInterfaces.Clearable;

//class
public final class MultiProperty<V>
extends DataPropertyoid<V>
implements Clearable<MultiProperty<V>> {
	
	//multi-attribute
	private final List<V> values = new List<V>();
	
	//method
	public void addUntypedValue(final V value) {
		addValue((V)value);
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
	public MultiProperty<V> clear() {
		
		internal_clear();
		
		return this;
	}
	
	//method
	public List<DocumentNode> getAttributes0() {
		return getValues().to(v -> new DocumentNode(v.toString()));
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
	
	//method
	protected void internal_clear() {
		values.clear();
	}
	
	//method
	protected List<Object> internal_getValues() {
		return new List<Object>(values);
	}

	//method
	@SuppressWarnings("unchecked")
	protected void internal_setValues(final Iterable<Object> values) {
		values.forEach(v -> addValue((V)v));
	}
}
