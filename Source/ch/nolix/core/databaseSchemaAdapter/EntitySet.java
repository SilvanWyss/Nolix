//package declaration
package ch.nolix.core.databaseSchemaAdapter;

//own imports
import ch.nolix.core.bases.NamedElement;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.EntitySetState;
import ch.nolix.core.databaseAdapter.EntityType;
import ch.nolix.core.databaseAdapter.MultiPropertyType;
import ch.nolix.core.databaseAdapter.MultiReferenceType;
import ch.nolix.core.databaseAdapter.OptionalPropertyType;
import ch.nolix.core.databaseAdapter.OptionalReferenceType;
import ch.nolix.core.databaseAdapter.PropertyType;
import ch.nolix.core.databaseAdapter.PropertyoidType;
import ch.nolix.core.databaseAdapter.ReferenceType;
import ch.nolix.core.invalidStateException.InvalidStateException;

//class
public final class EntitySet extends NamedElement {
	
	//attributes
	private final DatabaseSchemaAdapter parentDatabaseSchemaAdapter;
	private EntitySetState state = EntitySetState.CREATED;
	
	//multi-attribute
	private final List<Column> columns;
	
	//package-visible constructor
	EntitySet(
		final DatabaseSchemaAdapter parentDatabaseSchemaAdapter,
		final Class<Entity> entityClass
	) {
		super(new EntityType<Entity>(entityClass).getName());
		
		final var entityType =
		new EntityType<Entity>(entityClass);
		
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
	
	public IContainer<Column> getRefColumns() {
		return columns;
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
	
	//method
	private void addColumn(final Column column) {
		
		supposeDoesNotContainColumn(column.getHeader());
		
		columns.addAtEnd(column);
	}
	
	//method
	private void addColumn(final String header, final PropertyoidType<?> propertyoidType) {		
		addColumn(new Column(this, header, propertyoidType));
	}
	
	//package-visible method
	final void setChanged() {
		switch (getState()) {
			case PERSISTED:
				
				state = EntitySetState.CHANGED;
				
				parentDatabaseSchemaAdapter.noteChangedEntitySet(this);
				
				break;
			case CREATED:
				break;
			case CHANGED:
				break;
			case DELETED:
				throw new InvalidStateException(this, "is deleted");
			case REJECTED:
				throw new InvalidStateException(this, "is rejected");
		}
	}
	
	//package-visible method
	final void setDeleted() {
		switch (getState()) {
			case CREATED:
				throw new InvalidStateException(this, "is created");
			case PERSISTED:
			case CHANGED:
				state = EntitySetState.DELETED;
				break;
			case DELETED:
				break;
			case REJECTED:
				throw new InvalidStateException(this, "is rejected");
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
				throw new InvalidStateException(this, "is changed");
			case DELETED:
				throw new InvalidStateException(this, "is deleted");
			case REJECTED:
				throw new InvalidStateException(this, "is rejected");
		}
	}
	
	//method
	private void supposeDoesNotContainColumn(final String header) {
		if (containsColumn(header)) {
			throw new InvalidStateException(
				this,
				"contains a column with the header '" + header + "'"
			);
		}	
	}
}
