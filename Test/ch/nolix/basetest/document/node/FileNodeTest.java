/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.document.node;

import org.junit.jupiter.api.AfterEach;

import ch.nolix.base.document.node.FileNode;
import ch.nolix.base.document.node.MutableNode;
import ch.nolix.base.environment.filesystem.FileSystemAccessor;

/**
 * @author Silvan Wyss
 */
final class FileNodeTest extends BaseMutableNodeTest<FileNode> {
  @AfterEach
  void cleanup() {
    final var folderOfRunningJar = FileSystemAccessor.getFolderOfRunningJarFile();

    if (folderOfRunningJar.containsItem("fileNode")) {
      FileSystemAccessor.getFolderOfRunningJarFile().deleteFileSystemItem("fileNode");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected FileNode createBlankNode() {
    final var filePath = FileSystemAccessor.getFolderOfRunningJarFile().getPath() + "/fileNode";

    return FileNode.withFilePath(filePath);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected FileNode createNodeWithHeader(String header) {
    final var filePath = FileSystemAccessor.getFolderOfRunningJarFile().getPath() + "/fileNode";
    final var fileNode = FileNode.withFilePath(filePath);

    fileNode.setHeader(header);

    return fileNode;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected FileNode createNodeWithHeaderAndChildNodes(final String header, final String... childNodes) {
    final var filePath = FileSystemAccessor.getFolderOfRunningJarFile().getPath() + "/fileNode";
    final var fileNode = FileNode.withFilePath(filePath);

    fileNode.setHeader(header);

    for (final var c : childNodes) {
      final var childNode = MutableNode.fromString(c);

      fileNode.addChildNode(childNode);
    }

    return fileNode;
  }
}
