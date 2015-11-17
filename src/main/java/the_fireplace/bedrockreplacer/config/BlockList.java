package the_fireplace.bedrockreplacer.config;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.IConfigElement;

public class BlockList extends GuiConfigEntries.SelectValueEntry {
	public static Map entries = Maps.newHashMap();

	public BlockList(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement configElement) {
		super(owningScreen, owningEntryList, configElement, entries);
	}
	//TODO: Override rendering code and add a rendering of the highlighted block
}
