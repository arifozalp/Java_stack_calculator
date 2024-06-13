public class Stack{
    Node tail;
    
    //This function removes the last entered node and returns it.
    Node pop(){
        if(tail==null){
            return null;
        }else{
            Node current=tail;
            tail=tail.next;
            return current;
        }
    }
    //This function adds nodes to the top of the stack structure.
    void push(Node node){
        node.next=tail;
        tail=node;
    }
    //This function returns the node at the top of the stack structure.
    Node peek(){
        return tail;
    }
    //It checks whether the stack structure is empty or not.
    boolean isEmpty(){
        return tail==null;
    }
}
//This class was created to add nodes into the stack structure.
class Node{
        double element;
        Node next;
         Node (double element){
            this.element=element;
        }
    }
