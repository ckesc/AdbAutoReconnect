package ru.ckesc.adbautoreconnect;

import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

public class ReConnecter {
    public static final long PERIOD = 1000l;
    private Timer timer;
    private StatusUpdater statusUpdater;

    public ReConnecter(StatusUpdater statusUpdater) {
        this.statusUpdater = statusUpdater;
    }

    public void start() {
        if (timer != null) {
            stop();
        }

        timer = new Timer("ReConnecter");
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                onTimer();
            }
        };
        timer.schedule(timerTask, 0, PERIOD);
    }

    public void stop() {
        timer.cancel();
        timer.purge();
        timer = null;
    }

    private void onTimer() {
        Platform.runLater(() -> statusUpdater.updateStatus("I am lazy..."));
    }

}
