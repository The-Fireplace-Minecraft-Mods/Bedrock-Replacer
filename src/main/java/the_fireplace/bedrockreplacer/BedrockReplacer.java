package the_fireplace.bedrockreplacer;

import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.FMLControlledNamespacedRegistry;
import net.minecraftforge.fml.common.registry.GameData;
import the_fireplace.bedrockreplacer.config.BRConfigValues;
import the_fireplace.bedrockreplacer.config.BlockList;
import the_fireplace.bedrockreplacer.events.ClientEvents;
import the_fireplace.bedrockreplacer.events.CommonEvents;
import the_fireplace.bedrockreplacer.proxy.Common;

@Mod(modid=BedrockReplacer.MODID, name=BedrockReplacer.MODNAME, guiFactory = "the_fireplace.bedrockreplacer.config.BRGuiFactory", canBeDeactivated=true, updateJSON = "http://thefireplace.bitnamiapp.com/jsons/bedrockreplacer.json", acceptedMinecraftVersions = "[1.9.4,)")
public class BedrockReplacer {
	public static final String MODID = "bedrockreplacer";
	public static final String MODNAME = "Bedrock Replacer";
	@Mod.Instance(BedrockReplacer.MODID)
	public static BedrockReplacer instance;

    @SidedProxy(clientSide = "the_fireplace.bedrockreplacer.proxy.Client", serverSide = "the_fireplace.bedrockreplacer.proxy.Common")
    public static Common proxy;

	public static Configuration file;
	public static Property REPLACEWITH_PROPERTY;
	public static Property RISKYBLOCKS_PROPERTY;
	public static Property DIMENSIONS_PROPERTY;

	public static void syncConfig(){
		BRConfigValues.REPLACEWITH = REPLACEWITH_PROPERTY.getString();
		BRConfigValues.RISKYBLOCKS = RISKYBLOCKS_PROPERTY.getBoolean();
		BRConfigValues.DIMENSIONS = DIMENSIONS_PROPERTY.getIntList();
		if(file.hasChanged())
			file.save();
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		file = new Configuration(event.getSuggestedConfigurationFile());
		file.load();
		REPLACEWITH_PROPERTY = file.get(Configuration.CATEGORY_GENERAL, BRConfigValues.REPLACEWITH_NAME, BRConfigValues.REPLACEWITH_DEFAULT, proxy.translateToLocal(BRConfigValues.REPLACEWITH_NAME+".tooltip"));
		RISKYBLOCKS_PROPERTY = file.get(Configuration.CATEGORY_GENERAL, BRConfigValues.RISKYBLOCKS_NAME, BRConfigValues.RISKYBLOCKS_DEFAULT, proxy.translateToLocal(BRConfigValues.RISKYBLOCKS_NAME+".tooltip"));
		DIMENSIONS_PROPERTY = file.get(Configuration.CATEGORY_GENERAL, BRConfigValues.DIMENSIONS_NAME, BRConfigValues.DIMENSIONS_DEFAULT, proxy.translateToLocal(BRConfigValues.DIMENSIONS_NAME+".tooltip"));
		if(event.getSide().isClient()){
			REPLACEWITH_PROPERTY.setConfigEntryClass(BlockList.class);
			RISKYBLOCKS_PROPERTY.setRequiresMcRestart(true);
		}
		syncConfig();
	}
	@EventHandler
	public void init(FMLInitializationEvent event){
		MinecraftForge.EVENT_BUS.register(new CommonEvents());
		if(event.getSide().isClient())
			MinecraftForge.EVENT_BUS.register(new ClientEvents());
	}
	@EventHandler
    @SuppressWarnings("unchecked")
	public void postInit(FMLPostInitializationEvent event){
		if(event.getSide().isClient()) {
			FMLControlledNamespacedRegistry<Block> registry = GameData.getBlockRegistry();
			Object[] reg = registry.getKeys().toArray();
			for (Object element : reg) {
				String id = element.toString();
				String name = proxy.translateToLocal(Block.getBlockFromName(element.toString()).getUnlocalizedName() + ".name");
				if (!name.contains("tile.") && !name.contains(".name"))
					if (BRConfigValues.RISKYBLOCKS)
						BlockList.entries.put(id, name);
					else if (Block.getBlockFromName(id).getDefaultState().isOpaqueCube() && Block.getBlockFromName(id).isCollidable() && !Block.getBlockFromName(id).hasTileEntity())
						BlockList.entries.put(id, name);
			}
		}
	}
}
