package ch.nolix.coretest.document.node;

import org.junit.jupiter.api.AfterEach;

import ch.nolix.core.document.node.FileNode;
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.environment.filesystem.FileSystemAccessor;

final class FileNodeTest extends BaseMutableNodeTest<FileNode> {
  @AfterEach
  void cleanup() {
    final var folderOfRunningJar = FileSystemAccessor.getFolderOfRunningJarFile();

    if (folderOfRunningJar.containsItem("fileNode")) {
      FileSystemAccessor.getFolderOfRunningJarFile().deleteFileSystemItem("fileNode");
    }
  }

  @Override
  protected FileNode createBlankNode() {
    return new FileNode(FileSystemAccessor.getFolderOfRunningJarFile().getPath() + "/fileNode");
  }

  @Override
  protected FileNode createNodeWithHeader(String header) {
    final var fileNode = new FileNode(FileSystemAccessor.getFolderOfRunningJarFile().getPath() + "/fileNode");

    fileNode.setHeader(header);

    return fileNode;
  }

  @Override
  protected FileNode createNodeWithHeaderAndChildNodes(final String header, final String... childNodeHeaders) {
    final var fileNode = new FileNode(FileSystemAccessor.getFolderOfRunningJarFile().getPath() + "/fileNode");

    fileNode.setHeader(header);

    for (final var cnh : childNodeHeaders) {
      final var childNode = MutableNode.createEmpty();
      childNode.setHeader(cnh);

      fileNode.addChildNode(childNode);
    }

    return fileNode;
  }
}
