package controller;

import model.Position;

public interface Observable {
    public void addObserver(Observer o);

    public void removeObserver(Observer o);

    public void notifyObserver(Position position);
}