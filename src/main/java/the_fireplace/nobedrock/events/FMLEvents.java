package the_fireplace.nobedrock.events;

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import the_fireplace.nobedrock.NoBedrock;

public class FMLEvents {
	@SubscribeEvent
	public void configChanged(ConfigChangedEvent event){
		if(event.modID == NoBedrock.MODID){
			NoBedrock.syncConfig();
		}
	}
}
