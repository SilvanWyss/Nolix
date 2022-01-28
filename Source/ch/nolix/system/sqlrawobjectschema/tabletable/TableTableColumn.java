//package declaration
package ch.nolix.system.sqlrawobjectschema.tabletable;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.FullNamed;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.sqlrawobjectschema.structure.SystemDataTable;

//enum
public enum TableTableColumn implements FullNamed {
	ID(PascalCaseCatalogue.ID),
	NAME(PascalCaseCatalogue.NAME);
	
	//constant
	private static final String NAME_PREFIX = SystemDataTable.TABLE.getFullName() + StringCatalogue.DOT;
	
	//attribute
	private final String name;
	
	//constructor
	TableTableColumn(final String name) {
		
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
