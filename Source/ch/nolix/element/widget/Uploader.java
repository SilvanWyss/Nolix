//package declaration
package ch.nolix.element.widget;

import ch.nolix.element.input.Key;

//class
public final class Uploader extends TextLineWidget<Uploader,UploaderLook> {
	
	//constructor
	public Uploader() {
		resetAndApplyDefaultConfiguration();
	}
	
	//method
	@Override
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	@Override
	public Uploader reset() {
		
		super.reset();
		
		setText("Uploade");
		
		return this;
	}
	
	//method
	@Override
	public boolean shortensShownTextWhenHasLimitedWidth() {
		return false;
	}
	
	//method
	@Override
	protected void applyDefaultConfigurationWhenHasBeenReset() {}
	
	//method
	@Override
	protected UploaderLook createLook() {
		return new UploaderLook();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyPressOnContentAreaWhenFocused(final Key key) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled() {}
}
