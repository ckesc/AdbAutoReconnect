package ru.ckesc.adbautoreconnect;

import javafx.application.Platform;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class ReConnecter {
    public static final long PERIOD = 1000l;
    private Timer timer;
    private StatusUpdater statusUpdater;
    private String ip;

    public ReConnecter(StatusUpdater statusUpdater) {
        this.statusUpdater = statusUpdater;
    }

    public void start(String ip) {
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
        this.ip = ip;
        timer.schedule(timerTask, 0, PERIOD);
    }

    public void stop() {
        timer.cancel();
        timer.purge();
        timer = null;
    }

    private void onTimer() {
        StringWriter writerOutput = new StringWriter();
        StringWriter writerErr = new StringWriter();

        ProcessBuilder processBuilder = new ProcessBuilder("adb", "connect", ip);
        try {
            Process process = processBuilder.start();
            org.apache.commons.io.IOUtils.copy(process.getInputStream(), writerOutput);
            org.apache.commons.io.IOUtils.copy(process.getErrorStream(), writerErr);

        } catch (IOException e) {
            e.printStackTrace();
        }

        String errStr = writerErr.toString();
        final String status;
        if (!errStr.isEmpty()) {
            status = writerOutput.toString() + "\n" + errStr + "\n" + getDevices();
        } else {
            status = writerOutput.toString() + "\n" + getDevices();
        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                statusUpdater.updateStatus(status);
            }
        });
    }


    private String getDevices() {
        StringWriter writerOutput = new StringWriter();

        ProcessBuilder processBuilder = new ProcessBuilder("adb", "devices");
        try {
            Process process = processBuilder.start();
            org.apache.commons.io.IOUtils.copy(process.getInputStream(), writerOutput);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return writerOutput.toString();
    }
}
