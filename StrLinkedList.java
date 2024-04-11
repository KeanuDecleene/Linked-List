/*
 * A dynamic linked list of strings
 * @author Keanu De Cleene
 */
public class StrLinkedList{
private Node head; 
/*
 * A node that stores a datum and its pointer
 */
private class Node{
    private String value;
    private Node next;

    /*
     * takes a string and puts that value into value variable, intialises next to null
     * 
     * @param the string value to be stored
     */
    public Node(String s){
        value = s;
        next = null;
    }
}

/*
 * Returns boolean true if the linked list is empty
 * 
 * @return true if the list is empty, false otherwise
 */
public Boolean isEmpty(){
    return head == null;
}

/*
 * Gets length of linked list 
 * 
 * @return an int of the number of values in the list
 */
public int getLength(){
    int counter = 0;
    Node curr = head;
    while (curr != null){
        counter++;
        curr = curr.next;
    }
    return counter;
}

/*
 * Looks to see if linked list contains a node whose value is same as s
 * 
 * @param s the value to be checked 
 * return true if the list has the item, false otherwise
 */
public boolean hasValue(String s){
    Node curr = head;
    while (curr != null){
        //IF the value is the same as the value parsed in 
        if(curr.value.equals(s)){
            return true;
        }
        curr = curr.next;
    }
    return false;
}

/*
 * Gets string at certain position in the list (no err)
 * 
 * @param i the position of the value we are looking for
 * @return the String value at certain index in list
 */
public String getValueAt(int i){
    Node curr = head;
    int position = 0;
    if (getLength() >= i){
        while (curr != null){
            if (position == i){
                return curr.value;
            }
            curr = curr.next;
            position++;
        }
    }
    return String.valueOf(i) + " ERROR: value given is larger or smaller than length of the list";
}

/*
 * Adds to head of the list a new node with the string value parsed
 * 
 * @param the string value to add to the list
 */
public void add(String s){
    Node nAdd = new Node(s);
    nAdd.next = head;
    head = nAdd;
}

/*
 * Finds the first node whose String value is s and removes that node and maintains structure of the list
 * If the list has no node the method should leave the list unchanged
 * @param s the value to remove from list
 */
public void remove(String s){
    Node curr = head;
    if(head == null){
        return;
    }
    //Front of list removal
    if(head.value == s){
        head = head.next;
        return;
    }
    //middle or end of list removal
    while (curr.next != null){
        if(curr.next.value == s){
            curr.next = curr.next.next;
            return;
        }
        curr = curr.next;
    }
}

/*
 * For each node in the list, print to the screen the value in the node followed by a pointer to the next value
 */
public void print(){
Node curr = head;
while (curr != null){
    System.out.print(curr.value + "->");
    curr = curr.next;
}
System.out.println();
}
}