//package declaration
package ch.nolix.core.databaseAdapter;

//class
public final class Reference<E extends Entity>
extends SingleReference<E> {

	//method
	public boolean isOptional() {
		return false;
	}

	//method
	public ReferenceType<E> getPropertyType() {
		return new ReferenceType<E>(getValueClass());
	}
}
