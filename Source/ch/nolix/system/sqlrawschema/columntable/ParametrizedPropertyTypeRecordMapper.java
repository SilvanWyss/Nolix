//package declaration
package ch.nolix.system.sqlrawschema.columntable;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.sql.SQLSyntaxCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParametrizedBackReferenceTypeDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParametrizedReferenceTypeDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParametrizedValueTypeDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;

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
			"'" + baseParametrizedBackReferenceType.getDataType().name() + "'",
			SQLSyntaxCatalogue.NULL,
			"'" + baseParametrizedBackReferenceType.getBackReferencedColumnId() + "'"
		);
	}
	
	//method
	private ParametrizedPropertyTypeRecord createBaseParametrizedReferenceTypeRecord(
		final IBaseParametrizedReferenceTypeDTO baseParametrizedReferenceType
	) {
		return
		new ParametrizedPropertyTypeRecord(
			"'" + baseParametrizedReferenceType.getPropertyType().toString() + "'",
			"'" + baseParametrizedReferenceType.getDataType().name() + "'",
			"'" + baseParametrizedReferenceType.getReferencedTableId() + "'",
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
			"'" + baseParametrizedPropertyType.getDataType().name() + "'",
			SQLSyntaxCatalogue.NULL,
			SQLSyntaxCatalogue.NULL
		);
	}
}
