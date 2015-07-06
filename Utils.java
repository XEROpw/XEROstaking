import java.util.ArrayList;

import org.rev317.min.Loader;
import org.rev317.min.accessors.Interface;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Area;
import org.rev317.min.api.wrappers.Item;
import org.rev317.min.api.wrappers.Tile;

public class Utils
{
	// --- INVENTORY RELATED --- //
	public static final int ID_CASH = 996;
	public static final int ID_SHARDS = 12184;
	//Note to those viewing: probably best to implement store api to enable noobs who stake items, but then a banking class is a must
	
	public static int getWorth(Item[] items)
	{
		int amount = 0;
		
		if(items != null)
		{
			for(Item item : items)
			{
				switch(item.getId())
				{
				case ID_CASH:
					amount += item.getStackSize();
					break;
				case ID_SHARDS:
					amount += (25 * item.getStackSize());
					break;
				default:
					continue;
				}
			}
		}
		
		return amount;
	}
	
	// --- INTERFACE RELATED --- //
	public static final int INTERFACE_MYOFFER = 6669;
	public static final int INTERFACE_THEIROFFER = 6670;
	public static final int INTERFACE_STAKEWIN = 6733;
	
	public static boolean isInOffer()
	{
		return Game.getOpenInterfaceId() == 6575;
	}
	
	public static boolean isInConfirm()
	{
		return Game.getOpenInterfaceId() == 6412;
	}
	
	public static boolean isInStakeWindow()
	{
		return (isInOffer() || isInConfirm());
	}
	
	// --- SCRIPT AREAS --- //
	public static Area combatArea = new Area(new Tile(3332, 3259), new Tile(3332, 3243), new Tile(3358, 3243), new Tile(3358, 3259));
	public static Area messageArea = new Area(new Tile(3354, 3262), new Tile(3354, 3279), new Tile(3379, 3279), new Tile(3379, 3262));
	
	public static boolean inCombatArea()
	{
		return combatArea.contains(Players.getMyPlayer().getLocation());
	}
	
	public static boolean inMessageArea()
	{
		return messageArea.contains(Players.getMyPlayer().getLocation());
	}
	
	// --- STAKE OFFER RELATED --- //
	public static Item[] getOffer(int interfaceID)
	{
		ArrayList<Item> items = new ArrayList<Item>();
		int[] ids = {}, stacks = {};
		Interface iface;
		
		//Get items
		if((iface = Loader.getClient().getInterfaceCache()[interfaceID]) != null)
		{
			if(iface.getItems() != null) ids = iface.getItems();
			if(iface.getStackSizes() != null) stacks = iface.getStackSizes();
		}
		
		//Add to items array each item and the relative stack size
		for(int i = 0; i < ids.length; i++)
        {
            if(ids[i] > 0)
            {
                items.add(new Item(ids[i], stacks[i], i));
            }
        }
		
        return items.toArray(new Item[items.size()]);
    }
}