package com.flappydoggy.game.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    //A subclass of a class vector that implements a "last entered, first left" mechanism
    private Stack<State> states;

    public GameStateManager() {
        //Creates an empty stack
        states = new Stack<State>();
    }

    //Puts an item at the top of the stack (game state or screen)
    public void push(State state) {
        states.push(state);
    }

    //Removes the top item by removing it from the stack. Frees resources in the pop method
    public void pop() {
        states.pop().dispose();
    }

    //Removes the top screen from the stack, pops up its resources, and places the next screen at the top of the stack
    public void set(State state) {
        states.pop().dispose();
        states.push(state);
    }

    //Updates the game by updating only the game state that is at the top of the stack
    public void update(float dt) {
        states.peek().update(dt);
    }

    //Takes a state from the top of the stack, leave it and draw it
    public void render(SpriteBatch sb) {
        states.peek().render(sb);
    }

}
