package the_fireplace.nobedrock;

import net.minecraft.block.Block;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.FMLControlledNamespacedRegistry;
import net.minecraftforge.fml.common.registry.GameData;
import the_fireplace.nobedrock.config.BlockList;
import the_fireplace.nobedrock.config.ConfigValues;
import the_fireplace.nobedrock.events.FMLEvents;
import the_fireplace.nobedrock.events.ForgeEvents;

/**
 * @author The_Fireplace
 */
@Mod(modid=NoBedrock.MODID, name=NoBedrock.MODNAME, version=NoBedrock.VERSION, acceptedMinecraftVersions = "1.8", guiFactory = "the_fireplace.nobedrock.config.NoBedrockGuiFactory")
public class NoBedrock {
	@Instance(NoBedrock.MODID)
	public static NoBedrock instance;
	public static final String MODID = "nobedrock";
	public static final String MODNAME = "No Bedrock";
	public static final String VERSION = "2.0.0.1";
	public static final String downloadURL = "";

	public static Configuration file;
	public static Property REPLACEWITH_PROPERTY;

	public static void syncConfig(){
		ConfigValues.REPLACEWITH = REPLACEWITH_PROPERTY.getString();
		if(file.hasChanged()){
			file.save();
		}
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		file = new Configuration(event.getSuggestedConfigurationFile());
		file.load();
		REPLACEWITH_PROPERTY = file.get(Configuration.CATEGORY_GENERAL, ConfigValues.REPLACEWITH_NAME, ConfigValues.REPLACEWITH_DEFAULT, StatCollector.translateToLocal(ConfigValues.REPLACEWITH_NAME+".tooltip"));
		if(event.getSide().isClient())
			REPLACEWITH_PROPERTY.setConfigEntryClass(BlockList.class);
		syncConfig();
	}
	@EventHandler
	public void init(FMLInitializationEvent event){
		FMLCommonHandler.instance().bus().register(new FMLEvents());
		MinecraftForge.EVENT_BUS.register(new ForgeEvents());
	}
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		FMLControlledNamespacedRegistry<Block> registry = GameData.getBlockRegistry();
		Object[] reg = registry.getKeys().toArray();
		for (Object element : reg) {
			String id=element.toString();
			String name=StatCollector.translateToLocal(Block.getBlockFromName(element.toString()).getUnlocalizedName()+".name");
			if(!name.contains("tile.") && !name.contains(".name"))
				BlockList.entries.put(id, name);
		}
	}
	public static final String LATEST = "";
}
