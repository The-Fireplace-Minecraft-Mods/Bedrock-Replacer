package the_fireplace.bedrockreplacer.proxy;

import net.minecraft.client.resources.I18n;

/**
 * @author The_Fireplace
 */
public class Client extends Common {
    public String translateToLocal(String key, Object... args){
        return I18n.format(key, args);
    }
}
