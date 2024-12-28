package ch.nolix.system.noderawdata.dtomapper;

import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.sqlrawdata.datamapper.ValueMapper;
import ch.nolix.systemapi.noderawdataapi.dtomapperapi.IContentFieldDtoMapper;
import ch.nolix.systemapi.rawdataapi.dto.ContentFieldDto;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.IColumnView;
import ch.nolix.systemapi.sqlrawdataapi.datamapperapi.IValueMapper;

public final class ContentFieldDtoMapper implements IContentFieldDtoMapper {

  private static final IValueMapper VALUE_MAPPER = new ValueMapper();

  @Override
  public ContentFieldDto<Object> mapContentFieldNodeToContentFieldDto(
    final INode<?> contentFieldNode,
    final IColumnView columnInfo) {

    if (contentFieldNode.containsChildNodes()) {
      return ContentFieldDto.withColumnName(columnInfo.getColumnName());
    }

    if (!contentFieldNode.hasHeader()) {
      return ContentFieldDto.withColumnName(columnInfo.getColumnName());
    }

    return ContentFieldDto.withColumnNameAndContent(
      columnInfo.getColumnName(),
      VALUE_MAPPER.mapValueToString(contentFieldNode.getHeader(), columnInfo.getColumnDataType()));
  }
}
