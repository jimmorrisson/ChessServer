package view;

import model.Position;

import javax.swing.JButton;

import controller.Observable;
import controller.Observer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Button extends JButton implements ActionListener, Observable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private ArrayList<Observer> objectsToNotify = new ArrayList<Observer>();
    private Position position;

    public Button(String icon, Position position, Color color) {
        // super("(" + String.valueOf(position.getX()) + "," +
        // String.valueOf(position.getY()) + ")");
        super(icon);
        this.position = position;
        setBackground(color);
        setOpaque(true);
        setFont(new Font("Arial", Font.PLAIN, 40));
        addActionListener(this);
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        notifyObserver(position);
        BoardViewManager.refreshBoard();
    }

    @Override
    public void addObserver(Observer o) {
        objectsToNotify.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        objectsToNotify.remove(o);
    }

    @Override
    public void notifyObserver(Position position) {
        for (Observer o : objectsToNotify) {
            o.update(position);
        }
    }
}