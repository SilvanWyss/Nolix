//package declaration
package ch.nolix.core.databaseSchemaAdapter;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.MultiPropertyType;
import ch.nolix.core.databaseAdapter.MultiReferencePropertyType;
import ch.nolix.core.databaseAdapter.OptionalPropertyType;
import ch.nolix.core.databaseAdapter.OptionalReferencePropertyType;
import ch.nolix.core.databaseAdapter.PropertyType;
import ch.nolix.core.databaseAdapter.PropertyoidType;
import ch.nolix.core.databaseAdapter.ReferencePropertyType;
import ch.nolix.core.interfaces.Namable;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.validator2.Validator;

//class
public final class EntitySet implements Namable<EntitySet> {
	
	//attributes
	private String name;
	private DatabaseSchemaAdapter databaseSchemaAdapter;
	private final List<Column> columns = new List<Column>();
	
	//package-visible constructor
	EntitySet(
		final DatabaseSchemaAdapter databaseSchemaAdapter,
		final Class<Entity> entityClass
	) {
		final var entityType =
		new ch.nolix.core.databaseAdapter.EntityType<Entity>(entityClass);
		
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
	
	//method
	public String getName() {
		return name;
	}
	
	//method
	public boolean references(final EntitySet entitySet) {
		
		//TODO
		return false;
	}
	
	//method
	public EntitySet setName(final String name) {
		
		Validator
		.suppose(name)
		.thatIsNamed(VariableNameCatalogue.NAME)
		.isNotEmpty();
		
		if (!hasName(name)) {
		
			if (databaseSchemaAdapter.containsEntitySet(name)) {
				throw new InvalidStateException(
					databaseSchemaAdapter,
					"contains already an other entity set with the name '" + name + "'"
				);
			}			
			
			databaseSchemaAdapter
			.getRefInternalDatabaseSchemaAdapter()
			.noteRenameEntitySet(this, name);
		
			this.name = name;
		}
		
		return this;
	}
	
	//package-visible method
	void noteRenameColumn(final Column column, final String header) {
		databaseSchemaAdapter
		.getRefInternalDatabaseSchemaAdapter()
		.noteRenameColumn(column, header);
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
	private void supposeDoesNotContainColumn(String header) {
		if (containsColumn(header)) {
			throw new InvalidStateException(
				this,
				"contains a column with the header '" + header + "'"
			);
		}	
	}
}
