//package declaration
package ch.nolix.system.sqlrawschema.databasepropertytable;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.ILabelHolder;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public enum DatabaseProperty implements ILabelHolder {
  SCHEMA_TIMESTAMP("SchemaTimestamp");

  //attribute
  private final String label;

  //constructor
  DatabaseProperty(final String label) {

    GlobalValidator.assertThat(label).thatIsNamed(LowerCaseVariableCatalogue.LABEL).isNotBlank();

    this.label = label;
  }

  //method
  @Override
  public final String getLabel() {
    return label;
  }
}
