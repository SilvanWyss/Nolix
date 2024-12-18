package ch.nolix.systemapi.rawdataapi.datadto;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record EntityUpdateDto(String id, String saveStamp, IContainer<ContentFieldDto> updatedContentFields) {

  public EntityUpdateDto(String id, String saveStamp, ContentFieldDto updatedContentField) {
    this(id, saveStamp, ImmutableList.withElement(updatedContentField));
  }
}
