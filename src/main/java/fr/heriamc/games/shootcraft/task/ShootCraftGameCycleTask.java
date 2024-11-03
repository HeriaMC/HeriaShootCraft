package fr.heriamc.games.shootcraft.task;

import fr.heriamc.games.engine.utils.task.countdown.CountdownTask;
import fr.heriamc.games.engine.utils.task.countdown.GameCountdownTask;
import fr.heriamc.games.shootcraft.ShootCraftGame;

public class ShootCraftGameCycleTask extends GameCountdownTask<ShootCraftGame> {

    public ShootCraftGameCycleTask(ShootCraftGame game) {
        super(300, game);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onNext(CountdownTask countdownTask) {

    }

    @Override
    public void onComplete() {
        // SECURITY CHECK
        if (!game.getEndGameTask().isStarted())
            game.getEndGameTask().run();
    }

    @Override
    public void onCancel() {
        // CANCEL
    }

}