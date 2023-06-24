//package declaration
package ch.nolix.system.sqldatabaserawschema.columntable;

//Java imports
import java.util.List;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectschema.schemadto.BaseParametrizedBackReferenceTypeDto;
import ch.nolix.system.objectschema.schemadto.BaseParametrizedReferenceTypeDto;
import ch.nolix.system.objectschema.schemadto.BaseParametrizedValueTypeDto;
import ch.nolix.system.objectschema.schemadto.ColumnDto;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

//class
public final class ColumnDtoMapper {
	
	//method
	public ColumnDto createColumnDTO(final List<String> columnSystemTableSqlRecord) {
		switch (PropertyType.valueOf(columnSystemTableSqlRecord.get(3)).getBaseType()) {
			case BASE_VALUE:
				return createColumnDtoForBaseValue(columnSystemTableSqlRecord);
			case BASE_BACK_REFERENCE:
				return createColumnDtoForBaseBackReference(columnSystemTableSqlRecord);
			case BASE_REFERENCE:
				return createColumnDtoForBaseReference(columnSystemTableSqlRecord);
			default:
				throw
				InvalidArgumentException.forArgumentNameAndArgument(
					"column system table record",
					columnSystemTableSqlRecord
				);
		}
	}
	
	//method
	private ColumnDto createColumnDtoForBaseBackReference(final List<String> columnSystemTableSqlRecord) {
		return
		new ColumnDto(
			columnSystemTableSqlRecord.get(0),
			columnSystemTableSqlRecord.get(1),
			new BaseParametrizedBackReferenceTypeDto(
				PropertyType.valueOf(columnSystemTableSqlRecord.get(4)),
				DataType.valueOf(columnSystemTableSqlRecord.get(5)),
				columnSystemTableSqlRecord.get(6)
			)
		);
	}
	
	//method
	private ColumnDto createColumnDtoForBaseReference(final List<String> columnSystemTableSqlRecord) {
		return
		new ColumnDto(
			columnSystemTableSqlRecord.get(0),
			columnSystemTableSqlRecord.get(1),
			new BaseParametrizedReferenceTypeDto(
				PropertyType.valueOf(columnSystemTableSqlRecord.get(3)),
				DataType.valueOf(columnSystemTableSqlRecord.get(4)),
				columnSystemTableSqlRecord.get(5)
			)
		);
	}
	
	//method
	private ColumnDto createColumnDtoForBaseValue(final List<String> columnSystemTableSqlRecord) {
		return
		new ColumnDto(
			columnSystemTableSqlRecord.get(0),
			columnSystemTableSqlRecord.get(1),
			new BaseParametrizedValueTypeDto(
				PropertyType.valueOf(columnSystemTableSqlRecord.get(3)),
				DataType.valueOf(columnSystemTableSqlRecord.get(4))
			)
		);
	}
}
