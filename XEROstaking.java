import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.parabot.core.ui.Logger;
import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.api.utils.Timer;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.events.MessageEvent;
import org.rev317.min.api.events.listeners.MessageListener;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.Players.Option;
import org.rev317.min.api.wrappers.Player;

@ScriptManifest
(
	name = "XERO Staking",
	description = "The ultimate AIO staking experience.",
	author = "VectorX",
	version = 1.4,
	servers = "IKOV",
	category = Category.COMBAT
)
public class XEROstaking extends Script implements Paintable, MessageListener
{
	ArrayList<Strategy> strategies = new ArrayList<Strategy>();
	private StakeWindow stakeWindow; //cuz static access lol
	private CombatClass combatClass; //same I guess
	
	//For paint
	private Timer runtime = new Timer();
	private int startCash = 0;
	private Image paintImage = getImage("http://parabot.xero.pw/img/staking/paint.png");
	public static String scriptStatus = "";
	public static int won, lost;
	public static Settings settings;
	
	@Override
	public boolean onExecute()
	{		
		Logger.addMessage("Initiating settings dialog...", true);
		settings = new Settings();
		settings.setVisible(true);
		
		while(settings.isVisible())
		{
			Time.sleep(1000);
		}
		
		if(!settings.bRun)
		{
			Logger.addMessage("Script failed to initialize.", true);
			return false;
		}
		
		//strategies.add(new AntiLogout());
		strategies.add(new AntiTrade());
		strategies.add(new Messaging(settings.color.getSelectedIndex(), settings.effect.getSelectedIndex(), settings.message.getText()));
		stakeWindow = new StakeWindow(Integer.parseInt(settings.amount.getText()));
		strategies.add(stakeWindow);
		combatClass = new CombatClass();
		strategies.add(combatClass);
		strategies.add(new EndStake());
		
		provide(strategies);
		Logger.addMessage("Script successfully initialized.", true);
		runtime = new Timer();
		
		startCash = Utils.getWorth(Inventory.getItems(Utils.ID_CASH, Utils.ID_SHARDS));
		
		return true;
	}
	
	@Override
	public void messageReceived(MessageEvent me)
	{
		if(me.getType() == MessageEvent.TYPE_DUEL && !Utils.isInStakeWindow())
		{
			String enemy = me.getSender();
			Logger.addMessage("Received a dueling request from (" + enemy + "), accepting...", true);
			
			for(Player player : Players.getNearest())
			{
				if(player != null)
				{
					if(player.getName().toLowerCase().contains(enemy.toLowerCase()) && Utils.messageArea.contains(player.getLocation()))
					{
						player.interact(Option.CHALLENGE);
						scriptStatus = "Accepting";
						combatClass.enemy = enemy.toLowerCase();
						break;
					}
				}
			}
			
			Time.sleep(new SleepCondition()
			{
				@Override
				public boolean isValid()
				{
					return Utils.isInStakeWindow();
				}
			}, 2000);
		}
		else if(me.getType() == MessageEvent.TYPE_GENERIC)
		{
			if(me.getMessage().toLowerCase().contains("duel was recently modified"))
			{
				Menu.sendAction(315, 0, 0, 41400, 3159, 1);
				stakeWindow.modified = new Timer(5000);
			}
		}
	}

	@Override
	public void paint(Graphics g1)
	{
		Graphics2D g = (Graphics2D)g1;
		g.setFont(new Font("Verdana", 0, 12));
		g.setColor(new Color(255, 200, 0));
		
		//Titlebar
		g.drawImage(paintImage, -5, 260, null);
		g.drawString(runtime.toString(), 110, 367);
		g.drawString(scriptStatus, 356, 367);
		
		//Stats
		g.setFont(new Font("Verdana", 0, 16));
		g.drawString("TBD", 135, 401);
		g.drawString("TBD", 134, 427);
		g.drawString("TBD", 133, 453);
		
		g.drawString("" + formatNumber(Utils.getWorth(Inventory.getItems(Utils.ID_CASH, Utils.ID_SHARDS)) - startCash), 358, 401);
		g.drawString("" + (won+lost), 359, 427);
		g.drawString("" + runtime.getPerHour((won+lost)), 371, 453);
	}
	
	private Image getImage(String url)
	{
		try{ return ImageIO.read(new URL(url)); }
		catch(Exception exception) { return null; }
	}
	
	private String formatNumber(double number)
	{
		DecimalFormat format = new DecimalFormat("#,###.00");

		if (number >= 1000 && number < 1000000) { return format.format(number / 1000) + "K"; }
		else if (number >= 1000000 && number < 1000000000) { return format.format(number / 1000000) + "M"; }
		else if (number >= 1000000000){ return format.format(number / 1000000000) + "B"; }
		
        return ("" + number);
	}
}
