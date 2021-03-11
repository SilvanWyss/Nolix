//package declaration
package ch.nolix.system.database.entitysetmapper;

import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.database.databaseadapter.Column;
import ch.nolix.system.database.databaseschemaadapter.DatabaseSchemaAdapter;
import ch.nolix.system.database.databaseschemaadapter.EntitySet;
import ch.nolix.system.database.parametrizeddatatype.BaseParametrizedBackReferenceType;
import ch.nolix.system.database.parametrizeddatatype.BaseParametrizedReferenceType;
import ch.nolix.system.database.parametrizedschemadatatype.SchemaBackReferenceType;
import ch.nolix.system.database.parametrizedschemadatatype.SchemaDataType;
import ch.nolix.system.database.parametrizedschemadatatype.SchemaIdType;
import ch.nolix.system.database.parametrizedschemadatatype.SchemaMultiBackReferenceType;
import ch.nolix.system.database.parametrizedschemadatatype.SchemaMultiReferenceType;
import ch.nolix.system.database.parametrizedschemadatatype.SchemaMultiValueType;
import ch.nolix.system.database.parametrizedschemadatatype.SchemaOptionalBackReferenceType;
import ch.nolix.system.database.parametrizedschemadatatype.SchemaOptionalReferenceType;
import ch.nolix.system.database.parametrizedschemadatatype.SchemaOptionalValueType;
import ch.nolix.system.database.parametrizedschemadatatype.SchemaReferenceType;
import ch.nolix.system.database.parametrizedschemadatatype.SchemaValueType;

//class
public final class ParametrizedSchemaDataTypeMapper {
	
	//attribute
	private final DatabaseSchemaAdapter<?> parentDatabaseSchemaAdapter;
	
	//constructor
	public ParametrizedSchemaDataTypeMapper(final DatabaseSchemaAdapter<?> parentDatabaseSchemaAdapter) {
		
		Validator.assertThat(parentDatabaseSchemaAdapter).thatIsNamed("parent DatabaseSchemaAdapter").isNotNull();
		
		this.parentDatabaseSchemaAdapter = parentDatabaseSchemaAdapter;
	}
	
	//method
	public SchemaDataType<?> createParametrizedSchemaDataTypeFor(final Column<?> column) {
		switch (column.getPropertyKind()) {
			case VALUE:
				return new SchemaValueType<>(column.getRefContentClass());
			case OPTIONAL_VALUE:
				return new SchemaOptionalValueType<>(column.getRefContentClass());
			case MULTI_VALUE:
				return new SchemaMultiValueType<>(column.getRefContentClass());
			case REFERENCE:
				return new SchemaReferenceType(getReferencedEntitySetFor((BaseParametrizedReferenceType<?>)column.getDataType()));
			case OPTIONAL_REFERENCE:
				return
				new SchemaOptionalReferenceType(getReferencedEntitySetFor((BaseParametrizedReferenceType<?>)column.getDataType()));
			case MULTI_REFERENCE:
				return
				new SchemaMultiReferenceType(getReferencedEntitySetFor((BaseParametrizedReferenceType<?>)column.getDataType()));
			case BACK_REFERENCE:
				return
				new SchemaBackReferenceType(getBackReferencingEntitySetFor((BaseParametrizedBackReferenceType<?>)column.getDataType()));
			case OPTIONAL_BACK_REFERENCE:
				return
				new SchemaOptionalBackReferenceType(
					getBackReferencingEntitySetFor((BaseParametrizedBackReferenceType<?>)column.getDataType())
				);
			case MULTI_BACK_REFERENCE:
				return
					new SchemaMultiBackReferenceType(
						getBackReferencingEntitySetFor((BaseParametrizedBackReferenceType<?>)column.getDataType())
					);
			case ID:
				return new SchemaIdType();
			default:
				throw new InvalidArgumentException(column);
		}
	}
	
	//method
	private EntitySet getBackReferencingEntitySetFor(BaseParametrizedBackReferenceType<?> baseBackReferenceType) {
		return
		parentDatabaseSchemaAdapter
		.getRefEntitySets()
		.getRefFirst(es -> es.hasName(baseBackReferenceType.getBackReferencedEntitiesName()));
	}
	
	//method
	private EntitySet getReferencedEntitySetFor(final BaseParametrizedReferenceType<?> baseReferenceType) {
		return
		parentDatabaseSchemaAdapter
		.getRefEntitySets()
		.getRefFirst(es -> es.hasName(baseReferenceType.getReferencedEntitiesName()));
	}
}
