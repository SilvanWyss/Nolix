//package declaration
package ch.nolix.system.objectdata.propertyflyweight;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.functionapi.IAction;
import ch.nolix.systemapi.objectdataapi.dataflyweightapi.IPropertyFlyWeight;

//class
public final class VoidPropertyFlyWeight implements IPropertyFlyWeight {
	
	//constant
	public static final VoidPropertyFlyWeight INSTANCE = new VoidPropertyFlyWeight();
	
	//constructor
	private VoidPropertyFlyWeight() {}
	
	//method
	@Override
	public boolean isVoid() {
		return true;
	}
	
	//method
	@Override
	public void noteUpdate() {}
	
	//method
	@Override
	public void setUpdateAction(final IAction updateAction) {
		throw new ArgumentDoesNotSupportMethodException(this, "setUpdateAction");
	}
}
