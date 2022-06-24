//package declaration
package ch.nolix.system.objectdata.propertyflyweight;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.functionuniversalapi.IAction;
import ch.nolix.systemapi.objectdataapi.dataflyweightapi.IPropertyFlyWeight;

//class
public final class PropertyFlyWeight implements IPropertyFlyWeight {
	
	//optional attribute
	private IAction updateAction;
	
	//method
	@Override
	public boolean isVoid() {
		return false;
	}
	
	//method
	@Override
	public void noteUpdate() {
		if (hasUpdateAction()) {
			updateAction.run();
		}
	}
	
	@Override
	public void setUpdateAction(final IAction updateAction) {
		
		GlobalValidator.assertThat(updateAction).thatIsNamed("update action").isNotNull();
		
		this.updateAction = updateAction;
	}
	
	//method
	private boolean hasUpdateAction() {
		return (updateAction != null);
	}
}
