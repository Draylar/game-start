package draylar.gamestart.cca;

import nerdhub.cardinal.components.api.util.sync.EntitySyncedComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;

public class HasJoinedComponent implements EntitySyncedComponent {

    private final PlayerEntity playerEntity;
    private boolean hasJoined = false;

    public HasJoinedComponent(PlayerEntity playerEntity) {
        this.playerEntity = playerEntity;
    }

    public boolean hasJoined() {
        return hasJoined;
    }

    public void set(boolean b) {
        hasJoined = b;
    }

    @Override
    public Entity getEntity() {
        return playerEntity;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        hasJoined = tag.getBoolean("HasJoined");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.putBoolean("HasJoined", hasJoined);
        return tag;
    }
}
