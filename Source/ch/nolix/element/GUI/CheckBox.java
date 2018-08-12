//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.color.Color;
import ch.nolix.element.core.Boolean;
import ch.nolix.element.painter.IPainter;

//class
/**
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 280
 */
public final class Checkbox extends BackgroundWidget<Checkbox, CheckboxLook> {

	//constant
	public static final String TYPE_NAME = "Checkbox";
	
	//constants
	private final String CHECK_FLAG_HEADER = "Checked";
	
	//attribute
	private boolean checked = false;
	
	//constructor
	/**
	 * Creates a new {@link Checkbox}.
	 */
	public Checkbox() {
		reset();
		approveProperties();
	}
	
	//constructor
	/**
	 * Creates a new {@link Checkbox}.
	 * 
	 * @param checked
	 */
	public Checkbox(final boolean checked) {
		
		//Calls other constructor.
		this();
		
		setCheckFlag(checked);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public void addOrChangeAttribute(final Specification attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case CHECK_FLAG_HEADER:
				setCheckFlag(attribute.getOneAttributeAsBoolean());
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * Checks the current {@link Checkbox}.
	 * 
	 * @return the current {@link Checkbox}.
	 */
	public Checkbox check() {
		
		checked = true;
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public CursorIcon getCursorIcon() {
		return getCustomCursorIcon();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public List<StandardSpecification> getInteractionAttributes() {
		
		//Calls method of the base class.
		final var interactionAttributes = super.getInteractionAttributes();
		
		interactionAttributes.addAtEnd(
			new Boolean(isChecked()).getSpecificationAs(CHECK_FLAG_HEADER)
		);
		
		return interactionAttributes;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	/**
	 * @return true if the current {@link Checkbox} is checked.
	 */
	public boolean isChecked() {
		return checked;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public boolean keepsFocus() {
		return false;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public void noteLeftMouseButtonPress() {
		setCheckFlag(!isChecked());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public Checkbox reset() {
		
		uncheck();
		
		return this;
	}
	
	//method
	/**
	 * Sets the check flag of the current {@link Checkbox}.
	 * 
	 * @param checked
	 * @return the current {@link Checkbox}.
	 */
	public Checkbox setCheckFlag(boolean checked) {
		
		if (!checked) {
			uncheck();
		}
		else {
			check();
		}
		
		return this;
	}
	
	//method
	/**
	 * Unchecks the current {@link Checkbox}.
	 * 
	 * @return the current {@link Checkbox}.
	 */
	public Checkbox uncheck() {
		
		checked = false;
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected void applyUsableConfigurationWhenConfigurationIsReset() {
		getRefBaseLook()
		.setSize(20)
		.setLineColor(Color.BLACK);
	}
	
	//method
	/**
	 * @return a new widget look for the current {@link Checkbox}.
	 */
	protected CheckboxLook createWidgetLook() {
		return new CheckboxLook();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected void fillUpWidgets(final List<Widget<?, ?>> list) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected int getHeightWhenNotCollapsed() {
		return getRefCurrentLook().getRecursiveOrDefaultSize();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected int getWidthWhenNotCollapsed() {
		return getRefCurrentLook().getRecursiveOrDefaultSize();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected void paint(final CheckboxLook checkBoxLook, final IPainter painter) {
		
		//Calls method of the base class.
		super.paint(checkBoxLook, painter);
		
		final var s = checkBoxLook.getRecursiveOrDefaultSize();
		final var t = checkBoxLook.getRecursiveOrDefaultLineThickness();
		
		painter.setColor(checkBoxLook.getRecursiveOrDefaultLineColor());
		
		//Paints the left border of the current check box.
		painter.paintFilledRectangle(0, 0, t, s);
		
		//Paints the right border of the current check box.
		painter.paintFilledRectangle(s - t, 0, t, s);
		
		//Paints the top border of the current check box.
		painter.paintFilledRectangle(0, 0, s, t);
		
		//Paints the bottom border of the current check box.
		painter.paintFilledRectangle(0, s - t, s, t);
		
		//Paints the icon of the current check box if it is checked.
		if (isChecked()) {
			
			final var a = (int)(0.75 * t);
			
			//Paints the line from the bottom left corner to to the top right corner of the cross.
			{
				final int[] xs = new int[4];
				final int[] ys = new int[4];
				
				xs[0] = a;		ys[0] = s;
				xs[1] = s;		ys[1] = a;
				xs[2] = s - a;	ys[2] = 0;
				xs[3] = 0;		ys[3] = s - a;
				
				painter.paintFilledPolygon(xs, ys);
			}
			
			//Paints the line from the bottom right corner to the top left corner of the cross.
			{
				final int[] xs = new int[4];
				final int[] ys = new int[4];
				
				xs[0] = s;		ys[0] = s - a;
				xs[1] = a;		ys[1] = 0;
				xs[2] = 0;		ys[2] = a;
				xs[3] = s - a;	ys[3] = s;
				
				painter.paintFilledPolygon(xs, ys);
			}
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected boolean viewAreaIsUnderCursor() {
		return isUnderCursor();
	}
}
