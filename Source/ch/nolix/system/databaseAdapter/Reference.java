//package declaration
package ch.nolix.system.databaseAdapter;

//class
public final class Reference<E extends Entity>
extends SingleReference<E> {

	//method
	@Override
	public boolean isOptional() {
		return false;
	}

	//method
	@Override
	public ReferenceType<E> getPropertyType() {
		return new ReferenceType<E>(getValueClass());
	}
}
