package the_fireplace.bedrockreplacer;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

@Mod(modid=BedrockReplacer.MODID, name=BedrockReplacer.MODNAME, version=BedrockReplacer.VERSION, acceptedMinecraftVersions = "1.8", guiFactory = "the_fireplace.bedrockreplacer.config.BRGuiFactory", canBeDeactivated=true)
public class BedrockReplacer {
	public static final String MODID = "bedrockreplacer";
	public static final String MODNAME = "Bedrock Replacer";
	public static final String VERSION = "2.0.0.1";
	public static final String downloadURL = "http://goo.gl/bIxraI";
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		//Loader.instance().runtimeDisableMod(BRConfigValues.ENABLELITEMODE ? "bedrockreplacerfull" : "bedrockrepalcerlite");
	}
	public static final String LATEST = "https://dl.dropboxusercontent.com/s/x6led262x6sd11f/latest.version?dl=0";
}
