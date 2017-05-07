package Exercise02;

public class Rational 
{
	private int numerator; // in a/b this is a
	private int demominator; // in a/b this is b
	private boolean sign; // true means negative number

	//Remember a/0 is not a number and an exception should be thrown when this occurs in computation. 
	public class NotANumberException extends Exception
	{
		public NotANumberException()
		{ 
			super();
		}
		public NotANumberException(String message)
		{ 
			super(message);
		}
	 }
	
	//Constructors
	//Basic constructor
	public Rational()
	{
		numerator = 0;
		demominator = 1;
		sign = false;
	}
	
	//Constructor of a rational when given two integers
	public Rational(int n, int d) throws NotANumberException 
	{
		if(d == 0) throw new NotANumberException("Not A Number");
		numerator = (n<0)? -1*n : n ;
		demominator = (d<0)? -1*d : d;
		sign = (n*d >= 0)? false : true;
	}
	
	//Access 
	public int getNum() 
	{
		return numerator;
	} // returns a in a/b
	
	public int getDem() 
	{
		return demominator;
	} // returns b in a/b
	
	public boolean getSign() 
	{
		return sign;
	} // returns the sign
	
	//Helper functions
	//Computes the greatest common disvisor 
	private static int gcd(int a, int b)
	{
		if(b==0) return a; 
		else return gcd(b,a%b);
	}
	//Computes the least common multiple
	private static int lcm(int a, int b)
	{
		if(a==0 && b==0) return 0; 
		else return (a*b)/(gcd(a,b));
	}
	// Prints rationals
	public String toString()
	{
		return "("+((sign)?"-":"+")+","+ numerator +","+ this.getDem()+")";
	}
	
	//Outputs the simplest form of the rational number
	public Rational reduce()
	{
		int new_numerator = 0;
		int new_demominator = 1;
		
		new_numerator = this.numerator / gcd(this.numerator, this.demominator);
		new_demominator = this.demominator / gcd(this.numerator, this.demominator);
		
		this.numerator = new_numerator;
		this.demominator = new_demominator;
		
		try 
		{
			new Rational(this.numerator, this.demominator);
		} 
		catch (NotANumberException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return null;
	} 
	
	//Outputs true when they are equivalent, i.e. same number possibly different form, 
	//False when they are different  
	public static boolean Equal(Rational a, Rational b)
	{	
		if(a.getSign() == b.getSign() && ((double) a.getNum()/a.getDem()) == ((double) b.getNum()/b.getDem()))
		{
			return true;
		}
		
		return false;
	}
	
	//Operators
	//make sure errors are caught when writing these functions
	public static Rational add(Rational a, Rational b) 
	{	
		if(a.getSign())
		{
			if(a.getNum() == 0)
			{
				a.demominator = (-1)*a.demominator;
			}
			else
			{
				a.numerator = (-1)*a.numerator;
			}
				
		}
		
		if(b.getSign())
		{
			if(b.getNum() == 0)
			{
				b.demominator = (-1)*b.demominator;
			}
			
			else
			{
				b.numerator = (-1)*b.numerator;
			}
		}
		
		if(a.getDem() == b.getDem())
		{
			a.numerator = a.getNum() + b.getNum();
			a.demominator = a.getDem();
		}
		
		else
		{
			a.numerator = a.getNum()*(lcm(a.getDem(), b.getDem()) / a.getDem()) + b.getNum()*(lcm(a.getDem(), b.getDem()) / b.getDem());
			a.demominator = lcm(a.getDem(), b.getDem());
		}
		
		//System.out.println(a.numerator + " " + a.demominator);
		
		if(a.numerator < 0)
		{
			a.numerator = (-1)*a.numerator;
			a.sign = true;
		}
		
		else if(a.demominator < 0)
		{
			a.demominator = (-1)*a.demominator;
			a.sign = true;
		}
		
		else
		{
			a.sign = false;
		}
		
		return null;
	} 
	
	public static Rational sub(Rational a, Rational b) 
	{	
		if(a.getSign())
		{
			if(a.getNum() == 0)
			{
				a.demominator = (-1)*a.demominator;
			}
			else
			{
				a.numerator = (-1)*a.numerator;
			}
				
		}
		
		if(b.getSign())
		{
			if(b.getNum() == 0)
			{
				b.demominator = (-1)*b.demominator;
			}
			
			else
			{
				b.numerator = (-1)*b.numerator;
			}
		}
		
		if(a.getDem() == b.getDem())
		{
			a.numerator = a.getNum() - b.getNum();
			a.demominator = a.getDem();
		}
		
		else
		{
			a.numerator = a.getNum()*(lcm(a.getDem(), b.getDem()) / a.getDem()) - b.getNum()*(lcm(a.getDem(), b.getDem()) / b.getDem());
			a.demominator = lcm(a.getDem(), b.getDem());
		}
		
		//System.out.println(a.numerator + " " + a.demominator);
		
		if(a.numerator < 0)
		{
			a.numerator = (-1)*a.numerator;
			a.sign = true;
		}
		
		else if(a.demominator < 0)
		{
			a.demominator = (-1)*a.demominator;
			a.sign = true;
		}
		
		else
		{
			a.sign = false;
		}
		
		return null;
	} 
	
	public static Rational multi(Rational a, Rational b)
	{	
		if(a.getSign())
		{
			if(a.getNum() == 0)
			{
				a.demominator = (-1)*a.demominator;
			}
			else
			{
				a.numerator = (-1)*a.numerator;
			}
				
		}
		
		if(b.getSign())
		{
			if(b.getNum() == 0)
			{
				b.demominator = (-1)*b.demominator;
			}
			
			else
			{
				b.numerator = (-1)*b.numerator;
			}
		}

		a.numerator = a.getNum() * b.getNum();
		a.demominator = a.getDem() * b.getDem();
		
		//System.out.println(a.numerator + " " + a.demominator);
		
		if(a.numerator < 0)
		{
			a.numerator = (-1)*a.numerator;
			a.sign = true;
		}
		
		else if(a.demominator < 0)
		{
			a.demominator = (-1)*a.demominator;
			a.sign = true;
		}
		
		else
		{
			a.sign = false;
		}
		
		return null;
	} 
	
	public static Rational Div(Rational a, Rational b)  throws NotANumberException 
	{
		b.inverse();
		
		if(a.getSign())
		{
			a.numerator = (-1)*a.numerator;
		}
		
		if(b.getSign())
		{
			b.numerator = (-1)*b.numerator;
		}
		
		a.numerator = a.getNum() * b.getNum();
		a.demominator = a.getDem() * b.getDem();
		
		//System.out.println(a.numerator + " " + a.demominator);
		
		if(a.numerator < 0)
		{
			a.numerator = (-1)*a.numerator;
			a.sign = true;
		}
		
		else if(a.demominator < 0)
		{
			a.demominator = (-1)*a.demominator;
			a.sign = true;
		}
		
		else
		{
			a.sign = false;
		}
		
		new Rational(a.numerator, a.demominator);
		return null;
	}
	
	public Rational inverse() throws NotANumberException 
	{
		int temp;
		
		temp = this.numerator;
		this.numerator = this.demominator;
		this.demominator = temp;
	
		new Rational(this.numerator, this.demominator);
		return null;
	}
	
	public Rational exp(int pow)  throws NotANumberException
	{
		if(pow < 0)
		{
			inverse();
			pow = (-1)*pow;
		}
		
		if(pow%2 == 0)
		{
			this.sign = false;
		}
		
		this.numerator = (int) Math.pow(this.numerator, pow);
		this.demominator = (int) Math.pow(this.demominator, pow);
		
		new Rational(this.numerator, this.demominator);
		return null;
	} 
}

