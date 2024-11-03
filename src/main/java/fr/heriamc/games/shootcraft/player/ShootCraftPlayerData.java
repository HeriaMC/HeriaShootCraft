package fr.heriamc.games.shootcraft.player;

import fr.heriamc.api.data.SerializableData;

import java.util.UUID;

public class ShootCraftPlayerData implements SerializableData<UUID> {

    private UUID uuid;

    private int kills, deaths, playedGames, win, lose;

    @Override
    public UUID getIdentifier() {
        return uuid;
    }

    @Override
    public void setIdentifier(UUID uuid) {
        this.uuid = uuid;
    }

}