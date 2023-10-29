//package declaration
package ch.nolix.systemtest.webguitest.itemmenutest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.system.webgui.itemmenu.ItemMenuItem;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuStyle;
import ch.nolix.systemtest.webguitest.maintest.ControlTest;

//class
public abstract class ItemMenuTest<IM extends IItemMenu<IM, IMS>, IMS extends IItemMenuStyle<IMS>>
extends ControlTest<IM> {

  //method
  @TestCase
  public final void testCase_addItemWithIdAndText() {

    //setup
    final var testUnit = createTestUnit();

    //setup verification
    expect(testUnit.isEmpty());

    //execution
    testUnit
      .addItemWithIdAndText("my_id_1", "my_text_1")
      .addItemWithIdAndText("my_id_2", "my_text_2")
      .addItemWithIdAndText("my_id_3", "my_text_3");

    //verification
    expect(testUnit.getStoredItems()).containsExactlyEqualing(
      ItemMenuItem.withIdAndText("my_id_1", "my_text_1"),
      ItemMenuItem.withIdAndText("my_id_2", "my_text_2"),
      ItemMenuItem.withIdAndText("my_id_3", "my_text_3"));
  }

  //method
  @TestCase
  public final void testCase_selectItemById_whenContainsItemWithGivenId() {

    //setup
    final var item = ItemMenuItem.withIdAndText("my_id_2", "my_text_2");
    final var testUnit = createTestUnit()
      .addItem(
        ItemMenuItem.withIdAndText("my_id_1", "my_text_1"),
        item,
        ItemMenuItem.withIdAndText("my_id_3", "my_text_3"));

    //setup verification
    expectNot(item.isSelected());

    //execution
    testUnit.selectItemById("my_id_2");

    //verification
    expect(item.isSelected());
  }
}
