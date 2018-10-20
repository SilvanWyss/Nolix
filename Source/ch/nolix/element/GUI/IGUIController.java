//package declaration
package ch.nolix.element.GUI;

//own import
import ch.nolix.core.container.IContainer;

//interface
public interface IGUIController {
	
	//abstract
	public abstract IFileProvider getFileProvider(IContainer<Integer> indexPathOnRootGUI);
	
	//default method
	public default IFileProvider getFileProvider(final Widget<?, ?> widget) {
		return getFileProvider(widget.getIndexPathOnRootGUI());
	}
	
	//abstract method
	public abstract void noteLeftMouseButtonPressCommand(IContainer<Integer> indexPathOnRootGUI);
	
	//default method
	public default void noteLeftMouseButtonPressCommand(final Widget<?, ?> widget) {
		noteLeftMouseButtonPressCommand(widget.getIndexPathOnRootGUI());
	}
	
	//abstract method
	public abstract void noteLeftMouseButtonReleaseCommand(IContainer<Integer> indexPathOnRootGUI);
	
	//default method
	public default void noteLeftMouseButtonReleaseCommand(final Widget<?, ?> widget) {
		noteLeftMouseButtonReleaseCommand(widget.getIndexPathOnRootGUI());
	}
	
	//abstract method
	public abstract void noteRightMouseButtonPressCommand(IContainer<Integer> indexPathOnRootGUI);
	
	//default method
	public default void noteRightMouseButtonPressCommand(final Widget<?, ?> widget) {
		noteRightMouseButtonPressCommand(widget.getIndexPathOnRootGUI());
	}
	
	//abstract method
	public abstract void noteRightMouseButtonReleaseCommand(IContainer<Integer> indexPathOnRootGUI);
	
	//default method
	public default void noteRightMouseButtonReleaseCommand(final Widget<?, ?> widget) {
		noteRightMouseButtonReleaseCommand(widget.getIndexPathOnRootGUI());
	}
}
