import java.awt.event.KeyEvent;

import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;

/*
 * [ANTI-LOGOUT MODULE]
 * This module will log the user back in, so that unexpected server crashes
 * or updates do not hinder the script.
 */
public class AntiLogout implements Strategy
{
	@Override
	public boolean activate()
	{
		return (!Game.isLoggedIn());
	}

	@Override
	public void execute()
	{
		Logger.addMessage("Attempting to log in to the game...", true);
		
		Keyboard.getInstance().clickKey(KeyEvent.VK_ENTER);
		Time.sleep(new SleepCondition()
		{
			@Override
			public boolean isValid()
			{
				return Game.isLoggedIn();
			}
		}, 5000);
		
		if(Game.isLoggedIn())
		{
			Logger.addMessage("Successfully logged back into the game.", true);
			Time.sleep(3500);
		}
	}
}