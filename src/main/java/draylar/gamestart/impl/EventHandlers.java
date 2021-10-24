package draylar.gamestart.impl;

import draylar.gamestart.GameStart;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Objects;

public class EventHandlers {

    public static void registerHandlers() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayerEntity player = handler.getPlayer();
            boolean isPlayerNew = GameStart.isPlayerNew(player);

            // When a player joins, check if they have the relevant NBT tag attached to their player.
            // If the player does not have this tag (regardless of whether it is true or false),
            //    give them a warm welcome with broadcast messages or loot defined in the config file.
            if(isPlayerNew) {
                givePlayerStartingLoot(player);
                sendMessageTo(player, GameStart.CONFIG.firstJoinPersonalMessage, player.getEntityName());
                sendGlobalJoinMessage(player, GameStart.CONFIG.firstJoinBroadcastMessage, GameStart.CONFIG.sendFirstJoinBroadcastToTargetPlayer);
                GameStart.setJoinStatus(player, true);
            } else {
                sendMessageTo(player, GameStart.CONFIG.returningJoinPersonalMessage, player.getEntityName());
                sendGlobalJoinMessage(player, GameStart.CONFIG.returningJoinBroadcastMessage, GameStart.CONFIG.sendReturningJoinBroadcastToTargetPlayer);
            }
        });
    }

    private static void sendGlobalJoinMessage(ServerPlayerEntity newPlayer, String[] message, boolean sendToNewPlayer) {
        // player list does not have new player at this point
        newPlayer.server.getPlayerManager().getPlayerList().forEach(player -> {
            sendMessageTo(player, message, newPlayer.getEntityName());
        });

        // send global join message to player if option is enabled
        if(sendToNewPlayer) {
            sendMessageTo(newPlayer, message, newPlayer.getEntityName());
        }
    }

    private static void sendMessageTo(PlayerEntity newPlayer, String[] message, String playerNameReplacement) {
        for (String string : message) {
            if(!string.isEmpty()) {
                string = string.replace("{player}", playerNameReplacement);
                newPlayer.sendMessage(new LiteralText(string), false);
            }
        }
    }

    private static void givePlayerStartingLoot(ServerPlayerEntity player) {
        ServerWorld world = (ServerWorld) player.world;
        LootTable supplier = Objects.requireNonNull(world.getServer()).getLootManager().getTable(new Identifier("gamestart", "first_join"));

        LootContext.Builder builder =
                new LootContext.Builder(world)
                        .random(world.random)
                        .parameter(LootContextParameters.ORIGIN, player.getPos());

        // roll & give loot to player
        List<ItemStack> stacks = supplier.generateLoot(builder.build(LootContextTypes.CHEST));
        stacks.forEach(player::giveItemStack);
    }
}
