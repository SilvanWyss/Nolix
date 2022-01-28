//package declaration
package ch.nolix.system.sqlrawobjectschema.columntable;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.FullNamed;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.sqlrawobjectschema.structure.SystemDataTable;

//enum
public enum ColumnTableColumn implements FullNamed {
	ID(PascalCaseCatalogue.ID),
	PARENT_TABLE_ID("ParentTableId"),
	NAME(PascalCaseCatalogue.NAME),
	PROPERTY_TYPE("PropertyType"),
	DATA_TYPE(PascalCaseCatalogue.DATA_TYPE),
	REFERENCED_TABLE_ID("ReferencedTableId"),
	BACK_REFERENCED_COLUM_ID("BackReferencedColumnId");
	
	//constant
	private static final String NAME_PREFIX = SystemDataTable.COLUMN.getFullName() + StringCatalogue.DOT;
	
	//attribute
	private final String name;
	
	//constructor
	ColumnTableColumn(final String name) {
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		
		this.name = name;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
	
	//method
	@Override
	public String getNamePrefix() {
		return NAME_PREFIX;
	}
}
