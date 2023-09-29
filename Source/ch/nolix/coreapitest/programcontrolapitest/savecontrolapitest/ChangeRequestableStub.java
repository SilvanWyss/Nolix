//package declaration
package ch.nolix.coreapitest.programcontrolapitest.savecontrolapitest;

//own imports
import ch.nolix.coreapi.programcontrolapi.savecontrolapi.ChangeRequestable;

//class
public final class ChangeRequestableStub implements ChangeRequestable {
	
	//static method
	public static ChangeRequestableStub withHasChangesFlag(final boolean hasChanges) {
		return new ChangeRequestableStub(hasChanges);
	}
	
	//attribute
	private final boolean hasChanges;
	
	//constructor
	private ChangeRequestableStub(final boolean hasChanges) {
		this.hasChanges = hasChanges;
	}
	
	//method
	@Override
	public boolean hasChanges() {
		return hasChanges;
	}
}
