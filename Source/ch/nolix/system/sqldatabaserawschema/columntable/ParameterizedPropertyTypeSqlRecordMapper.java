//package declaration
package ch.nolix.system.sqldatabaserawschema.columntable;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.sql.SqlSyntaxCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedBackReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedValueTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedPropertyTypeDto;

//class
public final class ParameterizedPropertyTypeSqlRecordMapper {
	
	//method
	public ParameterizedPropertyTypeSqlRecord createParametrizedPropertyTypeRecordFrom(
		final IParameterizedPropertyTypeDto parameterizedPropertyType 
	) {
		return
		switch (parameterizedPropertyType.getPropertyType().getBaseType()) {
			case BASE_VALUE ->
				createBaseParametrizedValueTypeRecord((IBaseParameterizedValueTypeDto)parameterizedPropertyType);
			case BASE_REFERENCE ->
				createBaseParametrizedReferenceTypeRecord((IBaseParameterizedReferenceTypeDto)parameterizedPropertyType);
			case BASE_BACK_REFERENCE ->
				createBaseParametrizedBackReferenceRecord(
					(IBaseParameterizedBackReferenceTypeDto)parameterizedPropertyType
				);
			default ->
				throw InvalidArgumentException.forArgument(parameterizedPropertyType);
		};
	}
	
	//method
	private ParameterizedPropertyTypeSqlRecord createBaseParametrizedBackReferenceRecord(
		final IBaseParameterizedBackReferenceTypeDto baseParametrizedBackReferenceType
	) {
		return
		new ParameterizedPropertyTypeSqlRecord(
			"'" + baseParametrizedBackReferenceType.getPropertyType().toString() + "'",
			"'" + baseParametrizedBackReferenceType.getDataType().name() + "'",
			SqlSyntaxCatalogue.NULL,
			"'" + baseParametrizedBackReferenceType.getBackReferencedColumnId() + "'"
		);
	}
	
	//method
	private ParameterizedPropertyTypeSqlRecord createBaseParametrizedReferenceTypeRecord(
		final IBaseParameterizedReferenceTypeDto baseParametrizedReferenceType
	) {
		return
		new ParameterizedPropertyTypeSqlRecord(
			"'" + baseParametrizedReferenceType.getPropertyType().toString() + "'",
			"'" + baseParametrizedReferenceType.getDataType().name() + "'",
			"'" + baseParametrizedReferenceType.getReferencedTableId() + "'",
			SqlSyntaxCatalogue.NULL
		);
	}
	
	//method
	private ParameterizedPropertyTypeSqlRecord createBaseParametrizedValueTypeRecord(
		final IBaseParameterizedValueTypeDto baseParametrizedPropertyType
	) {
		return
		new ParameterizedPropertyTypeSqlRecord(
			"'" + baseParametrizedPropertyType.getPropertyType().toString() + "'",
			"'" + baseParametrizedPropertyType.getDataType().name() + "'",
			SqlSyntaxCatalogue.NULL,
			SqlSyntaxCatalogue.NULL
		);
	}
}
