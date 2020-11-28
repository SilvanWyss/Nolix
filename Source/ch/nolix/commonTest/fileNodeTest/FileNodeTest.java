//package delcaration
package ch.nolix.commonTest.fileNodeTest;

import ch.nolix.common.basetest.Cleanup;
import ch.nolix.common.filenode.FileNode;
import ch.nolix.common.filesystem.FileSystemAccessor;
import ch.nolix.commonTest.nodeTest.BaseNodeTest;

//class
public final class FileNodeTest extends BaseNodeTest {
	
	@Override
	protected FileNode createTestUnit() {
		return new FileNode(FileSystemAccessor.getFolderOfRunningJarFile().getPath() + "/fileNode");
	}
	
	@Cleanup
	private void afterTestCase() {
		FileSystemAccessor.getFolderOfRunningJarFile().deleteFileSystemItem("fileNode");
	}
}
