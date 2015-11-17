/*package the_fireplace.bedrockreplacer;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
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
import the_fireplace.bedrockreplacer.config.BRConfigValues;
import the_fireplace.bedrockreplacer.config.BlockList;
import the_fireplace.bedrockreplacer.events.FullFMLEvents;
import the_fireplace.bedrockreplacer.events.FullForgeEvents;

/**
 * @author The_Fireplace
 *,/
@Mod(modid=BedrockReplacer.MODID+"full", name=BedrockReplacer.MODNAME+" Full", version=BedrockReplacer.VERSION, acceptedMinecraftVersions = "1.8", guiFactory = "the_fireplace.bedrockreplacer.config.BRGuiFactory", canBeDeactivated=true)
public class BedrockReplacerFull {
	@Instance(BedrockReplacer.MODID+"full")
	public static BedrockReplacerFull instance;

	public static Configuration file;
	public static Property REPLACEWITH_PROPERTY;
	public static Property RISKYBLOCKS_PROPERTY;
	public static Property ENABLELITEMODE_PROPERTY;

	public static void syncConfig(){
		BRConfigValues.REPLACEWITH = REPLACEWITH_PROPERTY.getString();
		BRConfigValues.RISKYBLOCKS = RISKYBLOCKS_PROPERTY.getBoolean();
		BRConfigValues.ENABLELITEMODE = ENABLELITEMODE_PROPERTY.getBoolean();
		if(file.hasChanged()){
			file.save();
		}
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		event.getModMetadata().parent=BedrockReplacer.MODID;
		file = new Configuration(new File(new File(Minecraft.getMinecraft().mcDataDir, "config"), "bedrockreplacer.cfg"));
		file.load();
		REPLACEWITH_PROPERTY = file.get(Configuration.CATEGORY_GENERAL, BRConfigValues.REPLACEWITH_NAME, BRConfigValues.REPLACEWITH_DEFAULT, StatCollector.translateToLocal(BRConfigValues.REPLACEWITH_NAME+".tooltip"));
		RISKYBLOCKS_PROPERTY = file.get(Configuration.CATEGORY_GENERAL, BRConfigValues.RISKYBLOCKS_NAME, BRConfigValues.RISKYBLOCKS_DEFAULT, StatCollector.translateToLocal(BRConfigValues.RISKYBLOCKS_NAME+".tooltip"));
		ENABLELITEMODE_PROPERTY = file.get(Configuration.CATEGORY_GENERAL, BRConfigValues.ENABLELITEMODE_NAME, BRConfigValues.ENABLELITEMODE_DEFAULT, StatCollector.translateToLocal(BRConfigValues.ENABLELITEMODE_NAME+".tooltip"));
		if(event.getSide().isClient()){
			REPLACEWITH_PROPERTY.setConfigEntryClass(BlockList.class);
			RISKYBLOCKS_PROPERTY.setRequiresMcRestart(true);
			ENABLELITEMODE_PROPERTY.setRequiresMcRestart(true);
		}
		syncConfig();
	}
	@EventHandler
	public void init(FMLInitializationEvent event){
		if(!BRConfigValues.ENABLELITEMODE){
			FMLCommonHandler.instance().bus().register(new FullFMLEvents());
			MinecraftForge.EVENT_BUS.register(new FullForgeEvents());
		}
	}
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		if(!BRConfigValues.ENABLELITEMODE){
			FMLControlledNamespacedRegistry<Block> registry = GameData.getBlockRegistry();
			Object[] reg = registry.getKeys().toArray();
			for (Object element : reg) {
				String id=element.toString();
				String name=StatCollector.translateToLocal(Block.getBlockFromName(element.toString()).getUnlocalizedName()+".name");
				if(!name.contains("tile.") && !name.contains(".name"))
					if(BRConfigValues.RISKYBLOCKS)
						BlockList.entries.put(id, name);
					else if(Block.getBlockFromName(id).isOpaqueCube())
						BlockList.entries.put(id, name);
			}
		}
	}
}*/