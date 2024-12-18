package ch.nolix.system.sqlrawdata.datadto;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.rawdataapi.datadto.ContentFieldDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedEntityDto;

public record LoadedEntityDto(String id, String saveStamp, IContainer<ContentFieldDto<Object>> contentFields)
implements ILoadedEntityDto {

  public LoadedEntityDto( //NOSONAR: This implementations checks the given arguments.
    final String id,
    final String saveStamp,
    final IContainer<ContentFieldDto<Object>> contentFields) {

    if (id == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.ID);
    }

    if (saveStamp == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.SAVE_STAMP);
    }

    if (contentFields == null) {
      throw ArgumentIsNullException.forArgumentName("content fields");
    }

    this.id = id;
    this.saveStamp = saveStamp;
    this.contentFields = contentFields;
  }

  @Override
  public IContainer<ContentFieldDto<Object>> getContentFields() {
    return contentFields;
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
