
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
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.BreakBlockGoal;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.mctoolkitfusion.MctoolkitFusionModElements;

import java.util.Random;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@MctoolkitFusionModElements.ModElement.Tag
public class PsycheWarperEntity extends MctoolkitFusionModElements.ModElement {
	public static EntityType entity = null;
	public PsycheWarperEntity(MctoolkitFusionModElements instance) {
		super(instance, 130);
		FMLJavaModLoadingContext.get().getModEventBus().register(new ModelRegisterHandler());
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).immuneToFire().size(0.6f, 1.2f))
						.build("psyche_warper").setRegistryName("psyche_warper");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -15350623, -1, new Item.Properties().group(ItemGroup.MISC))
				.setRegistryName("psyche_warper_spawn_egg"));
	}

	@SubscribeEvent
	public void addFeatureToBiomes(BiomeLoadingEvent event) {
		boolean biomeCriteria = false;
		if (new ResourceLocation("warped_forest").equals(event.getName()))
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
				return new MobRenderer(renderManager, new Modelpsyche_warper(), 0.5f) {
					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("mctoolkit_fusion:textures/psyche_warper.png");
					}
				};
			});
		}
	}
	private void setupAttributes() {
		AttributeModifierMap.MutableAttribute ammma = MobEntity.func_233666_p_();
		ammma = ammma.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3);
		ammma = ammma.createMutableAttribute(Attributes.MAX_HEALTH, 18);
		ammma = ammma.createMutableAttribute(Attributes.ARMOR, 0);
		ammma = ammma.createMutableAttribute(Attributes.ATTACK_DAMAGE, 3);
		ammma = ammma.createMutableAttribute(Attributes.FLYING_SPEED, 0.3);
		GlobalEntityTypeAttributes.put(entity, ammma.create());
	}
	public static class CustomEntity extends MonsterEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 0;
			setNoAI(false);
			this.moveController = new FlyingMovementController(this, 10, true);
			this.navigator = new FlyingPathNavigator(this, this.world);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 0.8, 20) {
				@Override
				protected Vector3d getPosition() {
					Random random = CustomEntity.this.getRNG();
					double dir_x = CustomEntity.this.getPosX() + ((random.nextFloat() * 2 - 1) * 16);
					double dir_y = CustomEntity.this.getPosY() + ((random.nextFloat() * 2 - 1) * 16);
					double dir_z = CustomEntity.this.getPosZ() + ((random.nextFloat() * 2 - 1) * 16);
					return new Vector3d(dir_x, dir_y, dir_z);
				}
			});
			this.goalSelector.addGoal(3, new BreakBlockGoal(Blocks.WARPED_ROOTS.getDefaultState().getBlock(), this, 1, (int) 3));
			this.goalSelector.addGoal(4, new BreakBlockGoal(Blocks.WARPED_FUNGUS.getDefaultState().getBlock(), this, 1, (int) 3));
			this.goalSelector.addGoal(5, new BreakBlockGoal(Blocks.NETHER_SPROUTS.getDefaultState().getBlock(), this, 1, (int) 3));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(Blocks.WARPED_FUNGUS, (int) (1)));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
		}

		@Override
		public boolean onLivingFall(float l, float d) {
			return false;
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source == DamageSource.FALL)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
		}

		@Override
		public void setNoGravity(boolean ignored) {
			super.setNoGravity(true);
		}

		public void livingTick() {
			super.livingTick();
			this.setNoGravity(true);
		}
	}

	// Made with Blockbench 3.7.4
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class Modelpsyche_warper extends EntityModel<Entity> {
		private final ModelRenderer body;
		private final ModelRenderer main_body;
		private final ModelRenderer tail;
		private final ModelRenderer cube_r1;
		private final ModelRenderer head;
		private final ModelRenderer antennae;
		private final ModelRenderer cube_r4;
		private final ModelRenderer cube_r2;
		private final ModelRenderer cube_r3;
		private final ModelRenderer left_arm;
		private final ModelRenderer right_arm;
		public Modelpsyche_warper() {
			textureWidth = 64;
			textureHeight = 64;
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, 24.0F, 0.0F);
			main_body = new ModelRenderer(this);
			main_body.setRotationPoint(0.0F, -15.0F, 0.0F);
			body.addChild(main_body);
			main_body.setTextureOffset(0, 29).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, 0.0F, false);
			tail = new ModelRenderer(this);
			tail.setRotationPoint(0.0F, -3.0F, -1.0F);
			body.addChild(tail);
			cube_r1 = new ModelRenderer(this);
			cube_r1.setRotationPoint(0.0F, -2.0F, 0.0F);
			tail.addChild(cube_r1);
			setRotationAngle(cube_r1, -0.4363F, 0.0F, 0.0F);
			cube_r1.setTextureOffset(0, 16).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 3.0F, 10.0F, -0.01F, false);
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 24.0F, 0.0F);
			head.setTextureOffset(0, 0).addBox(-6.0F, -21.0F, -5.0F, 12.0F, 6.0F, 10.0F, 0.0F, false);
			antennae = new ModelRenderer(this);
			antennae.setRotationPoint(0.0F, 0.0F, 0.0F);
			head.addChild(antennae);
			antennae.setTextureOffset(0, 0).addBox(3.0F, -29.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
			antennae.setTextureOffset(0, 0).addBox(-5.0F, -29.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
			antennae.setTextureOffset(0, 51).addBox(-7.0F, -32.0F, 0.0F, 14.0F, 7.0F, 0.0F, 0.0F, false);
			cube_r4 = new ModelRenderer(this);
			cube_r4.setRotationPoint(0.0F, 0.0F, 0.0F);
			head.addChild(cube_r4);
			cube_r4.setTextureOffset(42, 16).addBox(-2.0F, -29.0F, -3.0F, 4.0F, 8.0F, 0.0F, 0.0F, false);
			cube_r2 = new ModelRenderer(this);
			cube_r2.setRotationPoint(3.0F, -21.0F, 3.0F);
			head.addChild(cube_r2);
			setRotationAngle(cube_r2, 0.0F, 0.6109F, 0.0F);
			cube_r2.setTextureOffset(42, 16).addBox(-2.0F, -8.0F, 0.0F, 4.0F, 8.0F, 0.0F, 0.0F, false);
			cube_r3 = new ModelRenderer(this);
			cube_r3.setRotationPoint(-3.0F, -21.0F, 3.0F);
			head.addChild(cube_r3);
			setRotationAngle(cube_r3, 0.0F, -0.6109F, 0.0F);
			cube_r3.setTextureOffset(42, 16).addBox(-2.0F, -8.0F, 0.0F, 4.0F, 8.0F, 0.0F, 0.0F, false);
			left_arm = new ModelRenderer(this);
			left_arm.setRotationPoint(4.0F, 12.0F, 0.0F);
			left_arm.setTextureOffset(28, 32).addBox(0.0F, -1.0F, -11.0F, 3.0F, 3.0F, 11.0F, 0.0F, false);
			left_arm.setTextureOffset(30, 0).addBox(0.0F, 2.0F, -15.0F, 3.0F, 0.0F, 4.0F, 0.0F, false);
			left_arm.setTextureOffset(30, 4).addBox(0.0F, -1.0F, -15.0F, 3.0F, 0.0F, 4.0F, 0.0F, false);
			right_arm = new ModelRenderer(this);
			right_arm.setRotationPoint(-4.0F, 12.0F, 0.0F);
			right_arm.setTextureOffset(25, 18).addBox(-3.0F, -1.0F, -11.0F, 3.0F, 3.0F, 11.0F, 0.0F, false);
			right_arm.setTextureOffset(30, 0).addBox(-3.0F, 2.0F, -15.0F, 3.0F, 0.0F, 4.0F, 0.0F, false);
			right_arm.setTextureOffset(30, 4).addBox(-3.0F, -1.0F, -15.0F, 3.0F, 0.0F, 4.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			body.render(matrixStack, buffer, packedLight, packedOverlay);
			head.render(matrixStack, buffer, packedLight, packedOverlay);
			left_arm.render(matrixStack, buffer, packedLight, packedOverlay);
			right_arm.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.right_arm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.left_arm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		}
	}
}
