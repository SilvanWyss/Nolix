//package declaration
package ch.nolix.system.gui.textbox;

//own imports
import ch.nolix.core.attributeuniversalapi.mutablemandatoryattributeuniversalapi.IMutableTextHolder;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.functionuniversalapi.IAction;
import ch.nolix.core.functionuniversalapi.IElementTaker;
import ch.nolix.core.name.PascalCaseCatalogue;
import ch.nolix.system.element.ForwardingMutableValue;
import ch.nolix.system.element.MutableValue;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.containerwidget.HorizontalStack;
import ch.nolix.system.gui.widget.BorderWidget;
import ch.nolix.system.gui.widget.Button;
import ch.nolix.system.gui.widget.TextBoxLook;
import ch.nolix.system.gui.widget.TextMode;
import ch.nolix.system.gui.widget.WidgetLookState;
import ch.nolix.systemapi.guiapi.baseapi.CursorIcon;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;
import ch.nolix.systemapi.guiapi.processproperty.RotationDirection;
import ch.nolix.systemapi.guiapi.widgetguiapi.IWidget;

//class
public final class TextBox extends BorderWidget<TextBox, TextBoxLook> implements IMutableTextHolder<TextBox> {
	
	//constants
	public static final String DEFAULT_TEXT = ch.nolix.system.gui.textbox.TextBoxInputField.DEFAULT_TEXT;
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
	private final TextBoxInputField inputField = new TextBoxInputField();
	private final Button toggleButton = new Button();
	
	//constructor
	public TextBox() {
		
		inputField.reset();
		mainHorizontalStack.addWidget(inputField, toggleButton);
		toggleButton.reset();
		toggleButton
		.setCustomCursorIcon(CursorIcon.HAND)
		.setText(TOGGLE_COVER_TEXT_BUTTON_TEXT)
		.setLeftMouseButtonPressAction(this::toggleCoverTextWhenSecretTextModeFlag);
		
		reset();
		
		setCustomCursorIcon(CursorIcon.EDIT);
		setProposalWidth(200);
		onLook(
			l ->
			l.setBorderThicknessForState(WidgetLookState.BASE, 1)
			.setBackgroundColorForState(WidgetLookState.BASE, Color.WHITE)
		);
	}
	
	//method
	public boolean coversTextWhenSecretTextMode() {
		return coverTextWhenSecretTextModeFlag.getValue();
	}
	
	//method
	public void emptyText() {
		inputField.emptyText();
	}
	
	//method
	@Override
	public String getText() {
		return inputField.getText();
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
	public TextBox setCoverTextWhenSecretTextMode() {
		
		coverTextWhenSecretTextModeFlag.setValue(true);
		updateInternalsFromTextMode();
		
		return this;
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
	public TextBox setNoteTextUpdateAction(final IAction noteTextUpdateAction) {
		
		inputField.setNoteTextUpdateAction(noteTextUpdateAction);
		
		return this;
	}
	
	//method
	public TextBox setNoteTextUpdateAction(final IElementTaker<String> noteTextUpdateAction) {
		
		inputField.setNoteTextUpdateAction(noteTextUpdateAction);
		
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
		
		inputField.setText(text);
		
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
	protected void fillUpChildWidgets(final LinkedList<IWidget<?, ?>> list) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpWidgetsForPainting(final LinkedList<IWidget<?, ?>> list) {
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
	protected void noteKeyDownOnSelfWhenFocused(final Key key) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void noteKeyReleaseOnSelfWhenFocused(final Key key) {
		//Does nothing.
	}
	
	@Override
	protected void noteKeyTypingOnSelfWhenFocused(final Key key) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void noteLeftMouseButtonClickOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	@Override
	protected void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	@Override
	protected void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	@Override
	protected void noteMouseMoveOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	@Override
	protected void noteMouseWheelClickOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	@Override
	protected void noteMouseWheelPressOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	@Override
	protected void noteMouseWheelReleaseOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	@Override
	protected void noteMouseWheelRotationStepOnBorderWidgetWhenFocused(final RotationDirection rotationDirection) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void noteRightMouseButtonClickOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	@Override
	protected void noteRightMouseButtonPressOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	@Override
	protected void noteRightMouseButtonReleaseOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	@Override
	protected void paintContentArea(final IPainter painter, final TextBoxLook textBoxLook) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void recalculateBorderWidget() {
		
		if (isFocused()) {
			inputField.setFocused();
		} else {
			inputField.setUnfocused();
		}
		
		if (hasTargetWidth() && getTextMode() == TextMode.SECRET) {
			inputField.setProposalWidth(
				getContentArea().getTargetWidth() - toggleButton.getWidth()
			);
		}
	}
	
	//method
	@Override
	protected void resetBorderWidget() {
		
		setTextMode(DEFAULT_TEXT_MODE);
		setCoverTextWhenSecretTextMode();
		
		inputField.reset();
	}
	
	//method
	@Override
	protected void resetBorderWidgetConfiguration() {
		//Does nothing.
	}
	
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
				inputField.setTextMode(TextMode.SECRET);
			} else {
				inputField.setTextMode(TextMode.NORMAL);
			}
			
			if (showsToggleButtonWhenSecretTextMode()) {
				toggleButton.setExpanded();
			} else {
				toggleButton.setCollapsed();
			}
		} else {
			inputField.setTextMode(TextMode.NORMAL);
			toggleButton.setCollapsed();
		}
	}
}
