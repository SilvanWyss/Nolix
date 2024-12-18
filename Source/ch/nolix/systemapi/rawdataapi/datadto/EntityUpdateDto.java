package ch.nolix.systemapi.rawdataapi.datadto;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record EntityUpdateDto(String id, String saveStamp, IContainer<ContentFieldWithContentAsStringDto> updatedContentFields) {

  public EntityUpdateDto(String id, String saveStamp, ContentFieldWithContentAsStringDto updatedContentField) {
    this(id, saveStamp, ImmutableList.withElement(updatedContentField));
  }
}
