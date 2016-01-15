/*
 * HashMap
 * (implemented in class)
 * This version eliminates collisions by using a linked list of EntryListNodes in
 * each slot of the entries array.
 */
public class HashMap
{
    private EntryListNode[] entries;
    private int size;

    public HashMap() {
        entries = new EntryListNode[100];
        size = 0;
    }

    public void put(String key, Object value) {
        if (get(key) == null) {  // there is no entry with the given key so add a new entry
            if (size >= entries.length / 2) { // if over half full, increase array size
                EntryListNode[] oldEntries = entries;
                entries = new EntryListNode[entries.length * 2];
                size = 0;
                for (EntryListNode node : oldEntries) {
                    while (node != null) {
                        put(node.key, node.value);
                        node = node.next;
                    }
                }
            }
            // add the new node to the head of the list
            int index = key.hashCode() % entries.length;
            entries[index] = new EntryListNode(key, value, entries[index]);
            size++;
        }
        else { // replace an entry
            EntryListNode node = entries[key.hashCode() % entries.length];
            while (!node.key.equals(key)) {
                node = node.next;
            }
            node.value = value;
        }
    }

    public Object get(String key) {
        EntryListNode node = entries[key.hashCode() % entries.length];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            else {
                node = node.next;
            }
        }
        return null;
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public void clear() {
        size = 0;
        entries = new EntryListNode[100];
    }

    public Object remove(String key) {
        Object value = get(key);
        if (value == null) {  // the key isn't being used currently
            return null;
        }
        else {
            int index = key.hashCode() % entries.length;
            if (entries[index].key.equals(key)) // remove the head
            {
                entries[index] = entries[index].next;
            }
            else {
                EntryListNode node = entries[index];
                while (! node.next.key.equals(key)) {
                    node = node.next;
                }
                node.next = node.next.next;  // remove node.next
            }
            size--;
            return value;
        }
    }

    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put("Lanya", 4.0);
        map.put("Sam", 3.5);
        map.put("Dale", 4.3);
        map.put("Dale", 4.4);
        System.out.println(map.size());
        map.remove("Sam");
        System.out.println(map.size());
        System.out.println("Sam: " + map.get("Sam"));
        System.out.println("Dale: " + map.get("Dale"));
        System.out.println("Lanya: " + map.get("Lanya"));
    }


    private class EntryListNode
    {
        public String key;
        public Object value;
        public EntryListNode next;

        EntryListNode(String k, Object v, EntryListNode n) {
            key = k;
            value = v;
            next = n;
        }
    }
}
