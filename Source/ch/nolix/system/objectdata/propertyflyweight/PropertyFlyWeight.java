//package declaration
package ch.nolix.system.objectdata.propertyflyweight;

//own imports
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.functionapi.IElementTaker;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;
import ch.nolix.systemapi.objectdataapi.dataflyweightapi.IPropertyFlyWeight;

//class
public final class PropertyFlyWeight implements IPropertyFlyWeight {
	
	//optional attribute
	private IElementTaker<IProperty<?>> updateAction;
	
	//method
	@Override
	public boolean isVoid() {
		return false;
	}
	
	//method
	@Override
	public void noteUpdate(final IProperty<?> property) {
		if (hasUpdateAction()) {
			updateAction.run(property);
		}
	}
	
	@Override
	public void setUpdateAction(final IElementTaker<IProperty<?>> updateAction) {
		
		Validator.assertThat(updateAction).thatIsNamed("update action").isNotNull();
		
		this.updateAction = updateAction;
	}
	
	//method
	private boolean hasUpdateAction() {
		return (updateAction != null);
	}
}
