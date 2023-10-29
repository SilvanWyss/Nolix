//package declaration
package ch.nolix.system.sqldatabaserawschema.multireferenceentrytable;

//own imports
import ch.nolix.core.commontype.commontypeconstant.StringCatalogue;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.system.sqldatabaserawschema.structure.MultiEntryTableType;

//enum
public enum MultiReferenceEntryTableColumn implements INameHolder {
  MULTI_REFERENCE_COLUMN_ID("MultiReferenceColumnId"),
  ENTITY_ID("EntityId"),
  REFERENCED_ENTITY_ID("ReferencedEntityId");

  //constant
  private static final String NAME_PREFIX = MultiEntryTableType.MULTI_REFERENCE_ENTRY.getQualifiedName()
  + StringCatalogue.DOT;

  //attribute
  private final String name;

  //constructor
  MultiReferenceEntryTableColumn(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();

    this.name = name;
  }

  //method
  @Override
  public final String getName() {
    return name;
  }

  //method
  public String getQualifiedName() {
    return (getQualifyingPrefix() + getName());
  }

  //method
  public String getQualifyingPrefix() {
    return NAME_PREFIX;
  }
}
