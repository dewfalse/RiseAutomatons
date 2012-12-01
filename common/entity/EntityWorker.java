package riseautomatons.common.entity;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityMob;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.PathEntity;
import net.minecraft.src.World;
import riseautomatons.common.Coord;
import riseautomatons.common.Universal;

public class EntityWorker extends EntityMob implements IBot {

	public static final String GOLEM1_PNG = "/RiseAutomatons/golem1.png";
	public static final String GOLEM2_PNG = "/RiseAutomatons/golem2.png";
	public static final String GOLEM3_PNG = "/RiseAutomatons/golem3.png";
	public static final String GOLEM4_PNG = "/RiseAutomatons/golem4.png";
	public static final String GOLEM5_PNG = "/RiseAutomatons/golem5.png";
	public static final String GOLEM6_PNG = "/RiseAutomatons/golem6.png";

	enum EnumWorkMode {STAY, FOLLOW, DIG, PANIC, PICKUP};
	private EnumWorkMode mode = EnumWorkMode.STAY;
	private int inventryItemID = 0;

	public EntityWorker(World par1World) {
		super(par1World);
		setSize(0.6F, 0.8F);
	}

	private static Map<Integer, Integer> target = new LinkedHashMap<Integer, Integer>();

	public static void init() {
		target.put(Block.stone.blockID, Block.stone.blockID);
		target.put(Block.grass.blockID, Block.grass.blockID);
		target.put(Block.dirt.blockID, Block.dirt.blockID);
		target.put(Block.cobblestone.blockID, Block.stone.blockID);

		target.put(Block.sand.blockID, Block.sand.blockID);
		target.put(Block.gravel.blockID, Block.gravel.blockID);
		target.put(Block.oreGold.blockID, Block.oreGold.blockID);
		target.put(Block.oreIron.blockID, Block.oreIron.blockID);
		target.put(Block.sandStone.blockID, Block.sandStone.blockID);
		target.put(Block.obsidian.blockID, Block.obsidian.blockID);
		target.put(Block.netherBrick.blockID, Block.netherBrick.blockID);
		target.put(Item.coal.shiftedIndex, Block.oreCoal.blockID);
		target.put(Item.dyePowder.shiftedIndex, Block.oreLapis.blockID);
		target.put(Item.emerald.shiftedIndex, Block.oreEmerald.blockID);
		target.put(Item.diamond.shiftedIndex, Block.oreDiamond.blockID);
		target.put(Item.redstone.shiftedIndex, Block.oreRedstone.blockID);
	}

	@Override
	public boolean interact(EntityPlayer entityplayer) {

		ItemStack itemstack = entityplayer.inventory.getCurrentItem();
		if(itemstack == null) {
			if(getMode() == EnumWorkMode.STAY) {
				setMode(EnumWorkMode.FOLLOW);
			}
			else {
				setMode(EnumWorkMode.STAY);
			}
			return true;
		}

		if(itemstack.itemID == Item.shovelStone.shiftedIndex) {
			setMode(EnumWorkMode.PANIC);
		}
		else if(itemstack.itemID == Item.stick.shiftedIndex) {
			setMode(EnumWorkMode.PICKUP);
		}
		else if(target.containsKey(itemstack.itemID)) {
			setMode(EnumWorkMode.DIG);
			setInventoryType(target.get(itemstack.itemID));
		}
		else {
			return false;
		}

		mode = EnumWorkMode.values()[(mode.ordinal() + 1) % EnumWorkMode.values().length];


		String s  = "explode";
		Random rand = worldObj.rand;

		for (int i = 0; i < 7; i++)
		{
			double d = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;
			worldObj.spawnParticle(s, (posX + rand.nextFloat() * 1.6F - 0.8f), posY + 0.5f + (rand.nextFloat() * 0.2f), (posZ + rand.nextFloat() * 1.6F) - 0.8f, d, d1, d2);
		}

		isJumping = false;
		setPathToEntity(null);

		return true;
	}


	private void setMode(EnumWorkMode follow) {
		if(Universal.improperWorld(worldObj)) {
			return;
		}
	}

	public EnumWorkMode getMode() {
		return mode;
	}

	@Override
	public int getMaxHealth() {
		return 6;
	}

	@Override
	public String getTexture() {
		switch(mode) {
		case STAY:
			return GOLEM1_PNG;
		case FOLLOW:
			return GOLEM2_PNG;
		case DIG:
			return GOLEM3_PNG;
		case PANIC:
			return GOLEM4_PNG;
		case PICKUP:
			return GOLEM5_PNG;
		}
		return GOLEM1_PNG;
	}

	public int getInventoryDamage() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	public int getInventoryType() {
		return inventryItemID;
	}

	private void setInventoryType(int itemID) {
		inventryItemID  = itemID;

	}

	private Coord dest;
	private Coord home;
	int F = 0;
	int R = 0;
	private int dig;
	private int trigger;

	enum EnumDigState {MOVE, CHECK, DIG};
	private EnumDigState state = EnumDigState.MOVE;

	private EnumDigState getState() {
		return state;
	}

	private void setState(EnumDigState state) {
		this.state = state;
	}


	protected int getDig()
	{
		return dig;//dataWatcher.getWatchableObjectInt(19);
	}
	protected void setDig(int i)
	{
		dig = i;
		//dataWatcher.updateObject(19, Integer.valueOf(i));
	}

	public String getD()
	{
		return dataWatcher.getWatchableObjectString(20);
	}

	public void setD(String s)
	{
		dataWatcher.updateObject(20, s);
	}

	protected int getT()
	{
		return Universal.getInt(dataWatcher, 19);
	}
	protected void setT(int i)
	{
		trigger = i;
		dataWatcher.updateObject(19, Integer.valueOf(i));
	}
	void gotoSpot(int x, int y, int z, float f)
	{
		PathEntity pathentity = worldObj.getEntityPathToXYZ(this, x, y, z, 16F, true, true, false, true);
		this.getNavigator().setPath(pathentity, moveSpeed);
	}

	public Object getHome() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public void modeDig(EntityLiving entityplayer){  //digger


		if (getState() == EnumDigState.MOVE)
		{
			boolean hasHome = (getHome() != null);

			int posX = MathHelper.floor_double(entityplayer.posX);
			int posY = MathHelper.floor_double(entityplayer.posY);
			int posZ = MathHelper.floor_double(entityplayer.posZ);

			Coord nextDest = hasHome ? new Coord(home) : new Coord(posX, posY, posZ) ;
			if (hasHome)
			{
				nextDest.addCoord(
						rand.nextInt(32) - 16,
						rand.nextInt(4) - 2,
						rand.nextInt(32) - 16);
			}
			else
			{
				nextDest.addCoord(
						rand.nextInt(16) - 8,
						rand.nextInt(4) - 2,
						rand.nextInt(16) - 8);
			}
			//System.out.println(xo+","+yo+","+zo);

			int destBlockId = worldObj.getBlockId(nextDest.x, nextDest.y, nextDest.z);

			if (destBlockId == 2)
			{
				destBlockId = 3;
			}
			//System.out.println("uh: "+getInventoryType());
			if (destBlockId == getInventoryType())
			{
				//System.out.println("stage 2!");
				setState(EnumDigState.CHECK);
				gotoSpot(nextDest.x, nextDest.y, nextDest.z, 16F);
				dest.setCoord(nextDest);
			}
			else
			{
				if (hasHome)
				{
					nextDest.setCoord(home);
				}
				else
				{
					nextDest.setCoord(posX, posY, posZ);
				}
				nextDest.addCoord(
						rand.nextInt(6) - 3,
						rand.nextInt(4) - 2,
						rand.nextInt(6) - 3);

				int bbb2 = worldObj.getBlockId(nextDest.x, nextDest.y, nextDest.z);

				if (bbb2 == 2)
				{
					bbb2 = 3;
				}

				if (bbb2 == getInventoryType())
				{
					setState(EnumDigState.CHECK);
					gotoSpot(nextDest.x, nextDest.y, nextDest.z, 5F);
					dest.setCoord(nextDest);
				}
			}
		}
		else if (getState() == EnumDigState.CHECK)
		{
			if (getDistance(dest.x, dest.y, dest.z) < 2)
			{
				setState(EnumDigState.DIG);
			}
			else
			{
				if (lastResortDig())
				{
					setState(EnumDigState.DIG);
				}
				else
				{
					setState(EnumDigState.MOVE);
				}
			}
		}
		else if (getState() == EnumDigState.DIG)
		{
			int bbb = worldObj.getBlockId(dest.x, dest.y, dest.z);

			if (bbb == 2)
			{
				bbb = 3;
			}

			if (bbb != getInventoryType())
			{
				setState(EnumDigState.MOVE);
			}
			else
			{
				if (getT() != 1)
				{
					setT(1);
				}

				int dd = getDig();
				setD("" + dest.x + "," + dest.y + "," + dest.z);
				Block bb = Block.blocksList[getInventoryType()];

				if(bb==null)
					return;

				if (dd >= bb.getBlockHardness(worldObj, dest.x, dest.y, dest.z) * 30)
				{
					 worldObj.setBlockWithNotify(dest.x, dest.y, dest.z, 0);
					EntityItem entityitem = new EntityItem(worldObj, dest.x, dest.y, dest.z, new ItemStack(bb.idDropped(0, rand, 0), 1, 0));
					entityitem.delayBeforeCanPickup = 10;
					worldObj.spawnEntityInWorld(entityitem);
					setT(0);
					setDig(0);

					//TODO
					if (optimizeDig())
					{
						setState(EnumDigState.CHECK);
						gotoSpot(dest.x, dest.y, dest.z, 5F);
					}
					else
					{
						F = 0;
						R = 0;
						setState(EnumDigState.MOVE);
					}

					//setState(0);
				}
				else
				{
					setDig(dd + 1);
				}
			}
		}

	}
	private boolean optimizeDig()
	{
		int xo = MathHelper.floor_double(dest.x);
		int yo = MathHelper.floor_double(dest.y);
		int zo = MathHelper.floor_double(dest.z);
		boolean bool = even(R);
		if(Universal.distance(home.x, home.y, home.z, posX, posY, posZ)>24){
			return false;
		}


		if (F < 6 && F>=0 && derp(xo, yo, zo + (bool?1:-1))){
			if(bool){
				F++;
			}else{
				F--;
			}
			return true;
		}
		else if (derp(xo - 1, yo, zo)){
			R++;
			return true;
		}
		else if (derp(xo + 1, yo, zo)){
			R--;
			return true;
		}
		else if (derp(xo, yo - 1, zo)){
			R=3;
			F=3;
			return true;
		}
		return false;
	}

	private boolean lastResortDig(){
		int xo = MathHelper.floor_double(posX);
		int yo = MathHelper.floor_double(posY);
		int zo = MathHelper.floor_double(posZ);

		if (derp(xo, yo + 1, zo))
		{
			return true;
		}
		else if (derp(xo - 1, yo, zo))
		{
			return true;
		}
		else if (derp(xo + 1, yo, zo))
		{
			return true;
		}
		else if (derp(xo, yo, zo - 1))
		{
			return true;
		}
		else if (derp(xo, yo, zo + 1))
		{
			return true;
		}
		else if (derp(xo, yo - 1, zo))
		{
			return true;
		}

		return false;
	}
	public boolean even(int i)
	{
		return i % 2 == 0;
	}

	private boolean derp(int xo, int yo, int zo)
	{
		if (worldObj.getBlockId(xo, yo, zo) == getInventoryType())
		{
			dest.x = xo;
			dest.y = yo;
			dest.z = zo;
			return true;
		}

		return false;
	}

}
