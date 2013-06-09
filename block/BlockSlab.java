package riseautomatons.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import riseautomatons.Ids;
import riseautomatons.item.EnumCraftSetType;

public class BlockSlab extends Block {

	public BlockSlab(int par1) {
		super(par1,  Material.rock);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1iBlockAccess,
			int par2, int par3, int par4, int par5) {
		return super.shouldSideBeRendered(par1iBlockAccess, par2, par3, par4,
				par5);
	}

	@Override
	public Icon getBlockTextureFromSideAndMetadata(int par1, int par2) {
		return blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("stone");
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World,
			int par2, int par3, int par4) {
		setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		return super.getCollisionBoundingBoxFromPool(par1World, par2, par3,
				par4);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 1;
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return Ids.craftSet;
	}

	@Override
	public int damageDropped(int par1) {
		return EnumCraftSetType.SMALLPLATE.ordinal();
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess,
			int par2, int par3, int par4) {
		int into = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, (16 - into) / 16f, 1.0F);
	}

	@Override
	public void setBlockBoundsForItemRender() {
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
	}

}
