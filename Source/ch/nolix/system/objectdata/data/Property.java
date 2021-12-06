//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.objectdata.propertyhelper.PropertyHelper;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.dataapi.IProperty;
import ch.nolix.techapi.objectdataapi.propertyhelperapi.IPropertyHelper;
import ch.nolix.techapi.rawobjectdataapi.dataadapterapi.IDataAdapter;
import ch.nolix.techapi.rawobjectdataapi.dataadapterapi.IDataReader;

//class
public abstract class Property implements IProperty<DataImplementation> {
	
	//static attribute
	private static final IPropertyHelper propertyHelper = new PropertyHelper();
	
	//optional attribute
	private Entity parentEntity;
	
	//method
	@Override
	public final boolean belongsToEntity() {
		return (parentEntity != null);
	}
	
	//method
	@Override
	public final IEntity<DataImplementation> getParentEntity() {
		
		propertyHelper.assertBelongsToEntity(this);
		
		return parentEntity;
	}
	
	//method
	@Override
	public final boolean isLinkedWithRealDatabase() {
		return (belongsToEntity() && getParentEntity().isLinkedWithRealDatabase());
	}
	
	//method
	protected final IDataAdapter getRefDataAdapter() {
		return parentEntity.internalGetRefDataAdapter();
	}
	
	//method
	protected final void setParentEntity(final Entity parentEntity) {
		
		Validator.assertThat(parentEntity).thatIsNamed("parent entity").isNotNull();
		
		propertyHelper.assertDoesNotBelongToEntity(this);
		
		this.parentEntity = parentEntity;
	}
}
