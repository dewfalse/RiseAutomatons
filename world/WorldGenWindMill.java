package riseautomatons.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import riseautomatons.Ids;

public class WorldGenWindMill extends WorldGenAbstractTree {
	int MAX = 12;

    public WorldGenWindMill(boolean p_i45448_1_) {
        super(p_i45448_1_);
    }

    @Override
	public boolean generate(World world, Random random, int i, int j,
			int k) {
		path(0, world, random, i, j, k);
		return true;
	}

	private void path(int f, World world, Random random, int i, int j, int k) {
		if (f >= MAX) {
			return;
		}

		int size = random.nextInt(MAX - f) + 3;
		int n = 0;

		for (n = 0; n < size - 1; n++) {
			if (n < size - 2) {
				//world.setBlock(i - 1, j + n, k, 4);
				//world.setBlock(i + 1, j + n, k, 4);
				//world.setBlock(i, j + n, k - 1, 4);
				//world.setBlock(i, j + n, k + 1, 4);
			}

			world.setBlock(i, j + n, k, Ids.blockTurn, 8, 3);
			//world.setBlock(i, j + n, k, 4);
		}
		world.setBlock(i, j + n - 1, k, Ids.blockTurn, 8, 3);
		world.setBlock(i, j + n, k, Ids.blockWindmill, 2, 3);

	}

}
