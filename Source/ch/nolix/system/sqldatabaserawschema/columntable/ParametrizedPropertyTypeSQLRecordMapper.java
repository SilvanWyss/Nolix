//package declaration
package ch.nolix.system.sqldatabaserawschema.columntable;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.sql.SQLSyntaxCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParametrizedBackReferenceTypeDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParametrizedReferenceTypeDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParametrizedValueTypeDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;

//class
public final class ParametrizedPropertyTypeSQLRecordMapper {
	
	//method
	public ParametrizedPropertyTypeSQLRecord createParametrizedPropertyTypeRecordFrom(
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
				throw InvalidArgumentException.forArgument(parametrizedPropertyType);
		}
	}
	
	//method
	private ParametrizedPropertyTypeSQLRecord createBaseParametrizedBackReferenceRecord(
		final IBaseParametrizedBackReferenceTypeDTO baseParametrizedBackReferenceType
	) {
		return
		new ParametrizedPropertyTypeSQLRecord(
			"'" + baseParametrizedBackReferenceType.getPropertyType().toString() + "'",
			"'" + baseParametrizedBackReferenceType.getDataType().name() + "'",
			SQLSyntaxCatalogue.NULL,
			"'" + baseParametrizedBackReferenceType.getBackReferencedColumnId() + "'"
		);
	}
	
	//method
	private ParametrizedPropertyTypeSQLRecord createBaseParametrizedReferenceTypeRecord(
		final IBaseParametrizedReferenceTypeDTO baseParametrizedReferenceType
	) {
		return
		new ParametrizedPropertyTypeSQLRecord(
			"'" + baseParametrizedReferenceType.getPropertyType().toString() + "'",
			"'" + baseParametrizedReferenceType.getDataType().name() + "'",
			"'" + baseParametrizedReferenceType.getOrierencedTableId() + "'",
			SQLSyntaxCatalogue.NULL
		);
	}
	
	//method
	private ParametrizedPropertyTypeSQLRecord createBaseParametrizedValueTypeRecord(
		final IBaseParametrizedValueTypeDTO baseParametrizedPropertyType
	) {
		return
		new ParametrizedPropertyTypeSQLRecord(
			"'" + baseParametrizedPropertyType.getPropertyType().toString() + "'",
			"'" + baseParametrizedPropertyType.getDataType().name() + "'",
			SQLSyntaxCatalogue.NULL,
			SQLSyntaxCatalogue.NULL
		);
	}
}
