package draylar.gamestart;

import draylar.gamestart.config.GameStartConfig;
import draylar.gamestart.impl.EventHandlers;
import draylar.gamestart.impl.Joiner;
import draylar.omegaconfig.OmegaConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.player.PlayerEntity;

public class GameStart implements ModInitializer {

    public static final GameStartConfig CONFIG = OmegaConfig.register(GameStartConfig.class);

    @Override
    public void onInitialize() {
        EventHandlers.registerHandlers();
    }

    public static boolean isPlayerNew(PlayerEntity player) {
        return !((Joiner) player).gs_hasJoined();
    }

    public static void setJoinStatus(PlayerEntity player, boolean joined) {
        ((Joiner) player).gs_setJoined(joined);
    }
}