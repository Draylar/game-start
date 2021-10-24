package draylar.gamestart.mixin;

import draylar.gamestart.impl.Joiner;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements Joiner {

    @Unique private boolean gs_hasPlayerJoined = false;

    @Override
    public boolean gs_hasJoined() {
        return gs_hasPlayerJoined;
    }

    @Override
    public void gs_setJoined(boolean joined) {
        gs_hasPlayerJoined = joined;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    private void writeJoinData(NbtCompound nbt, CallbackInfo ci) {
        nbt.putBoolean("HasJoined", gs_hasPlayerJoined);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    private void readJoinData(NbtCompound nbt, CallbackInfo ci) {
        gs_hasPlayerJoined = nbt.getBoolean("HasJoined");
    }
}
