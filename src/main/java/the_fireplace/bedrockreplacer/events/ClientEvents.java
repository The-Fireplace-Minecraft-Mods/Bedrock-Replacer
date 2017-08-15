package the_fireplace.bedrockreplacer.events;

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import the_fireplace.bedrockreplacer.BedrockReplacer;

/**
 * @author The_Fireplace
 */
@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientEvents {
	@SubscribeEvent
	public static void configChanged(ConfigChangedEvent event) {
		if (event.getModID().equals(BedrockReplacer.MODID))
			BedrockReplacer.syncConfig();
	}
}
