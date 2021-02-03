package com.threecubed.auber.entities.playerpowerups;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.threecubed.auber.entities.Player;

public abstract class PlayerPowerUp {

	/**
	 * The sprite of the power up
	 */
	protected Sprite sprite;

	/**
	 * The position of the power up
	 */
	private Vector2 position;

	/**
	 * The timestamp of when the player can next use the power up
	 */
	protected long nextActivatableTimeMs;

	/**
	 * The millisecond cooldown of how long the player must wait between uses of the
	 * powerup
	 */
	protected long cooldownMs;
	
	/**
	 * The code of the keyboard letter used to initiate the power up
	 */
	protected int keyCode;

	/**
	 * The player that has procured the power up
	 */
	protected Player player;

	/**
	 * The power up's constructer
	 * 
	 * @param sprite
	 * @param position
	 * @param cooldownMs
	 * @param keyCode
	 */
	public PlayerPowerUp(Sprite sprite, Vector2 position, long cooldownMs, int keyCode) {
		this.sprite = sprite;
		this.position = position;
		this.cooldownMs = cooldownMs;
		this.keyCode = keyCode;
	}

	/**
	 * Renders the powerup to the screen, if it hasn't been collected
	 * 
	 * @param batch
	 * @param camera
	 */
	public void render(Batch batch, Camera camera) {
		if (player == null) {
			sprite.setPosition(position.x, position.y);
			sprite.draw(batch);
		}
	}

	/**
	 * Fired when the player collects the power up
	 * 
	 * @param player
	 */
	public void collect(Player player) {
		this.player = player;
	}

	/**
	 * Activates the power up
	 */
	public void activate() {
		doAction();
		nextActivatableTimeMs = System.currentTimeMillis() + cooldownMs;
	}

	/**
	 * @param keyCode
	 * @return True if the key code supplied corresponds with the power up, False otherwise
	 */
	public boolean isKey(int keyCode) {
		return this.keyCode == keyCode;
	}

	/**
	 * @return True if the cooldown is over
	 */
	public boolean canActivate() {
		return System.currentTimeMillis() > nextActivatableTimeMs;
	}

	/**
	 * Performs the action of the power up
	 */
	protected abstract void doAction();

}