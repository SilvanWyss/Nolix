//package declaration
package ch.nolix.system.databaseSchemaAdapter;

//own imports
import ch.nolix.common.SQL.SQLDatabaseEngine;
import ch.nolix.common.attributeAPI.Named;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.system.dataTypes.DataType;
import ch.nolix.system.dataTypes.MultiReferenceType;
import ch.nolix.system.dataTypes.MultiValueType;
import ch.nolix.system.dataTypes.OptionalReferenceType;
import ch.nolix.system.dataTypes.OptionalValueType;
import ch.nolix.system.dataTypes.ReferenceType;
import ch.nolix.system.dataTypes.ValueType;
import ch.nolix.system.databaseAdapter.EntityType;
import ch.nolix.system.entity.Entity;

//class
public final class EntitySet implements Named {
	
	//attributes
	private final String name;
	private final DatabaseSchemaAdapter<?> parentDatabaseSchemaAdapter;
	private EntitySetState state = EntitySetState.NEW;
	
	//multi-attribute
	private final LinkedList<Column> columns;
	
	//constructor
	<E extends Entity>
	EntitySet(
		final DatabaseSchemaAdapter<?> parentDatabaseSchemaAdapter,
		final Class<E> entityClass
	) {
		
		name = new EntityType<E>(entityClass).getName();
		
		final var entityType = new EntityType<E>(entityClass);
		
		this.parentDatabaseSchemaAdapter = parentDatabaseSchemaAdapter;
		
		columns =
		entityType
		.getColumns()
		.to(c -> new Column(c.getHeader(), c.getDataType()));
	}
	
	//method
	public EntitySet addColumn(final String header, final Class<?> valueClass) {
		
		addColumn(header, new ValueType<>(valueClass));
		
		return this;
	}
	
	//method
	public EntitySet addMultiColumn(final String header, final Class<?> valueClass) {
		
		addColumn(header, new MultiValueType<>(valueClass));
		
		return this;
	}
	
	//method
	public EntitySet addMultiReferenceColumn(final String header, final Class<Entity> entityClass) {
		
		addColumn(header, new MultiReferenceType<Entity>(entityClass));
		
		return this;
	}
	
	//method
	public EntitySet addOptionalColumn(final String header, final Class<?> valueClass) {
		
		addColumn(header, new OptionalValueType<>(valueClass));
		
		return this;
	}
	
	//method
	public EntitySet addOptionalReferenceColumn(final String header, final Class<Entity> entityClass) {
		
		addColumn(header, new OptionalReferenceType<>(entityClass));
	
		return this;
	}
		
	//method
	public EntitySet addReferenceColumn(final String header, final Class<Entity> entityClass) {
				
		addColumn(header, new ReferenceType<Entity>(entityClass));
		
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
				
				parentDatabaseSchemaAdapter.noteMutatedEntitySet(this);
				
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
	private void addColumn(final String header, final DataType<?> dataType) {
		addColumn(new Column(header, dataType));
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
