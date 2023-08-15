//package declaration
package ch.nolix.systemapi.webguiapi.atomiccontrolapi;

//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IUploader extends IControl<IUploader, IUploaderStyle> {
	
	//method declaration
	IUploader setFileTaker(IElementTaker<Byte[]> fileTaker);
}
