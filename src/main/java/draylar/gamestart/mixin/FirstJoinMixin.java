package draylar.gamestart.mixin;

import draylar.gamestart.GameStart;
import draylar.gamestart.api.event.PlayerJoinCallback;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
public class FirstJoinMixin {

    @Inject(at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;sendPacket(Lnet/minecraft/network/Packet;)V",
            ordinal = 0
    ), method = "onPlayerConnect")
    private void connect(ClientConnection connection, ServerPlayerEntity player, CallbackInfo ci) {
        boolean isPlayerNew = !GameStart.HAS_JOINED.get(player).hasJoined();
        PlayerJoinCallback.EVENT.invoker().onPlayerJoin(player, isPlayerNew);
    }
}