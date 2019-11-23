//package declaration
package ch.nolix.system.entity;

//own import
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;

//abstract class
public abstract class DataPropertyoid<V> extends Propertyoid<V> {
	
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
	
	//TODO: Delete the feature for creating values from Strings.
	//method
	protected final V createValueFromString(final String string) {
		return getParentDatabaseAdapter().createValueFromSpecification(getValueClass(), Node.fromString(string));
	}
}
