//package declaration
package ch.nolix.system.sqlintermediateschema.columnsystemtable;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Labeled;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;

//enum
public enum ColumnSystemTableColumn implements Labeled {
	PARENT_TABLE("ParentTable"),
	HEADER(PascalCaseCatalogue.HEADER),
	PROPERTY_TYPE("PropertyType"),
	DATA_TYPE(PascalCaseCatalogue.DATA_TYPE),
	REFERENCED_TABLE("ReferencedTable"),
	BACK_REFERENCED_TABLE("BackReferencedTable"),
	BACK_REFERENCED_COLUM("BackReferencedColumn");
	
	//attribute
	private final String label;
	
	//constructor
	ColumnSystemTableColumn(final String label) {
		
		Validator.assertThat(label).thatIsNamed(LowerCaseCatalogue.LABEL).isNotBlank();
		
		this.label = label;
	}
	
	//method
	@Override
	public String getLabel() {
		return label;
	}
}
