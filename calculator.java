import java.io.File;
import java.util.Scanner;

public class calculator {
    Stack operand;
    Stack operator;
    
    //In this installer function, it reads the infix.txt file I pulled from the desktop, line by line.
    calculator(String pathWayOfInfix){
        try{
            
            File file = new File(pathWayOfInfix);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String newLine = scanner.nextLine();
                //If there is extra space in the txt file, the function does not read that line.
                if(newLine.trim().isEmpty())
                    continue;
                //I created a linkedlist here. First, I send the line I read with the scanner to the reader function.
                //the reader file returns the line it read to me by filling it into the linkedlist.
                //I send the linkedlist pointer to the calculation function.
                try{
                    calculation(reader(newLine.trim()));
                }catch(Exception e){
                    System.out.println("ERROR");
                }  
            }
        }
        catch(Exception e){
            System.out.println("Your file not found");
        }
    }
    //This function reads the linkedlist it receives and performs calculations using the stack structure. It prints the result.
    void calculation(LinkedList line){
        operand=new Stack();
        operator=new Stack();
        //Here, first of all, the index that is divisible by 2 must be the number parts of the linkedlist, and the index that is not divisible by 2 must be the operator.
        for(int i=0;i<line.size;i++){
            //If the value read in the linkedlist does not match the number or operator order, the program prints an error.
            if(i%2==0){
                double a=0;
                try {
                    a = Integer.parseInt(line.get(i));
                } catch (Exception e) {
                    System.out.println("ERROR");
                    return;
                }
                
                operand.push(new Node(a));
                
            }else{
                double a;
                
                try {
                    a = Integer.parseInt(line.get(i));
                    System.out.println("ERROR");
                    return;
                } catch (Exception e) {
                    //The operator read here is added to the checkandadd function and added to the stack structure.
                    if(line.get(i).compareTo("+")==0)
                        checkAndAdd(1);
                    else if(line.get(i).compareTo("-")==0)
                        checkAndAdd(2);
                    else if(line.get(i).compareTo("*")==0)
                        checkAndAdd(3);
                    else if(line.get(i).compareTo("/")==0)
                        checkAndAdd(4);
                    else{
                        System.out.println("ERROR");
                        return;
                    }   
                }
            }
            
        }
        
        //Here, after reading the linkedlist, it processes the remaining operators and operands and only 1 number remains in the operand stack structure, and that is the result.
        while(this.operator.peek()!=null){
           execute(); 
        }
        //Here we check whether the result has a fraction part or not. If there is no fraction part, it does not print the fraction part, but if there is a fraction part, it prints it directly.
        if(operand.peek().element-(int)operand.peek().element!=0)
            System.out.println(operand.peek().element);
        else
            System.out.println((int)operand.peek().element);
    }
    
    //This function compares the operator it receives with the upper element of the stack structure.
    //If the operator priority is in the upper element of the stack structure, it calculates the operation in the stack structure and adds the operator to the stack structure when operator priority is achieved.
    void checkAndAdd(int operator){
        while(this.operator.peek()!=null&&this.operator.peek().element>operator){
             execute();
        }
        this.operator.push(new Node(operator));
    }
    
    
    void execute(){
        //This function calculates using the top elements of the operator and operand stacks and adds the result to the operand stack.
            if(operator.peek().element==1){
                double temp=operand.pop().element;
                operand.peek().element=operand.peek().element+temp;
            }else if(operator.peek().element==2){
                double temp=operand.pop().element;
                operand.peek().element=operand.peek().element-temp;
            }else if(operator.peek().element==3){
                double temp=operand.pop().element;
                operand.peek().element=operand.peek().element*temp;
            }else if(operator.peek().element==4){
                double temp=operand.pop().element;
                operand.peek().element=operand.peek().element/temp;
            }
            operator.pop(); 
    }
    
    
    //This function reads the string expression it receives and fills the linklist and returns the linkedlist.
    LinkedList reader(String word){
        StringBuffer s=new StringBuffer(word);
        LinkedList list=new LinkedList();
        char space=' ';
        int startIndex=0;
        
        for(int i=1;i<s.length();i++){
            //Here, when reading the string, it first checks for spaces. 
            //If there is an operator or number before the space when it reads a space, it adds it to the list using startindex.
            if(s.charAt(i)==space){
                if((isoperator(s.charAt(i-1)))||isnumber(s.charAt(i-1))){
                  list.add(s.substring(startIndex, i).trim());  
                }
                startIndex=i;
                continue;
            }    
            //Here, if there is a number before the operator expression instead of a space, it returns the number to the list using startindex.
            if(isoperator(s.charAt(i))){
                if(s.charAt(i-1)!=space){
                    list.add(s.substring(startIndex, i).trim());
                }
                startIndex=i;
            }
            //If the index i is a number instead of a space or when reading adjacent numbers.
            //if the index i-1 is not a number, we start reading the next index because the number may not be completed. 
            //If i-1 is an operator, we add it to the list because it is the beginning of a number.
            if(isnumber(s.charAt(i))){
                if(!isnumber(s.charAt(i-1))){
                    if(isoperator(s.charAt(i-1))){
                        list.add(s.substring(startIndex, i).trim());
                        startIndex=i;
                    }
                }
            }  
        }
        //Finally, we delete the excess spaces at the beginning and end of each strings we added with the trim function.
        list.add(s.substring(startIndex).trim());
        list.checkFirstIndex();
        return list;
    }
    //It checks whether the received character is a number or not.
    boolean isnumber(char target){
        char[] numbers={'0','1','2','3','4','5','6','7','8','9'};
         for (int j = 0; j < numbers.length; j++) {
                if(numbers[j]==target)
                    return true;
            }
         return false;
    }
    //It checks whether the received character is an operator or not.
    boolean isoperator(char target){
        char[] operators={'+','-','*','/'};
        for (int j = 0; j < operators.length; j++) {
                if(operators[j]==target)
                    return true;
            }
         return false;
    }
}
