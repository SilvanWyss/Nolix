package ch.nolix.systemapi.sqlschemaapi.schemadto;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record ColumnDto(String name, DataTypeDto dataType, IContainer<ConstraintDto> constraints) {

  public static ColumnDto withNameAndDataType(final String name, final DataTypeDto dataType) {
    return new ColumnDto(name, dataType, ImmutableList.createEmpty());
  }
}
