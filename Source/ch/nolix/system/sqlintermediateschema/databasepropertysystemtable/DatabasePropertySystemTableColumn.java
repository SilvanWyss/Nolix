//package declaration
package ch.nolix.system.sqlintermediateschema.databasepropertysystemtable;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Labeled;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;

//class
public enum DatabasePropertySystemTableColumn implements Labeled {
	KEY(PascalCaseCatalogue.KEY),
	VALUE(PascalCaseCatalogue.VALUE);
	
	//attribute
	private final String label;
	
	//constructor
	DatabasePropertySystemTableColumn(final String label) {
		
		Validator.assertThat(label).thatIsNamed(LowerCaseCatalogue.LABEL).isNotBlank();
		
		this.label = label;
	}
	
	//method
	@Override
	public final String getLabel() {
		return label;
	}
}
