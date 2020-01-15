
public class Customer implements CustomerReadOnly {
	private String name;

	/* (non-Javadoc)
	 * @see CustomerReadOnly#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	public Customer(String name) {
		this.name = name;
	}

	public Customer(Customer oldCustomer) {
		this.name = oldCustomer.name;
	}

	/* (non-Javadoc)
	 * @see CustomerReadOnly#toString()
	 */
	@Override
	public String toString() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
