import org.parabot.core.Context;
import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.api.utils.Timer;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.wrappers.Item;

public class StakeWindow implements Strategy
{	
	private int money = 0;
	public Timer modified = new Timer();
	
	public StakeWindow(int money)
	{
		this.money = money;
	}
	
	@Override
	public boolean activate()
	{
		return Utils.isInStakeWindow();
	}
	
	@Override
	public void execute()
	{
		XEROstaking.scriptStatus = "Checking Offer";
		
		//In the offer window
		if(Utils.isInOffer())
		{
			//Check our offer
			checkMyOffer(Utils.getOffer(Utils.INTERFACE_MYOFFER));
			
			//Check enemy offer
			if(checkOpponentOffer(Utils.getOffer(Utils.INTERFACE_THEIROFFER)) && modified.isFinished())
			{
				Logger.addMessage("Accepting stake offer...", true);
				XEROstaking.scriptStatus = "Accepting...";
				Menu.sendAction(315, 5698, 3, 6674, 3190, 1);
				Time.sleep(500);
			}
		}
		//Confirmation window
		else if(Utils.isInConfirm())
		{
			Logger.addMessage("Starting the stake...", true);
			Menu.sendAction(315, 586, 0, 6520, 3190, 1);
			Time.sleep(500);
		}
		Time.sleep(2500);
	}
	
	//Checks player offer
	private void checkMyOffer(Item[] items)
	{
		boolean deposited = false;
		for(Item item : items)
		{
			if(item.getId() == Utils.ID_CASH)
			{
				deposited = true;
				break;
			}
		}
		
		if(deposited)
		{
			int amount = 0;
			
			for(Item item : items)
			{
				if(item.getId() == Utils.ID_CASH)
				{
					amount += item.getStackSize();
					break;
				}
			}
			
			if(amount != (money * 1000000))
			{
				Logger.addMessage("Wrong amount of money offered. Re-adjusting.", true);
				Menu.sendAction(431, Utils.ID_CASH - 1, 0, Utils.INTERFACE_MYOFFER, 3190, 3);
				Time.sleep(500);
			}
		}
		else
		{
			int amount = money * 1000000;
			
			if(Utils.getWorth(Inventory.getItems()) >= money * 1000000)
			{
				//previous options
				Menu.sendAction(315, 0, 0, 41400, 3159, 1);
				Time.sleep(250);
				
				Menu.sendAction(53, Utils.ID_CASH - 1, 0, 3322, 3159, 2);
				Time.sleep(1500);
				Keyboard.getInstance().sendKeys(money + "m");
				Time.sleep(500);
				
				amount = 0;
			}
			
			if(amount != 0)
			{
				Logger.addMessage("Not enough money to continue. Stopping script...", true);
				Context.getInstance().getRunningScript().setState(Script.STATE_STOPPED);
			}
		}
	}
	
	//Checks opponent's offer
	private boolean checkOpponentOffer(Item[] items)
	{
		boolean deposited = false;
		if(Utils.getWorth(items) >= (money * 1000000)) deposited = true;
		
		return deposited;
	}
}