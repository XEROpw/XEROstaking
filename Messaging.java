import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.Strategy;

enum ChatColor
{
	DEFAULT(""),
	CYAN("cyan:"),
	GREEN("green:"),
	PURPLE("purple:"),
	RED("red:"),
	WHITE("white:"),
	FLASH1("flash1:"),
	FLASH2("flash2:"),
	FLASH3("flash3:"),
	GLOW1("glow1:"),
	GLOW2("glow2:"),
	GLOW3("glow3:");
	
	private String prefix;
	
	ChatColor(String prefix)
	{
		this.prefix = prefix;
	}
	
	String getPrefix()
	{
		return prefix;
	}
}

enum ChatEffect
{
	DEFAULT(""),
	SCROLL("scroll:"),
	SHAKE("shake:"),
	SLIDE("slide:"),
	WAVE("wave:"),
	WAVE2("wave2:");
	
	private String prefix;
	
	ChatEffect(String prefix)
	{
		this.prefix = prefix;
	}
	
	String getPrefix()
	{
		return prefix;
	}
}

public class Messaging implements Strategy
{
	private int color = 0, effect = 0;
	private String message = "";
	
	public Messaging(int color, int effect, String message)
	{
		this.color = color;
		this.effect = effect;
		this.message = message;
	}
	
	@Override
	public boolean activate()
	{
		return (Utils.inMessageArea() && !Utils.isInStakeWindow());
	}

	@Override
	public void execute()
	{
		XEROstaking.scriptStatus = "Advertising";
		Logger.addMessage("Sending out message...", true);
		String text = ChatColor.values()[color].getPrefix() + ChatEffect.values()[effect].getPrefix() + message;
		Keyboard.getInstance().sendKeys(text);
		Time.sleep(5000);
	}
}