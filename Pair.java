class Pair<K,V>
{
	private K key;
	private V value;
	public Pair(K key,V value){this.key = key; this.value = value;}
	public K getKey(){return key;}
	public V getValue(){return value;}
	int hashVal1 = 10289;
	int hashVal2 = 10289*10289;
	
	@Override
	public int hashCode()
	{
		int a = (Integer)key;
		int b = (Integer)value;

		return a*hashVal1 + b*hashVal2;
	}

	@Override
	public boolean equals(Object o)
	{
		Pair<K,V> curr = (Pair<K,V>)o;
		return (this.key==curr.getKey() && this.value==curr.getValue());
	}
}
