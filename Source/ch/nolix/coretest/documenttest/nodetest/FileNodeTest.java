//package declaration
package ch.nolix.coretest.documenttest.nodetest;

//own imports
import ch.nolix.core.document.node.FileNode;
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.environment.filesystem.GlobalFileSystemAccessor;
import ch.nolix.coreapi.testingapi.testapi.Cleanup;

//class
public final class FileNodeTest extends BaseMutableNodeTest<FileNode> {

  //method
  @Cleanup
  public void cleanup() {

    final var folderOfRunningJar = GlobalFileSystemAccessor.getFolderOfRunningJarFile();

    if (folderOfRunningJar.containsItem("fileNode")) {
      GlobalFileSystemAccessor.getFolderOfRunningJarFile().deleteFileSystemItem("fileNode");
    }
  }

  //method
  @Override
  protected FileNode createBlankNode() {
    return new FileNode(GlobalFileSystemAccessor.getFolderOfRunningJarFile().getPath() + "/fileNode");
  }

  //method
  @Override
  protected FileNode createNodeWithHeaderAndChildNodes(final String header, final String... childNodeHeaders) {

    final var fileNode = new FileNode(GlobalFileSystemAccessor.getFolderOfRunningJarFile().getPath() + "/fileNode");

    fileNode.setHeader(header);

    for (final var cnh : childNodeHeaders) {

      final var childNode = new MutableNode();
      childNode.setHeader(cnh);

      fileNode.addChildNode(childNode);
    }

    return fileNode;
  }
}
