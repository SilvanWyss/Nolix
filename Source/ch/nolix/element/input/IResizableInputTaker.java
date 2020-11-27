//package declaration
package ch.nolix.element.input;

//interface
/**
 * @author Silvan Wyss
 * @month 2020-06
 * @lines 40
 */
public interface IResizableInputTaker extends IInputTaker {
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	default void noteInput(final IInput<?> input) {
		
		if (input instanceof ResizeInput) {
			noteResizeInput(input.as(ResizeInput.class));
		}
		
		else {
			IInputTaker.super.noteInput(input);
		}
	}
	
	//method declaration
	/**
	 * Lets the current {@link IResizableInputTaker} note a resize.
	 * The given viewAreaWidth and viewAreaHeight are the new size of the view area.
	 * 
	 * @param viewAreaWidth
	 * @param viewAreaHeight
	 */
	void noteResize(int viewAreaWidth, int viewAreaHeight);
	
	//method
	/**
	 * Lets the current {@link IResizableInputTaker} note a resize.
	 * The viewAreaWidth and viewAreaHeight of the given resizeInput are the new size of the view area.
	 * 
	 * @param viewAreaWidth
	 * @param viewAreaHeight
	 */
	default void noteResizeInput(final ResizeInput resizeInput) {
		noteResize(resizeInput.getViewAreaWidth(), resizeInput.getViewAreaHeigh());
	}
}
