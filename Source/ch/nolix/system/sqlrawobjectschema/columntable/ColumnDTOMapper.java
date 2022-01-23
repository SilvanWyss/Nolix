//package declaration
package ch.nolix.system.sqlrawobjectschema.columntable;

//Java imports
import java.util.List;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectschema.schemadto.BaseParametrizedBackReferenceTypeDTO;
import ch.nolix.system.objectschema.schemadto.BaseParametrizedReferenceTypeDTO;
import ch.nolix.system.objectschema.schemadto.BaseParametrizedValueTypeDTO;
import ch.nolix.system.objectschema.schemadto.ColumnDTO;
import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;

//class
public final class ColumnDTOMapper {
	
	//method
	public ColumnDTO createColumnDTO(final List<String> columnSystemTableRecord) {
		switch (PropertyType.valueOf(columnSystemTableRecord.get(2)).getBaseType()) {
			case BASE_VALUE:
				return createColumnDTOForBaseValue(columnSystemTableRecord);
			case BASE_BACK_REFERENCE:
				return createColumnDTOForBaseBackReference(columnSystemTableRecord);
			case BASE_REFERENCE:
				return createColumnDTOForBaseReference(columnSystemTableRecord);
			default:
				throw
				new InvalidArgumentException("column system table record", columnSystemTableRecord, "is not valid");
		}
	}
	
	//method
	private ColumnDTO createColumnDTOForBaseBackReference(final List<String> columnSystemTableRecord) {
		
		//TODO: Complete.
		return
		new ColumnDTO(
			"Id",
			columnSystemTableRecord.get(0),
			new BaseParametrizedBackReferenceTypeDTO(
				PropertyType.valueOf(columnSystemTableRecord.get(2)),
				columnSystemTableRecord.get(3),
				columnSystemTableRecord.get(5),
				columnSystemTableRecord.get(6)
			)
		);
	}
	
	//method
	private ColumnDTO createColumnDTOForBaseReference(final List<String> columnSystemTableRecord) {
		
		//TODO: Complete.
		return
		new ColumnDTO(
			"Id",
			columnSystemTableRecord.get(0),
			new BaseParametrizedReferenceTypeDTO(
				PropertyType.valueOf(columnSystemTableRecord.get(2)),
				columnSystemTableRecord.get(3),
				columnSystemTableRecord.get(4)
			)
		);
	}
	
	//method
	private ColumnDTO createColumnDTOForBaseValue(final List<String> columnSystemTableRecord) {
		
		//TODO: Complete.
		return
		new ColumnDTO(
			"Id",
			columnSystemTableRecord.get(0),
			new BaseParametrizedValueTypeDTO(
				PropertyType.valueOf(columnSystemTableRecord.get(2)),
				columnSystemTableRecord.get(3)
			)
		);
	}
}
