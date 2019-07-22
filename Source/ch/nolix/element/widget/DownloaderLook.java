package ch.nolix.element.widget;

import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.element.layerElement.LayerProperty;

public final class DownloaderLook extends TextLineWidgetLook<DownloaderLook> {
	
	public static final boolean DEFAULT_SHORTENS_TEXT_WHEN_LIMITED_FLAG = false;
	
	private static final String SHORTENS_TEXT_WHEN_LIMIITED_HEADER = "ShortensTextWhenLimited";
	
	private final LayerProperty<Boolean> shortensTextWhenLimited =
	new LayerProperty<>(
		SHORTENS_TEXT_WHEN_LIMIITED_HEADER,
		DEFAULT_SHORTENS_TEXT_WHEN_LIMITED_FLAG,
		s -> s.getOneAttributeAsBoolean(),
		b -> DocumentNode.createWithOneAttribute(b)
	);
	
	public boolean getRecursiveOrDefaultShortensTextWhenLimitedFlag() {
		return shortensTextWhenLimited.getRecursiveOrDefaultValue();
	}
	
	public DownloaderLook remainTextWhenLimited() {
		
		shortensTextWhenLimited.setValue(false);
		
		return this;
	}
	
	public DownloaderLook shortenTextWhenLimited() {
		
		shortensTextWhenLimited.setValue(true);
		
		return this;
	}
}
