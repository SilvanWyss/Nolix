//package delcaration
package ch.nolix.coreTest.fileNodeTest;

import ch.nolix.common.baseTest.AfterTestCase;
import ch.nolix.common.fileNode.FileNode;
import ch.nolix.common.fileSystem.FileSystemAccessor;
import ch.nolix.coreTest.nodeTest.BaseNodeTest;

//test class
public final class FileNodeTest extends BaseNodeTest {
	
	@Override
	protected FileNode createTestObject() {
		return new FileNode(FileSystemAccessor.getFolderOfRunningJarFile().getPath() + "/fileNode");
	}
	
	@AfterTestCase
	private void afterTestCase() {
		FileSystemAccessor.getFolderOfRunningJarFile().deleteFileSystemItem("fileNode");
	}
}
