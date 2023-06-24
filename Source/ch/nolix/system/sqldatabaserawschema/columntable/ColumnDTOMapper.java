//package declaration
package ch.nolix.system.sqldatabaserawschema.columntable;

//Java imports
import java.util.List;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectschema.schemadto.BaseParametrizedBackReferenceTypeDTO;
import ch.nolix.system.objectschema.schemadto.BaseParametrizedReferenceTypeDTO;
import ch.nolix.system.objectschema.schemadto.BaseParametrizedValueTypeDTO;
import ch.nolix.system.objectschema.schemadto.ColumnDTO;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

//class
public final class ColumnDTOMapper {
	
	//method
	public ColumnDTO createColumnDTO(final List<String> columnSystemTableSqlRecord) {
		switch (PropertyType.valueOf(columnSystemTableSqlRecord.get(3)).getBaseType()) {
			case BASE_VALUE:
				return createColumnDTOForBaseValue(columnSystemTableSqlRecord);
			case BASE_BACK_REFERENCE:
				return createColumnDTOForBaseBackReference(columnSystemTableSqlRecord);
			case BASE_REFERENCE:
				return createColumnDTOForBaseReference(columnSystemTableSqlRecord);
			default:
				throw
				InvalidArgumentException.forArgumentNameAndArgument(
					"column system table record",
					columnSystemTableSqlRecord
				);
		}
	}
	
	//method
	private ColumnDTO createColumnDTOForBaseBackReference(final List<String> columnSystemTableSqlRecord) {
		return
		new ColumnDTO(
			columnSystemTableSqlRecord.get(0),
			columnSystemTableSqlRecord.get(1),
			new BaseParametrizedBackReferenceTypeDTO(
				PropertyType.valueOf(columnSystemTableSqlRecord.get(4)),
				DataType.valueOf(columnSystemTableSqlRecord.get(5)),
				columnSystemTableSqlRecord.get(6)
			)
		);
	}
	
	//method
	private ColumnDTO createColumnDTOForBaseReference(final List<String> columnSystemTableSqlRecord) {
		return
		new ColumnDTO(
			columnSystemTableSqlRecord.get(0),
			columnSystemTableSqlRecord.get(1),
			new BaseParametrizedReferenceTypeDTO(
				PropertyType.valueOf(columnSystemTableSqlRecord.get(3)),
				DataType.valueOf(columnSystemTableSqlRecord.get(4)),
				columnSystemTableSqlRecord.get(5)
			)
		);
	}
	
	//method
	private ColumnDTO createColumnDTOForBaseValue(final List<String> columnSystemTableSqlRecord) {
		return
		new ColumnDTO(
			columnSystemTableSqlRecord.get(0),
			columnSystemTableSqlRecord.get(1),
			new BaseParametrizedValueTypeDTO(
				PropertyType.valueOf(columnSystemTableSqlRecord.get(3)),
				DataType.valueOf(columnSystemTableSqlRecord.get(4))
			)
		);
	}
}
