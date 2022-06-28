//package declaration
package ch.nolix.coretest.documenttest.filenodetest;

//own imports
import ch.nolix.core.document.filenode.FileNode;
import ch.nolix.core.environment.filesystem.FileSystemAccessor;
import ch.nolix.core.testing.basetest.Cleanup;
import ch.nolix.coretest.documenttest.nodetest.BaseNodeTest;

//class
public final class FileNodeTest extends BaseNodeTest<FileNode> {
	
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
