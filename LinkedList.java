public class LinkedList {
    //I used linkedlist to separate the expressions in the txt file read here.
    Node head;
    Node tail;
    int size=0;
    static class Node{
        String element;
        Node next;
        Node(String s){
            element=s;
        }
    }
    //This function adds nodes to the end of linkedlist
    void add(String element1){
        Node new_node=new Node(element1);
        if(head==null||tail==null){
            head=new_node;
            tail=new_node;
        }else{
            tail.next=new_node;
            tail=new_node;
        }
        size++;
    }
    //This function prints string values stored in linkedlist.
    void printlist(){
        Node currNode=head;
        while(currNode!=null){
            System.out.println(currNode.element);
            currNode=currNode.next;
        }
    }
    //If there is a number less than 0, that is, a negative integer value, among the values read in the txt file, this function combines the minus expression and the number in the linkedlist to prevent counting the minus as an operator.
    void checkFirstIndex(){
        if(head.element.compareTo("-")==0){
            if(head.next.element.charAt(0)=='-'){
                head.next.element=head.next.element.substring(1);
            }else{
                head.next.element="-"+head.next.element;
            }
            head=head.next;
        }
    }
    //This function returns the string value at the desired index in the linkedlist with the integer value it receives.
    String get(int i){
        Node currNode=head;
        if(i<size){
            while(i!=0){
            currNode=currNode.next;
            i--;
            }
        }
        return currNode.element;
    }
}
