//package declaration
package ch.nolix.system.sqlrawschema.databasepropertytable;

import ch.nolix.core.attributeuniversalapi.mandatoryattributeuniversalapi.Labeled;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.constant.PascalCaseCatalogue;
import ch.nolix.core.errorcontrol.validator.Validator;

//class
public enum DatabasePropertySystemTableColumn implements Labeled {
	
	//'Key' is a reserved word in MSSQL databases.
	KEY("ValueKey"),
	
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
