//package declaration
package ch.nolix.element.gui.widget;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.chainednode.ChainedNode;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.programcontrol.closeableelement.CloseController;
import ch.nolix.element.configuration.Configuration;
import ch.nolix.element.elementenum.RotationDirection;
import ch.nolix.element.gui.base.IWidgetGUI;
import ch.nolix.element.gui.base.InvisibleGUI;
import ch.nolix.element.gui.base.Layer;
import ch.nolix.element.gui.base.Widget;
import ch.nolix.element.gui.base.WidgetGUI;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.input.Key;
import ch.nolix.element.gui.painterapi.IPainter;

//class
public final class InnerGUI extends BorderWidget<InnerGUI, InnerGUILook> implements IWidgetGUI<InnerGUI> {
	
	//constant
	private static final String GUI_HEADER = PascalCaseCatalogue.GUI;
	
	//attributes
	private final WidgetGUI<?> internalGUI = new InvisibleGUI();
	private final Label titleLabel = new Label();
	
	//constructor
	public InnerGUI() {
		
		reset();
		
		createCloseDependencyTo(internalGUI);
		setProposalWidth(200);
		setProposalHeight(200);
	}
	
	//method
	@Override
	public InnerGUI addLayerOnTop(final Layer layer) {
		
		internalGUI.addLayerOnTop(layer);
		
		return this;
	}
	
	//method
	@Override
	public InnerGUI addLayerOnTop(final Widget<?, ?> rootWidget) {
		
		internalGUI.addLayerOnTop(rootWidget);
		
		return this;
	}
	
	//method
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		switch (attribute.getHeader()) {
			case GUI_HEADER:
				internalGUI.resetFrom(attribute);
				break;
			default:
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	@Override
	public void clear() {
		internalGUI.clear();
	}
	
	//method
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		
		super.fillUpAttributesInto(list);
		
		list.addAtEnd(internalGUI.getSpecificationAs(GUI_HEADER));
	}
	
	//method
	@Override
	public int getCursorXPositionOnViewArea() {
		return internalGUI.getCursorXPositionOnViewArea();
	}
	
	//method
	@Override
	public int getCursorYPositionOnViewArea() {
		return internalGUI.getCursorYPositionOnViewArea();
	}
	
	//method
	@Override
	public IContainer<ChainedNode> getPaintCommands() {
		return internalGUI.getPaintCommands();
	}
	
	//method
	@Override
	public CloseController getRefCloseController() {
		return internalGUI.getRefCloseController();
	}
	
	//method
	public <W extends Widget<?, ?>> W getRefWidgetById(final String id) {
		return internalGUI.getRefWidgetById(id);
	}
	
	//method
	@Override
	public String getTitle() {
		return internalGUI.getTitle();
	}
	
	//method
	@Override
	public int getViewAreaHeight() {
		return internalGUI.getViewAreaHeight();
	}
	
	//method
	@Override
	public int getViewAreaWidth() {
		return internalGUI.getViewAreaWidth();
	}
	
	//method
	@Override
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return internalGUI.isEmpty();
	}
	
	//method
	@Override
	public boolean isRootGUI() {
		return false;
	}
	
	//method
	@Override
	public boolean isVisible() {
		return true;
	}
	
	//method
	@Override
	public void noteClose() {}
	
	//method
	@Override
	public void noteResize(final int viewAreaWidth, final int viewAreaHeight) {
		internalGUI.noteResize(viewAreaWidth, viewAreaHeight);
	}
	
	//method
	@Override
	public void refresh() {}
	
	//method
	@Override
	public InnerGUI setBackgroundColor(final Color backgroundColor) {
		
		internalGUI.setBackgroundColor(backgroundColor);
		
		return this;
	}
	
	//method
	@Override
	public InnerGUI setConfiguration(final Configuration configuration) {
		
		internalGUI.setConfiguration(configuration);
		
		return this;
	}
	
	//method
	@Override
	public InnerGUI setTitle(final String title) {
		
		internalGUI.setTitle(title);
		titleLabel.setText(title);
		
		return this;
	}
	
	//method
	@Override
	protected boolean contentAreaMustBeExpandedToTargetSize() {
		return false;
	}
	
	//method
	@Override
	protected InnerGUILook createLook() {
		return new InnerGUILook();
	}
	
	//method
	@Override
	protected void fillUpChildWidgets(final LinkedList<Widget<?, ?>> list) {
		list.addAtEnd(titleLabel);
	}

	//method
	@Override
	protected void fillUpWidgetsForPainting(final LinkedList<Widget<?, ?>> list) {
		list.addAtEnd(titleLabel);
	}
	
	//method
	@Override
	protected int getNaturalContentAreaHeight() {
		return internalGUI.getHeight();
	}
	
	//method
	@Override
	protected int getNaturalContentAreaWidth() {
		return internalGUI.getWidth();
	}
	
	//method
	@Override
	protected void noteKeyPressOnSelfWhenFocused(final Key key) {
		internalGUI.noteKeyPress(key);
	}
	
	//method
	@Override
	protected void noteKeyReleaseOnSelfWhenFocused(final Key key) {}
	
	//method
	@Override
	protected void noteKeyTypingOnSelfWhenFocused(final Key key) {}
		
	//method
	@Override
	protected void noteLeftMouseButtonClickOnContentAreaWhenEnabled() {
		internalGUI.noteLeftMouseButtonClick();
	}
	
	//method
	@Override
	protected void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {
		internalGUI.noteLeftMouseButtonPress();
	}
	
	//method
	@Override
	protected void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled() {
		internalGUI.noteLeftMouseButtonRelease();
	}
	
	//method
	@Override
	protected void noteMouseMoveOnContentAreaWhenEnabled() {
		internalGUI.noteMouseMove(getCursorXPositionOnViewArea(), getCursorYPositionOnViewArea());
	}
	
	//method
	@Override
	protected void noteMouseWheelClickOnContentAreaWhenEnabled() {
		internalGUI.noteMouseWheelClick();
	}
	
	//method
	@Override
	protected void noteMouseWheelPressOnContentAreaWhenEnabled() {
		internalGUI.noteMouseWheelPress();
	}
	
	//method
	@Override
	protected void noteMouseWheelReleaseOnContentAreaWhenEnabled() {
		internalGUI.noteMouseWheelRelease();
	}
	
	//method
	@Override
	protected void noteMouseWheelRotationStepOnBorderWidgetWhenFocused(final RotationDirection rotationDirection) {}
		
	//method
	@Override
	protected void noteRightMouseButtonClickOnContentAreaWhenEnabled() {
		internalGUI.noteRightMouseButtonClick();
	}
	
	//method
	@Override
	protected void noteRightMouseButtonPressOnContentAreaWhenEnabled() {
		internalGUI.noteRightMouseButtonPress();
	}
	
	//method
	@Override
	protected void noteRightMouseButtonReleaseOnContentAreaWhenEnabled() {
		internalGUI.noteRightMouseButtonRelease();
	}
	
	//method
	@Override
	protected void paintContentArea(final IPainter painter, final InnerGUILook innerGUILook) {
		painter.translateVertically(titleLabel.getHeight());		
		internalGUI.paint(painter);
	}
	
	//method
	@Override
	protected void recalculateBorderWidget() {
		
		if (hasTargetWidth() && hasTargetHeight()) {
			internalGUI.noteResize(getContentArea().getTargetWidth(), getContentArea().getTargetHeight());
		}
		
		internalGUI.recalculate();
	}
	
	//method
	@Override
	protected void resetBorderWidgetConfiguration() {}
	
	//method
	@Override
	protected void resetBorderWidget() {
		internalGUI.reset();
	}
}
