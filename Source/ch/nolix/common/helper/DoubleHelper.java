/*
 * file:	DoubleHelper.java
 * author:	Silvan Wyss
 * month:	2016-07
 * lines:	10
 */

package ch.nolix.common.helper;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class DoubleHelper {

	public final static String toString(double value) {
		DecimalFormat decimalFormat = (DecimalFormat)NumberFormat.getNumberInstance(Locale.US);
		decimalFormat.applyPattern("0.################");
		return decimalFormat.format(value);
	}
}
