package objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import core.Boot;
import core.GameScreen;
import helper.BodyHelper;
import helper.Const;
import helper.ContactType;

public class Ball {

    private Body body;
    private float x,y, speed, velX, velY;
    private int width, height;
    private GameScreen gameScreen;
    private Texture texture;

    public Ball(GameScreen gameScreen){
        this.x = Boot.INSTANCE.getScreenWidth();
        this.y = Boot.INSTANCE.getScreenHeight();
        this.speed = 5;
        this.velX = getRandomDirection();
        this.velY = getRandomDirection();

        this.texture = new Texture("Pong1.png");
        this.gameScreen = gameScreen;
        this.width = 32;
        this.height = 32;

        World world = gameScreen.getWorld();
        this.body = BodyHelper.createBody(x, y, width, height, false, 0, world, ContactType.BALL);
    }

    private float getRandomDirection(){
        return(Math.random() <0.5) ? 1 : -1;
    }

    public void update(){
        x = body.getPosition().x * Const.PPM - (width / 2);
        y = body.getPosition().y * Const.PPM - (height / 2);

        this.body.setLinearVelocity(velX * speed, velY * speed);

        // score

        if (x < 0) {
            gameScreen.getPlayerAI().score();
            reset();
        }

        if (x > Boot.INSTANCE.getScreenWidth()) {
            gameScreen.getPlayer().score();
            reset();
        }
    }


    public void reset() {
        this.velX = this.getRandomDirection();
        this.velY = this.getRandomDirection();
        this.speed = 5;
        this.body.setTransform(Boot.INSTANCE.getScreenWidth() / 2 / Const.PPM,Boot.INSTANCE.getScreenHeight() / 2 / Const.PPM,0);
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, x, y, width, height);
    }

    public void reverseVelX() {
        this.velX *= -1;
    }

    public void reverseVelY() {
        this.velY *= -1;
    }

    public void incSpeed(){
        this.speed *= 1.1f;
    }


    public float getY() {
        return y;
    }
}
