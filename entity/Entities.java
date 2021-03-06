package riseautomatons.entity;

import net.minecraftforge.common.MinecraftForge;
import riseautomatons.Ids;
import riseautomatons.Particles;
import riseautomatons.RiseAutomatons;
import riseautomatons.block.BlockSentry;
import riseautomatons.block.BlockTote;
import riseautomatons.block.BlockWorker;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Entities {

	public static void init() {
		// Automatons
		//LanguageRegistry.instance().addStringLocalization("entity.Worker.name", "en_US", "Worker");
		BlockWorker.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerModEntity(EntityWorker.class, "Worker", Ids.entityWorkerId, RiseAutomatons.instance, 64, 2, true);

		//LanguageRegistry.instance().addStringLocalization("entity.Sentry.name", "en_US", "Sentry");
		BlockSentry.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerModEntity(EntitySentry.class, "Sentry", Ids.entitySentryId, RiseAutomatons.instance, 64, 2, true);

		//LanguageRegistry.instance().addStringLocalization("entity.Tote.name", "en_US", "Tote");
		BlockTote.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerModEntity(EntityTote.class, "Tote", Ids.entityToteId, RiseAutomatons.instance, 64, 2, true);

		//LanguageRegistry.instance().addStringLocalization("entity.Golem.name", "en_US", "Golem");
		EntityGolemNormal.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerModEntity(EntityGolemNormal.class, "Golem", Ids.entityGolemId, RiseAutomatons.instance, 64, 2, true);

		//LanguageRegistry.instance().addStringLocalization("entity.GolemPure.name", "en_US", "GolemPure");
		EntityGolemNormal.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerModEntity(EntityGolemPure.class, "GolemPure", Ids.entityGolemPureId, RiseAutomatons.instance, 64, 2, true);

		//LanguageRegistry.instance().addStringLocalization("entity.Watcher.name", "en_US", "Watcher");
		EntityWatcher.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerModEntity(EntityWatcher.class, "Watcher", Ids.entityWatcherId, RiseAutomatons.instance, 64, 2, true);

		//LanguageRegistry.instance().addStringLocalization("entity.Slider.name", "en_US", "Slider");
		EntitySlider.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerModEntity(EntitySlider.class, "Slider", Ids.entitySliderId, RiseAutomatons.instance, 64, 2, true);

		//LanguageRegistry.instance().addStringLocalization("entity.Bobby.name", "en_US", "Bobby");
		EntityBobby.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerModEntity(EntityBobby.class, "Bobby", Ids.entityBobbyId, RiseAutomatons.instance, 64, 2, true);

		//LanguageRegistry.instance().addStringLocalization("entity.Helios.name", "en_US", "Helios");
		EntityHelios.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerModEntity(EntityHelios.class, "Helios", Ids.entityHeliosId, RiseAutomatons.instance, 64, 2, true);

		//LanguageRegistry.instance().addStringLocalization("entity.Factotum.name", "en_US", "Factotum");
		EntityFactotum.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerModEntity(EntityFactotum.class, "Factotum", Ids.entityFactotumId, RiseAutomatons.instance, 64, 2, true);

		//LanguageRegistry.instance().addStringLocalization("entity.Beacon.name", "en_US", "Beacon");
		EntityBeacon.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerModEntity(EntityBeacon.class, "Beacon", Ids.entityBeaconId, RiseAutomatons.instance, 64, 2, true);

		//LanguageRegistry.instance().addStringLocalization("entity.Guard.name", "en_US", "Guard");
		EntityGuard.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerModEntity(EntityGuard.class, "Guard", Ids.entityGuardId, RiseAutomatons.instance, 64, 2, true);

		//LanguageRegistry.instance().addStringLocalization("entity.Omni.name", "en_US", "Omni");
		EntityOmni.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerModEntity(EntityOmni.class, "Omni", Ids.entityOmniId, RiseAutomatons.instance, 64, 2, true);

		EntityRegistry.registerModEntity(EntityLaser.class, "Laser", Ids.entityLaserId, RiseAutomatons.instance, 64, 2, true);
		EntityRegistry.registerGlobalEntityID(EntityLaser.class, "Laser", Ids.entityLaserId);

		EntityRegistry.registerModEntity(EntityVirus.class, "Virus", Ids.entityVirusId, RiseAutomatons.instance, 64, 2, true);
		EntityRegistry.registerGlobalEntityID(EntityVirus.class, "Virus", Ids.entityVirusId);

		// Effect

		EntityWorker.init();
	}

	@SideOnly(Side.CLIENT)
	public static void initEffect() {
		MinecraftForge.EVENT_BUS.register(new Particles());

	}

	@SideOnly(Side.CLIENT)
	public static void registerTextures() {
		RenderingRegistry.registerEntityRenderingHandler(EntityWorker.class, new RenderWorker(new ModelWorker(), 0.5F));

		RenderingRegistry.registerEntityRenderingHandler(EntitySentry.class, new RenderBot(new ModelSentry(), 0.75F));

		RenderingRegistry.registerEntityRenderingHandler(EntityTote.class, new RenderTote(new ModelTote(), 0.5F));

		RenderingRegistry.registerEntityRenderingHandler(EntityGolemNormal.class, new RenderGolem(new ModelGolem(), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(EntityGolemPure.class, new RenderGolem(new ModelGolem(), 0.25F));

		RenderingRegistry.registerEntityRenderingHandler(EntityWatcher.class, new RenderWatcher(new ModelWatcher(), 0.5F));

		RenderingRegistry.registerEntityRenderingHandler(EntitySlider.class, new RenderBot(new ModelSlider(), 1F));

		RenderingRegistry.registerEntityRenderingHandler(EntityBobby.class, new RenderBot(new ModelBobby(), 0.25F));

		RenderingRegistry.registerEntityRenderingHandler(EntityHelios.class, new RenderBot(new ModelHelios(), 0.3F));

		RenderingRegistry.registerEntityRenderingHandler(EntityFactotum.class, new RenderFactotum(new ModelFactotum(), 1.5F));

		RenderingRegistry.registerEntityRenderingHandler(EntityBeacon.class, new RenderBot(new ModelBeacon(), 0.5F));

		RenderingRegistry.registerEntityRenderingHandler(EntityGuard.class, new RenderBot(new ModelGuard(), 0.3F));

		RenderingRegistry.registerEntityRenderingHandler(EntityOmni.class, new RenderBot(new ModelOmni(), 0.25F));

		RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class, new RenderLaser());

		RenderingRegistry.registerEntityRenderingHandler(EntityVirus.class, new RenderVirus());
	}

}
