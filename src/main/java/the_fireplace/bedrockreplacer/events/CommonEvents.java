package the_fireplace.bedrockreplacer.events;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import the_fireplace.bedrockreplacer.config.BRConfigValues;

public class CommonEvents {
	private Block fromBlock = Blocks.BEDROCK;
	@SubscribeEvent
	public void onEvent(PopulateChunkEvent.Pre event)
	{
		for(int dim:BRConfigValues.DIMENSIONS)
			if (event.getWorld().provider.getDimension() == dim)
				return;
		Chunk chunk = event.getWorld().getChunkFromChunkCoords(event.getChunkX(), event.getChunkZ());

		Block toBlock;
		if(Block.getBlockFromName(BRConfigValues.REPLACEWITH) != null)
			toBlock = Block.getBlockFromName(BRConfigValues.REPLACEWITH);
		else
			toBlock = Blocks.BEDROCK;

		for (ExtendedBlockStorage storage : chunk.getBlockStorageArray())
		{
			if (storage != null)
			{
				for (int x = 0; x < 16; x++)
				{
					for (int y = 0; y < 16; y++)
					{
						for (int z = 0; z < 16; z++)
						{
							if(storage.get(x, y, z).equals(fromBlock.getDefaultState()))
							{
								storage.set(x, y, z, toBlock.getDefaultState());
								if(toBlock.equals(Blocks.BEDROCK))
									System.out.println("Bedrock replaced with Bedrock. Either your No Bedrock config is improperly formatted or you have it set to replace Bedrock with Bedrock, which is pointless. Please use the Config GUI to change the settings if possible.");
							}
						}
					}
				}
			}
		}
		chunk.setModified(true);
	}
}
