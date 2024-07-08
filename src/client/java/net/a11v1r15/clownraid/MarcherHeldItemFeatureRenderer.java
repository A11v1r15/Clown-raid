package net.a11v1r15.clownraid;

import net.a11v1r15.clownraid.util.RegistryHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;

    @Environment(EnvType.CLIENT)
    public class MarcherHeldItemFeatureRenderer<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
        private final HeldItemRenderer heldItemRenderer;

        public MarcherHeldItemFeatureRenderer(FeatureRendererContext<T, M> context, HeldItemRenderer heldItemRenderer) {
            super(context);
            this.heldItemRenderer = heldItemRenderer;
        }

        public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k, float l) {
            matrixStack.push();
            ItemStack itemStack = livingEntity.getEquippedStack(EquipmentSlot.MAINHAND);
            float deg = 90.0F;
            float wy = 0.3F;
            float zed = -0.5F;
            if (itemStack.isOf(RegistryHelper.getItem("wowozela:wowozela"))){
                deg = 270.0F;
            } else if (
                    itemStack.isOf(RegistryHelper.getItem("mib:acoustic_guitar")) ||
                    itemStack.isOf(RegistryHelper.getItem("mib:harpsichord")) ||
                    itemStack.isOf(RegistryHelper.getItem("mib:keyboard")) ||
                    itemStack.isOf(RegistryHelper.getItem("mib:violin")) ||
                    itemStack.isOf(RegistryHelper.getItem("powerchord:pan_flute")) ||
                    itemStack.isOf(RegistryHelper.getItem("powerchord:harmonica")) ||
                    itemStack.isOf(RegistryHelper.getItem("powerchord:wawa"))){
                deg = 180.0F;
                zed = -0.4F;
                if (itemStack.isOf(RegistryHelper.getItem("mib:acoustic_guitar")))
                    wy = 0.7F;
                if (itemStack.isOf(RegistryHelper.getItem("mib:harpsichord")) ||
                    itemStack.isOf(RegistryHelper.getItem("mib:violin")) ||
                    itemStack.isOf(RegistryHelper.getItem("powerchord:wawa")))
                    wy = 0.5F;
                if (itemStack.isOf(RegistryHelper.getItem("powerchord:pan_flute")) ||
                        itemStack.isOf(RegistryHelper.getItem("powerchord:harmonica")))
                    wy = 0.2F;
            } else if (itemStack.isOf(RegistryHelper.getItem("mib:flute"))){
                deg = 180.0F;
                wy = 0.1F;
                zed = -0.4F;
            }
            matrixStack.translate(0.0F, wy, zed);
            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180.0F));
            matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(deg));
            this.heldItemRenderer.renderItem(livingEntity, itemStack, ModelTransformationMode.GROUND, false, matrixStack, vertexConsumerProvider, i);
            matrixStack.pop();
        }
    }
