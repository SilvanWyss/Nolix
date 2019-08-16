//package delcaration
package ch.nolix.coreTest.fileNodeTest;

import ch.nolix.core.baseTest.AfterTestCase;
import ch.nolix.core.fileNode.FileNode;
import ch.nolix.core.fileSystem.FileSystemAccessor;
import ch.nolix.coreTest.nodeTest.BaseNodeTest;

//test class
public final class FileNodeTest extends BaseNodeTest {
	
	@Override
	protected FileNode createTestObject() {
		return new FileNode(FileSystemAccessor.getFolderOfRunningJarFile().getPath() + "/documentNode");
	}
	
	@AfterTestCase
	private void afterTestCase() {
		FileSystemAccessor.getFolderOfRunningJarFile().deleteFileSystemItem("documentNode");
	}
}
