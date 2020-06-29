package draylar.gamestart.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

@Config(name = "gamestart")
public class GameStartConfig implements ConfigData {
    @Comment(value = "The message to send a player when they first join the server.")
    public String[] firstJoinPersonalMessage = new String[] { "" };

    @Comment(value = "The message to send to all players when a player first joins the server.")
    public String[] firstJoinBroadcastMessage = new String[] { "" };

    @Comment(value = "The message to send a player when they re-join the server (not a new player).")
    public String[] returningJoinPersonalMessage =new String[] { "" };

    @Comment(value = "The message to send to all players when a player re-joins the server (not a new player).")
    public String[] returningJoinBroadcastMessage = new String[] { "" };

    @Comment(value = "Whether the first join broadcast should also be sent to the player who joined.")
    public boolean sendFirstJoinBroadcastToTargetPlayer = false;

    @Comment(value = "Whether the returning join broadcast should also be sent to the player who joined.")
    public boolean sendReturningJoinBroadcastToTargetPlayer = false;
}