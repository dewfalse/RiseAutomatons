package riseautomatons.common.block;

import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockHeal extends Block {

	static int D = 17;
	int[] ferp = { 1835782, 2819338, 4197138, 5378329, 6362656, 7677745,
			9386051, 11028307, 11753058, 13200761, 14586011, 14722220,
			15774908, 16428224, 16763351 };

	protected BlockHeal(int i) {
		super(i, Material.grass);
		this.blockIndexInTexture = 17;
		setTickRandomly(true);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getBlockTexture(IBlockAccess par1iBlockAccess, int i,
			int j, int k, int l) {

	    if (l <= 1)
	    {
	      return D;
	    }

	    return 0;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World,
			int par2, int par3, int par4) {
		return null;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void updateTick(World world, int i, int j, int k,
			Random par5Random) {

		int l = world.getBlockMetadata(i, j, k);
		if (l < 4) {
			world.setBlockAndMetadataWithNotify(i, j, k, this.blockID, l + 1);
		}
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1iBlockAccess,
			int par2, int par3, int par4) {
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
	}

	@Override
	public int colorMultiplier(IBlockAccess iblockaccess, int i,
			int j, int k) {
		return this.ferp[ferp.length - 1 - iblockaccess.getBlockMetadata(i, j, k)];
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j,
			int k, Entity entity) {

		if ((entity instanceof EntityLiving)) {
			int l = world.getBlockMetadata(i, j, k);
			if (l > 0) {
				world.spawnParticle("heart", i + 0.5F, j + 0.5F, k + 0.5F,
						0.0D, 0.4000000059604645D, 0.0D);
				((EntityLiving) entity).heal(l * 2);
				world.setBlockAndMetadataWithNotify(i, j, k, this.blockID, 0);
			}
		}
	}

	@Override
	public void setBlockBoundsForItemRender() {

		float f = 0.5F;
		float f1 = 0.125F;
		float f2 = 0.5F;
		setBlockBounds(0.5F - f, 0.5F - f1, 0.5F - f2, 0.5F + f, 0.5F + f1,
				0.5F + f2);
	}

	@Override
	public int getRenderColor(int par1) {
		return this.ferp[ferp.length - 1 - par1];
	}

}
