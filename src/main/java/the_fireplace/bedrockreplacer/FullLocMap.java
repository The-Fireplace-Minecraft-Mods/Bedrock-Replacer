package the_fireplace.bedrockreplacer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;
import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.ISaveHandler;

public class FullLocMap implements Serializable {
	public static final long serialVersionUID = 0xDEADBEEF;
	private static FullLocMap instance = null;
	private Map<BlockPos, IBlockState> bedrocks;
	public static FullLocMap getInstance(){
		return instance;
	}
	public FullLocMap(){
		bedrocks = Maps.newHashMap();
		this.instance = this;
	}
	private static MinecraftServer serv = MinecraftServer.getServer();
	private static World world = serv.getEntityWorld();
	private static ISaveHandler save = world.getSaveHandler();
	private static File dir = save.getWorldDirectory();
	private static String fn = ".brl";
	public static void load(){
		readFromFile();
	}
	public static void save(){
		saveToFile();
	}
	public Map<BlockPos, IBlockState> bedrocks(){
		return bedrocks;
	}
	private static void readFromFile() {
		File f = new File(dir, fn);
		if (f.exists()) {
			try {
				ObjectInputStream stream = new ObjectInputStream(new FileInputStream(f));
				instance = (FullLocMap) stream.readObject();
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
				instance = new FullLocMap();
				f.delete();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				instance = new FullLocMap();
				f.delete();
			}
		}
		if (instance == null)
			instance = new FullLocMap();
	}

	private static void saveToFile() {
		try{
			Path file = new File(dir, fn).toPath();
			DosFileAttributes attr = Files.readAttributes(file, DosFileAttributes.class);
			DosFileAttributeView view = Files.getFileAttributeView(file, DosFileAttributeView.class);
			if(attr.isHidden())
				view.setHidden(false);
		}catch(Exception e){
		}
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(dir, fn)));
			out.writeObject(instance);
			out.close();
		} catch (IOException e) {
		}
		try{
			Path file = new File(dir, fn).toPath();
			DosFileAttributes attr = Files.readAttributes(file, DosFileAttributes.class);
			DosFileAttributeView view = Files.getFileAttributeView(file, DosFileAttributeView.class);
			if(!attr.isHidden())
				view.setHidden(true);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
