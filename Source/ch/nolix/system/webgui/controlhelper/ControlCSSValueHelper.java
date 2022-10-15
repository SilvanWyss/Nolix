//package declaration
package ch.nolix.system.webgui.controlhelper;

//own imports
import ch.nolix.coreapi.webapi.cssapi.CSSUnitCatalogue;
import ch.nolix.systemapi.guiapi.colorapi.IColor;
import ch.nolix.systemapi.structureapi.IRelativeOrAbsoluteInt;

//class
public final class ControlCSSValueHelper {
	
	//static attribute
	public static final ControlCSSValueHelper INSTANCE = new ControlCSSValueHelper();
	
	//method
	public String getCSSValueFromColor(final IColor color) {
		return String.format("#%02x%02x%02x", color.getRedValue(), color.getGreenValue(), color.getBlueValue());
	}
	
	//method
	public String getCSSValueFromRelativeOrAbsoluteInt(
		final IRelativeOrAbsoluteInt relativeOrAbsoluteInt,
		final String relativeIntCSSUnit
	) {
		
		if (relativeOrAbsoluteInt.isAbsolute()) {
			return relativeOrAbsoluteInt.getAbsoluteValue() + CSSUnitCatalogue.PX;
		}
		
		return relativeOrAbsoluteInt.getPercentage() + relativeIntCSSUnit;
	}
}
