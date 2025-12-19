package ch.nolix.systemapi.nodemidschema.nodemapper;

import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.midschema.model.ColumnDto;

/**
 * @author Silvan Wyss
 */
public interface IColumnNodeChildNodeMapper {
  /**
   * @param columnDto
   * @return a new back referenceable column ids {@link INode} from the given
   *         columnDto.
   * @throws RuntimeException if the given columnDto is null.
   */
  INode<?> mapColumnDtoToBackReferenceableColumnIdsNode(ColumnDto columnDto);

  /**
   * @param columnDto
   * @return a new data type {@link INode} from the given columnDto.
   * @throws RuntimeException if the given columnDto is null.
   */
  INode<?> mapColumnDtoToDataTypeNode(ColumnDto columnDto);

  /**
   * @param columnDto
   * @return a new field type {@link INode} from the given columnDto.
   * @throws RuntimeException if the given columnDto is null.
   */
  INode<?> mapColumnDtoToFieldTypeNode(ColumnDto columnDto);

  /**
   * @param columnDto
   * @return a new id {@link INode} from the given columnDto.
   * @throws RuntimeException if the given columnDto is null.
   */
  INode<?> mapColumnDtoToIdNode(ColumnDto columnDto);

  /**
   * @param columnDto
   * @return a new name {@link INode} from the given columnDto.
   * @throws RuntimeException if the given columnDto is null.
   */
  INode<?> mapColumnDtoToNameNode(ColumnDto columnDto);

  /**
   * @param columnDto
   * @return a new referenceable table ids node {@link INode} from the given
   *         columnDto.
   * @throws RuntimeException if the given columnDto is null.
   */
  INode<?> mapColumnDtoToReferenceableTableIdsNode(ColumnDto columnDto);
}
