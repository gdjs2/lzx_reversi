package model;

public enum GameStatus {
	BLACK_WIN, WHITE_WIN, NONE, TIE;

	public ChessPiece getWinner() {
		if (this == BLACK_WIN) return ChessPiece.BLACK;
		else if (this == WHITE_WIN) return ChessPiece.WHITE;
		else return null;
	}
}
