package riseautomatons.common.entity;

import riseautomatons.common.entity.EntityWorker.EnumWorkMode;
import net.minecraft.src.EntityAIBase;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.MathHelper;
import net.minecraft.src.PathNavigate;

public class EntityAIWorkerFollow extends EntityAIBase {

	private EntityWorker bot;
	private PathNavigate pathFinder;
	private float moveSpeed;
	private float distanceMin;
	private float distanceMax;
	private int interval;

	public EntityAIWorkerFollow(EntityWorker entityWorker, float moveSpeed,
			float min, float max) {
		bot = entityWorker;
		pathFinder = entityWorker.getNavigator();
		this.moveSpeed = moveSpeed;
		distanceMin = min;
		distanceMax = max;
        setMutexBits(3);
	}

	@Override
	public boolean shouldExecute() {
		if(bot.getMode() != EnumWorkMode.FOLLOW) {
			return false;
		}

		EntityPlayer theOwner = bot.reallyGetBotOwner();
		if(theOwner == null) {
			return false;
		}

		if(bot.getDistanceSqToEntity(theOwner) < distanceMin * distanceMin) {
			return false;
		}

		return true;
	}

	@Override
	public void resetTask() {
		pathFinder.clearPathEntity();
	}

	@Override
	public void updateTask() {
		EntityPlayer theOwner = bot.reallyGetBotOwner();
		if(theOwner == null) {
			return;
		}

		bot.getLookHelper().setLookPositionWithEntity(theOwner, 10F, bot.getVerticalFaceSpeed());

		if( --interval > 0) {
			return;
		}

		interval = 10;

		if(pathFinder.tryMoveToEntityLiving(theOwner, moveSpeed)) {
			return;
		}

		if(bot.getDistanceSqToEntity(theOwner) < 144D) {
			return;
		}

		int i = MathHelper.floor_double(theOwner.posX) - 2;
		int j = MathHelper.floor_double(theOwner.posZ) - 2;
		int k = MathHelper.floor_double(theOwner.boundingBox.minY);

		for (int l = 0; l <= 4; l++) {
			for (int i1 = 0; i1 <= 4; i1++) {
				if ((l < 1 || i1 < 1 || l > 3 || i1 > 3)
						&& bot.worldObj.isBlockNormalCube(i + l, k - 1, j + i1)
						&& !bot.worldObj.isBlockNormalCube(i + l, k, j + i1)
						&& !bot.worldObj.isBlockNormalCube(i + l, k + 1, j + i1)) {
					bot.setLocationAndAngles((float) (i + l) + 0.5F, k,
							(float) (j + i1) + 0.5F, bot.rotationYaw,
							bot.rotationPitch);
					pathFinder.clearPathEntity();
					return;
				}
			}
		}	}

	@Override
	public boolean continueExecuting() {
		EntityPlayer theOwner = bot.reallyGetBotOwner();
		if(theOwner == null) {
			return false;
		}

		if(bot.dimension != theOwner.dimension) {
			return false;
		}

		return !pathFinder.noPath() && (bot.getDistanceSqToEntity(theOwner) > distanceMax * distanceMax);
	}

	@Override
	public void startExecuting() {
		interval = 0;
	}

}
