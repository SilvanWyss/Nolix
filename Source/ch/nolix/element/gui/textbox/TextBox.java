//package declaration
package ch.nolix.element.gui.textbox;

//own imports
import ch.nolix.common.attributeapi.mutablemandatoryattributeapi.IMutableTextHolder;
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.element.base.ForwardingMutableValue;
import ch.nolix.element.base.MutableValue;
import ch.nolix.element.elementenum.RotationDirection;
import ch.nolix.element.gui.base.CursorIcon;
import ch.nolix.element.gui.containerwidget.HorizontalStack;
import ch.nolix.element.gui.input.Key;
import ch.nolix.element.gui.painterapi.IPainter;
import ch.nolix.element.gui.widget.BorderWidget;
import ch.nolix.element.gui.widget.Button;
import ch.nolix.element.gui.widget.TextBoxLook;
import ch.nolix.element.gui.widget.TextMode;
import ch.nolix.element.gui.widget.Widget;

//class
public final class TextBox extends BorderWidget<TextBox, TextBoxLook> implements IMutableTextHolder<TextBox> {
	
	//constants
	public static final String DEFAULT_TEXT = ch.nolix.element.gui.widget.TextBox.DEFAULT_TEXT;
	public static final TextMode DEFAULT_TEXT_MODE = TextMode.NORMAL;
	public static final boolean DEFAULT_COVER_TEXT_WHEN_SECRET_TEXT_MODE_FLAG = true;
	public static final boolean DEFAULT_SHOW_TOGGLE_BUTTON_WHEN_SECRET_TEXT_MODE_FLAG = true;
	
	//constant
	private static final String TOGGLE_COVER_TEXT_BUTTON_TEXT = "\u0472";
	
	//constants
	private static final String TEXT_HEADER = PascalCaseCatalogue.TEXT;
	private static final String TEXT_MODE_HEADER = "TextMode";
	private static final String COVER_TEXT_WHEN_SECRET_TEXT_MODE_HEADER = "CoverTextWhenSecretText";
	private static final String SHOW_TOGGLE_BUTTON_WHEN_SECRET_TEXT_MODE_HEADER = "ShowToggleButtonWhenSecretTextMode";
	
	//attribute
	@SuppressWarnings("unused")
	private ForwardingMutableValue<String> text =
	ForwardingMutableValue.forString(TEXT_HEADER, this::setText, this::getText);
	
	//attribute
	private MutableValue<TextMode> textMode =
	new MutableValue<>(
		TEXT_MODE_HEADER,
		DEFAULT_TEXT_MODE,
		this::setTextMode,
		TextMode::fromSpecification,
		TextMode::getSpecification
	);
	
	//attribute
	private MutableValue<Boolean> coverTextWhenSecretTextModeFlag =
	MutableValue.forBoolean(
		COVER_TEXT_WHEN_SECRET_TEXT_MODE_HEADER,
		DEFAULT_COVER_TEXT_WHEN_SECRET_TEXT_MODE_FLAG,
		this::setCoverTextWhenSecretTextModeFlag
	);
	
	//attribute
	private MutableValue<Boolean> showToggleButtonWhenSecretTextModeFlag =
	MutableValue.forBoolean(
		SHOW_TOGGLE_BUTTON_WHEN_SECRET_TEXT_MODE_HEADER,
		DEFAULT_SHOW_TOGGLE_BUTTON_WHEN_SECRET_TEXT_MODE_FLAG,
		this::setShowToggleButtonWhenSecretTextModeFlag
	);
	
	//attributes
	private final HorizontalStack mainHorizontalStack = new HorizontalStack();
	private final ch.nolix.element.gui.widget.TextBox internalTextBox = new ch.nolix.element.gui.widget.TextBox();
	private final Button toggleButton = new Button();
	
	//constructor
	public TextBox() {
		
		internalTextBox.reset();
		
		mainHorizontalStack.addWidget(internalTextBox, toggleButton);
		
		toggleButton.reset();
		toggleButton
		.setCustomCursorIcon(CursorIcon.HAND)
		.setText(TOGGLE_COVER_TEXT_BUTTON_TEXT)
		.setLeftMouseButtonPressAction(this::toggleCoverTextWhenSecretTextModeFlag);
		
		reset();
	}
	
	//method
	public boolean coversTextWhenSecretTextMode() {
		return coverTextWhenSecretTextModeFlag.getValue();
	}
	
	//method
	@Override
	public String getText() {
		return internalTextBox.getText();
	}
	
	//method
	public TextMode getTextMode() {
		return textMode.getValue();
	}
	
	//method
	@Override
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	public TextBox setDoNotCoverTextWhenSecretTextMode() {
		
		coverTextWhenSecretTextModeFlag.setValue(false);
		updateInternalsFromTextMode();
		
		return this;
	}
	
	//method
	public TextBox setDoNotShowToggleButtonWhenSecretTextMode() {
		
		showToggleButtonWhenSecretTextModeFlag.setValue(false);
		updateInternalsFromTextMode();
		
		return this;
	}
	
	//method
	public TextBox setCoverTextWhenSecretTextMode() {
		
		coverTextWhenSecretTextModeFlag.setValue(true);
		updateInternalsFromTextMode();
		
		return this;
	}
	
	//method
	public TextBox setShowToggleButtonWhenSecretTextMode() {
		
		showToggleButtonWhenSecretTextModeFlag.setValue(true);
		updateInternalsFromTextMode();
		
		return this;
	}
	
	//method
	@Override
	public TextBox setText(final String text) {
		
		internalTextBox.setText(text);
		
		return this;
	}
	
	//method
	public TextBox setTextMode(final TextMode textMode) {
		
		this.textMode.setValue(textMode);
		updateInternalsFromTextMode();
				
		return this;
	}
	
	//method
	public boolean showsToggleButtonWhenSecretTextMode() {
		return showToggleButtonWhenSecretTextModeFlag.getValue();
	}
	
	//method
	@Override
	protected boolean contentAreaMustBeExpandedToTargetSize() {
		return false;
	}
	
	//method
	@Override
	protected TextBoxLook createLook() {
		return new TextBoxLook();
	}
	
	//method
	@Override
	protected void fillUpChildWidgets(final LinkedList<Widget<?, ?>> list) {}
	
	//method
	@Override
	protected void fillUpWidgetsForPainting(final LinkedList<Widget<?, ?>> list) {
		list.addAtEnd(mainHorizontalStack);
	}
	
	//method
	@Override
	protected int getNaturalContentAreaHeight() {
		return mainHorizontalStack.getHeight();
	}
	
	//method
	@Override
	protected int getNaturalContentAreaWidth() {
		return mainHorizontalStack.getWidth();
	}
	
	//method
	@Override
	protected void noteKeyDownOnSelfWhenFocused(final Key key) {}
	
	//method
	@Override
	protected void noteKeyReleaseOnSelfWhenFocused(final Key key) {}
	
	@Override
	protected void noteKeyTypingOnSelfWhenFocused(final Key key) {}
	
	//method
	@Override
	protected void noteLeftMouseButtonClickOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteMouseMoveOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteMouseWheelClickOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteMouseWheelPressOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteMouseWheelReleaseOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteMouseWheelRotationStepOnBorderWidgetWhenFocused(final RotationDirection rotationDirection) {}
	
	//method
	@Override
	protected void noteRightMouseButtonClickOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteRightMouseButtonPressOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteRightMouseButtonReleaseOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void paintContentArea(final IPainter painter, final TextBoxLook textBoxLook) {}
	
	//method
	@Override
	protected void recalculateBorderWidget() {
		
		if (isFocused()) {
			internalTextBox.setFocused();
		} else {
			internalTextBox.setUnfocused();
		}
		
		if (hasTargetWidth() && getTextMode() == TextMode.SECRET) {
			internalTextBox.setProposalWidth(
				getContentArea().getTargetWidth() - toggleButton.getWidth()
			);
		}
	}
	
	//method
	@Override
	protected void resetBorderWidget() {
		
		setTextMode(DEFAULT_TEXT_MODE);
		setCoverTextWhenSecretTextMode();
		
		internalTextBox.reset();
	}
	
	//method
	@Override
	protected void resetBorderWidgetConfiguration() {}
	
	//method
	private void setCoverTextWhenSecretTextModeFlag(final boolean coverTextWhenSecretTextModeFlag) {
		if (coverTextWhenSecretTextModeFlag) {
			setCoverTextWhenSecretTextMode();
		} else {
			setDoNotCoverTextWhenSecretTextMode();
		}
	}
	
	//method
	private void setShowToggleButtonWhenSecretTextModeFlag(final boolean showToggleButtonWhenSecretTextModeFlag) {
		if (showToggleButtonWhenSecretTextModeFlag) {
			setShowToggleButtonWhenSecretTextMode();
		} else {
			setDoNotShowToggleButtonWhenSecretTextMode();
		}
	}
	
	//method
	private void toggleCoverTextWhenSecretTextModeFlag() {
		setCoverTextWhenSecretTextModeFlag(!coversTextWhenSecretTextMode());
	}
	
	//method
	private void updateInternalsFromTextMode() {
		if (getTextMode() == TextMode.SECRET) {
			
			if (coversTextWhenSecretTextMode()) {
				internalTextBox.setTextMode(TextMode.SECRET);
			} else {
				internalTextBox.setTextMode(TextMode.NORMAL);
			}
			
			if (showsToggleButtonWhenSecretTextMode()) {
				toggleButton.setExpanded();
			} else {
				toggleButton.setCollapsed();
			}
		} else {
			internalTextBox.setTextMode(TextMode.NORMAL);
			toggleButton.setCollapsed();
		}
	}
}
