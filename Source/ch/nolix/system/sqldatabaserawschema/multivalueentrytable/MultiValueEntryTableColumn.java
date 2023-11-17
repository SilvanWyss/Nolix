//package declaration
package ch.nolix.system.sqldatabaserawschema.multivalueentrytable;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.commontypeapi.stringutilapi.StringCatalogue;
import ch.nolix.system.sqldatabaserawschema.structure.MultiEntryTableType;

//enum
public enum MultiValueEntryTableColumn implements INameHolder {
  MULTI_VALUE_COLUMN_ID("MultiValueColumnId"),
  ENTITY_ID("EntityId"),
  VALUE(PascalCaseCatalogue.VALUE);

  //constant
  private static final String NAME_PREFIX = MultiEntryTableType.MULTI_VALUE_ENTRY.getQualifiedName()
  + StringCatalogue.DOT;

  //attribute
  private final String name;

  //constructor
  MultiValueEntryTableColumn(final String name) {

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
