package draylar.gamestart;

import draylar.gamestart.cca.HasJoinedComponent;
import draylar.gamestart.config.GameStartConfig;
import draylar.gamestart.impl.EventHandlers;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.event.EntityComponentCallback;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameStart implements ModInitializer {

	public static final String MODID = "gamestart";
	public static final GameStartConfig CONFIG = AutoConfig.register(GameStartConfig.class, JanksonConfigSerializer::new).getConfig();
	public static final ComponentType<HasJoinedComponent> HAS_JOINED = ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier(MODID, "has_joined"), HasJoinedComponent.class)
			.attach(EntityComponentCallback.event(PlayerEntity.class), HasJoinedComponent::new);

	@Override
	public void onInitialize() {
		EventHandlers.registerHandlers();
	}
}