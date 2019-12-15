//package declaration
package ch.nolix.element.GUI;

//interface
public interface IFrontEnd {
	
	//method declaration
	public abstract byte[] readFile();
	
	//method declaration
	public abstract void saveFile(byte[] bytes);
}
