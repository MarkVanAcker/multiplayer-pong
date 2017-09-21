package com.mygdx.desktop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import client.ClientGame;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import entityG.EntityG;

public class Pong extends ApplicationAdapter implements Input {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private ClientGame cg;

    @Override
    public void create() {

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        try {
            cg = new ClientGame("localhost", this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void render() {
        // clear the screen with a dark blue color. The
        // arguments to glClearColor are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell the camera to update its matrices.
        camera.update();

        HashMap<Long, EntityG> sprites = cg.getEntities();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        for(EntityG entity : sprites.values()){
            batch.draw(entity.getTexture(),entity.getX(),entity.getY(),entity.getWidth(),entity.getHeight());
        }
        batch.end();

        /*
        // begin a new batch and draw the bucket and
        // all drops
        batch.begin();
        batch.draw(bucketImage, bucket.x, bucket.y);
        for(Rectangle raindrop: raindrops) {
            batch.draw(dropImage, raindrop.x, raindrop.y);
        }
        batch.end();

        // process user input
        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            bucket.x = touchPos.x - 64 / 2;
        }
        if(Gdx.input.isKeyPressed(Keys.LEFT)) bucket.x -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Keys.RIGHT)) bucket.x += 200 * Gdx.graphics.getDeltaTime();

        // make sure the bucket stays within the screen bounds
        if(bucket.x < 0) bucket.x = 0;
        if(bucket.x > 800 - 64) bucket.x = 800 - 64;

        // check if we need to create a new raindrop
        if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();

        // move the raindrops, remove any that are beneath the bottom edge of
        // the screen or that hit the bucket. In the later case we play back
        // a sound effect as well.
        Iterator<Rectangle> iter = raindrops.iterator();
        while(iter.hasNext()) {
            Rectangle raindrop = iter.next();
            raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
            if(raindrop.y + 64 < 0) iter.remove();
            if(raindrop.overlaps(bucket)) {
                dropSound.play();
                iter.remove();
            }
        }
        */
    }

    @Override
    public void dispose() {
        // dispose of all the native resources
        batch.dispose();
    }

    @Override
    public float getAccelerometerX() {
        return 0;
    }

    @Override
    public float getAccelerometerY() {
        return 0;
    }

    @Override
    public float getAccelerometerZ() {
        return 0;
    }

    @Override
    public float getGyroscopeX() {
        return 0;
    }

    @Override
    public float getGyroscopeY() {
        return 0;
    }

    @Override
    public float getGyroscopeZ() {
        return 0;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getX(int pointer) {
        return 0;
    }

    @Override
    public int getDeltaX() {
        return 0;
    }

    @Override
    public int getDeltaX(int pointer) {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public int getY(int pointer) {
        return 0;
    }

    @Override
    public int getDeltaY() {
        return 0;
    }

    @Override
    public int getDeltaY(int pointer) {
        return 0;
    }

    @Override
    public boolean isTouched() {
        return false;
    }

    @Override
    public boolean justTouched() {
        return false;
    }

    @Override
    public boolean isTouched(int pointer) {
        return false;
    }

    @Override
    public boolean isButtonPressed(int button) {
        return false;
    }

    @Override
    public boolean isKeyPressed(int key) {
        return Gdx.input.isKeyPressed(key);
    }

    @Override
    public boolean isKeyJustPressed(int key) {
        return false;
    }

    @Override
    public void getTextInput(TextInputListener listener, String title, String text, String hint) {

    }

    @Override
    public void setOnscreenKeyboardVisible(boolean visible) {

    }

    @Override
    public void vibrate(int milliseconds) {

    }

    @Override
    public void vibrate(long[] pattern, int repeat) {

    }

    @Override
    public void cancelVibrate() {

    }

    @Override
    public float getAzimuth() {
        return 0;
    }

    @Override
    public float getPitch() {
        return 0;
    }

    @Override
    public float getRoll() {
        return 0;
    }

    @Override
    public void getRotationMatrix(float[] matrix) {

    }

    @Override
    public long getCurrentEventTime() {
        return 0;
    }

    @Override
    public void setCatchBackKey(boolean catchBack) {

    }

    @Override
    public boolean isCatchBackKey() {
        return false;
    }

    @Override
    public void setCatchMenuKey(boolean catchMenu) {

    }

    @Override
    public boolean isCatchMenuKey() {
        return false;
    }

    @Override
    public void setInputProcessor(InputProcessor processor) {

    }

    @Override
    public InputProcessor getInputProcessor() {
        return null;
    }

    @Override
    public boolean isPeripheralAvailable(Peripheral peripheral) {
        return false;
    }

    @Override
    public int getRotation() {
        return 0;
    }

    @Override
    public Orientation getNativeOrientation() {
        return null;
    }

    @Override
    public void setCursorCatched(boolean catched) {

    }

    @Override
    public boolean isCursorCatched() {
        return false;
    }

    @Override
    public void setCursorPosition(int x, int y) {

    }
}