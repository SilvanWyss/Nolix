//package declaration
package ch.nolix.system.entity;

//class
public final class IdPropertyType
extends PropertyType<Integer> {

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
	public PropertyCategory getPropertyKind() {
		return PropertyCategory.ID;
	}

	//method
	@Override
	public boolean referencesEntitySet(final String name) {
		return false;
	}
}
