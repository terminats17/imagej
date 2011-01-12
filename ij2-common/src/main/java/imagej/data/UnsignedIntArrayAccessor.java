package imagej.data;

public class UnsignedIntArrayAccessor implements DataAccessor
{
	private int[] ints;
	
	public UnsignedIntArrayAccessor(Object data)
	{
		this.ints = (int[]) data;
	}
	
	@Override
	public double getReal(long index)
	{
		double value = this.ints[(int)index];
		
		if (value < 0)
			value += 4294967296.0;
		
		return value;
	}

	@Override
	public void setReal(long index, double value)
	{
		// TODO : Imglib sets values that out of range by wrapping them to other side (neg->pos or pos->neg).
		// Determine who needs to fix code. 
		//if (value < 0) value = 0;
		//if (value > 0xffffffffL) value = 0xffffffffL;
		value += (0.5d * Math.signum( value ) );  // TODO - this is essentially what imglib does
		this.ints[(int)index] = (int) ((long)value & 0xffffffffL);
	}

	@Override
	public long getIntegral(long index)
	{
		long value = this.ints[(int)index];
		
		if (value < 0)
			value += 4294967296L;
		
		return value;
	}

	@Override
	public void setIntegral(long index, long value)
	{
		// TODO : Imglib sets values that out of range by wrapping them to other side (neg->pos or pos->neg).
		// Determine who needs to fix code. 
		//if (value < 0) value = 0;
		//if (value > 0xffffffffL) value = 0xffffffffL;
		this.ints[(int)index] = (int) (value & 0xffffffffL);
	}

}
