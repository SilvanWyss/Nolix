package ch.nolix.core.programcontrol.usercontrol;

import ch.nolix.core.argumentcaptor.andargumentcaptor.AndPasswordCaptor;
import ch.nolix.core.argumentcaptor.withargumentcaptor.WithLoginNameCaptor;

public final class CredentialBuilder extends WithLoginNameCaptor<AndPasswordCaptor<Credential>> {

  private CredentialBuilder() {

    super(new AndPasswordCaptor<>());

    setBuilder(this::buildCredential);
  }

  public static CredentialBuilder createCredential() {
    return new CredentialBuilder();
  }

  private Credential buildCredential() {
    return new Credential(getLoginName(), nxtArgCpt().getPassword());
  }
}
