package Modele;

public interface IPlayable
{
	
	public void mPlay(Zen pZen, int[][] pPlateau, int pNoTurn);

	public void isClicked(int pLine, int pColumn);
	
	public boolean getSelected();
	
	public int[] getPion();
	
	public boolean getEndTurn();
	
	public int getColor();
	
	public int[] getWhere();
	
	public void setNewTurn();
	
	public void setNoTurn(int pNoTurn);

	boolean mEndTurn();

	public int[][] getPlateau();

	public Zen getZen();

	public boolean getLeaveGame();



}