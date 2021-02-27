package view;

import java.awt.Dimension;
import javax.swing.JFrame;

import controller.Observer;
import view.Window;
import model.Figure;
import java.util.ArrayList;

public class BoardViewManager {
    private BoardViewManager() {
    }

    private static Window window;

    public static void initializeBoard(ArrayList<Figure> context, Observer observer) {
        window = new Window(context, observer);
        window.setPreferredSize(new Dimension(1000, 600));

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }

    public static void refreshBoard(){
        window.refresh();
    }
}
