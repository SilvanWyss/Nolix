//package declaration
package ch.nolix.system.database.entitysetmapper;

//own imports
import ch.nolix.system.database.databaseadapter.EntityType;
import ch.nolix.system.database.databaseschemaadapter.Column;
import ch.nolix.system.database.databaseschemaadapter.DatabaseSchemaAdapter;
import ch.nolix.system.database.databaseschemaadapter.EntitySet;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.system.database.parametrizedschemadatatype.SchemaDataType;

//class
public final class EntitySetMapper {
	
	//attribute
	private final ParametrizedSchemaDataTypeMapper parametrizedSchemaDataTypeMapper;
	
	//constructor
	public EntitySetMapper(final DatabaseSchemaAdapter<?> parentDatabaseSchemaAdapter) {
		parametrizedSchemaDataTypeMapper = new ParametrizedSchemaDataTypeMapper(parentDatabaseSchemaAdapter);
	}
	
	//method
	public <E extends Entity> EntitySet createEntitySetFrom(final Class<E> entityClass) {
		
		final var entityType = new EntityType<>(entityClass);
		
		final var entitySet = new EntitySet(entityType.getName());
		
		for (final var c : entityType.getColumns()) {
			entitySet.addColumn(createColumnFor(c));
		}
		
		return entitySet;
	}
	
	//method
	private Column createColumnFor(final ch.nolix.system.database.databaseadapter.Column<?> column) {
		return new Column(column.getHeader(), createParametrizedSchemaDataTypeFor(column));
	}
	
	//method
	private SchemaDataType<?> createParametrizedSchemaDataTypeFor(
		ch.nolix.system.database.databaseadapter.Column<?> column
	) {
		return parametrizedSchemaDataTypeMapper.createParametrizedSchemaDataTypeFor(column);
	}
}
