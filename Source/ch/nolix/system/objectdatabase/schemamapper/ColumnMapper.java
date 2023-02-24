//package declaration
package ch.nolix.system.objectdatabase.schemamapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.schema.Column;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.schemamapperapi.IColumnMapper;
import ch.nolix.systemapi.objectdatabaseapi.schemamapperapi.IParametrizedPropertyTypeMapper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//class
public final class ColumnMapper implements IColumnMapper {
	
	//static attribute
	private static final EntityCreator entityCreator = new EntityCreator();
	
	//static attribute
	private static final IParametrizedPropertyTypeMapper parametrizedPropertyTypeMapper =
	new ParametrizedPropertyTypeMapper();
	
	//method
	@Override
	public IColumn createColumnFromGivenPropertyUsingGivenReferencableTables(
		final IProperty property,
		final IContainer<ITable> referencableTables
	) {
		return
		new Column(
			property.getName(),
			parametrizedPropertyTypeMapper.createParametrizedPropertyTypeFromGivenPropertyUsingGivenReferencableTables(
				property,
				referencableTables
			)
		);
	}
	
	//method
	@Override
	public <E extends IEntity> IContainer<IColumn>
	createColumnsFromGivenEntityTypeUsingGivenReferencableTables(
		final Class<E> entityType,
		final IContainer<ITable> referencableTables
	) {
		return
		createColumnsFromGivenEntityUsingGivenReferencableTables(
			entityCreator.createEmptyEntityOf(entityType),
			referencableTables
		);
	}
	
	//method
	@Override
	public IContainer<IColumn> createColumnsFromGivenEntityUsingGivenReferencableTables(
		final IEntity entity,
		final IContainer<ITable> referencableTables
	) {
		return
		entity
		.technicalGetRefProperties()
		.to(p -> createColumnFromGivenPropertyUsingGivenReferencableTables(p, referencableTables));
	}
}
