//package declaration
package ch.nolix.system.entity;

//class
public final class IdPropertyType
extends PropertyoidType<Integer> {

	//constructor
	public IdPropertyType() {
		super(Integer.class);
	}
	
	//method
	@Override
	public final boolean captionsPropertyThatCanReference(final Entity entity) {
		return false;
	}

	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.ID;
	}

	//method
	@Override
	public boolean referencesEntitySet(final String name) {
		return false;
	}
}
