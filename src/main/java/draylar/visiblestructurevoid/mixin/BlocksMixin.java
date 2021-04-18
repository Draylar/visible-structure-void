package draylar.visiblestructurevoid.mixin;

import draylar.visiblestructurevoid.block.PublicStructureVoidBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(Blocks.class)
public class BlocksMixin {

    @Inject(
            method = "register",
            at = @At("HEAD"), cancellable = true)
    private static void redirectRegister(String id, Block block, CallbackInfoReturnable<Block> cir) {
        if(block instanceof StructureVoidBlock) {
            PublicStructureVoidBlock reg = Registry.register(Registry.BLOCK, id, new PublicStructureVoidBlock(AbstractBlock.Settings.of(Material.STRUCTURE_VOID).noCollision().nonOpaque().dropsNothing()));
            cir.setReturnValue(reg);
        }
    }
}
