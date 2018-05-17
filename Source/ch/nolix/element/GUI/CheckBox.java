//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.color.Color;
import ch.nolix.element.painter.IPainter;

//class
/**
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 200
 */
public final class CheckBox extends BackgroundWidget<CheckBox, CheckBoxLook> {

	//constant
	public static final String TYPE_NAME = "CheckBox";
	
	//constants
	private final String CHECKED_HEADER = "Checked";
	private final String UNCHECKED_HEADER = "Unchecked";
	
	//attribute
	private boolean checked = false;
	
	//constructor
	/**
	 * Creates a new {@link CheckBox}.
	 */
	public CheckBox() {
		reset();
		approveProperties();
	}
	
	//method
	/**
	 * Adds or changes the given attribute to the current {@link CheckBox}.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final Specification attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case CHECKED_HEADER:
				check();
				break;
			case UNCHECKED_HEADER:
				uncheck();
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * Checks the current {@link CheckBox}.
	 * 
	 * @return the current {@link CheckBox}.
	 */
	public CheckBox check() {
		
		checked = true;
		
		return this;
	}
	
	//method
	/**
	 * @return the active cursor icon of the current {@link CheckBox}.
	 */
	public CursorIcon getActiveCursorIcon() {
		return getCursorIcon();
	}
	
	//method
	/**
	 * @return the attributes of the current {@link CheckBox}.
	 */
	public List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		final var attributes = super.getAttributes();
		
		//Handles the case that the current check box is checked.
		if (isChecked()) {
			attributes.addAtEnd(new StandardSpecification(CHECKED_HEADER));
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the widgets of the current {@link CheckBox}.
	 */
	public ReadContainer<Widget<?, ?>> getRefWidgets() {
		return new ReadContainer<>();
	}
	
	//method
	/**
	 * @return true if the current {@link CheckBox} has the given role.
	 */
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	/**
	 * @return true if the current {@link CheckBox} is checked.
	 */
	public boolean isChecked() {
		return checked;
	}
	
	//method
	/**
	 * Unchecks the current {@link CheckBox}.
	 * 
	 * @return the current {@link CheckBox}.
	 */
	public CheckBox uncheck() {
		
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
	 * @return a new widget look for the current {@link CheckBox}.
	 */
	protected CheckBoxLook createWidgetLook() {
		return new CheckBoxLook();
	}
	
	//method
	/**
	 * @return the height of the current {@link CheckBox} when it is not collapsed. 
	 */
	protected int getHeightWhenNotCollapsed() {
		return getRefCurrentLook().getActiveSize();
	}
	
	//method
	/**
	 * @return the width of the current {@link CheckBox} when it is not collapsed.
	 */
	protected int getWidthWhenNotCollapsed() {
		return getRefCurrentLook().getActiveSize();
	}
	
	//method
	/**
	 * Paints the current {@link CheckBox} using the given check box look and painter.
	 * 
	 * @param checkBoxLook
	 * @param painter
	 */
	protected void paint(final CheckBoxLook checkBoxLook, final IPainter painter) {
		
		//Calls method of the base class.
		super.paint(checkBoxLook, painter);
		
		final int s = checkBoxLook.getActiveSize();
		final int t = checkBoxLook.getActiveLineThickness();
		
		painter.setColor(checkBoxLook.getActiveLineColor());
		
		//Paints the left border of the current check box.
		painter.paintFilledRectangle(0, 0, t, s);
		
		//Paints the right border of the current check box.
		painter.paintFilledRectangle(s - t, 0, t, s);
		
		//Paints the top border of the current check box.
		painter.paintFilledRectangle(0, 0, s, t);
		
		//Paints the bottom border of the current check box.
		painter.paintFilledRectangle(s - t, 0, s, t);
		
		//Paints the icon of the current check box if it is checked.
		if (isChecked()) {
			
			final int[] x = new int[4];
			final int[] y = new int[4];
			
			x[0] = 0;		y[0] = t;
			x[1] = t;		y[1] = 0;
			x[2] = s;		y[2] = s - t;
			x[3] = s - t;	y[3] = s;
			
			//TODO
			//painter.paintedFilledPolygon(x, y, 4);
		}
	}
}
