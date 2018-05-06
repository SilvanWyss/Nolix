//package declaration
package ch.nolix.core.databaseAdapter;

//abstract class
public abstract class ReferencePropertyoid<E extends Entity>
extends Propertyoid<E> {
	
	//method
	public final EntitySet<E> getReferencedEntitySet() {
		return
		getParentDatabaseAdapter()
		.getRefEntitySet(getValueClass());
	}
}
