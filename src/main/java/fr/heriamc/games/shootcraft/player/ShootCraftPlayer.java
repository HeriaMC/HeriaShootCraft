package fr.heriamc.games.shootcraft.player;

import fr.heriamc.games.engine.player.GamePlayer;

import java.util.UUID;

public class ShootCraftPlayer extends GamePlayer<ShootCraftTeam> {

    private int points;

    public ShootCraftPlayer(UUID uuid, int kills, int deaths, boolean spectator) {
        super(uuid, kills, deaths, spectator);
    }

}