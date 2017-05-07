package Exercise02;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Exercise02.Rational.NotANumberException;

public class FileReading 
{
	public static void main (String[] args)
	{
		FileReader fr = null;
		FileWriter fw = null;
		
		try 
		{
			fr = new FileReader("TestInput.txt");
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}
	   
		BufferedReader br = new BufferedReader(fr);
		String s="";
		try 
		{
			fw = new FileWriter("Output.txt");
			
			while((s=br.readLine()) != null)
			{
				int numerator_a = 0;
				int demominator_a = 1;
				int numerator_b = 0;
				int demominator_b = 1;
				String calculate = "";
				String do_reduce = "";
				int other = 0;
				
				//System.out.println(s);
				String[] seperate_string = s.split(" ");
				for(int i=0; i<seperate_string.length; i++)
				{
					//System.out.println(seperate_string[i]);
					if(i == 0)
					{
						String first = seperate_string[i];
						//System.out.println(first);
						String[] number = first.split("\\D");
						/*for(int j=3; j<number.length; j++)
						{
							System.out.println(number[j]);
						}*/
						//System.out.println(first.charAt(1));
						if(first.charAt(1) == '-')
						{	
							if(Integer.parseInt(number[3]) == 0)
							{
								numerator_a = Integer.parseInt(number[3]);
								demominator_a = 0*(-1)*Integer.parseInt(number[4]);
							}
							
							else
							{
								numerator_a = (-1)*Integer.parseInt(number[3]);
								demominator_a = Integer.parseInt(number[4]);
							}
						}
						
						else
						{
							numerator_a = Integer.parseInt(number[3]);
							demominator_a = Integer.parseInt(number[4]);
						}
						
						//System.out.println(numerator_a + " " + demominator_a);
					}
					
					else if(i == 1)
					{
						calculate = seperate_string[i];
						//System.out.println("calculate:" + calculate);
					}
					
					else if(i == 2)
					{
						if(calculate.equals("+") || calculate.equals("-") || calculate.equals("*") || calculate.equals(":"))
						{
							String second = seperate_string[i];
							//System.out.println(second);
							String[] number_s = second.split("\\D");
							/*for(int j=3; j<number_s.length; j++)
							{
								System.out.println(number_s[j]);
							}*/
							//System.out.println(first.charAt(1));
							if(second.charAt(1) == '-')
							{	
								if(Integer.parseInt(number_s[3]) == 0)
								{
									numerator_b = Integer.parseInt(number_s[3]);
									demominator_b = 0*(-1)*Integer.parseInt(number_s[4]);
								}
								
								else
								{
									numerator_b = (-1)*Integer.parseInt(number_s[3]);
									demominator_b = Integer.parseInt(number_s[4]);
								}	
							}
							
							else
							{
								numerator_b = Integer.parseInt(number_s[3]);
								demominator_b = Integer.parseInt(number_s[4]);
							}
							
							//System.out.println(numerator_b + " " + demominator_b);
						}
						
						else if(calculate.equals("::"))
						{
							other = Integer.parseInt(seperate_string[i]);
							//System.out.println(other);
						}
					}
					
					else if(i == 3)
					{
						do_reduce = seperate_string[i];
						//System.out.println("do_reduce:" + do_reduce);
					}
				}
				
				try 
				{	
					Rational RA = new Rational(numerator_a, demominator_a);
					Rational RB = new Rational(numerator_b, demominator_b);
					//System.out.println(RA.toString());
					//System.out.println(RB.toString());
					switch (calculate) 
					{
						case "+": 
							Rational.add(RA,RB);
							//System.out.println(RA.toString());
						break;
						case "-": 
							Rational.sub(RA, RB);
							//System.out.println(RA.toString());
						break;
						case "*": 
							Rational.multi(RA, RB);
							//System.out.println(RA.toString());
						break;
						case ":": 
							Rational.Div(RA, RB);
							//System.out.println(RA.toString());
						break;
						case"!":
							RA.inverse();
							//System.out.println(RA.toString());
						break;
						case"::":
							RA.exp(other);
							//System.out.println(RA.toString());
						break;
					}
					
					if(do_reduce.equals("R"))
					{
						RA.reduce();
						//System.out.println(RA.toString());
					}
					
					//System.out.println(RA.toString());
					
					fw = new FileWriter("Output.txt", true);
					fw.write(s + " Results in " + RA.toString() + "\n");
					fw.close();
				} 
				catch (NotANumberException e) 
				{
					// TODO Auto-generated catch block
					//System.out.println("Not A Number");
					fw = new FileWriter("Output.txt", true);
					fw.write(s + " Results in " + "Not A Number\n");
					fw.close();
					//e.printStackTrace();
				}
			}
		}
		catch (IOException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

