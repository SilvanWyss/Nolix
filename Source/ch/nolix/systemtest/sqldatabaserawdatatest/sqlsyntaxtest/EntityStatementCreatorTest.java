//package declaration
package ch.nolix.systemtest.sqldatabaserawdatatest.sqlsyntaxtest;

import ch.nolix.core.container.immutablelist.ImmutableList;
//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.sqldatabaserawdata.databasedto.ContentFieldDTO;
import ch.nolix.system.sqldatabaserawdata.databasedto.EntityHeadDTO;
import ch.nolix.system.sqldatabaserawdata.databasedto.NewEntityDTO;
import ch.nolix.system.sqldatabaserawdata.sqlsyntax.EntityStatementCreator;

//class
public final class EntityStatementCreatorTest extends Test {
	
	//method
	@TestCase
	public void testCase_createStatementToDeleteEntity() {
		
		//setup
		final var testUnit = new EntityStatementCreator();
		final var entityHeadDTO = new EntityHeadDTO("my_id", "my_save_stamp");
		
		//execution
		final var result = testUnit.createStatementToDeleteEntity("MyTable", entityHeadDTO);
		
		//verification
		final var expectedResult =
		"DELETE FROM EMyTable WHERE Id = 'my_id' AND SaveStamp = 'my_save_stamp';"
		+ "IF @@RowCount = 0 BEGIN THROW error(100000, 'The data was changed in the meanwhile.', 0) END;";
		expect(result).isEqualTo(expectedResult);
	}
	
	//method
	@TestCase
	public void testCase_createStatementToInsertNewEntity() {
		
		//setup
		final var testUnit = new EntityStatementCreator();
		final var newEntityDTO =
		new NewEntityDTO(
			"my_id",
			ImmutableList.withElements(
				new ContentFieldDTO("MyColumn1", "my_column1_value"),
				new ContentFieldDTO("MyColumn2", "my_column2_value"),
				new ContentFieldDTO("MyColumn3", "my_column3_value")
			)
		);
		
		//execution
		final var result = testUnit.createStatementToInsertNewEntity("MyTable", newEntityDTO);
		
		//verification
		final var expectedResult =
		"INSERT INTO EMyTable (Id, SaveStamp, MyColumn1, MyColumn2, MyColumn3) "
		+ "VALUES ('my_id', '1', 'my_column1_value', 'my_column2_value', 'my_column3_value');";
		expect(result).isEqualTo(expectedResult);
	}
}
