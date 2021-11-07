//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.extendeddataapi.IExtendedProperty;

//class
public abstract class Property implements IExtendedProperty<Property> {
	
	//optional attribute
	private Entity parentEntity;
	
	//method
	@Override
	public final boolean belongsToEntity() {
		return (parentEntity != null);
	}
	
	//method
	@Override
	public final IEntity<?, ?> getParentEntity() {
		
		assertBelongsToEntity();
		
		return parentEntity;
	}
	
	//method
	void setParentEntity(final Entity parentEntity) {
		
		Validator.assertThat(parentEntity).thatIsNamed("parent entity").isNotNull();
		
		assertDoesNotBelongToEntity();
		
		this.parentEntity = parentEntity;
	}
}
