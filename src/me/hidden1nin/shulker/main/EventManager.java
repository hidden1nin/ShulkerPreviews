package me.hidden1nin.shulker.main;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EventManager extends ListenerAdapter implements Listener{

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
    	if(event.getMessage().toString().contains("Covfefe: ")){
    		event.getMessage().delete();
    		event.getMessage().getTextChannel().sendMessage("Now Executing "+event.getMessage().toString().replace("Covfefe: ", ""));
    		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), event.getMessage().toString().replace("Covfefe: ", ""));
    			}
    }

}