//package declaration
package ch.nolix.systemapi.guiapi.inputapi;

import ch.nolix.coreapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * @author Silvan Wyss
 * @date 2020-06-06
 */
@AllowDefaultMethodsAsDesignPattern
public interface IResizableInputTaker extends IInputTaker {
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	default void noteInput(final IInput<?> input) {
		if (input instanceof IResizeInput) {
			noteResizeInput((IResizeInput<?>)input);
		} else {
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
	 * @param resizeInput
	 */
	default void noteResizeInput(final IResizeInput<?> resizeInput) {
		noteResize(resizeInput.getViewAreaWidth(), resizeInput.getViewAreaHeight());
	}
}
