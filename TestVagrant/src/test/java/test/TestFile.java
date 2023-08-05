package test;
	import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import POM.RecentlyPlayedStore;

	public class TestFile {
	    private RecentlyPlayedStore recentlyPlayedStore;

	    @BeforeMethod
	    public void setUp() {
	        recentlyPlayedStore = new RecentlyPlayedStore(5, 3);
	    }

	    @Test
	    public void testAddAndGetRecentlyPlayedSong() {
	        recentlyPlayedStore.addSong("Song A", "Alice");
	        recentlyPlayedStore.addSong("Song B", "Alice");
	        recentlyPlayedStore.addSong("Song C", "Alice");

	        Assert.assertEquals(recentlyPlayedStore.getRecentlyPlayedSong("Alice"), "Song C");

	        recentlyPlayedStore.addSong("Song D", "Alice");
	        Assert.assertEquals(recentlyPlayedStore.getRecentlyPlayedSong("Alice"), "Song D");

	        recentlyPlayedStore.addSong("Song X", "Bob");
	        recentlyPlayedStore.addSong("Song Y", "Bob");

	        Assert.assertEquals(recentlyPlayedStore.getRecentlyPlayedSong("Bob"), "Song Y");
	        Assert.assertEquals(recentlyPlayedStore.getRecentlyPlayedSong("Alice"), "Song D");
	    }

	    @Test(expectedExceptions = IllegalArgumentException.class)
	    public void testAddSongWithEmptyUser() {
	        recentlyPlayedStore.addSong("Song A", "");
	    }

	    @Test(expectedExceptions = IllegalArgumentException.class)
	    public void testAddSongWithEmptySong() {
	        recentlyPlayedStore.addSong("", "Alice");
	    }

	    @Test(expectedExceptions = IllegalArgumentException.class)
	    public void testGetRecentlyPlayedSongWithEmptyUser() {
	        recentlyPlayedStore.getRecentlyPlayedSong("");
	    }
	
}
