//package declaration
package ch.nolix.system.sqldatabaserawschema.structure;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.QualifiedNamed;

//enum
public enum MultiContentTable implements QualifiedNamed {
  MULTI_VALUE_ENTRY("MultiValueEntry"),
  MULTI_REFERENCE_ENTRY("MultiReferenceEntry"),
  MULTI_BACK_REFERENCE_ENTRY("MultiBackReferenceEntry");

  //constant
  private static final String NAME_PREFIX = TableType.MULTI_FIELD_TABLE.getNamePrefix();

  //attribute
  private final String name;

  //constructor
  MultiContentTable(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();

    this.name = name;
  }

  //method
  @Override
  public String getName() {
    return name;
  }

  //method
  @Override
  public final String getQualifyingPrefix() {
    return NAME_PREFIX;
  }
}
