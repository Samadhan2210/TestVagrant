package POM;

	import java.util.HashMap;
	import java.util.Map;

	public class RecentlyPlayedStore {
	    private final int initialCapacity;
	    private final int maxSongsPerUser;
	    private final Map<String, Node> store;
	    private Node head;
	    private Node tail;
	    private int size;

	    public RecentlyPlayedStore(int initialCapacity, int maxSongsPerUser) {
	        this.initialCapacity = initialCapacity;
	        this.maxSongsPerUser = maxSongsPerUser;
	        this.store = new HashMap<>();
	        this.head = null;
	        this.tail = null;
	        this.size = 0;
	    }

	    public void addSong(String song, String user) {
	        if (user == null || user.isEmpty() || song == null || song.isEmpty()) {
	            throw new IllegalArgumentException("User and song cannot be empty");
	        }

	        if (store.containsKey(user)) {
	            Node node = store.get(user);
	            moveNodeToFront(node);
	            node.song = song;
	        } else {
	            Node newNode = new Node(song, user);
	            store.put(user, newNode);

	            if (size == 0) {
	                head = newNode;
	                tail = newNode;
	            } else {
	                newNode.next = head;
	                head.prev = newNode;
	                head = newNode;
	            }

	            if (size == initialCapacity) {
	                removeTail();
	            } else {
	                size++;
	            }
	        }
	    }

	    public String getRecentlyPlayedSong(String user) {
	        if (user == null || user.isEmpty()) {
	            throw new IllegalArgumentException("User cannot be empty");
	        }

	        if (store.containsKey(user)) {
	            Node node = store.get(user);
	            moveNodeToFront(node);
	            return node.song;
	        }

	        return null;
	    }

	    private void moveNodeToFront(Node node) {
	        if (node != head) {
	            if (node == tail) {
	                tail = node.prev;
	            } else {
	                node.next.prev = node.prev;
	            }

	            node.prev.next = node.next;
	            node.next = head;
	            head.prev = node;
	            head = node;
	        }
	    }

	    private void removeTail() {
	        if (tail != null) {
	            String user = tail.user;
	            store.remove(user);
	            if (head == tail) {
	                head = null;
	            }
	            tail = tail.prev;
	            if (tail != null) {
	                tail.next = null;
	            }
	            size--;
	        }
	    }

	    private static class Node {
	        String song;
	        String user;
	        Node prev;
	        Node next;

	        Node(String song, String user) {
	            this.song = song;
	            this.user = user;
	            this.prev = null;
	            this.next = null;
	        }
	    }
	}



