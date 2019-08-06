//package delcaration
package ch.nolix.coreTest.documentNodeTest;

import ch.nolix.core.baseTest.AfterTestCase;
//own imports
import ch.nolix.core.documentNode.FileDocumentNode;
import ch.nolix.core.fileSystem.FileSystemAccessor;

//test class
public final class FileDocumentNodeTest extends DocumentNodeoidTest {
	
	@Override
	protected FileDocumentNode createTestObject() {
		return new FileDocumentNode(FileSystemAccessor.getFolderOfRunningJarFile().getPath() + "/documentNode");
	}
	
	@AfterTestCase
	private void afterTestCase() {
		FileSystemAccessor.getFolderOfRunningJarFile().deleteFileSystemItem("documentNode");
	}
}
