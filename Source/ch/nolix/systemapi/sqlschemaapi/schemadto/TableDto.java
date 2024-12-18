package ch.nolix.systemapi.sqlschemaapi.schemadto;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record TableDto(String name, IContainer<ColumnDto> columns) {

  public static TableDto withNameAndColumn(final String name, final ColumnDto column, final ColumnDto... columns) {

    final var allColumns = ContainerView.forElementAndArray(column, columns);

    return new TableDto(name, allColumns);
  }
}
