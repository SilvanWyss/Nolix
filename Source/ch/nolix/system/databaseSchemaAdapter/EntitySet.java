//package declaration
package ch.nolix.system.databaseSchemaAdapter;

//own imports
import ch.nolix.core.SQL.SQLDatabaseEngine;
import ch.nolix.core.attributeAPI.Named;
import ch.nolix.core.containers.IContainer;
import ch.nolix.core.containers.List;
import ch.nolix.core.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.system.databaseAdapter.Entity;
import ch.nolix.system.databaseAdapter.EntitySetState;
import ch.nolix.system.databaseAdapter.EntityType;
import ch.nolix.system.databaseAdapter.MultiPropertyType;
import ch.nolix.system.databaseAdapter.MultiReferenceType;
import ch.nolix.system.databaseAdapter.OptionalPropertyType;
import ch.nolix.system.databaseAdapter.OptionalReferenceType;
import ch.nolix.system.databaseAdapter.PropertyType;
import ch.nolix.system.databaseAdapter.PropertyoidType;
import ch.nolix.system.databaseAdapter.ReferenceType;

//class
public final class EntitySet implements Named {
	
	//attributes
	private final String name;
	private final DatabaseSchemaAdapter<?> parentDatabaseSchemaAdapter;
	private EntitySetState state = EntitySetState.CREATED;
	
	//multi-attribute
	private final List<Column> columns;
	
	//package-visible constructor
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
		.to(c -> new Column(this, c.getHeader(), c.getPropertyType()));
	}
	
	//method
	public EntitySet addColumn(final String header, final Class<?> valueClass) {
		
		addColumn(header, new PropertyType<>(valueClass));
		
		return this;
	}
	
	//method
	public EntitySet addMultiColumn(final String header, final Class<?> valueClass) {
		
		addColumn(header, new MultiPropertyType<>(valueClass));
		
		return this;
	}
	
	//method
	public EntitySet addMultiReferenceColumn(final String header, final Class<Entity> entityClass) {
		
		addColumn(header, new MultiReferenceType<Entity>(entityClass));
		
		return this;
	}
	
	//method
	public EntitySet addOptionalColumn(final String header, final Class<?> valueClass) {
		
		addColumn(header, new OptionalPropertyType<>(valueClass));
		
		return this;
	}
	
	//method
	public EntitySet addOptionalReferenceColumn(final String header, final Class<Entity> entityClass) {
		
		addColumn(header, new OptionalReferenceType<>(entityClass));
	
		return this;
	}
		
	//method
	public EntitySet addReferenceColumn(final String header, final Class<Entity> entityClass) {
				
		addColumn(header, new ReferenceType<>(entityClass));
		
		return this;
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
	public final boolean isChanged() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (state == EntitySetState.CHANGED);
	}
	
	//method
	public final boolean isCreated() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (state == EntitySetState.CREATED);
	}
	
	//method
	public final boolean isDeleted() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (state == EntitySetState.DELETED);
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
	public boolean references(final EntitySet entitySet) {
		return columns.contains(c -> c.references(entitySet));
	}
	
	//package-visible method
	final void setChanged() {
		switch (getState()) {
			case PERSISTED:
				
				state = EntitySetState.CHANGED;
				
				parentDatabaseSchemaAdapter.noteMutatedEntitySet(this);
				
				break;
			case CREATED:
				break;
			case CHANGED:
				break;
			case DELETED:
				throw new InvalidArgumentException(this, "is deleted");
			case REJECTED:
				throw new InvalidArgumentException(this, "is rejected");
		}
	}
	
	//package-visible method
	final void setDeleted() {
		switch (getState()) {
			case CREATED:
				throw new InvalidArgumentException(this, "is created");
			case PERSISTED:
			case CHANGED:
				state = EntitySetState.DELETED;
				break;
			case DELETED:
				break;
			case REJECTED:
				throw new InvalidArgumentException(this, "is rejected");
		}
	}
	
	//package-visible method
	final void setPersisted() {
		switch (getState()) {
			case PERSISTED:
				break;
			case CREATED:
				state = EntitySetState.PERSISTED;
				break;
			case CHANGED:
				throw new InvalidArgumentException(this, "is changed");
			case DELETED:
				throw new InvalidArgumentException(this, "is deleted");
			case REJECTED:
				throw new InvalidArgumentException(this, "is rejected");
		}
	}
	
	//package-visible method
	final void setRejected() {
		state = EntitySetState.REJECTED;
	}
	
	//method
	private void addColumn(final Column column) {
		
		supposeDoesNotContainColumn(column.getHeader());
		
		columns.addAtEnd(column);
	}
	
	//method
	private void addColumn(final String header, final PropertyoidType<?> propertyoidType) {
		addColumn(new Column(this, header, propertyoidType));
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
