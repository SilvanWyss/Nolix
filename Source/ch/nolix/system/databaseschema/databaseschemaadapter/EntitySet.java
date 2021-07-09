//package declaration
package ch.nolix.system.databaseschema.databaseschemaadapter;

import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.UnsupportedArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.SQLDatabaseEngine;
import ch.nolix.system.databaseschema.parametrizedpropertytype.IEntitySet;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedPropertyType;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedMultiValueType;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedOptionalValueType;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedValueType;

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
	public EntitySet(final String name) {
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		
		this.name = name;
	}
	
	//constructor
	public EntitySet(final String name, final IContainer<Column> columns) {
		
		this(name);
		
		addColumns(columns);
	}
	
	//method
	public void addColumn(final Column column) {
		
		assertDoesNotContainColumn(column.getHeader());
		
		columns.addAtEnd(column);
	}
	
	//method
	public EntitySet addColumn(final String header, final Class<?> valueClass) {
		
		addColumn(header, new ParametrizedValueType<>(valueClass));
		
		return this;
	}
	
	//method
	private void addColumn(final String header, final ParametrizedPropertyType<?> dataType) {
		addColumn(new Column(header, dataType));
	}
	
	//method
	public EntitySet addMultiColumn(final String header, final Class<?> valueClass) {
		
		addColumn(header, new ParametrizedMultiValueType<>(valueClass));
		
		return this;
	}
	
	//method
	public EntitySet addMultiReferenceColumn(final String header, final EntitySet referencedEntitySet) {
		
		//TODO: Refactor.
		//addColumn(header, new ParametrizedMultiReferenceType(referencedEntitySet));
		
		return this;
	}
	
	//method
	public EntitySet addOptionalColumn(final String header, final Class<?> valueClass) {
		
		addColumn(header, new ParametrizedOptionalValueType<>(valueClass));
		
		return this;
	}
	
	//method
	public EntitySet addOptionalReferenceColumn(final String header, final EntitySet referencedEntitySet) {
		
		//TODO: Refactor.
		//addColumn(header, new ParametrizedOptionalReferenceType(referencedEntitySet));
	
		return this;
	}
		
	//method
	public EntitySet addReferenceColumn(final String header, final EntitySet referencedEntitySet) {
		
		//TODO: Refactor.
		//addColumn(header, new ParametrizedReferenceType(referencedEntitySet));
		
		return this;
	}
	
	//method
	public boolean containsColumn(final String header) {
		return columns.containsAny(c -> c.hasHeader(header));
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
	public EntitySetSQLHelper getSQLHelper(final SQLDatabaseEngine pSQLDatabaseEngine) {
		switch (pSQLDatabaseEngine) {
			case MSSQL:
				return new EntitySetMSSQLHelper(this);
			default:
				throw new UnsupportedArgumentException(pSQLDatabaseEngine);
		}
	}
	
	//method
	public EntitySetState getState() {
		return state;
	}
	
	//method
	public boolean isDeleted() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (state == EntitySetState.DELETED);
	}
	
	//method
	public boolean isEdited() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (state == EntitySetState.EDITED);
	}
	
	//method
	public boolean isNew() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (state == EntitySetState.NEW);
	}
	
	//method
	public boolean isPersisted() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (state == EntitySetState.PERSISTED);
	}
	
	//method
	public boolean isRejected() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (state == EntitySetState.REJECTED);
	}
	
	//method
	void setChanged() {
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
	void setDeleted() {
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
	void setParentSchemaAdapter(final DatabaseSchemaAdapter<?> parentDatabaseSchemaAdapter) {
		
		Validator.assertThat(parentDatabaseSchemaAdapter).thatIsNamed(DatabaseSchemaAdapter.class).isNotNull();
		
		assertDoesNotBelongToDatabaseSchemaAdapter();
		
		this.parentDatabaseSchemaAdapter = parentDatabaseSchemaAdapter;
	}
	


	//method
	void setPersisted() {
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
	void setRejected() {
		state = EntitySetState.REJECTED;
	}
	
	//method
	public void addColumns(final IContainer<Column> columns) {
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
			throw new ArgumentBelongsToParentException(this, getParentDatabaseSchemaAdapter());
		}
	}
	
	//method
	private void assertDoesNotContainColumn(final String header) {
		if (containsColumn(header)) {
			throw new InvalidArgumentException(
				this,
				"contains a column with the header '" + header + "'"
			);
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
}
