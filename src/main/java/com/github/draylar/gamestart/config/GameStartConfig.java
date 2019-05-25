package com.github.draylar.gamestart.config;

import me.sargunvohra.mcmods.autoconfig1.ConfigData;
import me.sargunvohra.mcmods.autoconfig1.annotation.Config;
import net.minecraft.world.loot.LootSupplier;

@Config(name = "gamestart")
public class GameStartConfig implements ConfigData
{
    public LootSupplier lootTable = LootSupplier.EMPTY;
    public String[] welcomeMessage = new String[0];
}
