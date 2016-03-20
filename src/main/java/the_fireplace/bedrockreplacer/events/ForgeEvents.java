package the_fireplace.bedrockreplacer.events;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import the_fireplace.bedrockreplacer.BedrockReplacer;
import the_fireplace.bedrockreplacer.config.BRConfigValues;

public class ForgeEvents {
	@SubscribeEvent
	public void onEvent(PopulateChunkEvent.Pre event)
	{
		for(int dim:BRConfigValues.DIMENSIONS)
			if (event.world.provider.getDimension() == dim)
				return;
		Chunk chunk = event.world.getChunkFromChunkCoords(event.chunkX, event.chunkZ);
		Block fromBlock = Blocks.bedrock;
		Block toBlock;
		if(Block.getBlockFromName(BRConfigValues.REPLACEWITH) != null)
			toBlock = Block.getBlockFromName(BRConfigValues.REPLACEWITH);
		else
			toBlock = Blocks.bedrock;

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
								if(toBlock.equals(Blocks.bedrock))
									System.out.println("Bedrock replaced with Bedrock. Either your No Bedrock config is improperly formatted or you have it set to replace Bedrock with Bedrock, which is pointless. Please use the Config GUI to change the settings if possible.");
							}
						}
					}
				}
			}
		}
		chunk.setModified(true);
	}

	@SubscribeEvent
	public void configChanged(ConfigChangedEvent event){
		if(event.modID.equals(BedrockReplacer.MODID))
			BedrockReplacer.syncConfig();
	}
}
