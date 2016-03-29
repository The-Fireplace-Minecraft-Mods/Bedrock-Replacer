package the_fireplace.bedrockreplacer.events;

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import the_fireplace.bedrockreplacer.BedrockReplacer;

/**
 * @author The_Fireplace
 */
public class ClientEvents {
    @SubscribeEvent
    public void configChanged(ConfigChangedEvent event){
        if(event.getModID().equals(BedrockReplacer.MODID))
            BedrockReplacer.syncConfig();
    }
}
