//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.entity2.Property;
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 140
 * @param <BWL> The type of a {@link BackgroundWidgetLook}.
 */
public abstract class BackgroundWidgetLook<BWL extends BackgroundWidgetLook<BWL>>
extends WidgetLook<BWL> {

	//default value
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
	
	//default value
	public static final ColorGradient DEFAULT_BACKGROUND_COLOR_GRADIENT
	= ColorGradient.VERTICAL_BLACK_WHITE_COLOR_GRADIENT;
	
	//constant
	private static final String BACKGROUND_COLOR_GRADIENT_HEADER = "BackgroundColorGradient";
	
	//attribute
	private final Property<Color> backgroundColor =
	new Property<Color>(
		PascalCaseNameCatalogue.BACKGROUND_COLOR,
		DEFAULT_BACKGROUND_COLOR,
		s -> Color.createFromSpecification(s)
	);

	//attribute
	private final Property<ColorGradient> backgroundColorGradient =
	new Property<ColorGradient>(
		BACKGROUND_COLOR_GRADIENT_HEADER,
		DEFAULT_BACKGROUND_COLOR_GRADIENT,
		s -> ColorGradient.createFromSpecification(s)
	);
	
    //method
    /**
     * @return the recursive or default background color
     * of the current {@link BackgroundWidgetLook}.
     */
    public final Color getRecursiveOrDefaultBackgroundColor() {
        return backgroundColor.getRecursiveOrDefaultValue();
    }
    
    //method
    /**
     * @return the recursive or default background color gradient
     * of the current {@link BackgroundWidgetLook}.
     */
    public final ColorGradient getRecursiveOrDefaultBackgroundColorGradient() {
        return backgroundColorGradient.getRecursiveOrDefaultValue();
    }
    
    //method
    /**
     * @return true if the current {@link BackgroundWidgetLook}
     * has a recursive background color.
     */
    public final boolean hasRecursiveBackgroundColor() {
        return backgroundColor.hasRecursiveValue();
    }
    
    //method
    /**
     * @return true if the current {@link BackgroundWidgetLook}
     * has a recursive background color gradient.
     */
    public final boolean hasRecursiveBackgroundColorGradient() {
        return backgroundColorGradient.hasRecursiveValue();
    }
    
    //method
    /**
     * Removes the background color of the current {@link BackgroundWidgetLook}.
     * 
     * @return the current {@link BackgroundWidgetLook}.
     */
    public final BWL removeBackgroundColor() {
        
        backgroundColor.removeValue();
        
        return getInstance();
    }
    
    //method
    /**
     * Removes the background color gradient of the current {@link BackgroundWidgetLook}.
     * 
     * @return the current {@link BackgroundWidgetLook}.
     */
    public final BWL removeBackgroundColorGradient() {
        
        backgroundColorGradient.removeValue();
        
        return getInstance();
    }
    
    //method
    /**
     * Sets the background color of the current {@link BackgroundWidgetLook}.
     * Removes the background color gradient of the current {@link BackgroundWidgetLook}.
     * 
     * @param backgroundColor
     * @return the current {@link BackgroundWidgetLook}.
     * @throws NullArgumentException if the given background color is not an instance.
     */
    public final BWL setBackgroundColor(final Color backgroundColor) {
    	
    	this.backgroundColor.setValue(backgroundColor);
    	removeBackgroundColorGradient();
    	
    	return getInstance();
    }
    
    //method
    /**
     * Sets the background color gradient of the current {@link BackgroundWidgetLook}.
     * Removes the background color of the current {@link BackgroundWidgetLook}.
     * 
     * @param backgroundColorGradient
     * @return the current {@link BackgroundWidgetLook}.
     * @throws NullArgumentException if the given background color is not an instance.
     */
    public final BWL setBackgroundColorGradient(final ColorGradient backgroundColorGradient) {
    	
    	this.backgroundColorGradient.setValue(backgroundColorGradient);
    	removeBackgroundColor();
    	
    	return getInstance();
    }
}
