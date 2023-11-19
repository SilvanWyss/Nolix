//package declaration
package ch.nolix.system.sqlrawschema.databasepropertytable;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.ILabelHolder;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;
import ch.nolix.coreapi.programatomapi.variablenameapi.PascalCaseCatalogue;

//class
public enum DatabasePropertySystemTableColumn implements ILabelHolder {

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
