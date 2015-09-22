package the_fireplace.nobedrock.events;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import the_fireplace.nobedrock.config.ConfigValues;

public class ForgeEvents {
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public void onEvent(PopulateChunkEvent.Pre event)
	{
		Chunk chunk = event.world.getChunkFromChunkCoords(event.chunkX, event.chunkZ);
		Block fromBlock = Blocks.bedrock;
		Block toBlock = Block.getBlockFromName(ConfigValues.REPLACEWITH);

		for (ExtendedBlockStorage storage : chunk.getBlockStorageArray())
		{
			if (storage != null)
			{
				for (int x = 0; x < 16; x++)
				{
					for (int y = 0; y < 16; y++)//256 was in the initial code, it gave an indexoutofboundsexception. This seems to work.
					{
						for (int z = 0; z < 16; z++)
						{
							if(storage.get(x, y, z).equals(fromBlock.getDefaultState()))
							{
								storage.set(x, y, z, toBlock.getDefaultState());
							}
						}
					}
				}
			}
		}
		chunk.setModified(true);
	}
}
