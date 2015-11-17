package the_fireplace.bedrockreplacer.events;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import the_fireplace.bedrockreplacer.FullLocMap;
import the_fireplace.bedrockreplacer.config.BRConfigValues;

public class FullForgeEvents {
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public void onEvent(PopulateChunkEvent.Pre event)
	{
		if(FullLocMap.getInstance() == null)
			FullLocMap.load();
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
							BlockPos pos = new BlockPos(chunk.xPosition*16+x, storage.getYLocation()*16+y, chunk.zPosition*16+z);
							if(storage.get(x, y, z).equals(fromBlock.getDefaultState())){
								FullLocMap.getInstance().bedrocks().put(pos, fromBlock.getDefaultState());
								//System.out.println("Bedrock Position should be ("+pos.getX()+","+pos.getY()+","+pos.getZ()+")");
							}
							if(FullLocMap.getInstance().bedrocks().containsKey(pos))
								if(storage.get(x, y, z).equals(FullLocMap.getInstance().bedrocks().get(pos))){
									storage.set(x, y, z, toBlock.getDefaultState());
									FullLocMap.getInstance().bedrocks().put(pos, toBlock.getDefaultState());
								}else if(FullLocMap.getInstance().bedrocks().containsKey(pos)){
									FullLocMap.getInstance().bedrocks().remove(pos);
								}
						}
					}
				}
			}
		}
		chunk.setModified(true);
		FullLocMap.save();
	}
}
