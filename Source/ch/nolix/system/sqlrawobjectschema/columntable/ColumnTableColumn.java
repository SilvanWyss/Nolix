//package declaration
package ch.nolix.system.sqlrawobjectschema.columntable;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Labeled;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;

//enum
public enum ColumnTableColumn implements Labeled {
	ID(PascalCaseCatalogue.ID),
	PARENT_TABLE_ID("ParentTableId"),
	NAME(PascalCaseCatalogue.NAME),
	PROPERTY_TYPE("PropertyType"),
	DATA_TYPE(PascalCaseCatalogue.DATA_TYPE),
	REFERENCED_TABLE_ID("ReferencedTableId"),
	BACK_REFERENCED_COLUM_ID("BackReferencedColumnId");
	
	//attribute
	private final String label;
	
	//constructor
	ColumnTableColumn(final String label) {
		
		Validator.assertThat(label).thatIsNamed(LowerCaseCatalogue.LABEL).isNotBlank();
		
		this.label = label;
	}
	
	//method
	@Override
	public String getLabel() {
		return label;
	}
}
