package the_fireplace.bedrockreplacer.events;

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import the_fireplace.bedrockreplacer.BedrockReplacer;
import the_fireplace.bedrockreplacer.BedrockReplacerLite;

public class LiteFMLEvents {
	@SubscribeEvent
	public void configChanged(ConfigChangedEvent event){
		if(event.modID == BedrockReplacer.MODID){
			BedrockReplacerLite.syncConfig();
		}
	}
}
