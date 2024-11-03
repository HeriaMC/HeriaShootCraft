package fr.heriamc.games.shootcraft.listener;

import fr.heriamc.bukkit.chat.event.HeriaChatEvent;
import fr.heriamc.games.api.pool.GameManager;
import fr.heriamc.games.shootcraft.ShootCraftGame;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public record PlayerChatListener(GameManager<ShootCraftGame> gameManager) implements Listener {

    @EventHandler
    public void onChat(HeriaChatEvent event) {
        var player = event.getPlayer();
        var game = gameManager.getNullableGame(player);

        event.setCancelled(true);

        if (game == null) return;

        var gamePlayer = game.getNullablePlayer(player);

        if (gamePlayer == null) return;

        var team = gamePlayer.getTeam();
        var space = new TextComponent(" ");
        var finalText = new TextComponent((team == null ? "§7" : team.getColoredName() + " ")
                + event.getName()
                + " §8» §f" + event.getFormattedMessage());

        game.getPlayers().values().forEach(otherPlayer -> otherPlayer.getPlayer().spigot().sendMessage(event.getReportComponent(), space, finalText));
    }

//    @EventHandler
//    public void onChat(AsyncPlayerChatEvent e){
//        Player player = e.getPlayer();
//        final PlayerInfo data = PlayerInfo.getPlayerData(player);
//        e.setCancelled(true);
//        UUID chatID = UUID.randomUUID();
//
//        HeriaPlayer heriaPlayer = HeriaAPI.get().getPlayerManager().get(player.getUniqueId());
//
//        TextComponent reportSymbol = new TextComponent(TextComponent.fromLegacyText("⚠"));
//        reportSymbol.setColor(ChatColor.RED);
//        reportSymbol.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/reportchat " + chatID));
//        reportSymbol.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
//                new ComponentBuilder("Cliquez pour signaler " + player.getName()).color(ChatColor.RED).create()));
//
//
//        if (data.hasTeam()){
//            TextComponent message = new TextComponent(TextComponent.fromLegacyText(heriaPlayer.getRank().getPrefix() + data.getName() + " §8» §f" + e.getMessage()));
//
//            for (Player p : Bukkit.getOnlinePlayers()) {
//                p.spigot().sendMessage(reportSymbol, message);
//            }
//        } else {
//            TextComponent message = new TextComponent(TextComponent.fromLegacyText(heriaPlayer.getRank().getPrefix() + data.getName() + " §8» §f" + e.getMessage()));
//
//            for (Player p : Bukkit.getOnlinePlayers()) {
//                p.spigot().sendMessage(reportSymbol, message);
//            }
//        }
//    }
}
