package com.vee.algorithms.problems.cake;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.testng.internal.collections.Pair;

public class CakeDuplicateFiles {
	/**
	 * 
	 * You left your computer unlocked and your friend decided to troll you by copying a lot of your files to random spots all over your file system.

		Even worse, she saved the duplicate files with random, embarrassing names ("this_is_like_a_digital_wedgie.txt" was clever, I'll give her that).

		Write a function that returns a list of all the duplicate files. We'll check them by hand before actually deleting them, since programmatically deleting files is really scary. To help us confirm that two files are actually duplicates, return a list of tuples ↴ where:

    	the first item is the duplicate file
    	the second item is the original file
    
    	Only once duped
	 */
	
	@Test
	List<Pair<String, String>> findDupeFiles(String dir) {
		List<Pair<String, String>> list = new ArrayList<>();
		Map <String, File> map= new HashMap<String, File>();
		File root = new File(dir);
		traverse(root.listFiles(), map, list); 
		return list;
	}

	private void traverse(File[] files, Map<String, File> map, List<Pair<String, String>> list) {
		for (File f : files) {
			if (f.isDirectory()) {
				traverse(f.listFiles(), map, list);
			} else {
				String md5 = calcMd5(f);
				if (map.containsKey(md5)) {
					list.add(createPair(map.get(md5), f));
					map.remove(md5);
				} else {
					map.put(md5, f);
				}
			}
		}
	}

	private String calcMd5(File f) {
		try(InputStream i = new FileInputStream(f)) {
			return DigestUtils.md5Hex(i);
		}
	}

	private Pair<String, String> createPair(File f1, File f2) {
		if (f1.lastModified() > f2.lastModified()) {
			return new Pair<String, String>(f2.getName(), f1.getName());
		} else {
			return new Pair<String, String>(f1.getName(), f2.getName());
		}
	}
}
