package the_fireplace.bedrockreplacer.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import the_fireplace.bedrockreplacer.BedrockReplacer;
import the_fireplace.bedrockreplacer.config.BlockList;

/**
 * @author The_Fireplace
 */
@SideOnly(Side.CLIENT)
public class Client extends Common {
	public String translateToLocal(String key, Object... args) {
		return I18n.format(key, args);
	}

	public void initBlockList(){
		BlockList.entries.clear();
		IForgeRegistry<Block> registry = ForgeRegistries.BLOCKS;
		Object[] reg = registry.getKeys().toArray();
		for (Object element : reg) {
			String id = element.toString();
			Block block = Block.getBlockFromName(id);
			if (block == null)
				continue;
			String name = translateToLocal(block.getUnlocalizedName() + ".name");
			if (!name.contains("tile.") && !name.contains(".name"))
				if (BedrockReplacer.ConfigValues.riskyblocks)
					BlockList.entries.put(id, name);
				else if (!BedrockReplacer.isBlockRisky(block))
					BlockList.entries.put(id, name);
		}
	}
}
