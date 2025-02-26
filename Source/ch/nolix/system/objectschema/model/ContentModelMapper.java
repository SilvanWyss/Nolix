package ch.nolix.system.objectschema.model;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectschemaapi.modelapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;
import ch.nolix.systemapi.rawschemaapi.modelapi.BackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.IContentModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.MultiBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.MultiReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.MultiValueModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.OptionalBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.OptionalReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.OptionalValueModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.ReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.ValueModelDto;

public final class ContentModelMapper {

  public IContentModel mapContentModelDtoToContentModel(
    final IContentModelDto contentModelDto,
    final IContainer<ITable> tables) {

    if (contentModelDto instanceof ValueModelDto(DataType dataType)) {
      return ValueModel.forDataType(dataType);
    }

    if (contentModelDto instanceof OptionalValueModelDto(DataType dataType)) {
      return ValueModel.forDataType(dataType);
    }

    if (contentModelDto instanceof MultiValueModelDto(DataType dataType)) {
      return ValueModel.forDataType(dataType);
    }

    if (contentModelDto instanceof ReferenceModelDto referenceModelDto) {

      final var referencedTableId = referenceModelDto.referencedTableId();
      final var referencedTable = tables.getStoredFirst(t -> t.hasId(referencedTableId));

      return ReferenceModel.forReferencedTable(referencedTable);
    }

    if (contentModelDto instanceof OptionalReferenceModelDto optionalReferenceModelDto) {

      final var referencedTableId = optionalReferenceModelDto.referencedTableId();
      final var referencedTable = tables.getStoredFirst(t -> t.hasId(referencedTableId));

      return OptionalReferenceModel.forReferencedTable(referencedTable);
    }

    if (contentModelDto instanceof MultiReferenceModelDto multiBackReferenceModelDto) {

      final var referencedTableId = multiBackReferenceModelDto.referencedTableId();
      final var referencedTable = tables.getStoredFirst(t -> t.hasId(referencedTableId));

      return MultiReferenceModel.forReferencedTable(referencedTable);
    }

    if (contentModelDto instanceof BackReferenceModelDto backReferenceModelDto) {

      final var backReferenceColumnId = backReferenceModelDto.backReferencedColumnId();
      final var columns = tables.toMultiple(ITable::getStoredColumns);
      final var backReferencedColumn = columns.getStoredFirst(c -> c.hasId(backReferenceColumnId));

      return BackReferenceModel.forBackReferencedColumn(backReferencedColumn);
    }

    if (contentModelDto instanceof OptionalBackReferenceModelDto optionalBackReferenceModelDto) {

      final var backReferencedColumnId = optionalBackReferenceModelDto.backReferencedColumnId();
      final var columns = tables.toMultiple(ITable::getStoredColumns);
      final var backReferencedColumn = columns.getStoredFirst(c -> c.hasId(backReferencedColumnId));

      return OptionalBackReferenceModel.forBackReferencedColumn(backReferencedColumn);
    }

    if (contentModelDto instanceof MultiBackReferenceModelDto multiBackReferenceModelDto) {

      final var backReferencedColumnId = multiBackReferenceModelDto.backReferencedColumnId();
      final var columns = tables.toMultiple(ITable::getStoredColumns);
      final var backReferencedColumn = columns.getStoredFirst(c -> c.hasId(backReferencedColumnId));

      return MultiBackReferenceModel.forBackReferencedColumn(backReferencedColumn);
    }

    throw InvalidArgumentException.forArgument(contentModelDto);
  }
}
