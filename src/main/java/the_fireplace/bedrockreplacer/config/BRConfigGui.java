package the_fireplace.bedrockreplacer.config;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import the_fireplace.bedrockreplacer.BedrockReplacer;
import the_fireplace.bedrockreplacer.BedrockReplacerLite;

/**
 * @author The_Fireplace
 */
public class BRConfigGui extends GuiConfig{
	public BRConfigGui(GuiScreen parentScreen) {
		super(parentScreen,
				new ConfigElement(BedrockReplacerLite.file.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), BedrockReplacer.MODID, true,
				false, GuiConfig.getAbridgedConfigPath(BedrockReplacerLite.file.toString()));
		this.titleLine2="";
	}
}