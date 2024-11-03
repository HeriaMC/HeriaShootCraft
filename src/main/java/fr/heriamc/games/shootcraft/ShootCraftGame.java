package fr.heriamc.games.shootcraft;

import fr.heriamc.api.game.size.GameSizeTemplate;
import fr.heriamc.games.engine.Game;
import fr.heriamc.games.engine.team.GameTeamColor;
import fr.heriamc.games.engine.utils.task.countdown.CountdownTask;
import fr.heriamc.games.shootcraft.player.ShootCraftPlayer;
import fr.heriamc.games.shootcraft.player.ShootCraftTeam;
import fr.heriamc.games.shootcraft.setting.ShootCraftMapManager;
import fr.heriamc.games.shootcraft.setting.ShootCraftSettings;
import fr.heriamc.games.shootcraft.task.ShootCraftEndTask;
import fr.heriamc.games.shootcraft.task.ShootCraftGameCycleTask;
import fr.heriamc.games.shootcraft.waiting.ShootCraftWaitingRoom;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ShootCraftGame extends Game<ShootCraftPlayer, ShootCraftTeam, ShootCraftSettings> {

    private final ShootCraftWaitingRoom waitingRoom;

    private final CountdownTask gameCycleTask, endGameTask;

    public ShootCraftGame() {
        super("shootcraft", new ShootCraftSettings(GameSizeTemplate.SIZE_SOLO.toGameSize()));
        this.settings.setGameMapManager(new ShootCraftMapManager(this));
        this.waitingRoom = new ShootCraftWaitingRoom(this);
        this.gameCycleTask = new ShootCraftGameCycleTask(this);
        this.endGameTask = new ShootCraftEndTask(this);
    }

    @Override
    public void load() {
        settings.getGameMapManager().setup();
    }

    @Override
    public ShootCraftTeam defaultGameTeam(int size, GameTeamColor gameTeamColor) {
        return new ShootCraftTeam(size, gameTeamColor);
    }

    @Override
    public ShootCraftPlayer defaultGamePlayer(UUID uuid, boolean spectator) {
        return new ShootCraftPlayer(uuid, 0, 0, spectator);
    }

}