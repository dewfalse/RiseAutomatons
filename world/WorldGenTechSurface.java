package riseautomatons.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import riseautomatons.Ids;
import riseautomatons.block.Blocks;

public class WorldGenTechSurface extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, int var3, int var4,
			int var5) {
		for(int i = var3; i < var3 + 16; i++) {
			for(int k = var5; k < var5 + 16; k++) {
				for(int j = 255; j >= var4; --j) {
					Block block = world.getBlock(i, j, k);
					int meta = 0;
					if(block == Blocks.sand) {
						meta = 1;
					}
					else if(block == Blocks.grass) {
						meta = 2;
					}
					else if(block == Blocks.clay) {
						meta = 3;
					}
					else if(block == Blocks.dirt) {
						meta = 2;
					}
					else {
						continue;
					}
					world.setBlock(i, j, k, Ids.blockFrass, meta, 3);
					break;
				}
			}
		}
		return true;
	}

}
