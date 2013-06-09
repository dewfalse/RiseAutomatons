package riseautomatons.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCrink extends Block {

	private Icon icons[];
	protected BlockCrink(int i, int j) {

		super(i, Material.circuits);
		slipperiness = 1.0F;
		loadSprites();
	}

	public BlockCrink(int par1, Material par2Material) {
		super(par1, par2Material);
		loadSprites();
	}

	public BlockCrink(int par1, int par2, Material par3Material) {
		super(par1, par3Material);
		loadSprites();
	}

	static int D[];

	static void loadSprites() {
		D = new int[3];
		D[0] = 9;// zei_Universal.modOverride("/terrain.png",
					// "/riseautomatons/crink.png");
		D[1] = 7;// zei_Universal.modOverride("/terrain.png",
					// "/riseautomatons/crink2.png");
		D[2] = 8;// zei_Universal.modOverride("/terrain.png",
					// "/riseautomatons/crink3.png");
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i,
			int j, int k, int l) {
		int i1 = iblockaccess.getBlockId(i, j, k);

		if (i1 == blockID) {
			return false;
		} else {
			return super.shouldSideBeRendered(iblockaccess, i, j, k, l);
		}
	}

	@Override
	public Icon getBlockTextureFromSideAndMetadata(int i, int j) {
		if (!Block.leaves.graphicsLevel) {
			return icons[2];
		}

		if (j > 0) {
			return icons[0]; // top 235
		}

		return icons[1]; // D[0]
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[3];
		icons[0] = par1IconRegister.registerIcon("riseautomatons:crink");
		icons[1] = par1IconRegister.registerIcon("riseautomatons:crink2");
		icons[2] = par1IconRegister.registerIcon("riseautomatons:crink3");
	}

	@Override
	public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer,
			int par3, int par4, int par5, int par6) {
		super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
	}

	@Override
	public boolean isOpaqueCube() {
		return (!Block.leaves.graphicsLevel);// ?1:0;//false;
	}

	@Override
	public int getRenderBlockPass() {
		return (!Block.leaves.graphicsLevel) ? 0 : 1; // false;;
	}

	@Override
	public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k) {
		int g = iblockaccess.getBlockMetadata(i, j, k);
		return derk[g];
	}

	int derk[] = {
			0xffffff, // 0x0093bd,
			0x496FD6, 0x003469, 0x96E6EB, 0x43cef1, 0x4b6e8a, 0x7D4B94,
			0x292f3f, 0x08143a, 0x737a7b, 0x3ED613 };

}
