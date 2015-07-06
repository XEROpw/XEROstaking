import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Random;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Walking;
import org.rev317.min.api.wrappers.Tile;


public class EndStake implements Strategy
{
	@Override
	public boolean activate()
	{
		return (Game.getOpenInterfaceId() == Utils.INTERFACE_STAKEWIN);
	}

	@Override
	public void execute()
	{
		Logger.addMessage("Claiming stake spoils.", true);
		int x = Random.between(3361, 3373);
		int y = Random.between(3264, 3271);
		Tile tile = new Tile(x, y);
		Walking.walkTo(tile);
		Time.sleep(500);
	}
}
