//package declaration
package ch.nolix.system.sqlrawschema.databasepropertytable;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Labeled;

//class
public enum DatabasePropertySystemTableColumn implements Labeled {
	
	//'Key' is a reserved word in MSSQL databases.
	KEY("ValueKey"),
	
	VALUE(PascalCaseCatalogue.VALUE);
	
	//attribute
	private final String label;
	
	//constructor
	DatabasePropertySystemTableColumn(final String label) {
		
		GlobalValidator.assertThat(label).thatIsNamed(LowerCaseCatalogue.LABEL).isNotBlank();
		
		this.label = label;
	}
	
	//method
	@Override
	public final String getLabel() {
		return label;
	}
}
