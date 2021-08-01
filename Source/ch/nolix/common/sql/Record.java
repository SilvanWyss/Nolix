//package declaration
package ch.nolix.common.sql;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;

//class
public final class Record implements IContainer<String> {
	
	//multi-attribute
	private final IContainer<String> values;
	
	//constructor
	public Record(final Iterable<String> values) {
		this.values = LinkedList.fromIterable(values);
	}
	
	@Override
	public Iterator<String> iterator() {
		return values.iterator();
	}

	@Override
	public int getElementCount() {
		return values.getElementCount();
	}

	@Override
	public String getRefAt(final int index) {
		return values.getRefAt(index);
	}
}
