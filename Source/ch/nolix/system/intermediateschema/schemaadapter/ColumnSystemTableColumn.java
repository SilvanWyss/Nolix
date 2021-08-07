//package declaration
package ch.nolix.system.intermediateschema.schemaadapter;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;

//enum
public enum ColumnSystemTableColumn implements Named {
	TABLE(PascalCaseCatalogue.TABLE),
	HEADER(PascalCaseCatalogue.HEADER),
	DATA_TYPE(PascalCaseCatalogue.DATA_TYPE),
	REFERENCED_TABLE("ReferencedTable"),
	BACK_REFERENCED_TABLE("BackReferencedTable"),
	BACK_REFERENCED_COLUM("BackReferencedColumn");
	
	//attribute
	private final String name;
	
	//constructor
	ColumnSystemTableColumn(final String name) {
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		
		this.name = name;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
}
