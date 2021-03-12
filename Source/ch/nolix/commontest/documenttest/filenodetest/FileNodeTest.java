//package declaration
package ch.nolix.commontest.documenttest.filenodetest;

//own imports
import ch.nolix.common.document.filenode.FileNode;
import ch.nolix.common.environment.filesystem.FileSystemAccessor;
import ch.nolix.common.testing.basetest.Cleanup;
import ch.nolix.commontest.documenttest.nodetest.BaseNodeTest;

//class
public final class FileNodeTest extends BaseNodeTest {
	
	//method
	@Cleanup
	public void cleanup() {
		FileSystemAccessor.getFolderOfRunningJarFile().deleteFileSystemItem("fileNode");
	}
	
	//method
	@Override
	protected FileNode createTestUnit() {
		return new FileNode(FileSystemAccessor.getFolderOfRunningJarFile().getPath() + "/fileNode");
	}
}
