package com.github.draylar.gamestart;

import com.github.draylar.gamestart.config.GameStartConfig;
import com.github.draylar.gamestart.mixin.LootManagerGsonAccessor;
import com.google.gson.Gson;
import me.sargunvohra.mcmods.autoconfig1.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.world.loot.LootManager;

import java.lang.reflect.Field;

public class GameStart implements ModInitializer
{
	@Override
	public void onInitialize()
	{
		Gson gson = setPrettyPrinting(((LootManagerGsonAccessor) new LootManager()).getGson());

		AutoConfig.register(GameStartConfig.class, (def, cls) -> new GsonConfigSerializer<>(def, cls, gson));
	}

	private Gson setPrettyPrinting(Gson gson)
	{
		try
		{
			Field field = gson.getClass().getDeclaredField("prettyPrinting");
			field.setAccessible(true);
			field.set(gson, true);
			return gson;
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}

		return gson;
	}
}
