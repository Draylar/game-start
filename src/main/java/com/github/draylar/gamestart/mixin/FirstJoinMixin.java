package com.github.draylar.gamestart.mixin;

import com.github.draylar.gamestart.GameStart;
import com.github.draylar.gamestart.config.GameStartConfig;
import com.raphydaphy.crochet.data.PlayerData;
import me.sargunvohra.mcmods.autoconfig1.AutoConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.world.loot.LootSupplier;
import net.minecraft.world.loot.context.LootContext;
import net.minecraft.world.loot.context.LootContextParameters;
import net.minecraft.world.loot.context.LootContextTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(PlayerManager.class)
public class FirstJoinMixin {

    @Inject(at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;sendPacket(Lnet/minecraft/network/Packet;)V",
            ordinal = 0
    ), method = "onPlayerConnect")
    private void connect(ClientConnection clientConnection_1, ServerPlayerEntity playerEntity, CallbackInfo ci) {
        if (!(PlayerData.get(playerEntity, GameStart.MODID).containsKey("hasJoined"))) {
            givePlayerStartingLoot(playerEntity);
            printWelcomeMessage(playerEntity);
            PlayerData.get(playerEntity, GameStart.MODID).putBoolean("hasJoined", true);
        }
    }

    @Unique
    private void givePlayerStartingLoot(ServerPlayerEntity playerEntity) {
        ServerWorld world = (ServerWorld) playerEntity.world;
        LootSupplier supplier = AutoConfig.getConfigHolder(GameStartConfig.class).getConfig().lootTable;

        if (supplier != null) {
            LootContext.Builder builder =
                    new LootContext.Builder(world)
                            .setRandom(world.getRandom())
                            .put(LootContextParameters.POSITION, playerEntity.getBlockPos())
                            .put(LootContextParameters.THIS_ENTITY, playerEntity);

            List<ItemStack> stacks = supplier.getDrops(builder.build(LootContextTypes.GIFT));
            stacks.forEach(playerEntity::giveItemStack);
        }
    }

    @Unique
    private void printWelcomeMessage(PlayerEntity playerEntity) {
        String[] welcomeMessage = AutoConfig.getConfigHolder(GameStartConfig.class).getConfig().welcomeMessage;

        if (welcomeMessage.length > 0) {
            for (String string : welcomeMessage) {
                string = string.replace("*playername*", playerEntity.getEntityName());
                playerEntity.addChatMessage(new LiteralText(string), false);
            }
        }
    }
}