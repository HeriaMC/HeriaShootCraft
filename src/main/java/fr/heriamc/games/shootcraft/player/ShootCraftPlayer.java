package fr.heriamc.games.shootcraft.player;

import fr.heriamc.games.engine.player.GamePlayer;
import fr.heriamc.games.engine.team.GameTeam;
import fr.heriamc.games.shootcraft.utils.NameTag;

import java.util.UUID;

public class ShootCraftPlayer extends GamePlayer<ShootCraftTeam> {

    public ShootCraftPlayer(UUID uuid, int kills, int deaths, boolean spectator) {
        super(uuid, kills, deaths, spectator);
    }

    @Override
    public <G extends GamePlayer<?>> void setTeam(GameTeam<G> team) {
        super.setTeam(team);
        NameTag.setNameTag(player,
                team == null ? "§7" : team.getColoredName() + " ", " ",
                team == null ? 999 : team.getColor().ordinal());
    }

    public void cleanUp() {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setHealth(player.getMaxHealth());
        player.getActivePotionEffects().clear();
        player.setLevel(0);
        player.setExp(0);
        player.setFoodLevel(20);
    }

}