import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.Players.Option;
import org.rev317.min.api.wrappers.Player;

public class CombatClass implements Strategy
{
	public String enemy;
	
	@Override
	public boolean activate()
	{
		return Utils.inCombatArea();
	}

	@Override
	public void execute()
	{
		if(Players.getMyPlayer().isInCombat())
		{
			for(Player target : Players.getNearest())
			{
				if(target.getName().toLowerCase().contains(enemy.toLowerCase()))
				{
					//Logger.addMessage("Enemy: " + enemy, true);
					XEROstaking.scriptStatus = "Attacking";
					target.interact(Option.ATTACK);
					Menu.sendAction(315, 0, 0, 7562, 3159, 1);
					Time.sleep(250);
					break;
				}
			}
		}
	}
}