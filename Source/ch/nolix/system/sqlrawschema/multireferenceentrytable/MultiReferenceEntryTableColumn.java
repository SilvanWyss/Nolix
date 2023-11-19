//package declaration
package ch.nolix.system.sqlrawschema.multireferenceentrytable;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.commontypeapi.stringutilapi.StringCatalogue;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;
import ch.nolix.system.sqlrawschema.structure.MultiEntryTableType;

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
