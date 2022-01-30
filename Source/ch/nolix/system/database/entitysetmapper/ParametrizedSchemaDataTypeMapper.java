//package declaration
package ch.nolix.system.database.entitysetmapper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.database.databaseadapter.Column;
import ch.nolix.system.database.parametrizeddatatype.BaseParametrizedBackReferenceType;
import ch.nolix.system.database.parametrizeddatatype.BaseParametrizedReferenceType;
import ch.nolix.system.objectschema.databaseschemaadapter.DatabaseSchemaAdapter;
import ch.nolix.system.objectschema.databaseschemaadapter.EntitySet;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedMultiValueType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedOptionalValueType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedPropertyType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedValueType;

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
	public ParametrizedPropertyType<?> createParametrizedSchemaDataTypeFor(final Column<?> column) {
		switch (column.getDataType()) {
			case VALUE:
				return new ParametrizedValueType<>(column.getRefContentClass());
			case OPTIONAL_VALUE:
				return new ParametrizedOptionalValueType<>(column.getRefContentClass());
			case MULTI_VALUE:
				return new ParametrizedMultiValueType<>(column.getRefContentClass());
			case REFERENCE:
				//TODO: Refactor.
				/*
				return
				new ParametrizedReferenceType(
					getReferencedEntitySetFor((BaseParametrizedReferenceType<?>)column.getParametrizedDataType())
				);
				*/
			case OPTIONAL_REFERENCE:
				//TODO: Refactor.
				/*
				return
				new ParametrizedOptionalReferenceType(
					getReferencedEntitySetFor((BaseParametrizedReferenceType<?>)column.getParametrizedDataType())
				);
				*/
			case MULTI_REFERENCE:
				//TODO: Refactor.
				/*
				return
				new ParametrizedMultiReferenceType(
					getReferencedEntitySetFor((BaseParametrizedReferenceType<?>)column.getParametrizedDataType())
				);
				*/
			case BACK_REFERENCE:
				//TODO: Refactor.
				/*
				return
				new ParametrizedBackReferenceType(
					getBackReferencingEntitySetFor((BaseParametrizedBackReferenceType<?>)column.getParametrizedDataType())
				);
				*/
			case OPTIONAL_BACK_REFERENCE:
				//TODO: Refactor.
				/*
				return
				new ParametrizedOptionalBackReferenceType(
					getBackReferencingEntitySetFor((BaseParametrizedBackReferenceType<?>)column.getParametrizedDataType())
				);
				*/
			case MULTI_BACK_REFERENCE:
				//TODO: Refactor.
				/*
				return
				new ParametrizedMultiBackReferenceType(
					getBackReferencingEntitySetFor((BaseParametrizedBackReferenceType<?>)column.getParametrizedDataType())
				);
				*/
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
