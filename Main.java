import io.*;
import command.*;
import storage.*;

import java.io.FileNotFoundException;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        Database db = new Database();
		ConsoleIO console = new ConsoleIO();
		DatabaseCSVSaver saver = new DatabaseCSVSaver(db,args[0]);
		DatabaseCSVLoader loader = new DatabaseCSVLoader(db,args[0]);
		CommandHandler ch = new CommandHandler(db,saver,console);
		try {
			loader.load();
		} catch (FileNotFoundException|ParseFileException e) {
			System.out.println(e.getMessage());
		}
		while(true){
			console.write(">");
			String command = null;
			try{
				command = console.readLine();
			} catch (NoSuchElementException e){
				break;
			}
			if(command == null)
				break;
			if(command.equals("exit")){
				break;
			}
			if(command.equals("")){
				continue;
			}
			LinkedList<String> commandArgs = new LinkedList<>(Arrays.asList(command.split(" ")));
			command = commandArgs.get(0);
			commandArgs.remove(0);
			try{
				ch.execute(command,commandArgs.size()==0?null:commandArgs.toArray(new String[commandArgs.size()]));
			} catch(ThereIsNotCommand e){
				console.writeError(e.getMessage());
			} catch(InvalidCommandArgumentException e){
				console.writeError(e.getMessage());
			}
		}
    }
}