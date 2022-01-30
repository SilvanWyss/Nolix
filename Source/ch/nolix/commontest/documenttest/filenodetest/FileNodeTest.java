//package declaration
package ch.nolix.commontest.documenttest.filenodetest;

import ch.nolix.commontest.documenttest.nodetest.BaseNodeTest;
import ch.nolix.core.document.filenode.FileNode;
import ch.nolix.core.environment.filesystem.FileSystemAccessor;
import ch.nolix.core.testing.basetest.Cleanup;

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
