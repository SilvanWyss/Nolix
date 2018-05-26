//package declaration
package ch.nolix.core.databaseAdapter;

//class
public final class IdPropertyType
extends PropertyoidType<Integer> {

	//constructor
	public IdPropertyType() {
		super(Integer.class);
	}
	
	//method
	public final boolean captionsPropertyThatCanReference(final Entity entity) {
		return false;
	}

	//method
	public PropertyKind getPropertyKind() {
		return PropertyKind.ID;
	}
}
