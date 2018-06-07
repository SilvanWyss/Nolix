//package declaration
package ch.nolix.core.databaseSchemaAdapter;

//own imports
import ch.nolix.core.bases.NamedElement;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.EntityType;
import ch.nolix.core.databaseAdapter.MultiPropertyType;
import ch.nolix.core.databaseAdapter.MultiReferencePropertyType;
import ch.nolix.core.databaseAdapter.OptionalPropertyType;
import ch.nolix.core.databaseAdapter.OptionalReferencePropertyType;
import ch.nolix.core.databaseAdapter.PropertyType;
import ch.nolix.core.databaseAdapter.PropertyoidType;
import ch.nolix.core.databaseAdapter.ReferencePropertyType;
import ch.nolix.core.databaseAdapter.State;
import ch.nolix.primitive.invalidStateException.InvalidStateException;

//class
public final class EntitySet extends NamedElement {
	
	//attributes
	private final DatabaseSchemaAdapter parentDatabaseSchemaAdapter;
	private State state = State.CREATED;
	
	//multi-attribute
	private final List<Column> columns = new List<Column>();
	
	//package-visible constructor
	EntitySet(
		final DatabaseSchemaAdapter parentDatabaseSchemaAdapter,
		final Class<Entity> entityClass
	) {
		super(new EntityType<Entity>(entityClass).getName());
		
		final var entityType =
		new EntityType<Entity>(entityClass);
		
		this.parentDatabaseSchemaAdapter = parentDatabaseSchemaAdapter;
		
		columns.addAtEnd(
			entityType
			.getColumns()
			.to(c -> new Column(this, c.getHeader(), c.getPropertyType()))
		);
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
		
		addColumn(header, new MultiReferencePropertyType<Entity>(entityClass));
		
		return this;
	}
	
	//method
	public EntitySet addOptionalColumn(final String header, final Class<?> valueClass) {
		
		addColumn(header, new OptionalPropertyType<>(valueClass));
		
		return this;
	}
	
	//method
	public EntitySet addOptionalReferenceColumn(final String header, final Class<Entity> entityClass) {
		
		addColumn(header, new OptionalReferencePropertyType<>(entityClass));
	
		return this;
	}
		
	//method
	public EntitySet addReferenceColumn(final String header, final Class<Entity> entityClass) {
				
		addColumn(header, new ReferencePropertyType<>(entityClass));
		
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
	public State getState() {
		return state;
	}
	
	//method
	public final boolean isCreated() {
		return (getState() == State.CREATED);
	}
	
	//method
	public final boolean isDeleted() {
		return (getState() == State.DELETED);
	}
	
	//method
	public final boolean isPersisted() {
		return (getState() == State.PERSISTED);
	}
	
	//method
	public final boolean isRejected() {
		return (getState() == State.REJECTED);
	}
	
	//method
	public final boolean isUpdated() {
		return (getState() == State.UPDATED);
	}
	
	//method
	public boolean references(final EntitySet entitySet) {
		
		//TODO
		return false;
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
	final void setDeleted() {
		switch (getState()) {
			case PERSISTED:
				state = State.DELETED;
				break;
			case CREATED:
				throw new InvalidStateException(this, "is created");
			case UPDATED:
				state = State.DELETED;
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
				state = State.PERSISTED;
				break;
			case UPDATED:
				throw new InvalidStateException(this, "is updated");
			case DELETED:
				throw new InvalidStateException(this, "is deleted");
			case REJECTED:
				throw new InvalidStateException(this, "is rejected");
		}
	}
	
	//package-visible method
	final void setUpdated() {
		switch (getState()) {
			case PERSISTED:
				
				state = State.UPDATED;
				
				parentDatabaseSchemaAdapter.noteChangedEntitySet(this);
				
				break;
			case CREATED:
				break;
			case UPDATED:
				break;
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
