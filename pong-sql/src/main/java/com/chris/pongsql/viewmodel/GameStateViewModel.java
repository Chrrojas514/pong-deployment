package com.chris.pongsql.viewmodel;

public class GameStateViewModel {

    private int gameStateId;

    private String roomName;

    private String playerA;
    private String playerB;

    private int playerAPaddlePos;
    private int playerBPaddlePos;

    private int playerAScore;
    private int playerBScore;

    private int ballPositionX;
    private int ballPositionY;

    private int ballVelocityX;
    private int ballVelocityY;

    private boolean isGameStarted;
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
}