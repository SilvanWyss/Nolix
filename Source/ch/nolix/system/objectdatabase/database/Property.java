//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.reflection.GlobalReflectionHelper;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.system.objectdatabase.propertyflyweight.PropertyFlyWeight;
import ch.nolix.system.objectdatabase.propertyflyweight.VoidPropertyFlyWeight;
import ch.nolix.system.objectdatabase.propertyvalidator.PropertyValidator;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IColumn;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.databaseflyweightapi.IPropertyFlyWeight;
import ch.nolix.systemapi.objectdatabaseapi.propertyvalidatorapi.IPropertyValidator;
import ch.nolix.systemapi.rawdatabaseapi.databaseandschemaadapterapi.IDataAndSchemaAdapter;

//class
public abstract class Property implements IProperty<DataImplementation> {
	
	//constant
	private static final IPropertyValidator PROPERTY_VALIDATOR = new PropertyValidator();
	
	//attribute
	private IPropertyFlyWeight propertyFlyWeight = VoidPropertyFlyWeight.INSTANCE;
	
	//attribute
	private boolean edited;
	
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
			return getRefParentColumn().getName();
		}
		
		if (belongsToEntity()) {
			return GlobalReflectionHelper.getFieldName(getRefParentEntity(), this);
		}
		
		throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "cannot evaluate own name");
	}
	
	//method
	@Override
	public IColumn<DataImplementation> getRefParentColumn() {
		
		PROPERTY_VALIDATOR.assertKnowsParentColumn(this);
		
		return parentColumn;
	}
	
	//method
	@Override
	public final IEntity<DataImplementation> getRefParentEntity() {
		
		PROPERTY_VALIDATOR.assertBelongsToEntity(this);
		
		return parentEntity;
	}
	
	//method
	@Override
	public final DatabaseObjectState getState() {
		
		if (!belongsToEntity()) {
			return DatabaseObjectState.NEW;
		}
		
		return getStateWhenBelongsToEntity();
	}
	
	//method
	@Override
	public final boolean isClosed() {
		
		if (!belongsToEntity()) {
			return false;
		}
		
		return getRefParentEntity().isClosed();
	}
	
	//method
	@Override
	public final boolean isDeleted() {
		
		if (!belongsToEntity()) {
			return false;
		}
		
		return getRefParentEntity().isDeleted();
	}
	
	//method
	@Override
	public final boolean isLinkedWithRealDatabase() {
		return (belongsToEntity() && getRefParentEntity().isLinkedWithRealDatabase());
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
	protected final void setAsEditedAndRunProbableUpdateAction() {
		
		if (belongsToEntity()) {
			((BaseEntity)getRefParentEntity()).internalSetEdited();
		}
		
		edited = true;
		
		propertyFlyWeight.noteUpdate();
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
		parentColumn = getRefParentEntity().getRefParentTable().getRefColumns().getRefFirst(c -> c.hasName(name));
	}
	
	//method
	final void internalSetParentEntity(final BaseEntity parentEntity) {
		
		GlobalValidator.assertThat(parentEntity).thatIsNamed("parent entity").isNotNull();
		PROPERTY_VALIDATOR.assertDoesNotBelongToEntity(this);
		
		this.parentEntity = parentEntity;
		setParentColumnFromParentTableIfParentEntityBelongsToTable(parentEntity);
	}
	
	//method declaration
	abstract void internalUpdateProbableBackReferencesWhenIsNew();
	
	//method
	private DatabaseObjectState getStateWhenBelongsToEntity() {
		switch (getRefParentEntity().getState()) {
			case NEW:
				return DatabaseObjectState.NEW;
			case LOADED:
				return DatabaseObjectState.LOADED;	
			case EDITED:
				
				if (!edited) {
					return DatabaseObjectState.LOADED;	 
				}
				
				return DatabaseObjectState.EDITED;
			case DELETED:
				return DatabaseObjectState.DELETED;
			case CLOSED:
				return DatabaseObjectState.CLOSED;
			default:
				throw
				InvalidArgumentException.forArgumentNameAndArgument(
					LowerCaseCatalogue.STATE,
					getRefParentEntity().getState()
				);
		}
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
