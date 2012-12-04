package riseautomatons.common.item;

import java.util.List;

import riseautomatons.common.Ids;
import riseautomatons.common.Universal;
import riseautomatons.common.entity.*;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;

public class ItemSoulCore extends Item {
	int textur[];

	public ItemSoulCore(int i) {
		super(i);

		setHasSubtypes(true);
		setMaxDamage(0);
	}

	@Override
	public int getIconFromDamage(int i) {
		return i;
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer,
			World world, int i, int j, int k, int par7, float par8, float par9,
			float par10) {

		if (ItemSoulCore.touch(entityplayer, itemstack.getItemDamage(), world,
				i, j, k)) {
			itemstack.stackSize--;
			return true;
		}
		return false;
	}

	public static boolean touch(EntityPlayer entityplayer, int itemDamage,
			World world, int i, int j, int k) {
		int ii = world.getBlockId(i, j, k);
		if (derr(ii)) {
			if (world.isAirBlock(i, j, k)) {
				return false;
			}
			if (world.isRaining() && world.canLightningStrikeAt(i, j + 1, k)) {
				return false;
			}
			if (!Universal.improperWorld(world)) {
				if (ii == Ids.blockWorker) {
					int m = world.getBlockMetadata(i, j, k);
					if (itemDamage == 0) {
						world.spawnEntityInWorld(new EntityWorker(world, (float) i + 0.5F, (float) j, (float) k + 0.5F, m, entityplayer == null ? "" : entityplayer.username));
						world.setBlockWithNotify(i, j, k, 0);
					}
					/*else if (itemDamage == 5) {
						world.spawnEntityInWorld(new EntityWorker(world,
								(float) i + 0.5F, (float) j, (float) k + 0.5F,
								m, ""));
						world.setBlockWithNotify(i, j, k, 0);
					}
					else if (itemDamage == 6) {
						EntityWorker wor = new EntityWorker(world,
								(float) i + 0.5F, (float) j, (float) k + 0.5F,
								m, "Tenebrae");
						wor.setMode(5);
						world.spawnEntityInWorld(wor);
						world.setBlockWithNotify(i, j, k, 0);
					}*/
					else {
						return false;
					}
				}
				/*else if (ii == Ids.sentryBlock) {
					if (itemDamage != 0) {
						return false;
					}
					int m = world.getBlockMetadata(i, j, k);
					world.spawnEntityInWorld(new EntitySentry(world,
							(float) i + 0.5F, (float) j, (float) k + 0.5F, m,
							entityplayer == null ? "huh" : entityplayer.username));
					world.setBlockWithNotify(i, j, k, 0);
				}
				else if (ii == Ids.toteBlock) {
					if (itemDamage != 0) {
						return false;
					}
					EntityTote toter = new EntityTote(world, (float) i + 0.5F,
							(float) j, (float) k + 0.5F,
							entityplayer == null ? "huh" : entityplayer.username);
					world.spawnEntityInWorld(toter);
					TileEntityLatch tot = (TileEntityLatch) world
							.getBlockTileEntity(i, j, k);
					toter.inv.cargoItems = tot.dispenserContents.clone();
					tot.dispenserContents = new ItemStack[9];
					world.setBlockWithNotify(i, j, k, 0);
				}
				else if (ii != 88) {
					Block bb = Block.blocksList[ii];
					int dam = world.getBlockMetadata(i, j, k);
					if (ii == Ids.arch) {
						dam = 0;
					}
					if (itemDamage == 0) {
						int he = 1 + MathHelper.floor_double(bb
								.getBlockHardness(world, i, j, k) * 3);
						world.spawnEntityInWorld(new EntityGolem(world,
								(float) i + 0.5F, (float) j, (float) k + 0.5F,
								ii, he, dam));
					} else if (itemDamage == 5) {
						int he = 1 + MathHelper.floor_double(bb
								.getBlockHardness(world, i, j, k) * 8);
						List<EntityItem> l = world.getEntitiesWithinAABB(
								EntityItem.class, AxisAlignedBB.getBoundingBox(
										i - 2, j - 2, k - 2, i + 2, j + 2,
										k + 2));
						int u = -1;
						if (!l.isEmpty()) {

							for (int n = 0; n < l.size(); n++) {
								EntityItem it = l.get(n);
								if (it.item.itemID == Ids.form) {
									u = it.item.getItemDamage();
									break;
								}
							}
							if (u != -1) {
								world.spawnEntityInWorld(new EntityGolemPure(u,
										world, (float) i + 0.5F, (float) j,
										(float) k + 0.5F, ii, he, dam));
							}
						}

						world.spawnEntityInWorld(new EntityGolemPure(world,
								(float) i + 0.5F, (float) j, (float) k + 0.5F,
								ii, he, dam));
					} else {
						return false;
					}
					world.setBlockWithNotify(i, j, k, 0);
				}
				else {
					world.spawnEntityInWorld(new EntityGolem2(world,
							(float) i + 0.5F, (float) j, (float) k + 0.5F));
					world.setBlockWithNotify(i, j, k, 0);
				}*/
			}
			return true;
		}
		return false;
	}

	@Override
	public String getItemNameIS(ItemStack itemstack) {
		return (new StringBuilder()).append(super.getItemName()).append(".")
				.append(EnumSoulCore.values()[itemstack.getItemDamage()])
				.toString();
	}

	public static boolean derr(int ii) {
		if (ii < 256) {
			if (/*ii == Ids.arch || */(ii > 0 && ii <= 5) || (ii >= 12 && ii <= 17)
					|| ii == 24 || ii == 35 || (ii >= 41 && ii <= 45)
					|| ii == 48 || ii == 49 || ii == 61
					|| (ii >= 79 && ii <= 82) || (ii >= 86 && ii <= 88)
					|| (ii >= 98 && ii <= 100)) {
				return true;
			} else {
				return false;
			}
		}

		if (ii == Ids.blockWorker /*|| ii == Ids.sentryBlock || ii == Ids.toteBlock || ii == Ids.sulfOre || ii == Ids.saltOre*/) {
			return true;
		}
		return false;
	}

	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs,
			List par3List) {
		for (int var4 = 0; var4 < EnumSoulCore.values().length; ++var4) {
			par3List.add(new ItemStack(par1, 1, var4));
		}
	}

}