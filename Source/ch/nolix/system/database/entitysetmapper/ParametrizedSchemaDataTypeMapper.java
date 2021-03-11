//package declaration
package ch.nolix.system.database.entitysetmapper;

import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.database.databaseadapter.Column;
import ch.nolix.system.database.databaseschemaadapter.DatabaseSchemaAdapter;
import ch.nolix.system.database.databaseschemaadapter.EntitySet;
import ch.nolix.system.database.parametrizeddatatype.BaseParametrizedBackReferenceType;
import ch.nolix.system.database.parametrizeddatatype.BaseParametrizedReferenceType;
import ch.nolix.system.database.parametrizedschemadatatype.ParametrizedSchemaBackReferenceType;
import ch.nolix.system.database.parametrizedschemadatatype.ParametrizedSchemaDataType;
import ch.nolix.system.database.parametrizedschemadatatype.SchemaIdType;
import ch.nolix.system.database.parametrizedschemadatatype.ParametrizedSchemaMultiBackReferenceType;
import ch.nolix.system.database.parametrizedschemadatatype.ParametrizedSchemaMultiReferenceType;
import ch.nolix.system.database.parametrizedschemadatatype.ParametrizedSchemaMultiValueType;
import ch.nolix.system.database.parametrizedschemadatatype.ParametrizedSchemaOptionalBackReferenceType;
import ch.nolix.system.database.parametrizedschemadatatype.ParametrizedSchemaOptionalReferenceType;
import ch.nolix.system.database.parametrizedschemadatatype.ParametrizedSchemaOptionalValueType;
import ch.nolix.system.database.parametrizedschemadatatype.ParametrizedSchemaReferenceType;
import ch.nolix.system.database.parametrizedschemadatatype.ParametrizedSchemaValueType;

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
	public ParametrizedSchemaDataType<?> createParametrizedSchemaDataTypeFor(final Column<?> column) {
		switch (column.getDataType()) {
			case VALUE:
				return new ParametrizedSchemaValueType<>(column.getRefContentClass());
			case OPTIONAL_VALUE:
				return new ParametrizedSchemaOptionalValueType<>(column.getRefContentClass());
			case MULTI_VALUE:
				return new ParametrizedSchemaMultiValueType<>(column.getRefContentClass());
			case REFERENCE:
				return new ParametrizedSchemaReferenceType(getReferencedEntitySetFor((BaseParametrizedReferenceType<?>)column.getParametrizedDataType()));
			case OPTIONAL_REFERENCE:
				return
				new ParametrizedSchemaOptionalReferenceType(getReferencedEntitySetFor((BaseParametrizedReferenceType<?>)column.getParametrizedDataType()));
			case MULTI_REFERENCE:
				return
				new ParametrizedSchemaMultiReferenceType(getReferencedEntitySetFor((BaseParametrizedReferenceType<?>)column.getParametrizedDataType()));
			case BACK_REFERENCE:
				return
				new ParametrizedSchemaBackReferenceType(getBackReferencingEntitySetFor((BaseParametrizedBackReferenceType<?>)column.getParametrizedDataType()));
			case OPTIONAL_BACK_REFERENCE:
				return
				new ParametrizedSchemaOptionalBackReferenceType(
					getBackReferencingEntitySetFor((BaseParametrizedBackReferenceType<?>)column.getParametrizedDataType())
				);
			case MULTI_BACK_REFERENCE:
				return
					new ParametrizedSchemaMultiBackReferenceType(
						getBackReferencingEntitySetFor((BaseParametrizedBackReferenceType<?>)column.getParametrizedDataType())
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
