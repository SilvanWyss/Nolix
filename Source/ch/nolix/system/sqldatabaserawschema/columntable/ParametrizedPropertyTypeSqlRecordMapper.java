//package declaration
package ch.nolix.system.sqldatabaserawschema.columntable;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.sql.SqlSyntaxCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParametrizedBackReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParametrizedReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParametrizedValueTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedPropertyTypeDto;

//class
public final class ParametrizedPropertyTypeSqlRecordMapper {
	
	//method
	public ParametrizedPropertyTypeSqlRecord createParametrizedPropertyTypeRecordFrom(
		final IParameterizedPropertyTypeDto parametrizedPropertyType 
	) {
		return
		switch (parametrizedPropertyType.getPropertyType().getBaseType()) {
			case BASE_VALUE ->
				createBaseParametrizedValueTypeRecord((IBaseParametrizedValueTypeDto)parametrizedPropertyType);
			case BASE_REFERENCE ->
				createBaseParametrizedReferenceTypeRecord((IBaseParametrizedReferenceTypeDto)parametrizedPropertyType);
			case BASE_BACK_REFERENCE ->
				createBaseParametrizedBackReferenceRecord(
					(IBaseParametrizedBackReferenceTypeDto)parametrizedPropertyType
				);
			default ->
				throw InvalidArgumentException.forArgument(parametrizedPropertyType);
		};
	}
	
	//method
	private ParametrizedPropertyTypeSqlRecord createBaseParametrizedBackReferenceRecord(
		final IBaseParametrizedBackReferenceTypeDto baseParametrizedBackReferenceType
	) {
		return
		new ParametrizedPropertyTypeSqlRecord(
			"'" + baseParametrizedBackReferenceType.getPropertyType().toString() + "'",
			"'" + baseParametrizedBackReferenceType.getDataType().name() + "'",
			SqlSyntaxCatalogue.NULL,
			"'" + baseParametrizedBackReferenceType.getBackReferencedColumnId() + "'"
		);
	}
	
	//method
	private ParametrizedPropertyTypeSqlRecord createBaseParametrizedReferenceTypeRecord(
		final IBaseParametrizedReferenceTypeDto baseParametrizedReferenceType
	) {
		return
		new ParametrizedPropertyTypeSqlRecord(
			"'" + baseParametrizedReferenceType.getPropertyType().toString() + "'",
			"'" + baseParametrizedReferenceType.getDataType().name() + "'",
			"'" + baseParametrizedReferenceType.getReferencedTableId() + "'",
			SqlSyntaxCatalogue.NULL
		);
	}
	
	//method
	private ParametrizedPropertyTypeSqlRecord createBaseParametrizedValueTypeRecord(
		final IBaseParametrizedValueTypeDto baseParametrizedPropertyType
	) {
		return
		new ParametrizedPropertyTypeSqlRecord(
			"'" + baseParametrizedPropertyType.getPropertyType().toString() + "'",
			"'" + baseParametrizedPropertyType.getDataType().name() + "'",
			SqlSyntaxCatalogue.NULL,
			SqlSyntaxCatalogue.NULL
		);
	}
}
