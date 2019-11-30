//package declaration
package ch.nolix.system.entity;

//own import
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;

//abstract class
public abstract class BaseValueProperty<V> extends Property<V> {
	
	//method
	@Override
	public final boolean canReference(final Entity entity) {
		return false;
	}
	
	//method
	@Override
	public final boolean references(final Entity entity) {
		return false;
	}
	
	//method
	@Override
	public final String toString() {
		return internal_getValues().toString();
	}
	
	//method
	protected final V createValueFromSpecification(final BaseNode specificaiton) {
		return getParentDatabaseAdapter().createValueFromSpecification(getValueClass(), specificaiton);
	}
	
	//TODO: Delete this method.
	//method
	protected final V createValueFromString(final String string) {
		return getParentDatabaseAdapter().createValueFromSpecification(getValueClass(), Node.fromString(string));
	}
}
