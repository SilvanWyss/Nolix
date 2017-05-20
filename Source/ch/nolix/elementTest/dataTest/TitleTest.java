//package declaration
package ch.nolix.elementTest.dataTest;

//own imports
import ch.nolix.core.constants.StringManager;
import ch.nolix.core.invalidArgumentException.EmptyArgumentException;
import ch.nolix.core.invalidArgumentException.NullArgumentException;
import ch.nolix.core.test2.Test;
import ch.nolix.element.data.Title;

//test class
/**
 * This class is a text class for the title class.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 70
 */
public final class TitleTest extends Test {
	
	//test method
	public void testGetType() {
		
		//setup
		final Title title = new Title();
		
		//verification
		expectThat(title.getType()).equals("Title");
	}

	//test method
	public void testReset() {
		
		//setup
		final Title title = new Title("Encyclopedia");
		
		//execution
		title.reset();
		
		//verification
		expectThat(title.hasValue(Title.DEFAULT_VALUE));
	}
	
	//test method
	public void testSetValue1() {
		
		//setup
		final Title title = new Title();
		
		//execution
		title.setValue("Encyclopedia");
		
		//verification
		expectThat(title.hasValue("Encyclopedia"));
	}
	
	//test method
	public void testSetValue2() {
		
		//setup
		final Title title = new Title();
		
		//execution and verification
		expectThat(() -> title.setValue(null))
		.throwsExceptionOfType(NullArgumentException.class);
	}
	
	//test method
	public void testSetValue3() {
		
		//setup
		final Title title = new Title();
		
		//execution and verification
		expectThat(() -> title.setValue(StringManager.EMPTY_STRING))
		.throwsExceptionOfType(EmptyArgumentException.class);
	}
}
