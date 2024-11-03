package fr.heriamc.games.shootcraft.board;

import fr.heriamc.games.engine.board.GameBoard;
import fr.heriamc.games.shootcraft.ShootCraftGame;
import fr.heriamc.games.shootcraft.player.ShootCraftPlayer;

public class ShootCraftBoard extends GameBoard<ShootCraftGame, ShootCraftPlayer> {

   private int playerCount, kill;

    public ShootCraftBoard(ShootCraftGame game, ShootCraftPlayer gamePlayer) {
        super(game, gamePlayer);
    }

    @Override
    public void reloadData() {
        this.playerCount = game.getSize();
        this.kill = gamePlayer.getKills();
    }

    @Override
    public void setLines(String ip) {
        objectiveSign.clearScores();
        objectiveSign.setDisplayName("§e§l» §6§lShootCraft §e§l«");

        switch (game.getState()) {
            case WAIT -> {
                objectiveSign.setLine(0, "§1");
                objectiveSign.setLine(1, "§8■ §7En attente...");
                objectiveSign.setLine(2, "§2");
                objectiveSign.setLine(3,  "§8■ §7Connectés : §e" + playerCount + "§7/§e" + game.getGameSize().getMaxPlayer());
                objectiveSign.setLine(4, "§3");
                objectiveSign.setLine(6, ip);
            }

            case STARTING -> {
                objectiveSign.setLine(0, "§1");
                objectiveSign.setLine(1, "§8■ §7Début dans: §6" + 0 + "s");
                objectiveSign.setLine(2, "§2");
                objectiveSign.setLine(3,  "§8■ §7Connectés : §e" + playerCount + "§7/§e" + game.getGameSize().getMaxPlayer());
                objectiveSign.setLine(4, "§3");
                objectiveSign.setLine(5, ip);
            }

            case IN_GAME, END -> {
                objectiveSign.clearScores();
                objectiveSign.setLine(0, "§1");
                objectiveSign.setLine(1, "§8■ §7Tué(s) : §a" + kill);
                objectiveSign.setLine(2, "§8■ §7Fin : §b" + 0); // TIME HERE
                objectiveSign.setLine(3, "§2");
                objectiveSign.setLine(4, "§8■ §7Classement:");
                objectiveSign.setLine(5, "  §a① §f" + 0); // TOP KILLERS NAME HERE
                objectiveSign.setLine(6, "  §e② §f" + 0); // TOP KILLERS NAME HERE
                objectiveSign.setLine(7, "  §c③ §f" + 0); // TOP KILLERS NAME HERE
                objectiveSign.setLine(8, "§3");
                objectiveSign.setLine(9, ip);
            }
        }

        objectiveSign.updateLines();
    }

    /*

    OLD IF NEEDED

        public void setLines(String ip) {
        String temps = EndGameTask.getTempsFormate();
        PlayerInfo data = PlayerInfo.getPlayerData(player);
        objectiveSign.setDisplayName("§f» §6§lShootCraft §f«");

        if (GameState.isStep(GameState.WAITING)) {
            objectiveSign.setLine(0, "§1");
            objectiveSign.setLine(1, "§8■ §fJeu: §7Shootcraft (Bêta)");
            objectiveSign.setLine(2, "§8■ §fDébut dans: §3" + (Bukkit.getOnlinePlayers().size() < 2 ? "1 joueur" : Shootcraft.getInstance().getWaitingTask().getRemainingSeconds()));
            objectiveSign.setLine(3, "§2");
            objectiveSign.setLine(4, "§8■ §fJoueurs: §a" + Bukkit.getOnlinePlayers().size());
            objectiveSign.setLine(5, "§8");
            objectiveSign.setLine(6, ip);
        }

        if (GameState.isStep(GameState.INGAME)) {
            objectiveSign.clearScores();
            objectiveSign.setLine(0, "§1");
            objectiveSign.setLine(1, "§8■ §fTué(s) : §a" + data.getKill());
            objectiveSign.setLine(2, "§8■ §fFin : §b" + temps);
            objectiveSign.setLine(3, "§2");
            objectiveSign.setLine(4, "§8■ §fClassement:");
            objectiveSign.setLine(5, "  §a① §f" + Shootcraft.getInstance().getTopKill(0));
            objectiveSign.setLine(6, "  §e② §f" + Shootcraft.getInstance().getTopKill(1));
            objectiveSign.setLine(7, "  §c③ §f" + Shootcraft.getInstance().getTopKill(2));
            objectiveSign.setLine(8, "§3");
            objectiveSign.setLine(9, ip);
        }

        objectiveSign.updateLines();
    }
     */
}