package core;

import com.badlogic.gdx.physics.box2d.*;
import helper.ContactType;

public class GameContactListener implements ContactListener {

    private GameScreen gameScreen;

    public GameContactListener(GameScreen gameScreen){
        this.gameScreen = gameScreen;

    }
    @Override
    public void beginContact(Contact contact) {
       Fixture a = contact.getFixtureA();
       Fixture b = contact.getFixtureB();

       if (a == null || b == null) return;
       if (a.getUserData() == null || b.getUserData() == null) return;

       if (a.getUserData() == ContactType.BALL || b.getUserData() == ContactType.BALL ) {
            // Ball - Player
           if (a.getUserData() == ContactType.PlAYER || b.getUserData() == ContactType.PlAYER){
               gameScreen.getBall().reverseVelX();
               gameScreen.getBall().incSpeed();
           }

           // Ball - Wall
           if (a.getUserData() == ContactType.WALL || b.getUserData() == ContactType.WALL){
               gameScreen.getBall().reverseVelY();
           }
       }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}
