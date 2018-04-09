package the_fireplace.bedrockreplacer;

import net.minecraft.block.Block;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import the_fireplace.bedrockreplacer.config.BlockList;
import the_fireplace.bedrockreplacer.proxy.Common;

@Mod(modid = BedrockReplacer.MODID, name = BedrockReplacer.MODNAME, guiFactory = "the_fireplace.bedrockreplacer.config.BRGuiFactory", canBeDeactivated = true, updateJSON = "http://thefireplace.bitnamiapp.com/jsons/bedrockreplacer.json", acceptedMinecraftVersions = "[1.12,)", acceptableRemoteVersions = "*")
public class BedrockReplacer {
	public static final String MODID = "bedrockreplacer";
	public static final String MODNAME = "Bedrock Replacer";

	@SidedProxy(clientSide = "the_fireplace.bedrockreplacer.proxy.Client", serverSide = "the_fireplace.bedrockreplacer.proxy.Common")
	public static Common proxy;

	@EventHandler
	@SuppressWarnings("unchecked")
	public void postInit(FMLPostInitializationEvent event) {
		if(event.getSide().isClient())
			proxy.initBlockList();
	}

	public static boolean isBlockRisky(Block block) {
		return !block.getDefaultState().isOpaqueCube() || !block.getDefaultState().isFullCube() || !block.isCollidable() || block.hasTileEntity(block.getDefaultState());
	}

	@Config(modid = MODID)
	public static class ConfigValues{
		@Config.Comment("The block id to replace Bedrock with.")
		@Config.LangKey("replacewith")
		public static String replacewith = "minecraft:stone";
		@Config.Comment("Enables using blocks that might crash/lag the game if used to replace bedrock. Enable at your own risk.")
		@Config.LangKey("riskyblocks")
		public static boolean riskyblocks = false;
		@Config.Comment("This is the Dimension Black/Whitelist. If it contains *, it is a blacklist. Otherwise, it is a whitelist.")
		@Config.LangKey("dimension_list")
		public static String[] dimension_list = new String[]{"*"};
		@Config.Comment("What percentage of bedrock gets replaced. 0.0D = 0%, 1.0D = 100%")
		@Config.RangeDouble(min = 0.0D, max=1.0D)
		@Config.LangKey("replacepercent")
		public static double replacepercent = 1.0D;
		@Config.Comment("Multiplies the bedrock removal chance by the block's y-value.")
		@Config.LangKey("multiplychance")
		public static boolean multiplychance = true;
	}
}
