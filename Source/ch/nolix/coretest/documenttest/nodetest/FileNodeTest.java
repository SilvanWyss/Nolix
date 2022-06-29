//package declaration
package ch.nolix.coretest.documenttest.nodetest;

import ch.nolix.core.document.node.FileNode;
import ch.nolix.core.environment.filesystem.FileSystemAccessor;
import ch.nolix.core.testing.basetest.Cleanup;

//class
public final class FileNodeTest extends BaseMutableNodeTest<FileNode> {
	
	//method
	@Cleanup
	public void cleanup() {
		
		final var folderOfRunningJar = FileSystemAccessor.getFolderOfRunningJarFile();
		
		if (folderOfRunningJar.containsItem("fileNode")) {
			FileSystemAccessor.getFolderOfRunningJarFile().deleteFileSystemItem("fileNode");
		}
	}
	
	//method
	@Override
	protected FileNode createTestUnit() {
		return new FileNode(FileSystemAccessor.getFolderOfRunningJarFile().getPath() + "/fileNode");
	}
}
