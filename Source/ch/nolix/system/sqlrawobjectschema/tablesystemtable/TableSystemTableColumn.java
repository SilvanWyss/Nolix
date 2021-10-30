//package declaration
package ch.nolix.system.sqlrawobjectschema.tablesystemtable;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Labeled;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;

//enum
public enum TableSystemTableColumn implements Labeled {
	NAME(PascalCaseCatalogue.NAME);
	
	//attribute
	private final String label;
	
	//constructor
	TableSystemTableColumn(final String label) {
		
		Validator.assertThat(label).thatIsNamed(LowerCaseCatalogue.LABEL).isNotBlank();
		
		this.label = label;
	}
	
	//method
	@Override
	public String getLabel() {
		return label;
	}
}
