//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.reflection.GlobalReflectionHelper;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.system.objectdata.propertyflyweight.PropertyFlyWeight;
import ch.nolix.system.objectdata.propertyflyweight.VoidPropertyFlyWeight;
import ch.nolix.system.objectdata.propertyhelper.PropertyHelper;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;
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
	private IPropertyFlyWeight propertyFlyWeight = VoidPropertyFlyWeight.INSTANCE;
	
	//optional attribute
	private IEntity<DataImplementation> parentEntity;
	
	//optional attribute
	private IColumn<DataImplementation> parentColumn;
	
	//method
	@Override
	public final boolean belongsToEntity() {
		return (parentEntity != null);
	}
	
	//method
	@Override
	public final String getName() {
		
		if (knowsParentColumn()) {
			return getParentColumn().getName();
		}
		
		if (belongsToEntity()) {
			return GlobalReflectionHelper.getFieldName(getParentEntity(), this);
		}
		
		throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "cannot evaluate own name");
	}
	
	//method
	@Override
	public IColumn<DataImplementation> getParentColumn() {
		
		propertyHelper.assertKnowsParentColumn(this);
		
		return parentColumn;
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
	public final boolean knowsParentColumn() {
		return (parentColumn != null);
	}
	
	//method
	@Override
	public final void setUpdateAction(final IAction updateAction) {
		
		setEffectivePropertyFlyWeightIfPropertyFlyWeightIsVoid();
		
		propertyFlyWeight.setUpdateAction(updateAction);
	}
	
	//method
	final IDataAndSchemaAdapter internalGetRefDataAndSchemaAdapter() {
		return ((BaseEntity)parentEntity).internalGetRefDataAndSchemaAdapter();
	}
	
	//method declaration
	abstract void internalSetOrClearDirectlyFromContent(Object content);
	
	//method
	final void internalSetParentColumn(final IColumn<DataImplementation> parentColumn) {
		
		GlobalValidator.assertThat(parentColumn).thatIsNamed("parent column").isNotNull();
		
		this.parentColumn = parentColumn;
	}
	
	//method
	final void internalSetParentColumnFromParentTable() {
		final var name = getName();
		parentColumn = getParentEntity().getRefParentTable().getColumns().getRefFirst(c -> c.hasName(name));
	}
	
	//method
	final void internalSetParentEntity(final BaseEntity parentEntity) {
		
		GlobalValidator.assertThat(parentEntity).thatIsNamed("parent entity").isNotNull();
		propertyHelper.assertDoesNotBelongToEntity(this);
		
		this.parentEntity = parentEntity;
		setParentColumnFromParentTableIfParentEntityBelongsToTable(parentEntity);
	}
	
	//method
	final void internalSetParentEntityAsEditedAndRunProbableUpdateAction() {
		
		if (belongsToEntity()) {
			((BaseEntity)getParentEntity()).internalSetEdited();
		}
		
		propertyFlyWeight.noteUpdate();
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
	
	//method
	private void setParentColumnFromParentTableIfParentEntityBelongsToTable(final BaseEntity parentEntity) {
		if (parentEntity.belongsToTable()) {
			internalSetParentColumnFromParentTable();
		}
	}
}
