package ch.nolix.systemtest.webguitest.itemmenutest;

import org.junit.jupiter.api.Test;

import ch.nolix.system.webgui.itemmenu.ItemMenuItem;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuItem;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuStyle;
import ch.nolix.systemtest.webguitest.maintest.ControlTest;

abstract class ItemMenuTest<M extends IItemMenu<M, S>, S extends IItemMenuStyle<S>> extends ControlTest<M> {

  @Test
  final void testCase_creation() {

    //setup
    final M testUnit = createTestUnit();

    //setup verification
    expect(testUnit.isEmpty());
    expectNot(testUnit.containsSelectedItem());
  }

  @Test
  final void testCase_addItemWithIdAndText() {

    //setup
    final M testUnit = createTestUnit();

    //setup verification
    expect(testUnit.isEmpty());

    //execution
    testUnit
      .addItemWithIdAndText("my_id1", "my_text1")
      .addItemWithIdAndText("my_id2", "my_text2")
      .addItemWithIdAndText("my_id3", "my_text3");

    //verification
    expect(testUnit.getStoredItems()).containsExactlyEqualing(
      ItemMenuItem.withIdAndText("my_id1", "my_text1"),
      ItemMenuItem.withIdAndText("my_id2", "my_text2"),
      ItemMenuItem.withIdAndText("my_id3", "my_text3"));
  }

  @Test
  final void testCase_addItemWithText() {

    //setup
    final M testUnit = createTestUnit();

    //setup verification
    expect(testUnit.isEmpty());

    //execution
    testUnit.addItemWithText("my_text1", "my_text2", "my_text3", "my_text4");

    //verification
    expect(testUnit.getStoredItems().to(IItemMenuItem::getText))
      .containsExactlyEqualing("my_text1", "my_text2", "my_text3", "my_text4");
  }

  @Test
  final void testCase_selectItemById_whenContainsItemWithGivenId() {

    //setup
    final var item = ItemMenuItem.withIdAndText("my_id2", "my_text2");
    final M testUnit = createTestUnit()
      .addItem(
        ItemMenuItem.withIdAndText("my_id1", "my_text1"),
        item,
        ItemMenuItem.withIdAndText("my_id3", "my_text3"));

    //setup verification
    expectNot(item.isSelected());

    //execution
    testUnit.selectItemById("my_id2");

    //verification
    expect(item.isSelected());
  }
}
