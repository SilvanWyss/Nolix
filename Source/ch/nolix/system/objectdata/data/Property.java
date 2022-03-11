//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.functionapi.IAction;
import ch.nolix.core.reflectionhelper.GlobalReflectionHelper;
import ch.nolix.system.objectdata.propertyflyweight.PropertyFlyWeight;
import ch.nolix.system.objectdata.propertyflyweight.VoidPropertyFlyWeight;
import ch.nolix.system.objectdata.propertyhelper.PropertyHelper;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;
import ch.nolix.systemapi.objectdataapi.dataflyweightapi.IPropertyFlyWeight;
import ch.nolix.systemapi.objectdataapi.propertyhelperapi.IPropertyHelper;
import ch.nolix.systemapi.rawdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;

//class
public abstract class Property implements IProperty<DataImplementation> {
	
	//static attribute
	private static final IPropertyHelper propertyHelper = new PropertyHelper();
	
	//attribute
	private String name;
	
	//attribute
	private IPropertyFlyWeight propertyFlyWeight = VoidPropertyFlyWeight.INSTANCE;
	
	//optional attribute
	private IEntity<DataImplementation> parentEntity;
	
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
		
		propertyHelper.assertBelongsToEntity(this);
		
		return parentEntity;
	}
	
	//method
	@Override
	public final DatabaseObjectState getState() {
		
		if (!belongsToEntity()) {
			return DatabaseObjectState.NEW;
		}
		
		return getParentEntity().getState();
	}
	
	//method
	@Override
	public final boolean isClosed() {
		
		if (!belongsToEntity()) {
			return false;
		}
		
		return getParentEntity().isClosed();
	}
	
	//method
	@Override
	public final boolean isDeleted() {
		
		if (!belongsToEntity()) {
			return false;
		}
		
		return getParentEntity().isDeleted();
	}
	
	//method
	@Override
	public final boolean isLinkedWithRealDatabase() {
		return (belongsToEntity() && getParentEntity().isLinkedWithRealDatabase());
	}
	
	//method
	@Override
	public void setUpdateAction(final IAction updateAction) {
		
		setEffectivePropertyFlyWeightIfPropertyFlyWeightIsVoid();
		
		propertyFlyWeight.setUpdateAction(updateAction);
	}
	
	//method
	final IDataAndSchemaAdapter internalGetRefDataAndSchemaAdapter() {
		return ((BaseEntity)parentEntity).internalGetRefDataAndSchemaAdapter();
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
	final void internalSetParentEntityAsEditedAndRunProbableUpdateAction() {
		
		if (belongsToEntity()) {
			((BaseEntity)getParentEntity()).internalSetEdited();
		}
		
		propertyFlyWeight.noteUpdate();
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
	
	//method
	private void setEffectivePropertyFlyWeightIfPropertyFlyWeightIsVoid() {
		if (propertyFlyWeight.isVoid()) {
			setEffectivePropertyFlyWeightWhenPropertyFlyWeightIsVoid();
		}
	}
	
	//method
	private void setEffectivePropertyFlyWeightWhenPropertyFlyWeightIsVoid() {
		propertyFlyWeight = new PropertyFlyWeight();
	}
}
