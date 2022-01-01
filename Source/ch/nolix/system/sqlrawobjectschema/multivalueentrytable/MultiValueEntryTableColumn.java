//package declaration
package ch.nolix.system.sqlrawobjectschema.multivalueentrytable;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Labeled;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;

//enum
public enum MultiValueEntryTableColumn implements Labeled {
	MULTI_VALUE_COLUMN("MutliValueColumn"),
	RECORD(PascalCaseCatalogue.RECORD),
	VALUE(PascalCaseCatalogue.VALUE);
	
	//attribute
	private final String label;
	
	//constructor
	MultiValueEntryTableColumn(final String label) {
		
		Validator.assertThat(label).thatIsNamed(LowerCaseCatalogue.LABEL).isNotBlank();
		
		this.label = label;
	}
	
	//method
	@Override
	public final String getLabel() {
		return label;
	}
}
