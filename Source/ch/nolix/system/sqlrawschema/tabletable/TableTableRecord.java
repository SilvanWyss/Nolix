package ch.nolix.system.sqlrawschema.tabletable;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;

public record TableTableRecord(String idValue, String nameValue) {

  //For a better performance, this implementation does not use all comfortable methods.
  public TableTableRecord( //NOSONAR: This constructor does more than the default one.
    final String idValue,
    final String nameValue) {

    if (idValue == null) {
      throw ArgumentIsNullException.forArgumentName("id value");
    }

    if (nameValue == null) {
      throw ArgumentIsNullException.forArgumentName("name value");
    }

    this.idValue = idValue;
    this.nameValue = nameValue;
  }

  public String getIdValue() {
    return idValue;
  }

  public String getNameValue() {
    return nameValue;
  }
}
