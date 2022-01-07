//package declaration
package ch.nolix.system.sqlrawobjectschema.columntable;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.sql.SQLSyntaxCatalogue;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IBaseParametrizedBackReferenceTypeDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IBaseParametrizedReferenceTypeDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IBaseParametrizedValueTypeDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;

//class
public final class ParametrizedPropertyTypeRecordMapper {
	
	//method
	public ParametrizedPropertyTypeRecord createParametrizedPropertyTypeRecordFrom(
		final IParametrizedPropertyTypeDTO parametrizedPropertyType 
	) {
		switch (parametrizedPropertyType.getPropertyType().getBaseType()) {
			case BASE_VALUE:
				return createBaseParametrizedValueTypeRecord((IBaseParametrizedValueTypeDTO)parametrizedPropertyType);
			case BASE_REFERENCE:
				return
				createBaseParametrizedReferenceTypeRecord((IBaseParametrizedReferenceTypeDTO)parametrizedPropertyType);
			case BASE_BACK_REFERENCE:
				return
				createBaseParametrizedBackReferenceRecord(
					(IBaseParametrizedBackReferenceTypeDTO)parametrizedPropertyType
				);
			default:
				throw new InvalidArgumentException(parametrizedPropertyType);
		}
	}
	
	//method
	private ParametrizedPropertyTypeRecord createBaseParametrizedBackReferenceRecord(
		final IBaseParametrizedBackReferenceTypeDTO baseParametrizedBackReferenceType
	) {
		return
		new ParametrizedPropertyTypeRecord(
			"'" + baseParametrizedBackReferenceType.getPropertyType().toString() + "'",
			"'" + baseParametrizedBackReferenceType.getDataTypeFullClassName() + "'",
			SQLSyntaxCatalogue.NULL,
			"'" + baseParametrizedBackReferenceType.getBackReferencedTableName() + "'",
			"'" + baseParametrizedBackReferenceType.getBackReferencedColumnName() + "'"
		);
	}
	
	//method
	private ParametrizedPropertyTypeRecord createBaseParametrizedReferenceTypeRecord(
		final IBaseParametrizedReferenceTypeDTO baseParametrizedReferenceType
	) {
		return
		new ParametrizedPropertyTypeRecord(
			"'" + baseParametrizedReferenceType.getPropertyType().toString() + "'",
			"'" + baseParametrizedReferenceType.getDataTypeFullClassName() + "'",
			"'" + baseParametrizedReferenceType.getReferencedTableName() + "'",
			SQLSyntaxCatalogue.NULL,
			SQLSyntaxCatalogue.NULL
		);
	}
	
	//method
	private ParametrizedPropertyTypeRecord createBaseParametrizedValueTypeRecord(
		final IBaseParametrizedValueTypeDTO baseParametrizedPropertyType
	) {
		return
		new ParametrizedPropertyTypeRecord(
			"'" + baseParametrizedPropertyType.getPropertyType().toString() + "'",
			"'" + baseParametrizedPropertyType.getDataTypeFullClassName() + "'",
			SQLSyntaxCatalogue.NULL,
			SQLSyntaxCatalogue.NULL,
			SQLSyntaxCatalogue.NULL
		);
	}
}
