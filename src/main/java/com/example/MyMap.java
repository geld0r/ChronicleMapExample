package com.example;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import net.openhft.chronicle.map.ChronicleMap;

public class MyMap
{
	private static final String DB_PATH = "db/keyValue/";
	private static final String FILENAME = "store.dat";
	private static final int MAX_SIZE = 1_000_000;
	private static final String AVERAGE_KEY_AND_VALUE = "exampleValue";
	private File file;
	private ChronicleMap<String, String> chronicle;
	
	public MyMap()
	{
		this(DB_PATH, FILENAME);
	}
	
	protected MyMap(String path, String filename)
	{
		File folder = new File(path);
		folder.mkdirs();
		file = new File(folder, filename);
	}
	
	@PostConstruct
	protected void initialize() throws IOException
	{
		chronicle = ChronicleMap.of(String.class, String.class)
									.averageKey(AVERAGE_KEY_AND_VALUE)
									.averageValue(AVERAGE_KEY_AND_VALUE)
									.entries(MAX_SIZE)
									.createPersistedTo(file);
	}
	
	@PreDestroy
	protected void destroy()
	{
		chronicle.close();
	}
	
	public String get(String key)
	{
		return chronicle.get(key);
	}
	
	public void put(String key, String value)
	{
		chronicle.put(key, value);
	}
}
