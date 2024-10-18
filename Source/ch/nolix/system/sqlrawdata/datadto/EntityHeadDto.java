package ch.nolix.system.sqlrawdata.datadto;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityHeadDto;

public record EntityHeadDto(String id, String saveStamp) implements IEntityHeadDto {

  public EntityHeadDto(final String id, final String saveStamp) { //NOSONAR: This implementations checks the given
                                                                  //arguments.

    if (id == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.ID);
    }

    if (saveStamp == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.SAVE_STAMP);
    }

    this.id = id;
    this.saveStamp = saveStamp;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getSaveStamp() {
    return saveStamp;
  }
}
