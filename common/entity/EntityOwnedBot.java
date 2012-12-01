package riseautomatons.common.entity;

import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityCreature;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public class EntityOwnedBot extends EntityCreature {

	public EntityOwnedBot(World par1World) {
		super(par1World);
	}

	@Override
	public int getMaxHealth() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return 0;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(17, "");
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {

		if (par1DamageSource == DamageSource.inWall) {
			this.pushOutOfBlocks(posX, posY, posZ);
			return false;
		}
		Entity entity = par1DamageSource.getEntity();

		if (entity != null && entity != this
				&& (entity instanceof EntityPlayer)
				&& ((EntityPlayer) entity).username.equals(getBotOwner())) {
			par2 = 20;
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	@Override
	protected int getDropItemId() {
		return 0;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {

		super.writeEntityToNBT(par1nbtTagCompound);

		if (getBotOwner() == null) {
			par1nbtTagCompound.setString("Owner", "");
		} else {
			par1nbtTagCompound.setString("Owner", getBotOwner());
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {

		super.readEntityFromNBT(par1nbtTagCompound);
		String s = par1nbtTagCompound.getString("Owner");

		if (s.length() > 0) {
			setBotOwner(s);
		}
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	public String getBotOwner() {
		return dataWatcher.getWatchableObjectString(17);
	}

	public void setBotOwner(String s) {
		dataWatcher.updateObject(17, s);
	}

	public EntityPlayer reallyGetBotOwner() {
		return (EntityPlayer) worldObj.getPlayerEntityByName(getBotOwner());
	}

}