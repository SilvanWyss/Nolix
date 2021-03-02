//package delcaration
package ch.nolix.commontest.documenttest.filenodetest;

//own imports
import ch.nolix.common.basetest.Cleanup;
import ch.nolix.common.document.filenode.FileNode;
import ch.nolix.common.filesystem.FileSystemAccessor;
import ch.nolix.commontest.documenttest.nodetest.BaseNodeTest;

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
