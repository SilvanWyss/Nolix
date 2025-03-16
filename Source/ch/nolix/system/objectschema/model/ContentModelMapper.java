package ch.nolix.system.objectschema.model;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.midschemaapi.modelapi.BackReferenceModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.IContentModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.MultiBackReferenceModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.MultiReferenceModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.MultiValueModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.OptionalBackReferenceModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.OptionalReferenceModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.OptionalValueModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.ReferenceModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.ValueModelDto;
import ch.nolix.systemapi.objectschemaapi.modelapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

public final class ContentModelMapper {

  private ContentModelMapper() {
  }

  public static IContentModel mapRawContentModelDtoToContentModel(
    final IContentModelDto rawContentModelDto,
    final IContainer<ITable> tables) {

    if (rawContentModelDto instanceof ValueModelDto(DataType dataType)) {
      return ValueModel.forDataType(dataType);
    }

    if (rawContentModelDto instanceof OptionalValueModelDto(DataType dataType)) {
      return ValueModel.forDataType(dataType);
    }

    if (rawContentModelDto instanceof MultiValueModelDto(DataType dataType)) {
      return ValueModel.forDataType(dataType);
    }

    if (rawContentModelDto instanceof ReferenceModelDto referenceModelDto) {

      final var referencedTableId = referenceModelDto.referencedTableId();
      final var referencedTable = tables.getStoredFirst(t -> t.hasId(referencedTableId));

      return ReferenceModel.forReferencedTable(referencedTable);
    }

    if (rawContentModelDto instanceof OptionalReferenceModelDto optionalReferenceModelDto) {

      final var referencedTableId = optionalReferenceModelDto.referencedTableId();
      final var referencedTable = tables.getStoredFirst(t -> t.hasId(referencedTableId));

      return OptionalReferenceModel.forReferencedTable(referencedTable);
    }

    if (rawContentModelDto instanceof MultiReferenceModelDto multiBackReferenceModelDto) {

      final var referencedTableId = multiBackReferenceModelDto.referencedTableId();
      final var referencedTable = tables.getStoredFirst(t -> t.hasId(referencedTableId));

      return MultiReferenceModel.forReferencedTable(referencedTable);
    }

    if (rawContentModelDto instanceof BackReferenceModelDto backReferenceModelDto) {

      final var backReferenceColumnId = backReferenceModelDto.backReferencedColumnId();
      final var columns = tables.toMultiples(ITable::getStoredColumns);
      final var backReferencedColumn = columns.getStoredFirst(c -> c.hasId(backReferenceColumnId));

      return BackReferenceModel.forBackReferencedColumn(backReferencedColumn);
    }

    if (rawContentModelDto instanceof OptionalBackReferenceModelDto optionalBackReferenceModelDto) {

      final var backReferencedColumnId = optionalBackReferenceModelDto.backReferencedColumnId();
      final var columns = tables.toMultiples(ITable::getStoredColumns);
      final var backReferencedColumn = columns.getStoredFirst(c -> c.hasId(backReferencedColumnId));

      return OptionalBackReferenceModel.forBackReferencedColumn(backReferencedColumn);
    }

    if (rawContentModelDto instanceof MultiBackReferenceModelDto multiBackReferenceModelDto) {

      final var backReferencedColumnId = multiBackReferenceModelDto.backReferencedColumnId();
      final var columns = tables.toMultiples(ITable::getStoredColumns);
      final var backReferencedColumn = columns.getStoredFirst(c -> c.hasId(backReferencedColumnId));

      return MultiBackReferenceModel.forBackReferencedColumn(backReferencedColumn);
    }

    throw InvalidArgumentException.forArgument(rawContentModelDto);
  }
}
