package ch.nolix.system.element.multistateconfiguration;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

public abstract class AbstractProperty<S extends Enum<S>> implements INameHolder {

  private final String name;

  protected AbstractMultiStateConfiguration<?, S> parent;

  protected AbstractProperty(final String name) {

    Validator.assertThat(name).thatIsNamed(LowerCaseVariableCatalog.NAME).isNotBlank();

    this.name = name;
  }

  @Override
  public final String getName() {
    return name;
  }

  protected abstract void fillUpValuesSpecificationInto(ILinkedList<INode<?>> list);

  protected abstract void setFrom(AbstractProperty<S> property);

  protected abstract void setUndefined();

  protected abstract void setValueFromSpecification(INode<?> specification);

  final void setParent(final AbstractMultiStateConfiguration<?, S> parent) {

    Validator.assertThat(parent).thatIsNamed(LowerCaseVariableCatalog.PARENT).isNotNull();

    this.parent = parent;
  }
}
