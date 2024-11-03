package fr.heriamc.games.shootcraft.task;

import fr.heriamc.api.game.GameState;
import fr.heriamc.games.engine.utils.task.countdown.CountdownTask;
import fr.heriamc.games.engine.utils.task.countdown.GameCountdownTask;
import fr.heriamc.games.shootcraft.ShootCraftGame;
import fr.heriamc.games.shootcraft.setting.ShootCraftSettings;
import fr.heriamc.games.shootcraft.setting.message.ShootCraftMessages;
import org.bukkit.Sound;

public class ShootCraftStartingTask extends GameCountdownTask<ShootCraftGame> {

    private final ShootCraftSettings settings;

    public ShootCraftStartingTask(ShootCraftGame game) {
        super(10, game);
        this.settings = game.getSettings();
    }

    @Override
    public void onStart() {
        game.getPlayers().values().forEach(settings.getBoardManager()::update);
        game.setState(GameState.STARTING);
    }

    @Override
    public void onNext(CountdownTask countdownTask) {
        game.broadcast(ShootCraftMessages.STARTING_MESSAGE.getMessage(secondsLeft.get()));

        game.getAlivePlayers()
                .forEach(gamePlayer -> {
                    gamePlayer.getPlayer().setLevel(secondsLeft.get());
                    gamePlayer.getPlayer().playSound(gamePlayer.getLocation(), Sound.NOTE_PLING, 20f, 20f);

                    switch (secondsLeft.get()) {
                        case 3 -> gamePlayer.sendTitle(20, 20, 20, "§c3", "");
                        case 2 -> gamePlayer.sendTitle(20, 20, 20, "§62", "");
                        case 1 -> gamePlayer.sendTitle(20, 20, 20, "§e1", "");
                    }
                });
    }

    @Override
    public void onComplete() {
        game.setState(GameState.IN_GAME);
        game.getPlayers().values().forEach(settings.getBoardManager()::update);

        // RUN GAME CYCLE TASK HERE
        game.getGameCycleTask().run();
        game.fillTeam();
        //game.getTeams().forEach(JumpScadeTeam::teleportMembers);
    }

    @Override
    public void onCancel() {
        game.setState(GameState.WAIT);
        game.broadcast(ShootCraftMessages.STARTING_CANCELLED.getMessage());
    }

}