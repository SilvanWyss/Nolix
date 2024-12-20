package ch.nolix.system.sqlrawdata.datadto;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedContentFieldDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedEntityDto;

public record LoadedEntityDto(String id, String saveStamp, IContainer<ILoadedContentFieldDto> contentFields)
implements ILoadedEntityDto {

  public LoadedEntityDto( //NOSONAR: This implementations checks the given arguments.
    final String id,
    final String saveStamp,
    final IContainer<ILoadedContentFieldDto> contentFields) {

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
  public IContainer<ILoadedContentFieldDto> getContentFields() {
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
