//package declaration
package ch.nolix.system.objectschema.schemaadapter;

//own imports
import ch.nolix.core.commontype.commontypeconstant.RegularExpressionPatternCatalogue;
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.environment.filesystem.FileAccessor;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

//class
public final class NodeSchemaAdapter extends SchemaAdapter {

  //constructor
  private NodeSchemaAdapter(final String databaseName, final IMutableNode<?> databaseNode) {
    super(databaseName,
      ch.nolix.system.nodedatabaserawschema.schemaadapter.SchemaAdapter.forDatabaseNode(databaseNode));
  }

  //static method
  public static NodeSchemaAdapter forDatabaseNode(final String databaseName, final IMutableNode<?> databaseNode) {
    return new NodeSchemaAdapter(databaseName, databaseNode);
  }

  //static method
  public static NodeSchemaAdapter forDatabaseNodeInFile(final String filePath) {

    final var fileName = new FileAccessor(filePath).getName();
    final var databaseName = RegularExpressionPatternCatalogue.DOT_PATTERN.split(fileName)[0];
    final var databaseNode = MutableNode.fromFile(filePath);

    return new NodeSchemaAdapter(databaseName, databaseNode);
  }
}
