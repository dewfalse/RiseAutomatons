package riseautomatons.item;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import riseautomatons.Ids;

public class ItemAPickaxe extends ItemPickaxe {
	  private static Block[] blocksEffectiveAgainst = {
		    Block.cobblestone, Block.stone, Block.sandStone, Block.cobblestoneMossy, Block.oreIron, Block.blockIron, Block.oreCoal, Block.blockGold,
		    Block.oreGold, Block.oreDiamond, Block.blockDiamond, Block.ice, Block.netherrack, Block.oreLapis, Block.blockLapis, Block.blocksList[Ids.blockTech], Block.blocksList[Ids.blockFrass] };

	public ItemAPickaxe(int i, int j) {
		super(i, EnumToolMaterial.EMERALD);
		this.efficiencyOnProperMaterial = 4.0F;
		setMaxDamage(4);
		this.efficiencyOnProperMaterial = 100.0F;
		this.damageVsEntity = (j + 2);
	}

	public ItemAPickaxe(int i) {
		this(i, 2);
	}

	@Override
	public float getStrVsBlock(ItemStack par1ItemStack, Block block) {
		return block.getBlockHardness(null, 0,0,0) > 3.0F ? 1.0F : 100.0F;
	}

	@Override
	public boolean hitEntity(ItemStack itemstack,
			EntityLivingBase par2EntityLiving, EntityLivingBase entityliving1) {
		itemstack.damageItem(2, entityliving1);
	    return true;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack itemstack, World par2World,
			int par3, int par4, int par5, int par6,
			EntityLivingBase entityliving) {
		itemstack.damageItem(1, entityliving);
	    return true;
	}

	@Override
	public boolean isFull3D() {
		return true;
	}

	@Override
	public boolean canHarvestBlock(Block block) {
		return block.getBlockHardness(null, 0,0,0) <= 3.0F;
	}

}
