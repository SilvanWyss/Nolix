//package declaration
package ch.nolix.element.GUI;

//interface
public interface IFrontEnd {
	
	//abstract method
	public abstract byte[] readFile();
	
	//abstract method
	public abstract void saveFile(byte[] bytes);
}
