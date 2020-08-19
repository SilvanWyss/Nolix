//package declaration
package ch.nolix.system.databaseSchemaAdapter;

//own imports
import ch.nolix.common.SQL.SQLDatabaseEngine;
import ch.nolix.common.attributeAPI.Named;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidArgumentException.ArgumentBelongsToUnexchangeableParentException;
import ch.nolix.common.invalidArgumentException.ArgumentDoesNotBelongToParentException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.validator.Validator;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.schemaDataType.IEntitySet;
import ch.nolix.system.schemaDataType.SchemaDataType;
import ch.nolix.system.schemaDataType.SchemaMultiReferenceType;
import ch.nolix.system.schemaDataType.SchemaMultiValueType;
import ch.nolix.system.schemaDataType.SchemaOptionalReferenceType;
import ch.nolix.system.schemaDataType.SchemaOptionalValueType;
import ch.nolix.system.schemaDataType.SchemaReferenceType;
import ch.nolix.system.schemaDataType.SchemaValueType;

//class
public final class EntitySet implements IEntitySet, Named {
	
	//attributes
	private final String name;
	private EntitySetState state = EntitySetState.NEW;
	
	//optional attribute
	private DatabaseSchemaAdapter<?> parentDatabaseSchemaAdapter;
	
	//multi-attribute
	private final LinkedList<Column> columns = new LinkedList<>();
	
	//constructor
	public EntitySet(final String name, final IContainer<Column> columns) {
		
		Validator.assertThat(name).thatIsNamed(VariableNameCatalogue.NAME).isNotBlank();
		
		this.name = name;
		
		addColumns(columns);
	}
	
	//method
	public EntitySet addColumn(final String header, final Class<?> valueClass) {
		
		addColumn(header, new SchemaValueType(valueClass));
		
		return this;
	}
	
	//method
	public EntitySet addMultiColumn(final String header, final Class<?> valueClass) {
		
		addColumn(header, new SchemaMultiValueType(valueClass));
		
		return this;
	}
	
	//method
	public EntitySet addMultiReferenceColumn(final String header, final EntitySet referencedEntitySet) {
		
		addColumn(header, new SchemaMultiReferenceType(referencedEntitySet));
		
		return this;
	}
	
	//method
	public EntitySet addOptionalColumn(final String header, final Class<?> valueClass) {
		
		addColumn(header, new SchemaOptionalValueType<>(valueClass));
		
		return this;
	}
	
	//method
	public EntitySet addOptionalReferenceColumn(final String header, final EntitySet referencedEntitySet) {
		
		addColumn(header, new SchemaOptionalReferenceType(referencedEntitySet));
	
		return this;
	}
		
	//method
	public EntitySet addReferenceColumn(final String header, final EntitySet referencedEntitySet) {
				
		addColumn(header, new SchemaReferenceType(referencedEntitySet));
		
		return this;
	}
	
	//method
	public <E extends Entity> boolean canReferenceBackEntityOfType(final Class<E> type) {
		return columns.contains(c -> c.canReferenceBackEntityOfType(type));
	}
	
	//method
	public <E extends Entity> boolean canReferenceEntityOfType(final Class<E> type) {
		return columns.contains(c -> c.canReferenceEntityOfType(type));
	}
	
	//method
	public boolean containsColumn(final String header) {
		return columns.contains(c -> c.hasHeader(header));
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
	
	//method
	public IContainer<Column> getRefColumns() {
		return columns;
	}
	
	//method
	public EntitySetSQLHelper getSQLHelper(final SQLDatabaseEngine SQLDatabaseEngine) {
		switch (SQLDatabaseEngine) {
			case MSSQL:
				return new EntitySetMSSQLHelper(this);
			default:
				throw
				new RuntimeException("The given SQL database engine '" + SQLDatabaseEngine + "' is not supported.");
		}
	}
	
	//method
	public EntitySetState getState() {
		return state;
	}
	
	//method
	public final boolean isDeleted() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (state == EntitySetState.DELETED);
	}
	
	//method
	public final boolean isEdited() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (state == EntitySetState.EDITED);
	}
	
	//method
	public final boolean isNew() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (state == EntitySetState.NEW);
	}
	
	//method
	public final boolean isPersisted() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (state == EntitySetState.PERSISTED);
	}
	
	//method
	public final boolean isRejected() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (state == EntitySetState.REJECTED);
	}
	
	//method
	final void setChanged() {
		switch (getState()) {
			case PERSISTED:
				
				state = EntitySetState.EDITED;
				
				getParentDatabaseSchemaAdapter().noteMutatedEntitySet(this);
				
				break;
			case NEW:
				break;
			case EDITED:
				break;
			case DELETED:
				throw new InvalidArgumentException(this, "is deleted");
			case REJECTED:
				throw new InvalidArgumentException(this, "is rejected");
		}
	}
	
	//method
	final void setDeleted() {
		switch (getState()) {
			case NEW:
				throw new InvalidArgumentException(this, "is created");
			case PERSISTED:
			case EDITED:
				state = EntitySetState.DELETED;
				break;
			case DELETED:
				break;
			case REJECTED:
				throw new InvalidArgumentException(this, "is rejected");
		}
	}
	
	//method
	final void setParentSchemaAdapter(final DatabaseSchemaAdapter<?> parentDatabaseSchemaAdapter) {
		
		Validator.assertThat(parentDatabaseSchemaAdapter).thatIsNamed(DatabaseSchemaAdapter.class).isNotNull();
		
		assertDoesNotBelongToDatabaseSchemaAdapter();
		
		this.parentDatabaseSchemaAdapter = parentDatabaseSchemaAdapter;
	}
	


	//method
	final void setPersisted() {
		switch (getState()) {
			case PERSISTED:
				break;
			case NEW:
				state = EntitySetState.PERSISTED;
				break;
			case EDITED:
				throw new InvalidArgumentException(this, "is changed");
			case DELETED:
				throw new InvalidArgumentException(this, "is deleted");
			case REJECTED:
				throw new InvalidArgumentException(this, "is rejected");
		}
	}
	
	//method
	final void setRejected() {
		state = EntitySetState.REJECTED;
	}
	
	//method
	private void addColumn(final Column column) {
		
		supposeDoesNotContainColumn(column.getHeader());
		
		columns.addAtEnd(column);
	}
	
	//method
	private void addColumn(final String header, final SchemaDataType<?> dataType) {
		addColumn(new Column(header, dataType));
	}
	
	//method
	private void addColumns(final IContainer<Column> columns) {
		columns.forEach(this::addColumn);
	}
	
	//method
	private void assertBelongsToDatabaseSchemaAdapter() {
		if (belongsToDatabaseSchemaAdapter()) {
			throw new ArgumentDoesNotBelongToParentException(this, DatabaseSchemaAdapter.class);
		}
	}
	
	//method
	private void assertDoesNotBelongToDatabaseSchemaAdapter() {
		if (belongsToDatabaseSchemaAdapter()) {
			throw new ArgumentBelongsToUnexchangeableParentException(this, getParentDatabaseSchemaAdapter());
		}
	}
	
	//method
	private boolean belongsToDatabaseSchemaAdapter() {
		return (parentDatabaseSchemaAdapter != null);
	}

	//method
	private DatabaseSchemaAdapter<?> getParentDatabaseSchemaAdapter() {
		
		assertBelongsToDatabaseSchemaAdapter();
		
		return parentDatabaseSchemaAdapter;
	}
	
	//method
	private void supposeDoesNotContainColumn(final String header) {
		if (containsColumn(header)) {
			throw new InvalidArgumentException(
				this,
				"contains a column with the header '" + header + "'"
			);
		}
	}
}
