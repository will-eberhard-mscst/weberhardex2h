package weberhard2740ex3h;

public class Rainfall {
	private double [] array;
	
	public Rainfall(double[] rainfall){
		array= new double[rainfall.length];
		
		for(int i = 0; i < rainfall.length; i++)
			array[i] = rainfall[i];
	}
	
	public Rainfall(String[] rainfall){
		array= new double[rainfall.length];
		
		for(int i = 0; i < rainfall.length; i++)
			array[i] = Double.parseDouble(rainfall[i]);
	}
	
	public double getTotal(){
		double total = 0.0;
		for(double i : array)
			total += i;
		return total;
	}
	
	public double getAverage(){
		return getTotal() / this.array.length;
	}
	
	public double getHighest(){
		double highest = array[0];
		for(int i = 1; i < array.length; i++){
			if(array[i] > highest)
				highest = array[i];
		}
		return highest;
	}
	
	public double getLowest(){
		double lowest = array[0];
		for(int i = 1; i < array.length; i++){
			if(array[i] < lowest)
				lowest = array[i];
		}
		return lowest;
	}

}
