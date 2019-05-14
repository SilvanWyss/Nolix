//package delcaration
package ch.nolix.coreTest.documentNodeTest;

//own imports
import ch.nolix.core.documentNode.FileDocumentNode;
import ch.nolix.core.fileSystem.FileSystemAccessor;
import ch.nolix.core.testoid.AfterTestCase;

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
