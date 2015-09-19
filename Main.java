package PabloTL.Logowanie;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	public static Plugin plugin;
	public static Main getInst;
	
	public Main(){
		plugin = this;
}
	
	public void onEnable(){
		getInst = this;
		saveDefaultConfig();
		System.out.println("TEN SERWER UZYWA PABLO LOGOWANIE!");
}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent evt){
		Player p = evt.getPlayer();
		try {
			@SuppressWarnings("resource")
			Scanner skaner = new Scanner(new URL("https://minecraft.net/haspaid.jsp?user="+p.getName()).openStream());
			while(skaner.hasNext()){
				String ma_premium = skaner.nextLine();
				if(ma_premium == "true"){
					p.sendMessage("§7PabloLogowanie §8>> §2Zalogowano z konta premium.");
					getConfig().set("Konta."+p.getName()+".Zalogowany", "tak");
					saveConfig();
}
				if(ma_premium == "false"){
					p.sendMessage("§7PabloLogowanie §8>> §2Zaloguj siê przez /login has³o.");
					getConfig().set("Konta."+p.getName()+".Zalogowany", "nie");
					saveConfig();
}
}
}
		catch (MalformedURLException e){
			e.printStackTrace();
}
		catch (IOException e) {
			e.printStackTrace();
}
}
	
	@EventHandler
	public void onBreak(BlockBreakEvent evt){
		Player p = evt.getPlayer();
		if(getConfig().getString("Konta."+p.getName()+".Zalogowany")=="nie"){
			p.sendMessage("§7PabloLogowanie §8>> §2Zaloguj siê przez /login has³o.");
			evt.setCancelled(true);
}
}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent evt){
		Player p = evt.getPlayer();
		if(getConfig().getString("Konta."+p.getName()+".Zalogowany")=="nie"){
			p.sendMessage("§7PabloLogowanie §8>> §2Zaloguj siê przez /login has³o.");
			evt.setCancelled(true);
}
}
	
	@EventHandler
	public void onMove(PlayerMoveEvent evt){
		Player p = evt.getPlayer();
		if(getConfig().getString("Konta."+p.getName()+".Zalogowany")=="nie"){
			p.sendMessage("§7PabloLogowanie §8>> §2Zaloguj siê przez /login has³o.");
			evt.setCancelled(true);
}
}
	
	public boolean onCommand(CommandSender s, Command cmd, String l, String[] args){
		if(l == "login"){
			Player p = (Player) s;
			if(args.length==1){
				String has³o = args[0];
				String zapisane_has³o = getConfig().getString("Konta."+p.getName()+".Has³o");
				if(zapisane_has³o==null){
					p.sendMessage("§7PabloLogowanie §8>> §2Konto nie istnieje.");
					return true;
}
				if(zapisane_has³o==has³o){
					p.sendMessage("§7PabloLogowanie §8>> §2Zalogowa³eœ siê na serwerze.");
					getConfig().set("Konta."+p.getName()+".Zalogowany", "tak");
					saveConfig();
					return true;
}
				else{
					p.sendMessage("§7PabloLogowanie §8>> §2Wpisa³eœ Z³e has³o.");
					return true;
}
}
			else{
				p.sendMessage("§7PabloLogowanie §8>> §2Wpisz has³o.");
				return true;
}
}
		if(l == "register"){
			Player p = (Player) s;
			if(args.length==2){
				String has³o = args[0];
				String has³o2 = args[1];
				String zapisane_has³o = getConfig().getString("Konta."+p.getName()+".Has³o");
				if(zapisane_has³o==null){
					p.sendMessage("§7PabloLogowanie §8>> §2Zaloguj siê przez /login has³o.");
					return true;
}
				if(has³o==has³o2){
					p.sendMessage("§7PabloLogowanie §8>> §2Zarejestrowa³eœ nowe konto.");
					getConfig().set("Konta."+p.getName()+".Has³o", has³o);
					getConfig().set("Konta."+p.getName()+".Zalogowany", "tak");
					saveConfig();
					return true;
}
				else{
					p.sendMessage("§7PabloLogowanie §8>> §2Wpisa³eœ Z³e has³o.");
					return true;
}
}
			else{
				p.sendMessage("§7PabloLogowanie §8>> §2Wpisz has³o.");
				return true;
}
}
		return true;
}
	
}
