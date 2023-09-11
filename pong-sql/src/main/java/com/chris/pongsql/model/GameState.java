package com.chris.pongsql.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "game_state")
public class GameState implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_state_id")
    private int gameStateId;
    @Column(name = "room_name")
    private String roomName;

    @Column(name = "player_a")
    private String playerA;
    @Column(name = "player_b")
    private String playerB;

    @Column(name = "player_a_paddle_pos")
    private int playerAPaddlePos;
    @Column(name = "player_b_paddle_pos")
    private int playerBPaddlePos;

    @Column(name = "player_a_score")
    private int playerAScore;
    @Column(name = "player_b_score")
    private int playerBScore;

    @Column(name = "ball_pos_x")
    private int ballPositionX;
    @Column(name = "ball_pos_y")
    private int ballPositionY;

    @Column(name = "balL_vel_x")
    private int ballVelocityX;
    @Column(name = "ball_vel_y")
    private int ballVelocityY;

    @Column(name = "game_started")
    private boolean isGameStarted;
    @Column(name = "game_over")
    private boolean isGameOver;

    public int getGameStateId() {
        return gameStateId;
    }

    public void setGameStateId(int gameStateId) {
        this.gameStateId = gameStateId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getPlayerA() {
        return playerA;
    }

    public void setPlayerA(String playerA) {
        this.playerA = playerA;
    }

    public String getPlayerB() {
        return playerB;
    }

    public void setPlayerB(String playerB) {
        this.playerB = playerB;
    }

    public int getPlayerAPaddlePos() {
        return playerAPaddlePos;
    }

    public void setPlayerAPaddlePos(int playerAPaddlePos) {
        this.playerAPaddlePos = playerAPaddlePos;
    }

    public int getPlayerBPaddlePos() {
        return playerBPaddlePos;
    }

    public void setPlayerBPaddlePos(int playerBPaddlePos) {
        this.playerBPaddlePos = playerBPaddlePos;
    }

    public int getPlayerAScore() {
        return playerAScore;
    }

    public void setPlayerAScore(int playerAScore) {
        this.playerAScore = playerAScore;
    }

    public int getPlayerBScore() {
        return playerBScore;
    }

    public void setPlayerBScore(int playerBScore) {
        this.playerBScore = playerBScore;
    }

    public int getBallPositionX() {
        return ballPositionX;
    }

    public void setBallPositionX(int ballPositionX) {
        this.ballPositionX = ballPositionX;
    }

    public int getBallPositionY() {
        return ballPositionY;
    }

    public void setBallPositionY(int ballPositionY) {
        this.ballPositionY = ballPositionY;
    }

    public int getBallVelocityX() {
        return ballVelocityX;
    }

    public void setBallVelocityX(int ballVelocityX) {
        this.ballVelocityX = ballVelocityX;
    }

    public int getBallVelocityY() {
        return ballVelocityY;
    }

    public void setBallVelocityY(int ballVelocityY) {
        this.ballVelocityY = ballVelocityY;
    }

    public boolean isGameStarted() {
        return isGameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        isGameStarted = gameStarted;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameState)) return false;
        GameState gameState = (GameState) o;
        return getGameStateId() == gameState.getGameStateId() && getPlayerAPaddlePos() == gameState.getPlayerAPaddlePos() && getPlayerBPaddlePos() == gameState.getPlayerBPaddlePos() && getPlayerAScore() == gameState.getPlayerAScore() && getPlayerBScore() == gameState.getPlayerBScore() && getBallPositionX() == gameState.getBallPositionX() && getBallPositionY() == gameState.getBallPositionY() && getBallVelocityX() == gameState.getBallVelocityX() && getBallVelocityY() == gameState.getBallVelocityY() && isGameStarted() == gameState.isGameStarted() && isGameOver() == gameState.isGameOver() && Objects.equals(getRoomName(), gameState.getRoomName()) && Objects.equals(getPlayerA(), gameState.getPlayerA()) && Objects.equals(getPlayerB(), gameState.getPlayerB());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGameStateId(), getRoomName(), getPlayerA(), getPlayerB(), getPlayerAPaddlePos(), getPlayerBPaddlePos(), getPlayerAScore(), getPlayerBScore(), getBallPositionX(), getBallPositionY(), getBallVelocityX(), getBallVelocityY(), isGameStarted(), isGameOver());
    }
}