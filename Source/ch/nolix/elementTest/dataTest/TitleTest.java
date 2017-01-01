//package declaration
package ch.nolix.elementTest.dataTest;

//own imports
import ch.nolix.common.constants.StringManager;
import ch.nolix.common.exception.EmptyArgumentException;
import ch.nolix.common.exception.NullArgumentException;
import ch.nolix.common.zetaTest.ZetaTest;
import ch.nolix.element.data.Title;

//test class
/**
 * This class is a text class for the title class.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 70
 */
public final class TitleTest extends ZetaTest {
	
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
