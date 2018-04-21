//package declaration
package ch.nolix.core.databaseAdapter;

//class
public final class ReferenceProperty<E extends Entity>
extends SingleReferenceProperty<E> {

	//method
	public boolean isOptional() {
		return false;
	}
}
