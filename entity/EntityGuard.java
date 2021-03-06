package riseautomatons.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import riseautomatons.Ids;
import riseautomatons.Universal;

public class EntityGuard extends EntityOwnedBot implements IBot {

	public static final ResourceLocation GUARD_PNG = new ResourceLocation("riseautomatons", "textures/entities/guardSkin.png");
	public static int renderId;

	public EntityGuard(World world) {
		super(world);
		this.setHealth(getMaxHealth());
		float moveSpeed = 0.0F;
		setSize(0.5F, 0.5F);
		this.tasks.addTask(4, new EntityAIBotArrowAttack(this, moveSpeed, 1, 6));

		tasks.addTask(4, new EntityAIAttackOnCollide(this, moveSpeed, true));
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityMob.class, 8F));
		tasks.addTask(9, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityMob.class, 1, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityDragon.class, 1, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityFlying.class, 1, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySlime.class, 1, true));
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(24.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0F);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
	}

	public EntityGuard(World world, double d, double d1, double d2) {

		this(world);
		setPosition(d, d1 + this.yOffset, d2);
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.prevPosX = d;
		this.prevPosY = d1;
		this.prevPosZ = d2;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Override
	public boolean attackEntityFrom(DamageSource damagesource, float i) {

		Entity entity = damagesource.getEntity();
		if ((entity != null) && (entity != this)
				&& ((entity instanceof EntityPlayer))) {
			i = 20;
		}
		return super.attackEntityFrom(damagesource, i);
	}

	@Override
	protected Item getDropItem() {
		return null;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeEntityToNBT(par1nbtTagCompound);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readEntityFromNBT(par1nbtTagCompound);
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	protected String getLivingSound() {
		return "riseautomatons:swee";
	}

	@Override
	protected String getHurtSound() {
		return "riseautomatons:clank";
	}

	@Override
	protected String getDeathSound() {
		return "random.glass";
	}

	@Override
	protected void attackEntity(Entity entity, float f) {

		if (f < 15.0F) {
			double d = entity.posX - this.posX;
			double d1 = entity.posZ - this.posZ;
			if (this.attackTime == 0) {
				Entity entity1 = entity;
				double d2 = entity1.posX - this.posX;
				double d3 = entity1.boundingBox.minY + entity1.height / 2.0F
						- (this.posY + this.height);
				double d4 = entity1.posZ - this.posZ;
				EntityLaser entitylaser = new EntityLaser(
						this.worldObj, this, d2, d3, d4, 0.1D);
				entitylaser.posX = this.posX;
				entitylaser.posY = (this.posY + this.height / 1.5F);
				entitylaser.posZ = this.posZ;
				this.worldObj.spawnEntityInWorld(entitylaser);
				this.worldObj.playSoundAtEntity(this, "riseautomatons:fwoom", 1.0F, 1.0F);
				this.attackTime = 40;
			}
			this.rotationYawHead = (float) ((Math.atan2(d1, d)) * 180D / Math.PI - 90D);

			double xx = entity.posX - (double) ((float) this.posX + 0.5F);
			double zz = entity.posZ - (double) ((float) this.posZ + 0.5F);
			double yy = entity.posY - (double) ((float) this.posY + 0.5F);
		}
	}

	@Override
	protected Entity findPlayerToAttack() {

		List list = this.worldObj.getEntitiesWithinAABB(EntityMob.class, this.boundingBox.expand(12.0D, 6.0D, 12.0D));
		list.addAll(this.worldObj.getEntitiesWithinAABB(EntitySlime.class, this.boundingBox.expand(12.0D, 6.0D, 12.0D)));
		list.addAll(this.worldObj.getEntitiesWithinAABB(EntityAniBot.class, this.boundingBox.expand(12.0D, 6.0D, 12.0D)));
		//list.addAll(list1);
		if (!list.isEmpty()) {
			return closest(list);
		}

		return null;
	}

	@Override
	protected float getSoundVolume() {
		return 0.1F;
	}

	@Override
	public void onDeath(DamageSource damagesource) {

		super.onDeath(damagesource);
		if (!Universal.improperWorld(this.worldObj)) {
			dropper();
		}
		setDead();
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public void onLivingUpdate() {
		if (!Universal.improperWorld(this.worldObj)) {
			if (!this.worldObj.getBlock(
					MathHelper.floor_double(this.posX),
					MathHelper.floor_double(this.posY) - 1,
					MathHelper.floor_double(this.posZ)).getMaterial().isSolid()) {
				dropper();
			}
			updateEntityActionState();
		}
	}

	private Entity closest(List list) {
		double d = 9000.0D;
		Entity entity = null;
		for (int i = 0; i < list.size(); i++) {
			Entity entity1 = (Entity) list.get(i);
			double d1 = entity1.getDistanceSq(this.posX, this.posY, this.posZ);
			if (d1 >= d)
				continue;
			d = d1;
			entity = entity1;
			entityToAttack = entity;
			setRevengeTarget((EntityLiving)entity);
		}

		return entity;
	}

	void dropper() {
		for (int i = 0; i < 20; i++) {
			double d = this.rand.nextGaussian() * 0.02D;
			double d1 = this.rand.nextGaussian() * 0.02D;
			double d2 = this.rand.nextGaussian() * 0.02D;
			this.worldObj.spawnParticle("explode",
					this.posX + this.rand.nextFloat() * this.width * 2.0F
							- this.width, this.posY + this.rand.nextFloat()
							* this.height, this.posZ + this.rand.nextFloat()
							* this.width * 2.0F - this.width, d, d1, d2);
		}

		if (!Universal.improperWorld(this.worldObj)) {
			entityDropItem(new ItemStack(Ids.itemGuard, 1, 0), 0.0F);
			setDead();
		}
	}

	@Override
	public ResourceLocation getTexture() {
		return GUARD_PNG;
	}

	@Override
	public float getEyeHeight() {
		return 0.5F;
	}

	@Override
	public boolean canEntityBeSeen(Entity par1Entity) {
		return true;
	}

}
