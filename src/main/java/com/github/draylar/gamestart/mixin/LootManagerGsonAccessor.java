package com.github.draylar.gamestart.mixin;

import com.google.gson.Gson;
import net.minecraft.world.loot.LootManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LootManager.class)
public interface LootManagerGsonAccessor
{
    @Accessor("gson")
    Gson getGson();
}
