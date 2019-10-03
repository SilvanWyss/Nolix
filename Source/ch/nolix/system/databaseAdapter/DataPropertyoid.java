//package declaration
package ch.nolix.system.databaseAdapter;

//own import
import ch.nolix.common.node.BaseNode;

//abstract class
public abstract class DataPropertyoid<V> extends Propertyoid<V> {
	
	//method
	@Override
	public final boolean canReferenceEntity(final Entity entity) {
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
	
	//method
	protected final V createValueFromString(final String string) {
		return getParentDatabaseAdapter().createValueFromString(getValueClass(), string);
	}
}
