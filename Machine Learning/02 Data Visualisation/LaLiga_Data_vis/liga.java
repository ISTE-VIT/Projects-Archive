import java.util.Scanner;  
public class FindSquareRootExample1   
{  
public static void main(String[] args)    
{   
System.out.print("Enter a number: ");  
//creating object of the Scanner class  
Scanner sc = new Scanner(System.in);  
//reading a number form the user  
int n = sc.nextInt();  
//calling the method and prints the result  
System.out.println("The square root of "+ n+ " is: "+squareRoot(n));  
}  
//user-defined method that contains the logic to find the square root  
public static double squareRoot(int num)   
{  
//temporary variable  
double t;  
double sqrtroot=num/2;  
do   
{  
t=sqrtroot;  
sqrtroot=(t+(num/t))/2;  
}   
while((t-sqrtroot)!= 0);  
return sqrtroot;  
}  
}  
