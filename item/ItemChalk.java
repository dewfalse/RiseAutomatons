package riseautomatons.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import riseautomatons.block.Blocks;

public class ItemChalk extends Item {

	public ItemChalk() {
		super();
		this.setMaxDamage(1000);
		this.setMaxStackSize(1);
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer,
			World world, int i, int j, int k, int l, float par8, float par9,
			float par10) {

		Block block = world.getBlock(i, j, k);

		if (block == Blocks.chalk) {
			int meta = world.getBlockMetadata(i, j, k);

			if (meta < 7) {
				world.setBlockMetadataWithNotify(i, j, k, meta + 1, 3); // zei_Ids.chalk,
				world.markBlockForUpdate(i, j, k);
				itemstack.damageItem(1, entityplayer);
				return true;
			}
		}

		if (block != Blocks.snow) {
			if (l == 0) {
				j--;
			}

			if (l == 1) {
				j++;
			}

			if (l == 2) {
				k--;
			}

			if (l == 3) {
				k++;
			}

			if (l == 4) {
				i--;
			}

			if (l == 5) {
				i++;
			}

			block = world.getBlock(i, j, k);
			if (!world.isAirBlock(i, j, k) && world.getBlock(i, j, k) != Blocks.chalk) {
				return false;
			}
		}

		if (!entityplayer.canPlayerEdit(i, j, k, l, null)) {
			return false;
		}

		if (Blocks.chalk.canPlaceBlockAt(world, i, j, k)) {
			// itemstack.stackSize--;
			itemstack.damageItem(1, entityplayer);
			int meta = (block == Blocks.chalk) ? world.getBlockMetadata(i, j, k) : 0;
			if (meta < 7) {
				meta++;
			}
			world.setBlock(i, j, k, Blocks.chalk, meta, 3);
		}

		return true;
	}
}
