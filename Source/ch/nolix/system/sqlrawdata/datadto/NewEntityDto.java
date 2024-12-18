package ch.nolix.system.sqlrawdata.datadto;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.rawdataapi.datadto.ContentFieldDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.INewEntityDto;

public record NewEntityDto(String id, ImmutableList<ContentFieldDto> contentFields) implements INewEntityDto {

  public NewEntityDto(final String id, final IContainer<ContentFieldDto> contentFields) {
    this(id, ImmutableList.forIterable(contentFields));
  }

  public NewEntityDto( //NOSONAR: This constructor does more than the default one.
    final String id,
    final ImmutableList<ContentFieldDto> contentFields) {

    if (id == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.ID);
    }

    if (contentFields == null) {
      throw ArgumentIsNullException.forArgumentName("content fields");
    }

    this.id = id;
    this.contentFields = contentFields;
  }

  @Override
  public IContainer<ContentFieldDto> getContentFields() {
    return contentFields;
  }

  @Override
  public String getId() {
    return id;
  }
}
