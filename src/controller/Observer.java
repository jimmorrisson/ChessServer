package controller;

import model.Position;

public interface Observer {
    public void update(Position position);
}