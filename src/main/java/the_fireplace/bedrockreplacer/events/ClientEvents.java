package the_fireplace.bedrockreplacer.events;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import the_fireplace.bedrockreplacer.BedrockReplacer;

/**
 * @author The_Fireplace
 */
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = BedrockReplacer.MODID)
public class ClientEvents {
	@SubscribeEvent
	public static void configChanged(ConfigChangedEvent event) {
		if (event.getModID().equals(BedrockReplacer.MODID)) {
			ConfigManager.sync(BedrockReplacer.MODID, Config.Type.INSTANCE);
			BedrockReplacer.proxy.initBlockList();
		}
	}
}
