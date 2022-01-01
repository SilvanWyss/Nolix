//package declaration
package ch.nolix.system.sqlrawobjectschema.multireferenceentrytable;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Labeled;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;

//enum
public enum MultiReferenceEntryTableColumn implements Labeled {
	MULTI_REFERENCE_COLUMN("MutliReferenceColumn"),
	RECORD(PascalCaseCatalogue.RECORD),
	REFERENCED_RECORD("ReferencedRecord");
	
	//attribute
	private final String label;
	
	//constructor
	MultiReferenceEntryTableColumn(final String label) {
		
		Validator.assertThat(label).thatIsNamed(LowerCaseCatalogue.LABEL).isNotBlank();
		
		this.label = label;
	}
	
	//method
	@Override
	public final String getLabel() {
		return label;
	}
}
