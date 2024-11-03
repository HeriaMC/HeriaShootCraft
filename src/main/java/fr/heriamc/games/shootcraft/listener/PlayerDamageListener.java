package fr.heriamc.games.shootcraft.listener;

import fr.heriamc.api.game.GameState;
import fr.heriamc.games.api.pool.GameManager;
import fr.heriamc.games.shootcraft.ShootCraftGame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public record PlayerDamageListener(GameManager<ShootCraftGame> gameManager) implements Listener {

    @EventHandler
    public void DamageEvent(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player victim) {
            var game = gameManager.getNullableGame(victim);

            if (game == null || game.getState() == GameState.IN_GAME) return;

            var gamePlayer = game.getNullablePlayer(victim);

            if (gamePlayer == null) return;

            var playerTeam = gamePlayer.getTeam();

            if (playerTeam == null) return;

            if (event.getDamager() instanceof Player a) {
                var attacker = game.getNullablePlayer(a);

                if (attacker == null) return;

                var attackerTeam = attacker.getTeam();

                if (attackerTeam == null) return;

                if (attackerTeam.equals(playerTeam)) {
                    event.setCancelled(true);
                }
            }
        }
    }

}