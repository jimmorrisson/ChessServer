package view;

import model.Position;

import java.awt.*;
import javax.swing.*;

import controller.Observer;
import model.Figure;

import java.util.ArrayList;

public class Window extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private ArrayList<Figure> context;
    private ArrayList<Button> buttons = new ArrayList<>();

    public Window(ArrayList<Figure> context, Observer observer) {
        super("Chess");
        this.context = context;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new GridLayout(0, 8));

        for (int col = 7; col >= 0; col--) {
            for (int row = 0; row < 8; row++) {
                Position position = new Position(row, col);
                Color color = Color.WHITE;
                Figure figure = null; //context.contains(position);
                for (Figure f : context) {
                    if (f.getPosition().equals(position)) {
                        figure = f;
                        break;
                    }
                }
                if (((row + col) % 2) == 0) {
                    color = Color.BLACK;
                } else {
                    color = Color.WHITE;
                    // this.add(new Button(new Position(row, col), Color.WHITE));
                }
                if (figure != null) {
                    Button button = new Button(figure.getIcon(), position, color);
                    button.addObserver(observer);
                    buttons.add(button);
                    this.add(button);
                } else {
                    Button button = new Button(null, position, color);
                    button.addObserver(observer);
                    buttons.add(button);
                    this.add(button);
                }
            }
        }
    }

    public void refresh() {
        for (Button button : buttons) {
            Position buttonPosition = button.getPosition();
            Figure figure = null;
            for (Figure f : context) {
                if (f.getPosition().equals(buttonPosition)) {
                    figure = f;
                    break;
                }
            }
            if (figure != null) {
                button.setText(figure.getIcon());
            } else {
                button.setText("");
            }
        }
    }
}