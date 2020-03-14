//package delcaration
package ch.nolix.commonTest.fileNodeTest;

import ch.nolix.common.baseTest.Cleanup;
import ch.nolix.common.fileNode.FileNode;
import ch.nolix.common.fileSystem.FileSystemAccessor;
import ch.nolix.commonTest.nodeTest.BaseNodeTest;

//test class
public final class FileNodeTest extends BaseNodeTest {
	
	@Override
	protected FileNode createTestObject() {
		return new FileNode(FileSystemAccessor.getFolderOfRunningJarFile().getPath() + "/fileNode");
	}
	
	@Cleanup
	private void afterTestCase() {
		FileSystemAccessor.getFolderOfRunningJarFile().deleteFileSystemItem("fileNode");
	}
}
