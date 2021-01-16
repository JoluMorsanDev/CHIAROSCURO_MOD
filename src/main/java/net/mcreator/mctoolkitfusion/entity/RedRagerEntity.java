
package net.mcreator.mctoolkitfusion.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.World;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.BreakBlockGoal;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.block.Blocks;

import net.mcreator.mctoolkitfusion.MctoolkitFusionModElements;

import java.util.Random;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@MctoolkitFusionModElements.ModElement.Tag
public class RedRagerEntity extends MctoolkitFusionModElements.ModElement {
	public static EntityType entity = null;
	public RedRagerEntity(MctoolkitFusionModElements instance) {
		super(instance, 46);
		FMLJavaModLoadingContext.get().getModEventBus().register(new ModelRegisterHandler());
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).immuneToFire().size(1.4f, 2.5f))
						.build("red_rager").setRegistryName("red_rager");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -6750208, -1609696, new Item.Properties().group(ItemGroup.MISC))
				.setRegistryName("red_rager_spawn_egg"));
	}

	@SubscribeEvent
	public void addFeatureToBiomes(BiomeLoadingEvent event) {
		boolean biomeCriteria = false;
		if (new ResourceLocation("crimson_forest").equals(event.getName()))
			biomeCriteria = true;
		if (!biomeCriteria)
			return;
		event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(entity, 5, 1, 2));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		DeferredWorkQueue.runLater(this::setupAttributes);
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::canMonsterSpawn);
	}
	private static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
				return new MobRenderer(renderManager, new Modelredrager(), 0.5f) {
					{
						this.addLayer(new GlowingLayer<>(this));
					}
					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("mctoolkit_fusion:textures/red_rager.png");
					}
				};
			});
		}
	}
	private void setupAttributes() {
		AttributeModifierMap.MutableAttribute ammma = MobEntity.func_233666_p_();
		ammma = ammma.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.15);
		ammma = ammma.createMutableAttribute(Attributes.MAX_HEALTH, 15);
		ammma = ammma.createMutableAttribute(Attributes.ARMOR, 5);
		ammma = ammma.createMutableAttribute(Attributes.ATTACK_DAMAGE, 6);
		ammma = ammma.createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 3);
		ammma = ammma.createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 3);
		GlobalEntityTypeAttributes.put(entity, ammma.create());
	}
	public static class CustomEntity extends MonsterEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 8;
			setNoAI(false);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, true));
			this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 1));
			this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
			this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(5, new SwimGoal(this));
			this.goalSelector.addGoal(6, new BreakBlockGoal(Blocks.CRIMSON_ROOTS.getDefaultState().getBlock(), this, 1, (int) 3));
			this.goalSelector.addGoal(7, new BreakBlockGoal(Blocks.CRIMSON_FUNGUS.getDefaultState().getBlock(), this, 1, (int) 3));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(Blocks.CRIMSON_FUNGUS, (int) (1)));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
		}

		public void livingTick() {
			super.livingTick();
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Random random = this.rand;
			Entity entity = this;
			if (true)
				for (int l = 0; l < 5; ++l) {
					double d0 = (x + random.nextFloat());
					double d1 = (y + random.nextFloat());
					double d2 = (z + random.nextFloat());
					int i1 = random.nextInt(2) * 2 - 1;
					double d3 = (random.nextFloat() - 0.5D) * 0.5D;
					double d4 = (random.nextFloat() - 0.5D) * 0.5D;
					double d5 = (random.nextFloat() - 0.5D) * 0.5D;
					world.addParticle(ParticleTypes.CRIMSON_SPORE, d0, d1, d2, d3, d4, d5);
				}
		}
	}

	@OnlyIn(Dist.CLIENT)
	private static class GlowingLayer<T extends Entity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
		public GlowingLayer(IEntityRenderer<T, M> er) {
			super(er);
		}

		public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing,
				float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
			IVertexBuilder ivertexbuilder = bufferIn
					.getBuffer(RenderType.getEyes(new ResourceLocation("mctoolkit_fusion:textures/red_rager_eye.png")));
			this.getEntityModel().render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
		}
	}

	// Made with Blockbench 3.7.4
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class Modelredrager extends EntityModel<Entity> {
		private final ModelRenderer Rotation;
		private final ModelRenderer Body;
		private final ModelRenderer Leg1;
		private final ModelRenderer Leg2;
		private final ModelRenderer right_arm;
		private final ModelRenderer left_arm;
		private final ModelRenderer shoulder_mushroom;
		private final ModelRenderer cube_r1;
		private final ModelRenderer cube_r2;
		private final ModelRenderer Head;
		private final ModelRenderer head_mushroom;
		private final ModelRenderer cube_r3;
		private final ModelRenderer cube_r4;
		private final ModelRenderer roots;
		private final ModelRenderer roots2_r1;
		public Modelredrager() {
			textureWidth = 128;
			textureHeight = 128;
			Rotation = new ModelRenderer(this);
			Rotation.setRotationPoint(0.0F, 19.4F, -0.45F);
			Body = new ModelRenderer(this);
			Body.setRotationPoint(0.0F, 0.0F, 0.0F);
			Rotation.addChild(Body);
			setRotationAngle(Body, 0.0F, 3.1416F, 0.0F);
			Body.setTextureOffset(92, 22).addBox(-6.0F, -15.4F, -3.55F, 12.0F, 5.0F, 6.0F, 0.0F, false);
			Body.setTextureOffset(80, 0).addBox(-8.0F, -26.4F, -4.55F, 16.0F, 11.0F, 8.0F, 0.0F, false);
			Leg1 = new ModelRenderer(this);
			Leg1.setRotationPoint(4.0F, -10.4F, -0.55F);
			Body.addChild(Leg1);
			Leg1.setTextureOffset(0, 109).addBox(-2.0F, 0.0F, -2.0F, 5.0F, 15.0F, 4.0F, 0.0F, false);
			Leg1.setTextureOffset(92, 103).addBox(-2.0F, 0.0F, -2.0F, 5.0F, 13.0F, 4.0F, 0.2F, true);
			Leg2 = new ModelRenderer(this);
			Leg2.setRotationPoint(-4.0F, -10.4F, -0.55F);
			Body.addChild(Leg2);
			Leg2.setTextureOffset(0, 109).addBox(-3.0F, 0.0F, -2.0F, 5.0F, 15.0F, 4.0F, 0.0F, true);
			right_arm = new ModelRenderer(this);
			right_arm.setRotationPoint(8.0F, -23.4F, -0.55F);
			Body.addChild(right_arm);
			right_arm.setTextureOffset(110, 103).addBox(0.0F, -4.0F, -2.0F, 5.0F, 21.0F, 4.0F, 0.0F, false);
			right_arm.setTextureOffset(92, 103).addBox(0.0F, -4.0F, -2.0F, 5.0F, 21.0F, 4.0F, 0.2F, false);
			left_arm = new ModelRenderer(this);
			left_arm.setRotationPoint(-8.0F, -23.4F, -0.55F);
			Body.addChild(left_arm);
			left_arm.setTextureOffset(110, 103).addBox(-5.0F, -4.0F, -2.0F, 5.0F, 21.0F, 4.0F, 0.0F, true);
			shoulder_mushroom = new ModelRenderer(this);
			shoulder_mushroom.setRotationPoint(-3.0F, -1.0F, -1.0F);
			left_arm.addChild(shoulder_mushroom);
			setRotationAngle(shoulder_mushroom, 0.0F, -0.48F, 0.0F);
			cube_r1 = new ModelRenderer(this);
			cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
			shoulder_mushroom.addChild(cube_r1);
			setRotationAngle(cube_r1, 0.0F, -0.7854F, 0.0F);
			cube_r1.setTextureOffset(0, 42).addBox(-3.7456F, -12.0F, -0.653F, 10.0F, 12.0F, 0.0F, 0.0F, false);
			cube_r2 = new ModelRenderer(this);
			cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
			shoulder_mushroom.addChild(cube_r2);
			setRotationAngle(cube_r2, 0.0F, 0.7854F, 0.0F);
			cube_r2.setTextureOffset(0, 42).addBox(-4.347F, -12.0F, 1.2544F, 10.0F, 12.0F, 0.0F, 0.0F, false);
			Head = new ModelRenderer(this);
			Head.setRotationPoint(0.0F, -26.9F, 0.825F);
			Body.addChild(Head);
			Head.setTextureOffset(0, 0).addBox(-6.0F, -8.5F, -6.375F, 12.0F, 9.0F, 10.0F, 0.0F, false);
			Head.setTextureOffset(0, 20).addBox(-5.0F, -7.5F, 3.625F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			head_mushroom = new ModelRenderer(this);
			head_mushroom.setRotationPoint(0.0F, -8.5F, -1.375F);
			Head.addChild(head_mushroom);
			cube_r3 = new ModelRenderer(this);
			cube_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
			head_mushroom.addChild(cube_r3);
			setRotationAngle(cube_r3, 0.0F, -0.7854F, 0.0F);
			cube_r3.setTextureOffset(0, 30).addBox(-5.0F, -12.0F, 0.0F, 10.0F, 12.0F, 0.0F, 0.0F, false);
			cube_r4 = new ModelRenderer(this);
			cube_r4.setRotationPoint(0.0F, 0.0F, 0.0F);
			head_mushroom.addChild(cube_r4);
			setRotationAngle(cube_r4, 0.0F, 0.7854F, 0.0F);
			cube_r4.setTextureOffset(0, 30).addBox(-5.0F, -12.0F, 0.0F, 10.0F, 12.0F, 0.0F, 0.0F, false);
			roots = new ModelRenderer(this);
			roots.setRotationPoint(0.0F, 4.6F, 0.45F);
			Body.addChild(roots);
			roots.setTextureOffset(22, 21).addBox(-6.0F, -52.0F, 0.0F, 12.0F, 12.0F, 0.0F, 0.0F, false);
			roots2_r1 = new ModelRenderer(this);
			roots2_r1.setRotationPoint(0.0F, -40.0F, 0.0F);
			roots.addChild(roots2_r1);
			setRotationAngle(roots2_r1, 0.0F, 1.5708F, 0.0F);
			roots2_r1.setTextureOffset(20, 21).addBox(-7.0F, -12.0F, 0.0F, 12.0F, 12.0F, 0.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			Rotation.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.Leg2.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.right_arm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.Leg1.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.Rotation.rotateAngleZ = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.left_arm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		}
	}
}
