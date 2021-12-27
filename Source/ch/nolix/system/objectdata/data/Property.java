//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.reflectionhelper.GlobalReflectionHelper;
import ch.nolix.system.objectdata.propertyhelper.PropertyHelper;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.dataapi.IProperty;
import ch.nolix.techapi.objectdataapi.propertyhelperapi.IPropertyHelper;
import ch.nolix.techapi.rawobjectdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;

//class
public abstract class Property implements IProperty<DataImplementation> {
	
	//static attribute
	private static final IPropertyHelper propertyHelper = new PropertyHelper();
	
	//attribute
	private String name;
	
	//optional attribute
	private BaseEntity parentEntity;
	
	//method
	@Override
	public final boolean belongsToEntity() {
		return (parentEntity != null);
	}
	
	//method
	@Override
	public final String getName() {
		
		fetchNameIfNotFetched();
		
		return name;
	}
	
	//method
	@Override
	public final IEntity<DataImplementation> getParentEntity() {
		return internalGetParentEntity();
	}
	
	//method
	@Override
	public final boolean isLinkedWithRealDatabase() {
		return (belongsToEntity() && getParentEntity().isLinkedWithRealDatabase());
	}
	
	//method
	final BaseEntity internalGetParentEntity() {
		
		propertyHelper.assertBelongsToEntity(this);
		
		return parentEntity;
	}
	
	//method
	final IDataAndSchemaAdapter internalGetRefDataAndSchemaAdapter() {
		return parentEntity.internalGetRefDataAndSchemaAdapter();
	}
	
	//method
	final void internalSetParentEntity(final BaseEntity parentEntity) {
		
		Validator.assertThat(parentEntity).thatIsNamed("parent entity").isNotNull();
		
		propertyHelper.assertDoesNotBelongToEntity(this);
		
		this.parentEntity = parentEntity;
	}
	
	//method
	abstract void internalSetOrClearDirectlyFromContent(final Object content);
	
	//method
	final void internalSetParentEntityAsEdited() {
		if (belongsToEntity()) {
			internalGetParentEntity().internalSetEdited();
		}
	}
	
	//method
	private boolean fetchedName() {
		return (name != null);
	}
	
	//method
	private void fetchName() {
		name = findName();
	}
	
	//method
	private void fetchNameIfNotFetched() {
		if (!fetchedName()) {
			fetchName();
		}
	}
	
	//method
	private String findName() {
		return GlobalReflectionHelper.getFieldName(getParentEntity(), this);
	}
}
