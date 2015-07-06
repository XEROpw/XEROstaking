import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Trading;


public class AntiTrade implements Strategy
{
	@Override
	public boolean activate()
	{
		return Trading.isOpen();
	}

	@Override
	public void execute()
	{
		Logger.addMessage("Closing trade window...", true);
		Trading.close();
		Time.sleep(new SleepCondition()
		{
			@Override
			public boolean isValid()
			{
				return !Trading.isOpen();
			}
			
		}, 2000);
	}
}
