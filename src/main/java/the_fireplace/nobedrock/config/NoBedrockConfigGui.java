package the_fireplace.nobedrock.config;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import the_fireplace.nobedrock.NoBedrock;

/**
 * @author The_Fireplace
 */
public class NoBedrockConfigGui extends GuiConfig{
	public NoBedrockConfigGui(GuiScreen parentScreen) {
		super(parentScreen,
				new ConfigElement(NoBedrock.file.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), NoBedrock.MODID, true,
				false, GuiConfig.getAbridgedConfigPath(NoBedrock.file.toString()));
		this.titleLine2="";
	}
}