package by.epamtc.pashun.runner;

import by.epamtc.pashun.matrixstate.MatrixState;
import by.epamtc.pashun.threads.ThreadsManager;
import by.epamtc.pashun.outputinput.PropertyLoader;
import by.epamtc.pashun.outputinput.Writer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
    private static final String keyN = "key.n";
    private static final String keyY = "key.y";

    public static void main(String[] args) {
        Writer writer = new Writer();
        ReentrantLock lock = new ReentrantLock();
        int n = Integer.parseInt(PropertyLoader.getProperty(keyN));
        int y = Integer.parseInt(PropertyLoader.getProperty(keyY));
        MatrixState matrixState = new MatrixState(n);
        matrixState.setStateDefault();
        ThreadsManager tManager = new ThreadsManager(lock, writer, matrixState);
        try {
            tManager.runThreads(y, n);
        } catch (InterruptedException | IOException e) {
            Logger logger = LogManager.getLogger(Runner.class);
            logger.error("exception while running threads");
            System.exit(0);
        }
    }
}