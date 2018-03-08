package interfaces;

import models.Player;

public interface Ownable
{
	public String getTYPE();
	public String getName();
	public int getRent();
	public Player getOwner();
}
